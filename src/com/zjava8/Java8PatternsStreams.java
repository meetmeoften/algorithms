package com.zjava8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8PatternsStreams {


	static class Person {
		String name;
		int age;

		Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	static List<Person> persons =
			Arrays.asList(
					new Person("Max", 18),
					new Person("Peter", 23),
					new Person("Pamela", 23),
					new Person("David", 12));



	static class Foo {
		String name;
		List<Bar> bars = new ArrayList<>();

		Foo(String name) {
			this.name = name;
		}
	}

	static class Bar {
		String name;

		Bar(String name) {
			this.name = name;
		}
	}

	public static void stream() {
		List<String> myList =
				Arrays.asList("a1", "a2", "b1", "c2", "c1");
		myList
		.stream()
		.filter(s -> s.startsWith("c"))
		.map(String::toUpperCase)
		.sorted()
		.forEach(System.out::println);


		System.out.println("-----2------");
		IntStream.range(1, 4).forEach(System.out::println);

		System.out.println("-----4------");
		Arrays.stream(new int[] {1, 2, 3})
		.map(n -> 2 * n + 1)
		.average()
		.ifPresent(System.out::println);


		System.out.println("-----4------");
		Stream.of("a1", "a2", "a3")
		.map(s -> s.substring(1))
		.mapToInt(Integer::parseInt)
		.max()
		.ifPresent(System.out::println);

		System.out.println("-----5------");
		IntStream.range(1, 4)
		.mapToObj(i -> "a" + i)
		.forEach(System.out::println);

		System.out.println("-----6------");
		Stream.of(1.0, 2.0, 3.0)
		.mapToInt(Double::intValue)
		.mapToObj(i -> "a" + i)
		.forEach(System.out::println);

		System.out.println("-----7------");
		Stream<String> stream =
				Stream.of("d2", "a2", "b1", "b3", "c")
				.filter(s -> s.startsWith("a"));

		System.out.println("-----7------");
		Stream<String> stream1 =
				Stream.of("d2", "a2", "b1", "b3", "c")
				.filter(s -> s.startsWith("a"));

		stream1.anyMatch(s -> true);    // ok

		//stream1.noneMatch(s -> true);

		System.out.println("-----8------");
		Supplier<Stream<String>> streamSupplier = () ->

		Stream.of("d2", "a2", "b1", "b3", "c")
		.filter(s -> s.startsWith("a"));

		streamSupplier.get().anyMatch(s -> true);   // ok
		streamSupplier.get().noneMatch(s -> true);  // ok



		System.out.println("-----9------");
		List<Foo> foos = new ArrayList<>();

		IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));
		foos.forEach(f -> IntStream
				.range(1, 4)
				.forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

		foos.stream()
		.flatMap(f -> f.bars.stream())
		.forEach(b -> System.out.println(b.name));

		System.out.println("-----10------");
		persons
		.stream()
		.reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
		.ifPresent(System.out::println);    // Pamela


		System.out.println("-----11------");
		Person result =
				persons
				.stream()
				.reduce(new Person("", 0), (p1, p2) -> {
					p1.age += p2.age;
					p1.name += p2.name;
					return p1;
				});

		System.out.format("name=%s; age=%s", result.name, result.age);


		System.out.println("-----12------");
		Integer ageSum1 = persons
				.stream()
				.reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);

		System.out.println(ageSum1);

		System.out.println("-----13------");
		Integer ageSum = persons
				.stream()
				.reduce(0,
						(sum, p) -> {
							System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
							return sum += p.age;
						},
						(sum1, sum2) -> {
							System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
							return sum1 + sum2;
						});

		System.out.println(ageSum);

		System.out.println("-----14------");

	}


	public static void reduce() {
		System.out.println("-----1------");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int result = numbers
				.stream()
				.reduce(0, (subtotal, element) -> subtotal + element);
		System.out.println(result);

		System.out.println("-----1------");
		List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
		String result1 = letters
				.stream()
				.reduce("", (partialString, element) ->  {
					String ele = partialString + element;
					System.out.println(ele);
					return ele;
				});
		System.out.println(result1);
	}

	public static void flatMap() {
		List<List<String>> list = Arrays.asList(
				Arrays.asList("a"),
				Arrays.asList("b"));
		System.out.println(list);

		System.out.println(list
				.stream()
				//				.flatMap(Collection::stream)
				.flatMap(e -> e.stream())
				.collect(Collectors.toList()));



		int[][] matrix = {
				{1, 5, 2, 3, 4},
				{2, 4, 5, 2},
				{1, 2, 3, 4, 5, 6},
				{}
		};

		int[] array = Stream.of(matrix) //we start with a stream of objects Stream<int[]>
				.flatMapToInt(IntStream::of) //we I'll map each int[] to IntStream
				.toArray();
		System.out.println(array);


		int[] nums = new int[] {1,1,1,2,2,3};
		List<Integer> list2 = Arrays.stream(nums).boxed().collect(Collectors.toList());


		List<Integer> value = list2.stream().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
				.entrySet()
				.stream()
				.sorted((a, b) -> b.getValue().intValue() - a.getValue().intValue())
				.limit(2)
				.map(e -> e.getKey())
				.collect(Collectors.toList());

		Integer[] array2 = Stream.of(matrix)
				.flatMap(Stream::of)
				.toArray(Integer[]::new);



		list2.stream().max(Integer::compare).get();  // max value


		Set<Integer> items = new HashSet<>();
		Set<Integer> res = list2.stream()
				.filter(n -> !items.add(n)) // Set.add() returns false if the element was already in the set.
				.collect(Collectors.toSet());
	}


	public static void groupBy() {
		//		Map<String, List> groupByCountry = items.stream().collect(
		//				Collectors.groupingBy(Country::getCountryName));
		//
		//		System.out.println(groupByCountry.get("India"));
		//
		//		// Group by CountryName and calculates the count
		//		Map<String, Long> counting = items.stream().collect(
		//				Collectors.groupingBy(Country::getCountryName,Collectors.counting()));
		//
		//		// Group by countryName and sum up the population
		//		System.out.println(counting);
		//		Map<String, Long> populationCount = items.stream().collect(
		//				Collectors.groupingBy(Country::getCountryName,
		//						Collectors.summingLong(Country::getPopulation)));
		//		System.out.println(populationCount);


	}

	public static void biFunction() {

		BiFunction<Integer, Integer, Integer> func = (x1, x2) -> x1 + x2;
		Integer result1 = func.apply(2, 3);

		System.out.println(result1); // 5




		List<String> list1 = Arrays.asList("a", "b", "c");
		List<Integer> list2 = Arrays.asList(1, 2, 3);

		List<String> result = listCombiner(list1, list2, (letter, number) -> letter + number);
		//assertThat(result).containsExactly("a1", "b2", "c3");
	}


	private static <T, U, R> List<R> listCombiner(
			List<T> list1, List<U> list2, BiFunction<T, U, R> combiner) {
		List<R> result = new ArrayList<>();
		for (int i = 0; i < list1.size(); i++) {
			result.add(combiner.apply(list1.get(i), list2.get(i)));
		}
		return result;
	}

	public static void main(String[] args) {


		int[] nums = {1,1,1,2,2,3};
		List<Integer> list2 = Arrays.stream(nums).boxed().collect(Collectors.toList());


		Map<Integer, Integer> res = list2.stream().distinct().collect(Collectors.toMap(Integer::intValue, Integer::intValue));
		Map<Integer, Integer> map = list2.stream().collect(Collectors.groupingBy(Integer::intValue, Collectors.summingInt(Integer::intValue)));
		System.out.println(map);

		List<Integer> list = map.keySet().stream().collect(Collectors.toList());
		System.out.println(list);

		stream();
		reduce();


	}

}
