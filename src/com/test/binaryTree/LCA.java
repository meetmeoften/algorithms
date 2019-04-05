package com.test.binaryTree;

public class LCA {

	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
		if(root == null) {
			return null;
		}
		if(root.val == a.val || root.val == b.val ) {
			return root;
		}
		TreeNode left=lowestCommonAncestor(root.left,a,b);
		TreeNode right=lowestCommonAncestor(root.right,a,b);


		// If we get left and right not null , it is lca for a and b
		if(left!=null && right!=null) {
			System.out.println("Root " + root.val  + "  Left " + left.val + "  Right " + right.val);
			return root;
		}
		if(left== null) {
			if(right != null) {
				System.out.println("Root " + root.val  + "  Right " + right.val);
			} else {
				System.out.println("Root " + root.val  + "  Right NULL"  );
			}
			return right;
		} else {
			System.out.println("Root " + root.val  + "  Left " + left.val);
			return left;
		}

	}
	public static void main(String[] args)
	{
		// Creating a binary tree
		TreeNode rootNode=TreeNode.createBinaryTree();
		System.out.println("Lowest common ancestor for node 5 and 30:");
		TreeNode node5=new TreeNode(5);
		TreeNode node30=new TreeNode(30);
		System.out.println(lowestCommonAncestor(rootNode,node5,node30).val);

	}

}
