package ai.glider.design.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class AvlTree<T extends Comparable<T>> implements Tree<T> {

    public AvlTree(Node<T> root) {
        this.root = root;
    }

    static class Node<T> {

        T key;

        int height;

        Node<T> left, right;

        Node(T d) {
            key = d;
            height = 1;
        }
    }

    Node<T> root;

    // A utility function to get the height of the tree
    int height(Node<T> N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node<T> rightRotate(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node<T> leftRotate(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node<T> N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    public void insert(T key) {
        root = insert(root, key);
    }

    private Node<T> insert(Node<T> node, T key) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new Node<T>(key));

        if (key.compareTo(node.key) < 0)
            node.left = insert(node.left, key);
        else if (key.compareTo(node.key) > 0)
            node.right = insert(node.right, key);
        else
            throw new DuplicateItemException();

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                height(node.right));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key.compareTo(node.left.key) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key.compareTo(node.right.key) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    void preOrder(Node<T> node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    @Override
    public Tree<T> getLeft() {
        return new AvlTree<>(root.left);
    }

    @Override
    public Tree<T> getRight() {
        return new AvlTree<>(root.right);
    }

    @Override
    public Optional<T> getValue() {
        return Optional.ofNullable(root).map(it -> it.key);
    }

    @Override
    public String getName() {
        return "avl";
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

