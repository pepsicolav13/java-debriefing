import java.util.ArrayList;
import java.util.List;
class PizzaBuilder {
    private String crust;
    private String sauce;
    private List<String> toppings = new ArrayList<>();

    public PizzaBuilder setCrust(String crust) {
        this.crust = crust;
        return this;
    }

    public PizzaBuilder setSauce(String sauce) {
        this.sauce = sauce;
        return this;
    }

    public PizzaBuilder addTopping(String topping) {
        toppings.add(topping);
        return this;
    }

    public Pizza build() {
        return new Pizza(crust, sauce, toppings);
    }
}
