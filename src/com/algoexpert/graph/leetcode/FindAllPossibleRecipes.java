package com.algoexpert.graph.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class FindAllPossibleRecipes {

	public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
		// Track available ingredients and recipes
		Set<String> suppliesSet = new HashSet<>();
		for (String supply : supplies) {
			suppliesSet.add(supply);
		}

		// Queue to process recipe indices
		Queue<Integer> recipeQueue = new LinkedList<>();
		for (int idx = 0; idx < recipes.length; ++idx) {
			recipeQueue.offer(idx);
		}

		List<String> createdRecipes = new ArrayList<>();
		int lastSize = -1;

		// Continue while we keep finding new recipes
		while (suppliesSet.size() > lastSize) {
			lastSize = suppliesSet.size();
			int queueSize = recipeQueue.size();

			// Process all recipes in current queue
			while (queueSize-- > 0) {
				int recipeIdx = recipeQueue.poll();
				boolean canCreate = true;

				// Check if all ingredients are available
				for (String ingredient : ingredients.get(recipeIdx)) {
					if (!suppliesSet.contains(ingredient)) {
						canCreate = false;
						break;
					}
				}

				if (!canCreate) {
					recipeQueue.offer(recipeIdx);
				} else {
					// Recipe can be created - add to available items
					suppliesSet.add(recipes[recipeIdx]);
					createdRecipes.add(recipes[recipeIdx]);
				}
			}
		}

		return createdRecipes;
	}

	// --- DFS

	public List<String> findAllRecipes2(String[] recipes, List<List<String>> ingredients, String[] supplies) {
		List<String> possibleRecipes = new ArrayList<>();
		// Track if ingredient/recipe can be made
		Map<String, Boolean> canMake = new HashMap<>();
		// Map recipe name to its index in ingredients list
		Map<String, Integer> recipeToIndex = new HashMap<>();

		// Mark all initial supplies as available
		for (String supply : supplies) {
			canMake.put(supply, true);
		}

		// Create recipe to index mapping
		for (int idx = 0; idx < recipes.length; idx++) {
			recipeToIndex.put(recipes[idx], idx);
		}

		// Try to make each recipe using DFS
		for (String recipe : recipes) {
			checkRecipe(recipe, ingredients, new HashSet<String>(), canMake, recipeToIndex);
			if (canMake.get(recipe)) {
				possibleRecipes.add(recipe);
			}
		}

		return possibleRecipes;
	}

	private void checkRecipe(String recipe, List<List<String>> ingredients, Set<String> visited,
			Map<String, Boolean> canMake, Map<String, Integer> recipeToIndex) {
		// Return if we already know if recipe can be made
		if (canMake.containsKey(recipe) && canMake.get(recipe)) {
			return;
		}

		// Not a valid recipe or cycle detected
		if (!recipeToIndex.containsKey(recipe) || visited.contains(recipe)) {
			canMake.put(recipe, false);
			return;
		}

		visited.add(recipe);

		// Check if we can make all required ingredients
		List<String> neededIngredients = ingredients.get(recipeToIndex.get(recipe));
		for (String ingredient : neededIngredients) {
			checkRecipe(ingredient, ingredients, visited, canMake, recipeToIndex);
			if (!canMake.get(ingredient)) {
				canMake.put(recipe, false);
				return;
			}
		}

		// All ingredients can be made
		canMake.put(recipe, true);
	}

	public static void main(String[] args) {
		FindAllPossibleRecipes fap = new FindAllPossibleRecipes();

		List<List<String>> list = new ArrayList<>();
		List<String> list1 = new ArrayList<>();
		list1.add("yeast");
		list1.add("flour");

		List<String> list2 = new ArrayList<>();
		list2.add("bread");
		list2.add("meat");

		list.add(list1);
		list.add(list2);

		fap.findAllRecipes(new String[] { "bread", "sandwich" }, list, new String[] { "yeast", "flour", "meat" });
	}

}
