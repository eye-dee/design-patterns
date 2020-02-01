package ai.glider.design.integral;

import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class IntegralCalculatorImpl implements IntegralCalculator {

    @Override
    public double integral(Function<Double, Double> fun, double a, double b) {
        double sum = 0.0;
        double h = (b - a) / 10_000.0;
        for (double x = a; x < b; x += h) {
            sum += h * (fun.apply(x) + fun.apply(x + h)) / 2.0;
        }
        return sum;
    }

    @Override
    public void setStrategy(Strategy strategy) {

    }
}
