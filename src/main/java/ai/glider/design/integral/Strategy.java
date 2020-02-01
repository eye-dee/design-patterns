package ai.glider.design.integral;

import java.util.function.Function;

public interface Strategy {

    double calculateSegment(Function<Double, Double> fun, double a, double b);
}
