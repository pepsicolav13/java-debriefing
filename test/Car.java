class Car {
    private Engine engine;
    private Wheel wheel;

    public Car() {
        this.engine = new Engine(); 
        this.wheel = new Wheel();
    }

    public void start() {
        // 내용 추가

      this.engine.start();
    this.wheel.rotate();
        System.out.println("Car is moving.");
    }
}
