package ai.glider.design.common;

import java.util.Iterator;
import java.util.Optional;

public interface Tree<T extends Comparable<T>> {

    Tree<T> getLeft();

    Tree<T> getRight();

    Optional<T> getValue();

    String getName();

    Iterator<T> sortedIterator();
}
