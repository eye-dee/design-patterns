package ai.glider.design.factory;

import ai.glider.design.builder.TreeBuilder;
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
public class TreeFactoryTest {

    private static final TreeName[] names = TreeName.values();

    @Autowired
    private List<TreeFactory> factories;

    @Test
    public void testAllFactories() {
        List<String> trees = factories.stream()
                .map(TreeFactory::createBuilder)
                .map(TreeBuilder::build)
                .map(Tree::getName)
                .collect(Collectors.toList());

        assertThat(trees).hasSize(names.length)
                .containsExactlyInAnyOrder(Arrays.stream(names)
                        .map(TreeName::getName)
                        .toArray(String[]::new));
    }

    @Test
    public void allValuesAreEmpty() {
        List<Optional> trees = factories.stream()
                .map(TreeFactory::createBuilder)
                .map(TreeBuilder::build)
                .distinct()
                .map(Tree::getValue)
                .collect(Collectors.toList());

        assertThat(trees).allMatch(it -> !it.isPresent());
    }

}
