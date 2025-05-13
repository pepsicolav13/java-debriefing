# Collections & Generics

## Java Collection

다음 함수들을 Override해야 합니다.
해당 함수들을 Override하지 않으면 HashMap 입장에서는 해당 객체를 어떻게 hashing해야 할지, 동일한 객체인지를 어떻게 판단할지 알 수 없게 됩니다. (구현 방법은 상이할 수 있으나 같은 객체에 대해서 일관성을 가지면 됨.)

```java
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student other = (Student) o;
        return name.equals(other.name) && studentId == other.studentId;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + studentId; 
    }
```

- 읽을거리: https://velog.io/@ghkvud2/equals%EC%99%80-hashCode%EC%99%80-HashMap%EC%9D%98-%EA%B4%80

## RingBuffer

생략 

## Comparator

다음과 같은 `Employee` 라는 클래스가 있을 때, salary 순으로 정렬이 될 수 있도록 `Comparator` 클래스를 구현해 봅시다. (ascending, descending 상관 없음)

```java
// EmployeeComparator.java
import java.util.Comparator;

class EmployeeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return Double.compare(e2.getSalary(), e1.getSalary());
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
        return new Pair<>(value, key);
    }

    @SuppressWarnings("unchecked") // to suppress generic casting warning
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<K, V> other = (Pair<K, V>) o;G
        return this.key.equals(other.key);
    }

    @Override
    public String toString() {
        return "{ 'K': '" + key + "', 'V': '" + value + "'}";
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