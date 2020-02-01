package ai.glider.design.tree;

import ai.glider.design.builder.TreeBuilder;
import ai.glider.design.common.Tree;
import ai.glider.design.factory.TreeFactory;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class BinarySearchTreeTest extends AbstractTreeTest {

    @Autowired
    TreeFactory binarySearchTreeFactory;

    @Before
    public void setUp() {
        treeFactory = binarySearchTreeFactory;
        name = "search_binary";
    }

    @Test
    public void allToTheRight() {
        TreeBuilder binary = binarySearchTreeFactory.createBuilder("search_binary");

        Stream.iterate(1, it -> it + 1)
                .limit(10000)
                .forEach(binary::addValue);

        Tree tree = binary.build();

        int i = 1;
        while (tree.getValue().isPresent()) {
            assertThat(tree.getValue().get()).isEqualTo(i);
            assertThat(tree.getLeft().getValue()).isEmpty();

            tree = tree.getRight();
            ++i;
        }
    }

    @Test
    public void allToTheLeft() {
        TreeBuilder binary = binarySearchTreeFactory.createBuilder("search_binary");

        Stream.iterate(10000, it -> it - 1)
                .limit(10000)
                .forEach(binary::addValue);

        Tree tree = binary.build();

        int i = 10000;
        while (tree.getValue().isPresent()) {
            assertThat(tree.getValue().get()).isEqualTo(i);
            assertThat(tree.getRight().getValue()).isEmpty();

            tree = tree.getLeft();
            --i;
        }
    }
}
