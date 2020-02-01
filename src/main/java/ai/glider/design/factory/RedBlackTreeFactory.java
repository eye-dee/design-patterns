package ai.glider.design.factory;

import ai.glider.design.builder.RedBlackTreeBuilder;
import ai.glider.design.builder.TreeBuilder;
import org.springframework.stereotype.Service;

@Service
public class RedBlackTreeFactory implements TreeFactory {

    @Override
    public <T extends Comparable<T>> TreeBuilder<T> createBuilder() {
        return new RedBlackTreeBuilder<>();
    }
}
