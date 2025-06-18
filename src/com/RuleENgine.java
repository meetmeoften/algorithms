//package com;
//
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class RuleENgine {
//
//    import org.apache.spark.sql.*;
//import org.apache.spark.sql.types.*;
//import org.apache.spark.sql.functions.*;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//    public class AdvancedRuleProcessor {
//
//        public static void main(String[] args) {
//            SparkSession spark = SparkSession.builder()
//                    .appName("Advanced Rule Processor")
//                    .master("local[*]")
//                    .getOrCreate();
//
//            spark.conf().set("spark.sql.debug.maxToStringFields", 100);
//
//            // Sample data with complex nested rules
//            Dataset<Row> sourceDF = createSourceDataFrame(spark);
//            System.out.println("SOURCE DATA:");
//            sourceDF.show(false);
//
//            Dataset<Row> rulesDF = createComplexRulesDataFrame(spark);
//            System.out.println("COMPLEX NESTED RULES:");
//            rulesDF.show(false);
//
//            // Register UDF to parse complex rules
//            spark.udf().register("parseRule",
//                    (String rule) -> parseComplexRule(rule),
//                    DataTypes.createArrayType(
//                            DataTypes.createStructType(Arrays.asList(
//                                    DataTypes.createStructField("clause_id", DataTypes.StringType, false),
//                                    DataTypes.createStructField("field", DataTypes.StringType, false),
//                                    DataTypes.createStructField("value", DataTypes.StringType, false)
//                            ))
//                    )
//            );
//
//            // Step 1: Parse complex rules into simplified conditions
//            rulesDF.createOrReplaceTempView("complex_rules");
//            Dataset<Row> parsedRules = spark.sql(
//                    "SELECT segmentid, parseRule(rules) as parsed_conditions FROM complex_rules"
//            );
//            System.out.println("PARSED RULE CONDITIONS:");
//            parsedRules.show(false);
//
//            // Step 2: Explode the array of parsed conditions
//            Dataset<Row> explodedConditions = parsedRules
//                    .select(col("segmentid"), explode(col("parsed_conditions")).as("condition"))
//                    .select(
//                            col("segmentid"),
//                            col("condition.clause_id").as("clause_id"),
//                            col("condition.field").as("field"),
//                            col("condition.value").as("value")
//                    );
//            System.out.println("EXPLODED CONDITIONS:");
//            explodedConditions.show(false);
//
//            // Step 3: Transform source data for matching
//            Dataset<Row> expandedSource = transformSourceData(sourceDF);
//            System.out.println("EXPANDED SOURCE DATA:");
//            expandedSource.show(false);
//
//            // Step 4: Join source data with rule conditions
//            Dataset<Row> potentialMatches = expandedSource.join(
//                    explodedConditions,
//                    expandedSource.col("field").equalTo(explodedConditions.col("field"))
//                            .and(expandedSource.col("value").equalTo(explodedConditions.col("value"))),
//                    "inner"
//            ).select(
//                    expandedSource.col("userid"),
//                    explodedConditions.col("segmentid"),
//                    explodedConditions.col("clause_id"),
//                    explodedConditions.col("field"),
//                    explodedConditions.col("value")
//            );
//            System.out.println("POTENTIAL MATCHES:");
//            potentialMatches.show(false);
//
//            // Step 5: Count conditions per clause using our metadata
//            // Here we use a new approach - tracking match progress using metadata
//            // The metadata is extracted from the clause_id format we used
//
//            // Extract clause metadata from our rule parser
//            Map<String, Integer> clauseRequirements = getClauseRequirements();
//            List<Row> requirementData = new ArrayList<>();
//
//            for (Map.Entry<String, Integer> entry : clauseRequirements.entrySet()) {
//                String clauseId = entry.getKey();
//                Integer requiredMatches = entry.getValue();
//                String segmentId = clauseId.split("_")[0]; // Extract segment ID from clause ID
//
//                requirementData.add(RowFactory.create(segmentId, clauseId, requiredMatches));
//            }
//
//            StructType requirementSchema = new StructType(new StructField[]{
//                    new StructField("segmentid", DataTypes.StringType, false, Metadata.empty()),
//                    new StructField("clause_id", DataTypes.StringType, false, Metadata.empty()),
//                    new StructField("required_matches", DataTypes.IntegerType, false, Metadata.empty())
//            });
//
//            Dataset<Row> clauseRequirementsDF = spark.createDataFrame(requirementData, requirementSchema);
//            System.out.println("CLAUSE REQUIREMENTS:");
//            clauseRequirementsDF.show(false);
//
//            // Step 6: Count matches per user and clause
//            Dataset<Row> userClauseMatches = potentialMatches
//                    .groupBy("userid", "segmentid", "clause_id")
//                    .count()
//                    .withColumnRenamed("count", "matched_conditions");
//            System.out.println("USER CLAUSE MATCHES:");
//            userClauseMatches.show(false);
//
//            // Step 7: Join with requirements and check for satisfied clauses
//            Dataset<Row> satisfiedClauses = userClauseMatches.join(
//                    clauseRequirementsDF,
//                    userClauseMatches.col("segmentid").equalTo(clauseRequirementsDF.col("segmentid"))
//                            .and(userClauseMatches.col("clause_id").equalTo(clauseRequirementsDF.col("clause_id"))),
//                    "inner"
//            ).filter(
//                    col("matched_conditions").equalTo(col("required_matches"))
//            ).select(
//                    userClauseMatches.col("userid"),
//                    userClauseMatches.col("segmentid"),
//                    userClauseMatches.col("clause_id")
//            );
//            System.out.println("SATISFIED CLAUSES:");
//            satisfiedClauses.show(false);
//
//            // Step 8: Process the clause tree to determine final segment matches
//            // For (A AND B) OR (C AND (D OR E)), if any top-level OR clause is satisfied, the rule is satisfied
//            // In our model, clause IDs with the same first two parts belong to the same OR group
//
//            // We need to determine which clauses represent complete OR branches
//            // For our example, this is hard-coded, but in practice would come from the parser
//            List<Row> orBranchData = Arrays.asList(
//                    RowFactory.create("segment4", Arrays.asList("segment4_1", "segment4_2")),
//                    RowFactory.create("segment5", Arrays.asList("segment5_1", "segment5_2_1", "segment5_2_2")),
//                    RowFactory.create("segment6", Arrays.asList("segment6_1_1", "segment6_1_2", "segment6_2"))
//            );
//
//            StructType orBranchSchema = new StructType(new StructField[]{
//                    new StructField("segmentid", DataTypes.StringType, false, Metadata.empty()),
//                    new StructField("branch_clauses", DataTypes.createArrayType(DataTypes.StringType), false, Metadata.empty())
//            });
//
//            Dataset<Row> orBranchesDF = spark.createDataFrame(orBranchData, orBranchSchema);
//            System.out.println("OR BRANCH STRUCTURE:");
//            orBranchesDF.show(false);
//
//            // Join satisfied clauses with branch information
//            satisfiedClauses.createOrReplaceTempView("satisfied_clauses");
//            orBranchesDF.createOrReplaceTempView("or_branches");
//
//            // Find users who satisfy at least one branch of each segment
//            Dataset<Row> finalSegments = spark.sql(
//                    "SELECT DISTINCT sc.userid, sc.segmentid " +
//                            "FROM satisfied_clauses sc " +
//                            "JOIN or_branches ob ON sc.segmentid = ob.segmentid " +
//                            "WHERE sc.clause_id IN ( " +
//                            "    SELECT branch_clause " +
//                            "    FROM or_branches, LATERAL EXPLODE(branch_clauses) AS branch_clause " +
//                            "    WHERE segmentid = sc.segmentid " +
//                            ")"
//            );
//
//            System.out.println("FINAL USER SEGMENTS:");
//            finalSegments.sort("userid", "segmentid").show(false);
//
//            spark.stop();
//        }
//
//        // Simulated complex rule parser for nested expressions like (A AND B) OR (C AND (D OR E))
//        private static Object[] parseComplexRule(String rule) {
//            List<Map<String, String>> parsedConditions = new ArrayList<>();
//
//            // This is a simplified parser for our example
//            // A full parser would use recursive descent or other parsing algorithm
//
//            // Pattern to match field=value expressions
//            Pattern conditionPattern = Pattern.compile("([a-zA-Z0-9]+)=([a-zA-Z0-9]+)");
//
//            // For our example rule: (A AND B) OR (C AND (D OR E))
//            // We'll return predefined parsed conditions
//
//            if (rule.contains("(A AND B) OR (C AND (D OR E))")) {
//                // First OR branch: (A AND B)
//                parsedConditions.add(createCondition("segment6_1_1", "field", "A"));
//                parsedConditions.add(createCondition("segment6_1_1", "field", "B"));
//
//                // Second OR branch: (C AND (D OR E))
//                parsedConditions.add(createCondition("segment6_2", "field", "C"));
//                parsedConditions.add(createCondition("segment6_2", "field", "D")); // Part of D OR E
//                parsedConditions.add(createCondition("segment6_2", "field", "E")); // Part of D OR E
//            }
//            else {
//                // Parse other rules
//                Matcher matcher = conditionPattern.matcher(rule);
//                int clauseCounter = 1;
//
//                while (matcher.find()) {
//                    String field = matcher.group(1);
//                    String value = matcher.group(2);
//
//                    // Simple mapping to clause_id based on the rule's segmentid
//                    // In a real implementation, this would be based on the actual rule structure
//                    String segmentId = "segment" + (parsedConditions.size() / 3 + 4);
//                    String clauseId = segmentId + "_" + clauseCounter;
//
//                    parsedConditions.add(createCondition(clauseId, field, value));
//                    clauseCounter++;
//                }
//            }
//
//            return parsedConditions.toArray();
//        }
//
//        private static Map<String, String> createCondition(String clauseId, String field, String value) {
//            Map<String, String> condition = new HashMap<>();
//            condition.put("clause_id", clauseId);
//            condition.put("field", field);
//            condition.put("value", value);
//            return condition;
//        }
//
//        // Get the required number of matches for each clause
//        private static Map<String, Integer> getClauseRequirements() {
//            Map<String, Integer> requirements = new HashMap<>();
//
//            // For (A AND B) OR (C AND (D OR E))
//            // A AND B needs 2 conditions to match
//            requirements.put("segment6_1_1", 2);
//
//            // C AND (D OR E) - Since D OR E is treated as one condition, we need 2 matches
//            requirements.put("segment6_2", 2);
//
//            // Additional requirements for other segments
//            requirements.put("segment4_1", 2); // Example: field=value AND field2=value2
//            requirements.put("segment4_2", 1); // Example: field3=value3
//            requirements.put("segment5_1", 1); // Example: field=value
//            requirements.put("segment5_2_1", 2); // Example: field2=value2 AND field3=value3
//            requirements.put("segment5_2_2", 1); // Example: field4=value4
//
//            return requirements;
//        }
//
//        private static Dataset<Row> transformSourceData(Dataset<Row> sourceDF) {
//            // Extended to include generic field-value pairs for our complex examples
//            Dataset<Row> standardFields = sourceDF.select(
//                    col("userid"),
//                    col("brand").as("value"),
//                    lit("brand").as("field")
//            ).union(
//                    sourceDF.select(
//                            col("userid"),
//                            col("itemid").as("value"),
//                            lit("itemid").as("field")
//                    )
//            ).union(
//                    sourceDF.select(
//                            col("userid"),
//                            col("eventType").as("value"),
//                            lit("eventType").as("field")
//                    )
//            ).union(
//                    sourceDF.select(
//                            col("userid"),
//                            col("categoryid").as("value"),
//                            lit("categoryid").as("field")
//                    )
//            );
//
//            // Add synthetic data for complex rule example (A=1, B=2, etc.)
//            List<Row> syntheticData = Arrays.asList(
//                    RowFactory.create("user1", "A", "field"),
//                    RowFactory.create("user1", "B", "field"),
//                    RowFactory.create("user2", "C", "field"),
//                    RowFactory.create("user2", "D", "field"),
//                    RowFactory.create("user3", "A", "field"),
//                    RowFactory.create("user3", "B", "field"),
//                    RowFactory.create("user3", "C", "field"),
//                    RowFactory.create("user4", "C", "field"),
//                    RowFactory.create("user4", "E", "field")
//            );
//
//            StructType syntheticSchema = new StructType(new StructField[]{
//                    new StructField("userid", DataTypes.StringType, false, Metadata.empty()),
//                    new StructField("value", DataTypes.StringType, false, Metadata.empty()),
//                    new StructField("field", DataTypes.StringType, false, Metadata.empty())
//            });
//
//            Dataset<Row> syntheticDF = spark.createDataFrame(syntheticData, syntheticSchema);
//
//            return standardFields.union(syntheticDF);
//        }
//
//        private static Dataset<Row> createSourceDataFrame(SparkSession spark) {
//            // Same implementation as previous examples
//            List<Row> sourceData = Arrays.asList(
//                    RowFactory.create("user1", "cat1", "pepsi", "itema", "purchase"),
//                    RowFactory.create("user1", "cat2", "pepsi", "itemb", "purchase"),
//                    RowFactory.create("user2", "cat1", "pepsi", "itema", "atc"),
//                    RowFactory.create("user2", "cat2", "pepsi", "itemb", "atc"),
//                    RowFactory.create("user3", "cat3", "samsung", "s25", "purchase"),
//                    RowFactory.create("user3", "cat3", "samsung", "s26", "purchase"),
//                    RowFactory.create("user4", "cat4", "apple", "iphone", "view"),
//                    RowFactory.create("user5", "cat1", "coke", "drinkc", "purchase")
//            );
//
//            StructType sourceSchema = new StructType(new StructField[]{
//                    new StructField("userid", DataTypes.StringType, false, Metadata.empty()),
//                    new StructField("categoryid", DataTypes.StringType, false, Metadata.empty()),
//                    new StructField("brand", DataTypes.StringType, false, Metadata.empty()),
//                    new StructField("itemid", DataTypes.StringType, false, Metadata.empty()),
//                    new StructField("eventType", DataTypes.StringType, false, Metadata.empty())
//            });
//
//            return spark.createDataFrame(sourceData, sourceSchema);
//        }
//
//        private static Dataset<Row> createComplexRulesDataFrame(SparkSession spark) {
//            List<Row> rulesData = Arrays.asList(
//                    RowFactory.create("segment1", "brand=samsung and itemid=s25 and eventType=purchase"),
//                    RowFactory.create("segment2", "brand=pepsi and itemid=itema and eventType=purchase"),
//                    RowFactory.create("segment3", "brand=pepsi and itemid=itemb and eventType=atc"),
//                    RowFactory.create("segment4", "brand=samsung and eventType=purchase or categoryid=cat4"),
//                    RowFactory.create("segment5", "categoryid=cat1 or brand=samsung and itemid=s25"),
//                    RowFactory.create("segment6", "(A AND B) OR (C AND (D OR E))")
//            );
//
//            StructType rulesSchema = new StructType(new StructField[]{
//                    new StructField("segmentid", DataTypes.StringType, false, Metadata.empty()),
//                    new StructField("rules", DataTypes.StringType, false, Metadata.empty())
//            });
//
//            return spark.createDataFrame(rulesData, rulesSchema);
//        }
//    }
//}
