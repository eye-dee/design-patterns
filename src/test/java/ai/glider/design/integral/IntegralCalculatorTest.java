package ai.glider.design.integral;

import java.util.List;
import java.util.function.Function;
import org.assertj.core.data.Offset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IntegralCalculatorTest {

    private static final int STEPS = 10000;

    @Autowired
    private List<Strategy> strategies;

    @Autowired
    private IntegralCalculator integralCalculator;

    void integral() {
        Object[][] args = getFuns();

        for (Object[] arg : args) {
            Function<Double, Double> fun = (Function<Double, Double>) arg[0];
            double a = (double) arg[1];
            double b = (double) arg[2];
            double res = (double) arg[3];
            for (Strategy strategy : strategies) {
                integralCalculator.setStrategy(strategy);
                double v = integralCalculator.integral(fun, a, b);
                assertThat(v).isCloseTo(res, Offset.offset(0.0001));
            }
        }

    }

    private static Object[][] getFuns() {
        Function<Double, Double> f1 = Math::sin;
        Function<Double, Double> f2 = Math::cos;
        Function<Double, Double> f3 = x -> x + Math.cos(x / 2) - Math.sin(x);
        Function<Double, Double> f4 = x -> x * 2;
        Function<Double, Double> f5 = x -> Math.pow(x, 1. / 3.);
        return new Object[][]{
                {f1, 0, Math.PI, 2.0},
                {f2, 0, 4 * Math.PI, 0.0},
                {f4, 0, 10, 100},
                {f5, 0, 8, 12.0}
        };
    }
}
