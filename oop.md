# Object-Oriented Programming


## 다향성 programming

다음과 같이 각 도형의 면적을 구하는 코드가 있습니다.
코드가 동작할 수 있도록 수정해 주세요.

```java
// Shape.java
interface Shape {
    double getArea();
}

// Circle.java
class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    // 면적 공식: 3.14 * radius * radius;
}

// Rectangle.java
class Rectangle {
    private double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // 면적 공식: width * height;
    
}

// Triangle.java
class Triangle {
    private double base, height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    // 면적 공식: 0.5 * base * height;
}

// Main.java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(3, 8)
        );

        double totalArea = 0.0;

        for (Shape shape : shapes) {
            totalArea += shape.getArea();
        }

        System.out.println("Total Area: " + totalArea);
    }
}
```

## Decorator Pattern

다음 코드가 정상적으로 동작하기 위해서 코드를 수정해 주세요.

```java
// Coffee.java
interface Coffee {
    String getDescription();
    double cost();
}

// BasicCoffee.java
class BasicCoffee {
    public String getDescription() {
        return "Basic Coffee";
    }

    public double cost() {
        return 2.0;
    }
}

// MilkDecorator.java
class MilkDecorator {
	private Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    public double cost() {
        return coffee.cost() + 0.5;
    }
}

// SugarDecorator.java
class SugarDecorator {
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

// Main.java
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SugarDecorator(new MilkDecorator(new BasicCoffee()));

        System.out.println("Order: " + coffee.getDescription());
        System.out.println("Total: $" + coffee.cost());
    }
}
```

## Composition 관계

다음은 Composition (Has-a 관계)를 나타내기 위한 코드입니다.

- `Car` 클래스 `start()` 함수에서 빠진 코드 부분을 추가 해주세요.
- Has-a 관계가 있는 클래스가 어떻게 되나요?
- 실제로 의미 있는 작업을 수행하는 컴포넌트는 누구인가요?

```java
// Engine.java
class Engine {
    public void start() {
        System.out.println("Engine started.");
    }
}

// Wheel.java
class Wheel {
    public void rotate() {
        System.out.println("Wheels are rotating.");
    }
}

// Car.java
class Car {
    private Engine engine;
    private Wheel wheel;

    public Car() {
        this.engine = new Engine(); 
        this.wheel = new Wheel();
    }

    public void start() {
        // 내용 추가

        System.out.println("Car is moving.");
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
    }
}
```

## Builder Pattern

다음은 Builder pattern을 이용한 코드입니다.

```java
// Pizza.java
import java.util.ArrayList;
import java.util.List;

class Pizza {
    private String crust;
    private String sauce;
    private List<String> toppings;

    public Pizza(String crust, String sauce, List<String> toppings) {
        this.crust = crust;
        this.sauce = sauce;
        this.toppings = toppings;
    }

    public void display() {
        System.out.println("Crust: " + crust);
        System.out.println("Sauce: " + sauce);
        System.out.println("Toppings: " + toppings);
        System.out.println("\n");
    }
}

// PizzaBuilder.java
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


// Main.java
public class Main {
    public static void main(String[] args) {
        PizzaBuilder builder = new PizzaBuilder();
        
        Pizza margherita = builder.setCrust("Thin Crust")
                      .setSauce("Tomato Sauce")
                      .addTopping("Mozzarella")
                      .addTopping("Basil")
                      .build();
        Pizza pepperoni = builder.setCrust("Thick Crust")
                      .setSauce("Spicy Tomato Sauce")
                      .addTopping("Mozzarella")
                      .addTopping("Pepperoni")
                      .build();

        System.out.println("Margherita:");
        margherita.display();

        System.out.println("Pepperoni:");
        pepperoni.display();
    }
}
```

아래와 같이 `PizzaBuilder`를 static inner class로 만들어 주세요.

```java
Pizza margherita = new Pizza.PizzaBuilder().setCrust("Thin Crust")
                      .setSauce("Tomato Sauce")
                      .addTopping("Mozzarella")
                      .addTopping("Basil")
                      .build();
```

## Strategy Pattern

다음은 strategy pattern에 대한 코드입니다.

- 코드가 정상적으로 돌아갈 수 있게 수정해 주세요.
- 이때 has-a 관계를 갖는 클래스가 어떻게 되나요?
- 위에서 푼 문제 "Composition 관계"의 Car와 Engine 관계와 Strategy Pattern에는 어떤 차이점이 있나요?

```java
// PaymentStrategy.java
interface PaymentStrategy {
    // Add interface method
}

// CreditCardPayment.java
class CreditCardPayment {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card: " + cardNumber);
    }
}

// PayPalPayment.java
class PayPalPayment {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal: " + email);
    }
}

// BankTransfer.java
class BankTransfer {
    private String accountNumber;

    public BankTransfer(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via Bank Transfer: " + accountNumber);
    }
}

// OnlineStore.java
class OnlineStore {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void checkout(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        paymentStrategy.pay(amount);
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        OnlineStore store = new OnlineStore();

        store.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        store.checkout(100.0);

        store.setPaymentStrategy(new PayPalPayment("user@example.com"));
        store.checkout(50.0);

        store.setPaymentStrategy(new BankTransfer("BANK-000987654"));
        store.checkout(200.0);
    }
}
```
