package binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class HBinaryTree<T> {
    BNode<T> root;
    int size;

    HBinaryTree() {
        this.size = 0;
    }

    private int idx;

    /*-------------To set pointer while starting to build tree-----------*/
    void setPtr() {
        this.idx = -1;
    }

    // Recursive function to build tree
    public BNode<T> buildTree(T[] nodes) {
        idx++;
        // base case
        if (nodes[idx] == null) {
            return null;
        }
        // store current index value into the root
        BNode<T> root = new BNode<T>(nodes[idx]);
        this.size += 1;
        // create left subtree of root
        // root.left = buildTree(nodes, idx);
        root.left = buildTree(nodes);
        // create right subtree of root
        root.right = buildTree(nodes);
        return root;
    }

    /**
     * Traversal method format
     * The same method can be used of all three teaversals
     * Just chnage the sequence of recursive call
     */
    // DFS
    /*-------------Preorder-----------*/
    public void preOrderTraverse(BNode<T> root) {
        // base case
        if (root == null) {
            // System.out.print("null" + " ");
            return;
        }
        // root --> left --> right
        System.out.print(root.data + " ");
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    /*-------------Post Order-----------*/
    public void postOrderTraverse(BNode<T> root) {
        // base case
        if (root == null) {
            // System.out.print("null" + " ");
            return;
        }
        // left --> right --> root
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.print(root.data + " ");
    }

    /*-------------In Order-----------*/
    public void inOrderTraverse(BNode<T> root) {
        // base case
        if (root == null) {
            // System.out.print("null" + " ");
            return;
        }
        // left --> root --> right
        inOrderTraverse(root.left);
        System.out.print(root.data + " ");
        inOrderTraverse(root.right);
    }

    // BFS
    /*-------------Level Order-----------*/
    public void levelOrder(BNode<T> root) {
        Queue<BNode<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        BNode<T> currNode;
        while (!queue.isEmpty()) {
            currNode = queue.poll();
            if (currNode == null) {
                System.out.println(); // new line
                if (queue.isEmpty()) {
                    break;
                }
                queue.add(null);
            } else {
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
                System.out.print(currNode.data + " ");
            }
        }
    }

    /*-------------Count Number of nodes in tree-----------*/
    public int count(BNode<T> root) {
        if (root == null) {
            return 0;
        }
        // Total Nodes = 1 + nodes in left subtree + nodes in right subtree
        return 1 + count(root.left) + count(root.right);
    }

    /*-------------Calculate Sum of values of nodes in tree-----------*/
    public int sum(BNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        // Total sum = root value + sum of left subtree + sum of right subtree
        return root.data + sum(root.left) + sum(root.right);
    }

    /*-------------Calculate Height of tree-----------*/
    public int height(BNode<T> root) {
        if (root == null) {
            return 0;
        }
        // int leftHeight = 1 + height(root.left);
        // int rightHeight = 1 + height(root.right);
        // if (leftHeight > rightHeight) {
        // return leftHeight;
        // } else {
        // return rightHeight;
        // }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /*-------------Calculate Diameter of tree-----------
     * Diameter is the longest path between any 2 nodes of a tree
     * It can be in three cases:
     * Case-1: Diameter is in left subtree
     * Case-2: Diameter is in right subtree
     * Case-3: Diameter includes root itself (height of left + height of right + 1)
     * We'll return maximum of these 3 cases
    */
    public int diameter(BNode<T> root) {
        if (root == null) {
            return 0;
        }
        int d1 = diameter(root.left);
        int d2 = diameter(root.right);
        int d3 = height(root.left) + height(root.right) + 1;

        return Math.max(Math.max(d1, d2), d3);

        /*
         * Time Complexity
         * n times for diameter
         * n times for height
         * Total = O(n^2)
         */
    }

    /*
     * Using another class TreeInfo that can help to get tree height and diameter
     * both with single recursive function in O(n) time
     * With each recurive call, getInfo of left and right subtree including its
     * height and diametere
     * Now we can calculate height and diametere of current node with these info
     */
    class TreeInfo {
        int height;
        int diameter;

        TreeInfo(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }

    // This method will return an object of TreeInfo which will have both height and
    // diameter of node passed to it as argument
    public TreeInfo getInfo(BNode<T> root) {
        if (root == null) {
            return new TreeInfo(0, 0); // height and diameter of null node = 0
        }
        // get info of left subtree
        TreeInfo leftTree = getInfo(root.left);
        // get info of right subtree
        TreeInfo rightTree = getInfo(root.right);

        // Calculate height of current node
        int selfHeight = Math.max(leftTree.height, rightTree.height) + 1;

        int d1 = leftTree.diameter;
        int d2 = rightTree.diameter;
        int d3 = leftTree.height + rightTree.height + 1;

        // Calculate diameter of current node
        int selfDiameter = Math.max(d1, Math.max(d2, d3));

        return new TreeInfo(selfHeight, selfDiameter);
    }

    /*-------------Sum of nodes at K level-----------*/
    public int getSumAt(int k, BNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        Queue<BNode<Integer>> queue = new LinkedList<>();
        queue.add(null); // null will indicate start of a level
        queue.add(root);
        int level = 0;
        int sum = 0;
        while (!queue.isEmpty()) {
            BNode<Integer> currNode = queue.remove();
            if (currNode == null) {
                level++;
                if (level == k) {
                    while (!queue.isEmpty()) {
                        sum += queue.remove().data;
                    }
                    return sum;
                }
                if (queue.isEmpty()) {
                    break;
                } else {
                    queue.add(null);
                }
            } else {
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }

        }
        return sum;
    }

    // Main method for Testing
    public static void main(String[] args) {
        System.out.println("\n"); // new line
        Integer[] nodes = { 1, 2, 4, null, null, 5, null, null, 3, null, 6, null, null }; // tree structure in array
                                                                                          // form
        HBinaryTree<Integer> tree = new HBinaryTree<>();
        tree.setPtr();
        tree.root = tree.buildTree(nodes);
        System.out.println("Root value: " + tree.root.data);
        System.out.print("PreOrder: ");
        tree.preOrderTraverse(tree.root);
        System.out.println();
        System.out.print("InOrder: ");
        tree.inOrderTraverse(tree.root);
        System.out.println();
        System.out.print("PostOrder: ");
        tree.postOrderTraverse(tree.root);
        System.out.println();
        System.out.println("Level Order: ");
        tree.levelOrder(tree.root);
        System.out.println("Size: " + tree.count(tree.root));
        System.out.println("Sum of Node: " + tree.sum(tree.root));
        // System.out.println("Height of Tree: " + tree.height(tree.root));
        // System.out.println("Diameter of Tree: " + tree.diameter(tree.root));
        System.out.println("Height of Tree: " + tree.getInfo(tree.root).height);
        System.out.println("Diameter of Tree: " + tree.getInfo(tree.root).diameter);
        System.out.println("Sum of Nodes at Level 2 of Tree: " + tree.getSumAt(3, tree.root));

        System.out.println("\n"); // new line
    }
}
