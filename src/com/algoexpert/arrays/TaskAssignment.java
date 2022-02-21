package com.algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class TaskAssignment {

	public ArrayList<ArrayList<Integer>> taskAssignment(int k, ArrayList<Integer> tasks) {
		// Write your code here.
		ArrayList<ArrayList<Integer>> pairedTasks = new ArrayList<ArrayList<Integer>>();

		HashMap<Integer, ArrayList<Integer>> taskDurationToIndices = getTaskDurationsToIndices(tasks);

		ArrayList<Integer> sortedTasks = tasks;
		Collections.sort(sortedTasks);

		for(int i= 0; i<k; i++) {
			int task1Duration = sortedTasks.get(i);
			var indicesWithTask1Duration = taskDurationToIndices.get(task1Duration);
			var task1Index = indicesWithTask1Duration.remove(indicesWithTask1Duration.size()-1);

			var task2SortedIndex = tasks.size() -1-i;
			int task2Duration = sortedTasks.get(task2SortedIndex);
			var indicesWithTask2Duration = taskDurationToIndices.get(task2Duration);
			var task2Index = indicesWithTask2Duration.remove(indicesWithTask2Duration.size()-1);

			ArrayList<Integer> pairedTask = new ArrayList<Integer>();
			pairedTask.add(task1Index);
			pairedTask.add(task2Index);
			pairedTasks.add(pairedTask);
		}
		return pairedTasks;
	}

	public HashMap<Integer, ArrayList<Integer>> getTaskDurationsToIndices(ArrayList<Integer> tasks) {
		HashMap<Integer, ArrayList<Integer>> taskDurationsToIndices = new HashMap<>();

		for(int i=0; i<tasks.size(); i++) {
			int taskDuration = tasks.get(i);
			if(taskDurationsToIndices.containsKey(taskDuration)) {
				taskDurationsToIndices.get(taskDuration).add(i);
			} else {
				ArrayList<Integer> temp =  new ArrayList<Integer>();
				temp.add(i);
				taskDurationsToIndices.put(taskDuration, temp);
			}
		}
		return taskDurationsToIndices;
	}


	public static void main(String[] args) {
		var k = 3;
		var tasks = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 3, 1, 4));
		var expected = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> subarr = new ArrayList<Integer>(Arrays.asList(4, 2));
		ArrayList<Integer> subarr2 = new ArrayList<Integer>(Arrays.asList(0, 5));
		ArrayList<Integer> subarr3 = new ArrayList<Integer>(Arrays.asList(3, 1));
		expected.add(subarr);
		expected.add(subarr2);
		expected.add(subarr3);
		var actual = new TaskAssignment().taskAssignment(k, tasks);
	}

}
