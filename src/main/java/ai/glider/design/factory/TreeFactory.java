package ai.glider.design.factory;

import ai.glider.design.builder.AvlTreeBuilder;
import ai.glider.design.builder.BinarySearchTreeBuilder;
import ai.glider.design.builder.RedBlackTreeBuilder;
import ai.glider.design.builder.TreeBuilder;

public interface TreeFactory {

    <T extends Comparable<T>> TreeBuilder<T> createBuilder();

    default <T extends Comparable<T>> TreeBuilder<T> createBuilder(String name) {
        switch (name) {
            case "red_black": {
                return new RedBlackTreeBuilder<>();
            }
            case "search_binary": {
                return new BinarySearchTreeBuilder<>();
            }
            case "avl": {
                return new AvlTreeBuilder<>();
            }
            default:
                throw new UnsupportedOperationException();
        }
    }
}
