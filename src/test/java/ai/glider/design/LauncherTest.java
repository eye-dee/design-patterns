package ai.glider.design;

import ai.glider.design.builder.TreeBuilder;
import ai.glider.design.factory.TreeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LauncherTest {

    @Autowired
    TreeFactory avlTreeFactory;

    @Autowired
    TreeFactory binarySearchTreeFactory;

    @Autowired
    TreeFactory redBlackTreeFactory;

    @Autowired
    TreeBuilder avlTreeBuilder;

    @Autowired
    TreeBuilder binarySearchTreeBuilder;

    @Autowired
    TreeBuilder redBlackTreeBuilder;

    @Test
    public void applicationRunning() {

    }

}
