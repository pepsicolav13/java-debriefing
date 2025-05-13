# Java Basics

몸풀기용 문제

## String 비교

다음 코드의 결과값이 어떻게 될까요? 그 이유는?

```java
String s1 = "hello";
String s2 = new String("hello");
String s3 = "hello";
String s4 = new String("hello");

System.out.println(s1 == s2);
System.out.println(s1 == s3);
System.out.println(s2 == s4);
```


## static 함수는 override할 수 있을까?

- 가능/불가능?
- 그 이유는?

## 그렇다면 자식 클래스에서 동일한 이름의 static 메소드를 작성하면 어떻게 될까?

다음 코드의 결과가 어떻게 될까요?

```java
// Parent.java
class Parent {
    static void show() {
        System.out.println("Parent static show()");
    }
}

// Child.java
class Child extends Parent {
    static void show() {
        System.out.println("Child static show()");
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Parent obj = new Child();
        obj.show();
    }
}
```
