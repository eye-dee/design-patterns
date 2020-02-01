package ai.glider.design.integral;

import java.util.function.Function;

public interface IntegralCalculator {

    double integral(Function<Double, Double> fun, double a, double b);

    void setStrategy(Strategy strategy);

}
