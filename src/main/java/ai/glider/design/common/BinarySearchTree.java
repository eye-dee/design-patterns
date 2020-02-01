package ai.glider.design.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    public BinarySearchTree(Node<T> root) {
        this.root = root;
    }

    private static class Node<T> {

        T key;

        Node<T> left, right;

        public Node(T item) {
            key = item;
            left = right = null;
        }
    }

    public void insert(T key) {
        root = insertRec(root, key);
    }

    private Node<T> insertRec(Node<T> root, T key) {

        if (root == null) {
            root = new Node<>(key);
            return root;
        }

        if (key.compareTo(root.key) < 0)
            root.left = insertRec(root.left, key);
        else
            root.right = insertRec(root.right, key);

        return root;
    }

    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node<T> root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }

    @Override
    public Tree<T> getLeft() {
        return new BinarySearchTree<>(root.left);
    }

    @Override
    public Tree<T> getRight() {
        return new BinarySearchTree<>(root.right);
    }

    @Override
    public Optional<T> getValue() {
        return Optional.ofNullable(root).map(it -> root.key);
    }

    @Override
    public String getName() {
        return "search_binary";
    }

    @Override
    public Iterator<T> sortedIterator() {
        ArrayList<T> res = new ArrayList<>();
        inorderRec(root, res);
        return res.iterator();
    }

    void inorderRec(Node<T> root, List<T> res) {
        if (root != null && root.key != null) {
            inorderRec(root.left, res);
            res.add(root.key);
            inorderRec(root.right, res);
        }
    }
}
