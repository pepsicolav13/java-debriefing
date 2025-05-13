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
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return 3.14 * radius * radius;
    }
}

// Rectangle.java
class Rectangle implements Shape {
    private double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }
}

// Triangle.java
class Triangle implements Shape {
    private double base, height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double getArea() {
        return 0.5 * base * height;
    }
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

Decorator Pattern의 구조에 대해서 알아보기 위한 예제였습니다.
정답은 다음과 같습니다.

```java
// Coffee.java
interface Coffee {
    String getDescription();
    double cost();
}

// BasicCoffee.java
class BasicCoffee implements Coffee {
    public String getDescription() {
        return "Basic Coffee";
    }

    public double cost() {
        return 2.0;
    }
}

// MilkDecorator.java
class MilkDecorator implements Coffee {
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

```java
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
```

- Has-a 관계가 있는 클래스가 어떻게 되나요?
	- Car이 engine을 소유(has)합니다.
	- Car이 wheel을 소유(has)합니다.
- 실제로 의미 있는 작업을 수행하는 컴포넌트는 누구인가요?
	- engine과 wheel이 실제로 작업을 수행합니다.
	- car는 실제로 일하는 컴포넌트를 소유합니다.

## Builder Pattern

아래와 같이 `PizzaBuilder`를 static inner class로 만들어 주세요.

```java
Pizza margherita = new Pizza.PizzaBuilder().setCrust("Thin Crust")
                      .setSauce("Tomato Sauce")
                      .addTopping("Mozzarella")
                      .addTopping("Basil")
                      .build();
```

- `PizzaBuilder.java`는 삭제
- `Pizza.java`는 다음과 같이 static PizzaBuilder 추가

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

    static class PizzaBuilder {
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
}
```

- 읽을거리: https://atoz-develop.tistory.com/entry/JAVA-static-class%EC%99%80-Builder-Pattern%EB%B9%8C%EB%8D%94-%ED%8C%A8%ED%84%B4


## Strategy Pattern

다음은 strategy pattern에 대한 코드입니다.

- 코드가 정상적으로 돌아갈 수 있게 수정해 주세요.

```java
// PaymentStrategy.java
interface PaymentStrategy {
    void pay(double amount);
}

// CreditCardPayment.java
class CreditCardPayment implements PaymentStrategy {
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
class PayPalPayment implements PaymentStrategy {
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
class BankTransfer implements PaymentStrategy {
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

- 이때 has-a 관계를 갖는 클래스가 어떻게 되나요?
    - OnlineStore와 PaymentStrategy가 Has-a 관계입니다. (OnlineStore가 PaymentStrategy를 소유)
- 위에서 푼 문제 "Composition 관계"의 Car와 Engine 관계와 Strategy Pattern에는 어떤 차이점이 있나요?
    - 단순히 Has-a 관계일 뿐만 아니라 특정 인터페이스 (`PaymentStrategy`)를 구현하기 때문에 결제 전략(PaymentStrategy)를 runtime에서 다른 것으로 갈아끼우기가 쉽습니다. 반대로 Car, Engine 관계에서는 아무런 인터페이스를 구현하지 않았기 때문에 구체적인 객체로써 다른 객체로 갈아끼우기 위해서는 컴파일 타임에서의 코드 변경이 필요로 합니다.

