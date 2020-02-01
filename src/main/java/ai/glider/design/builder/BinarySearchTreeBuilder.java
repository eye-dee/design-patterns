package ai.glider.design.builder;

import ai.glider.design.common.BinarySearchTree;
import ai.glider.design.common.Tree;
import org.springframework.stereotype.Service;

@Service
public class BinarySearchTreeBuilder<T extends Comparable<T>> implements TreeBuilder<T> {

    private BinarySearchTree<T> tree = new BinarySearchTree<>(null);

    @Override
    public TreeBuilder<T> addValue(T value) {
        tree.insert(value);
        return this;
    }

    @Override
    public Tree<T> build() {
        return tree;
    }
}
