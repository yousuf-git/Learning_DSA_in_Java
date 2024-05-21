package binarytree;

/**
 * Binary Tree Node class
 * It contains basic structure of tree
 * A binary tree has at most 2 child nodes, left and right
 * And current or root node contains some data in it
 *  */ 
public class BNode<T> {
    T data;
    BNode<T> left;
    BNode<T> right;
    BNode(T data) {
        this.data = data;
    }
}
