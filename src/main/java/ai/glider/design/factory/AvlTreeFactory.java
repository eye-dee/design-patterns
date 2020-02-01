package ai.glider.design.factory;

import ai.glider.design.builder.AvlTreeBuilder;
import ai.glider.design.builder.TreeBuilder;
import org.springframework.stereotype.Service;

@Service
public class AvlTreeFactory implements TreeFactory {

    @Override
    public <T extends Comparable<T>> TreeBuilder<T> createBuilder() {
        return new AvlTreeBuilder<>();
    }
}
