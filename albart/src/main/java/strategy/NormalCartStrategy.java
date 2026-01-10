package strategy;

public class NormalCartStrategy implements CartStrategy {
    @Override
    public double calculate(double total) {
        return total;
    }
}
