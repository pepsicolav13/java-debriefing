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

```
false
true
false
```

이유는 다음과 같습니다.

```java
String s1 = "hello";               // 내부 String pool에 "hello" const 생성
String s2 = new String("hello");   // 새로운 힙 객체에 hello string 생성
String s3 = "hello";               // 기존 String pool에 존재하는 "hello" 참조
String s4 = new String("hello");   // 새로운 힙 객체에 다른 hello string 생성
```

## static 함수는 override할 수 있을까?

static 함수는 override할 수 없습니다.
static 함수는 객체가 아니라 클래스에 속하기 때문입니다.

Static 함수는 compile-time에 결정됩니다.
반면에 다향성을 위한 함수 overriding은 runtime에 결정됩니다.

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

`Parent static show()`가 출력됩니다.
`obj`가 Child 객체이긴 하지만 static 함수는 레퍼런스 타입(Parent)에 의해 결정되지 Object type(Child objection)에 결정되지 않습니다.
