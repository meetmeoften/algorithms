package com.algoexpert2.binarysearchtree;

public class ValidateThreeNodes {

    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
        // Write your code here.
        if (isDescendant(nodeTwo, nodeOne)) {
            return isDescendant(nodeThree, nodeTwo);
        }

        if (isDescendant(nodeTwo, nodeThree)) {
            return isDescendant(nodeOne, nodeTwo);
        }
        return false;
    }

    public boolean isDescendant(BST node, BST target) {
        if (node == null) {
            return false;
        }

        if (node == target) {
            return true;
        }

        return (node.value > target.value) ? isDescendant(node.left, target) : isDescendant(node.right, target);
    }

    public boolean validateThreeNodes2(BST nodeOne, BST nodeTwo, BST nodeThree) {
        // Quick check: nodeTwo must be between nodeOne and nodeThree
        if (nodeTwo.value < nodeOne.value || nodeTwo.value > nodeThree.value) {
            return false;
        }

        // nodeTwo must be ancestor of both nodeOne and nodeThree
        return nodeTwoIsAncestor(nodeTwo, nodeOne) &&
                nodeTwoIsAncestor(nodeTwo, nodeThree) &&
                nodeOneIsLeftChild(nodeTwo, nodeOne) &&
                nodeThreeIsRightChild(nodeTwo, nodeThree);
    }

    // Check if ancestor is ancestor of descendant
    private boolean nodeTwoIsAncestor(BST ancestor, BST descendant) {
        if (ancestor == descendant) return true;

        if (descendant.value < ancestor.value) {
            return nodeTwoIsAncestor(ancestor.left, descendant);
        } else {
            return nodeTwoIsAncestor(ancestor.right, descendant);
        }
    }

    // Check if nodeOne is in left subtree of nodeTwo
    private boolean nodeOneIsLeftChild(BST ancestor, BST descendant) {
        if (ancestor == descendant) return true;
        return descendant.value < ancestor.value &&
                nodeOneIsLeftChild(ancestor.left, descendant);
    }

    // Check if nodeThree is in right subtree of nodeTwo
    private boolean nodeThreeIsRightChild(BST ancestor, BST descendant) {
        if (ancestor == descendant) return true;
        return descendant.value > ancestor.value &&
                nodeThreeIsRightChild(ancestor.right, descendant);
    }


    public static void main(String[] args) {
        var root = new BST(5);
        root.left = new BST(2);
        root.right = new BST(7);
        root.left.left = new BST(1);
        root.left.right = new BST(4);
        root.right.left = new BST(6);
        root.right.right = new BST(8);
        root.left.left.left = new BST(0);
        root.left.right.left = new BST(3);

        var nodeOne = root;
        var nodeTwo = root.left;
        var nodeThree = root.left.right.left;
        var nodeFour = root.right;
        boolean expected = true;
        boolean actual = new ValidateThreeNodes().validateThreeNodes(nodeTwo, nodeOne, nodeFour);
        boolean actual2 = new ValidateThreeNodes().validateThreeNodes2(nodeTwo, nodeOne, nodeFour);
        System.out.println(actual);
        System.out.println(actual2);
    }

}
