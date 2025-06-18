package com.techiedelight.binarytree;

import com.algoexpert.binarytree.view.Node;
import com.algoexpert.graph.EvaluateDivision;

import java.util.*;

public class TreeViews {

    public void topViewRecursive(Node root) {
        Map<Integer, EvaluateDivision.Pair<Integer, Integer>> map = new TreeMap<>();
        printTop(root, 0, 0, map);
    }

    private void printTop(Node root, int dist, int level, Map<Integer, EvaluateDivision.Pair<Integer, Integer>> map) {
        if (root == null) {
            return;
        }

        // condition
        if (!map.containsKey(dist) || map.get(dist).getValue() > level) {
            map.put(dist, new EvaluateDivision.Pair(root.data, level));
        }

        printTop(root.left, dist - 1, level + 1, map);
        printTop(root.right, dist + 1, level + 1, map);
    }



    public void verticalViewIterative(Node root) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<EvaluateDivision.Pair> q = new LinkedList<>();
        q.add(new EvaluateDivision.Pair<>(root, 0));

        int min =0, max = 0;
        while(!q.isEmpty()) {

            for(int i=0; i < q.size(); i++) {

                EvaluateDivision.Pair<Node, Integer> pair = q.poll();
                Node node  = pair.getKey();
                Integer dist = pair.getValue();

                if(map.get(dist) == null) {
                    map.put(dist, new ArrayList<>());
                }

                map.get(dist).add(node.data);

                if(node.left != null) {
                    q.add(new EvaluateDivision.Pair(node.left, dist - 1));
                    min = Math.min(min, dist - 1);
                }

                if(node.right != null) {
                    q.add(new EvaluateDivision.Pair(node.right, dist + 1));
                    max = Math.max(max, dist + 1);
                }
            }

            for(int i=min; i <= max; i++) {
                System.out.println(map.get(i));
            }
        }
    }

    public void topViewIterative(Node root) {
        Queue<EvaluateDivision.Pair<Node, Integer>> q = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        q.add(new EvaluateDivision.Pair<>(root, 0));

        while(!q.isEmpty()) {

            for(int i=0; i < q.size(); i++) {
                EvaluateDivision.Pair<Node, Integer> pair = q.poll();
                Node node = pair.getKey();
                Integer dist = pair.getValue();

                if(!map.containsKey(dist)) {
                    map.put(dist, node.data);
                }

               if(node.left != null) {
                   q.add(new EvaluateDivision.Pair<>(node.left, dist-1));
               }

                if(node.right != null) {
                    q.add(new EvaluateDivision.Pair<>(node.right, dist+1));
                }
            }

            for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                System.out.println(entry.getValue());
            }
        }

    }


    public void rightView(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Integer size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                if (i == size) {
                    System.out.println(node.data);
                }

                if (node.left != null) {
                    q.add(node.left);
                }

                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
    }

    public void leftView(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Integer size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                if (i == 0) {
                    System.out.println(node.data);
                }

                if (node.left != null) {
                    q.add(node.left);
                }

                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
    }

}
