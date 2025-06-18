package com.techiedelight.binarytree;

import com.algoexpert.binarytree.view.Node;

public class BinaryTreeCousins {

    public boolean areCousins(Node root, int node1, int node2) {

        if(root == null) {
            return false;
        }

        NodeInfo nodeInfo1 = null;
        NodeInfo nodeInfo2 = null;

        dfs(root, null, nodeInfo1, nodeInfo2, node1, node2, 0);

        if(nodeInfo1 == null || nodeInfo2 == null) {
            return false;
        }

        return nodeInfo1.depth == nodeInfo2.depth &&
                nodeInfo1.parent != nodeInfo2.parent;
    }

    private void dfs(Node root, Node parent, NodeInfo nodeInfo1, NodeInfo nodeInfo2, int node1, int node2, int depth) {
        if(root == null) {
            return;
        }

        if( root.data == node1) {
             nodeInfo1 = new NodeInfo(parent, depth);
        }

        if( root.data == node2) {
            nodeInfo2 = new NodeInfo(parent, depth);
        }

        // If both nodes are found, no need to continue the traversal
        if (nodeInfo1 != null && nodeInfo2 != null) {
            return;
        }

        dfs(root.left, root, nodeInfo1, nodeInfo2, node1, node2, depth +1 );
        dfs(root.right, root, nodeInfo1, nodeInfo2, node1, node2, depth +1 );
    }


    class NodeInfo {
        Node parent;
        int depth;

        NodeInfo(Node parent, int depth) {
            this.parent = parent;
            this.depth = depth;
        }
    }
}
