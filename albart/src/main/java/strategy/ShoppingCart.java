package strategy;

public class ShoppingCart {

    private CartStrategy strategy;

    public void setStrategy(CartStrategy strategy){
        this.strategy = strategy;
    }

    public double execute(double total){
        return strategy.calculate(total);
    }
}
