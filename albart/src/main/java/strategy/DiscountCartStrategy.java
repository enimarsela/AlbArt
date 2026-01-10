package strategy;

public class DiscountCartStrategy implements CartStrategy {

    private double discount;

    public DiscountCartStrategy(double discount) {
        this.discount = discount;
    }

    @Override
    public double calculate(double total) {
        return total - (total * discount);
    }
}
