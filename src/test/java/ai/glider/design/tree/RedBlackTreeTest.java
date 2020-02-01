package ai.glider.design.tree;

import ai.glider.design.builder.TreeBuilder;
import ai.glider.design.common.Tree;
import ai.glider.design.factory.TreeFactory;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedBlackTreeTest extends AbstractHeightTest {

    @Autowired
    TreeFactory redBlackTreeFactory;

    @Before
    public void setUp() {
        treeFactory = redBlackTreeFactory;
        name = "red_black";
    }

    @Test
    public void allToTheRight() {
        TreeBuilder binary = redBlackTreeFactory.createBuilder(name);

        Stream.iterate(1, it -> it + 1)
                .limit(10000)
                .forEach(binary::addValue);

        Tree tree = binary.build();

        validateTree(tree);
    }

    @Test
    public void allToTheLeft() {
        TreeBuilder binary = redBlackTreeFactory.createBuilder(name);

        Stream.iterate(10000, it -> it - 1)
                .limit(10000)
                .forEach(binary::addValue);

        Tree tree = binary.build();

        validateTree(tree);
    }

}
