package ai.glider.design.integral;

import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class StrategyImpl implements Strategy {

    @Override
    public double calculateSegment(Function<Double, Double> fun, double a, double b) {
        return 0.0;
    }
}
