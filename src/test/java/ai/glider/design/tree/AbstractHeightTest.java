package ai.glider.design.tree;

import ai.glider.design.builder.TreeBuilder;
import ai.glider.design.common.Tree;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

abstract class AbstractHeightTest extends AbstractTreeTest {

    @Test
    public void validateBalancedTree() {
        List<Integer> indexes = getIndexes();

        for (Integer num : indexes) {
            TreeBuilder binary = treeFactory.createBuilder(name);

            Stream.generate(random::nextInt)
                    .distinct()
                    .limit(num * 1000)
                    .forEach(binary::addValue);

            Tree tree = binary.build();
            Result balancedRecursive = isBalancedRecursive(tree, 0);
            assertThat(balancedRecursive.isBalanced).isTrue();
            assertThat(balancedRecursive.height).isLessThan(log2(num * 1000));
        }

    }

    @Test
    public void validateRandomTreeAgain() {
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

    private class Result {

        private boolean isBalanced;

        private int height;

        private Result(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    private Result isBalancedRecursive(Tree tree, int depth) {
        if (tree.getValue().isPresent()) {
            return new Result(true, -1);
        }

        Result leftSubtreeResult = isBalancedRecursive(tree.getLeft(), depth + 1);
        Result rightSubtreeResult = isBalancedRecursive(tree.getRight(), depth + 1);

        boolean isBalanced = Math.abs(leftSubtreeResult.height - rightSubtreeResult.height) <= 1;
        boolean subtreesAreBalanced = leftSubtreeResult.isBalanced && rightSubtreeResult.isBalanced;
        int height = Math.max(leftSubtreeResult.height, rightSubtreeResult.height) + 1;

        return new Result(isBalanced && subtreesAreBalanced, height);
    }

    public static int log2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }
}
