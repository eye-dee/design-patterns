package ai.glider.design.builder;

import ai.glider.design.common.Tree;
import ai.glider.design.common.TreeName;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TreeBuilderTest {

    private static final TreeName[] names = TreeName.values();

    @Autowired
    private List<TreeBuilder> builders;

    @Test
    public void testEmptyBuilders() {
        assertThat(builders).hasSize(names.length);
        List<String> trees = builders.stream()
                .map(TreeBuilder::build)
                .distinct()
                .map(Tree::getName)
                .collect(Collectors.toList());

        assertThat(trees).hasSize(names.length)
                .containsExactlyInAnyOrder(Arrays.stream(names)
                        .map(TreeName::getName)
                        .toArray(String[]::new));
    }

    @Test
    public void allValuesAreEmpty() {
        List<Optional> trees = builders.stream()
                .map(TreeBuilder::build)
                .distinct()
                .map(Tree::getValue)
                .collect(Collectors.toList());

        assertThat(trees).allMatch(it -> !it.isPresent());
    }
}
