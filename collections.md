# Collections & Generics

## Java Collection

다음과 같은 Student 클래스가 있습니다. Main 함수에서 실수로 같은 학생을 두번 Map에 추가했습니다. (Bob) 정상적인 상황이라면 한명의 Bob만 추가가 되어야 하는데 두명 다 추가가 됩니다.

HashMap에서 같은 Student에 대해서 동일한 Element로 인식하게 하려면 어떤 함수들을 override해야 할까요? 해당 함수를 정의해 봅시다.

```java
// Student.java
public class Student {
    private String name;
    private int age;
    private int studentId;

    public Student(String name, int age, int studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "name: " + name + ", age: " + age + ", ID: " + studentId;
    }
}

// Main.java
import java.util.HashMap;
import java.util.Map;
public class Main {
    public static void main(String[] args) {
        Map<Student, String> map = new HashMap<>();
        map.put(new Student("Bob", 20, 123), "Bob");
        map.put(new Student("John", 22, 231), "John");
        map.put(new Student("Bob", 20, 123), "Bob"); // add Bob again
        map.put(new Student("Mike", 21, 532), "Mike");

        for (Map.Entry<Student, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
```

## RingBuffer

다음 문서를 참고하여 RingBuffer를 직접 구현해 봅시다.

https://www.baeldung.com/java-ring-buffer

- RingBuffer란?: 버퍼의 일종으로 버퍼가 다 차게 되면 맨 앞에 저장된 element부터 overwrite되면서 순환적으로 데이터를 저장하는 collection 자료구조.


## Comparator

다음과 같은 `Employee` 라는 클래스가 있을 때, salary 순으로 정렬이 될 수 있도록 `Comparator` 클래스를 구현해 봅시다. (ascending, descending 상관 없음)

```java
// Employee.java
class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " / " + salary;
    }
}

// Main.java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 500),
            new Employee("Bob", 700),
            new Employee("Charlie", 800),
            new Employee("Diana", 550),
            new Employee("Eve", 450)
        );

        Collections.sort(employees, new EmployeeComparator());

        System.out.println("Sorted Employees by salary:");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}
```

## Generics

`Pair<K, V>`라는 generic 클래스를 만들과 다음과 같은 함수들을 정의해 보세요.

- `swap`함수: K와 V를 서로 바꿔서 리턴합니다: `Pair<V, K>`
- `equals` 함수: K만 동일하다면 동일한 객체로 판별합니다.
- `toString` 함수: json string 형태로 출력 합니다. `{ 'K': '', 'V': ''}`

```java
// Pair.java
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }


    public Pair<V, K> swap() {
        // swap K and V
    }

    @Override
    public boolean equals(Object o) {
        // if K is same, return true
    }

    @Override
    public String toString() {
        // json string
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Pair<String, Integer> p1 = new Pair<>("Bob", 123);
        Pair<String, Integer> p2 = new Pair<>("Bob", 324);
        Pair<String, Integer> p3 = new Pair<>("Mike", 567);

        System.out.println(p1.equals(p2));
        System.out.println(p3);
        System.out.println(p3.swap());
    }
}
```