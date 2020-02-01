package ai.glider.design.builder;

import ai.glider.design.common.AvlTree;
import ai.glider.design.common.Tree;
import org.springframework.stereotype.Service;

@Service
public class AvlTreeBuilder<T extends Comparable<T>> implements TreeBuilder<T> {

    private AvlTree<T> tree = new AvlTree<>(null);

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
