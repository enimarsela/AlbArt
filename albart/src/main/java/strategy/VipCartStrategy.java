package strategy;

public class VipCartStrategy implements CartStrategy {

    @Override
    public double calculate(double total) {
        return total * 0.80; // Ulja e kartes VIP: 20%
    }
}
