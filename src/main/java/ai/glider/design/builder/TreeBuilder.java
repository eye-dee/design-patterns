package ai.glider.design.builder;

import ai.glider.design.common.Tree;

public interface TreeBuilder<T extends Comparable<T>> {

    TreeBuilder<T> addValue(T value);

    Tree<T> build();
}
