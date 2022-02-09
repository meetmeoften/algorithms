package com.algoexpert.medium;

import java.util.ArrayList;
import java.util.List;

public class MediumProblemBST {

	public static boolean validateBst(BST tree) {
		return validateBst(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean validateBst(BST tree, int minValue, int maxValue) {
		// TODO Auto-generated method stub
		if (tree.value < minValue || tree.value >= maxValue) {
			return false;
		}

		if (tree.left != null && !validateBst(tree.left, minValue, tree.value)) {
			return false;
		}

		if (tree.right != null && !validateBst(tree.right, tree.value, maxValue)) {
			return false;
		}
		return true;

	}

	public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
		if (tree != null) {
			inOrderTraverse(tree.left, array);
			array.add(tree.value);
			inOrderTraverse(tree.right, array);
		}
		return array;
	}

	// MinHeights BST
	public static BST minHeightBst(List<Integer> array) {
		return construct(array, null, 0, array.size() - 1);

	}


	private static BST construct2(List<Integer> array, int start, int end) {
		if(start > end) {
			return null;
		}
		int mid = (start+ end)/2;
		int valueToAdd = array.get(mid);

		BST bst = new BST(valueToAdd);

		bst.left = construct2(array, start, mid-1);
		bst.right = construct2(array, mid+1, end);

		return bst;

	}

	private static BST construct(List<Integer> array, BST bst, int start, int end) {
		// TODO Auto-generated method stub
		if(start > end) {
			return null;
		}
		int mid = (start+ end)/2;
		int valueToAdd = array.get(mid);

		if(bst == null) {
			bst = new BST(valueToAdd);
		} else {
			bst.insert(valueToAdd);
		}

		construct(array, bst, start, mid-1);
		construct(array, bst, mid+1, end);

		return bst;

	}

	static class BST {
		public int value;
		public BST left;
		public BST right;

		public BST(int value) {
			this.value = value;
		}

		public void insert(int value) {
			if(value < this.value) {
				if(left == null) {
					left = new BST(value);
				} else {
					left.insert(value);
				}
			} else {
				if(right == null) {
					right = new BST(value);
				} else {
					right.insert(value);
				}
			}
		}

		public boolean contains(int value) {
			// Write your code here.
			if(value < this.value) {
				if(left == null ) {
					return false;
				} else {
					left.contains(value);
				}
			} else if(value > this.value) {
				if(right == null) {
					return false;
				} else {
					right.contains(value);
				}
			}
			return true;
		}

		public BST remove(int value) {
			remove(value, null);
			return this;
		}

		private void remove(int value, BST parent) {
			if(value < this.value) {
				if(left != null) {
					left.remove(value, this);
				}
			} else if (value > this.value) {
				if(right != null) {
					right.remove(value, this);
				}
			} else {
				if(left != null && right != null) {
					this.value = right.getMinValue();
					right.remove(this.value, this);
				} else if(parent == null) {
					if(left != null) {

					}
				} else if(parent.left == this) {

				} else if (parent.right == this) {

				}
			}

		}

		public int getMinValue() {
			if(left == null) {
				return this.value;
			} else {
				return left.getMinValue();
			}
		}
	}


	// ReConstruct BST
	public BST reconstructBst(List<Integer> preOrderTraversalValues) {
		if(preOrderTraversalValues.size() == 0) {
			return null;
		}

		int currentValue = preOrderTraversalValues.get(0);
		int rightSubTreeRootIndex = preOrderTraversalValues.size();

		// Calculate rightSubTree Root Index
		for(int i= 1; i < preOrderTraversalValues.size();i++) {
			int value = preOrderTraversalValues.get(i);
			if(value >= currentValue) {
				rightSubTreeRootIndex = i;
				break;
			}
		}

		BST leftSubTree = reconstructBst(preOrderTraversalValues.subList(1, rightSubTreeRootIndex));
		BST rightSubTree = reconstructBst(preOrderTraversalValues.
				subList(rightSubTreeRootIndex, preOrderTraversalValues.size()));

		BST bst = new BST(currentValue);
		bst.left = leftSubTree;
		bst.right = rightSubTree;

		return bst;

	}

	// Kth Largest Value
	public int findKthLargestValueInBst(BST tree, int k) {
		// Write your code here.
		List<Integer> array = new ArrayList<>();
		performInOrderTraverse(tree, array);
		return array.get(array.size() -k);
	}

	private void performInOrderTraverse(BST tree, List<Integer> array) {
		if(tree != null) {
			performInOrderTraverse(tree.left, array);
			array.add(tree.value);
			performInOrderTraverse(tree.right, array);
		}

	}

	// Closest value in BST
	public static int findClosestValueInBst(BST tree, int target) {
		return findClosestValueInBst(tree, target, tree.value);

	}

	public static int findClosestValueInBst(BST tree, int target, int closest) {
		BST currentNode = tree;

		while(currentNode != null) {

			var value1 = Math.abs(target - closest);
			var value2 = Math.abs(target - currentNode.value);

			if(value1 > value2) {
				closest = currentNode.value;
			}

			if(target < currentNode.value) {
				currentNode = currentNode.left;
			} else if(target > currentNode.value) {
				currentNode = currentNode.right;
			} else {
				break;
			}
		}

		return closest;
	}

}
