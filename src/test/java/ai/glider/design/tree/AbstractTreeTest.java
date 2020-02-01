package ai.glider.design.tree;

import ai.glider.design.builder.TreeBuilder;
import ai.glider.design.common.Tree;
import ai.glider.design.factory.TreeFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
abstract public class AbstractTreeTest {

    protected TreeFactory treeFactory;

    protected String name;

    protected final Random random = new Random();

    @Test
    public void validateRandomTree() {
        List<Integer> indexes = getIndexes();

        for (Integer num : indexes) {
            TreeBuilder binary = treeFactory.createBuilder(name);

            Stream.generate(random::nextInt)
                    .distinct()
                    .limit(num * 1000)
                    .forEach(binary::addValue);

            Tree tree = binary.build();
            validateTree(tree);
        }
    }

    @Test
    public void validateSortedIterator() {
        List<Integer> indexes = getIndexes();

        for (Integer num : indexes) {
            TreeBuilder binary = treeFactory.createBuilder(name);

            List<Integer> collect = Stream.generate(random::nextInt)
                    .distinct()
                    .limit(num * 1000)
                    .peek(binary::addValue)
                    .sorted()
                    .collect(Collectors.toList());

            Tree tree = binary.build();
            Iterator iterator = tree.sortedIterator();

            List list = new ArrayList<>();
            iterator.forEachRemaining(list::add);
            assertThat(list)
                    .isSorted()
                    .hasSize(collect.size());

            for (int i = 0; i < collect.size(); i++) {
                assertThat(collect.get(i)).isEqualTo(list.get(i));
            }
        }
    }

    public static void validateTree(Tree tree) {
        if (tree.getValue().isPresent()) {
            if (tree.getRight().getValue().isPresent()) {
                Comparable actual = (Comparable) tree.getValue().get();

                assertThat(actual.compareTo(tree.getRight().getValue().get()))
                        .isLessThanOrEqualTo(0);
                validateTree(tree.getRight());
            }
            if (tree.getLeft().getValue().isPresent()) {
                Comparable actual = (Comparable) tree.getValue().get();
                assertThat(actual.compareTo(tree.getLeft().getValue().get()))
                        .isGreaterThanOrEqualTo(0);
                validateTree(tree.getLeft());
            }
        }
    }

    protected static List<Integer> getIndexes() {
        return Stream.iterate(1, i -> i + 1)
                .limit(100)
                .collect(Collectors.toList());
    }
}
