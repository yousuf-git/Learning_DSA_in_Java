/*
 * Binary Search tree class with following methods:
 * - build BST
 * - insert elements in BST by array
 * - insert single element in BST
 * - delete element from BST
 * - search element in BST
 * - inOrder Traversal
 * - inOrderSuccessor Finder
 * - print values in tree from specific range
 * - print all paths from root to leaf
 * 
*/

package binarytree;

import java.util.ArrayList;

public class BSTree<T> {
    BNode<Integer> root;

    BSTree(Integer[] nodes) {
        root = null;
        buildTree(nodes);
    }

    // Iteratively calls insert() for each value in nodes array
    public void buildTree(Integer[] nodes) {
        for (Integer value : nodes) {
            root = insert(root, value);
        }
    }

    // Recursive function to add one node at a time based on its value
    public BNode<Integer> insert(BNode<Integer> root, Integer data) {
        if (root == null) { // here is the place for node
            BNode<Integer> newNode = new BNode<Integer>(data);
            root = newNode;
        } else if (data > root.data) { // insert in right subtree
            root.right = insert(root.right, data);
        } else { // insert in left subtree
            root.left = insert(root.left, data);
        }
        return root;
    }

    /*----------------InOrder Traverse------------------*/
    public void inOrder(BNode<T> root) {
        if (root == null) {
            return;
        }
        // left --> root --> right
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    /*----------------Search a value in tree------------------*/
    public void search(BNode<Integer> root, Integer data, int level) {
        level++;
        if (root == null) {
            System.out.println(data + " not found :( ");
        } else if (root.data == data) {
            System.out.println(data + " Found in BST at level " + level + " :) ");
            return;
        } else if (data < root.data) {
            search(root.left, data, level);
        } else {
            search(root.right, data, level);
        }
    }

    /*----------------Delete a value in tree------------------*/
    public BNode<Integer> delete(BNode<Integer> root, Integer data) {
        if (root == null) {
            System.out.println(data + " Not found :( ");
            return root;
        }
        // Step 1: find the node to be deleted
        if (data < root.data) {
            root.left = delete(root.left, data);
        } else if (data > root.data) {
            root.right = delete(root.right, data);
        }
        // Step 2: if data is found check the 3 cases
        else {
            // Case-1: target is leaf
            if (root.left == null && root.right == null) {
                return null;
            }
            // Case-2: target has only 1 child
            else if (root.right == null) {
                return root.left;
            } else if (root.left == null) {
                return root.right;
            }
            // Case-3: target has 2 children
            else {
                // find inOrder successor --> left most in right subtree
                BNode<Integer> inOrderSucc = inOrderSuccessor(root.right);
                // replace the value of root with successor's value
                root.data = inOrderSucc.data;
                // delete the inOrderSuccessor from the right subtree
                root.right = delete(root.right, inOrderSucc.data);
            }
        }
        return root;
    }

    /*------------Find inOrderSuccessor of a node in tree---------------*/
    public BNode<Integer> inOrderSuccessor(BNode<Integer> root) {
        BNode<Integer> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /*----------Print Values from tree that are in range--------------*/
    // x and y are included and x <= y
    public void printInRange(BNode<Integer> root, int x, int y) {
        if (root == null) {
            return;
        }
        // case-1
        if (root.data < x) { // values will be in right subtree
            printInRange(root.right, x, y);
        }
        // case-2
        else if (root.data > y) { // values will be in left subtree
            printInRange(root.left, x, y);
        }
        // case-3: values are in both left and right subtree
        else if (root.data >= x && root.data <= y) {
            printInRange(root.left, x, y);
            System.out.print(root.data + " ");
            printInRange(root.right, x, y);
        }
    }

    /*------------All paths from root to leaf in a tree---------------*/

    public void rootToLeaf(BNode<T> root, ArrayList<T> path) {
        // base case
        if (root == null) {
            return;
        }
        // Step 1: add current node value in path
        path.add(root.data);

        // case-1: if root is leaf node
        if (root.left == null && root.right == null) {
            // print path till now
            printPath(path);
        }
        // case-2: otherwise find paths in left and right subtree
        else {
            rootToLeaf(root.left, path);
            rootToLeaf(root.right, path);
        }
        // Step 2: remove current node value from path (back tracking)
        path.removeLast();
    }

    // helper function for path printing
    public void printPath(ArrayList<T> path) {
        for (int i = 0; i < path.size() - 1; i++) {
            System.out.print(path.get(i) + " --> ");
        }
        System.out.println(path.get(path.size() - 1));

    }

    public static void main(String[] args) {
        System.out.println("\n");
        Integer nodes[] = { 5, 1, 3, 4, 2, 6 };
        BSTree<Integer> bsTree = new BSTree<>(nodes);
        // bsTree.insert(bsTree.root, 0);

        System.out.print("InOrder Traversal: ");
        bsTree.inOrder(bsTree.root);
        System.out.println();

        bsTree.search(bsTree.root, 2, 0);

        // bsTree.root = bsTree.delete(bsTree.root, 1);
        // System.out.print("InOrder Traversal: ");
        // bsTree.inOrder(bsTree.root);

        System.out.print("Values in range (0, 4): ");
        bsTree.printInRange(bsTree.root, 0, 4);
        System.out.println("All Paths from root to leaf: ");

        bsTree.rootToLeaf(bsTree.root, new ArrayList<>());

        System.out.println("\n");
    }
}
