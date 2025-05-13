class SugarDecorator implements Coffee {
    private Coffee coffee;

    public SugarDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    public double cost() {
        return coffee.cost() + 0.3;
    }
}
