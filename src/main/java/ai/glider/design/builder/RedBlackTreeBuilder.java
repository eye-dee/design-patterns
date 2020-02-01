package ai.glider.design.builder;

import ai.glider.design.common.RedBlackTree;
import ai.glider.design.common.Tree;
import org.springframework.stereotype.Service;

@Service
public class RedBlackTreeBuilder<T extends Comparable<T>> implements TreeBuilder<T> {

    private RedBlackTree<T> tree = new RedBlackTree<>();

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
