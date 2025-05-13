# Stream API Multi Thread & File IO


## Stream API

다음 단어들을 전부 uppercase로 바꾸고 단어의 글자수가 5를 넘으면 truncate 시켜주세요.

```java
// Main.java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "orange", "pineapple", "raspberry", "banana");

        List<String> processed = words... // do stream API process

        System.out.println(processed);
    }
}
```

### Multi threading & Collections

다음 코드는 thread-safe 하지 않습니다. 최종 counter가 20000이 되지 않는 경우가 발생합니다.
이를 해결하기 위해 synchronized나 lock을 이용하여 race condition이 발생하지 않게 수정해 주세요.

```java
// Main.java
public class Main {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();  // wait for thread 1
        t2.join();  // wait for thread 2

        System.out.println("Final counter: " + counter);  // counter < 2000
    }
}
```

## File I/O

인터넷에서 자바 로고를 다운 받아서 현재 디렉토리에 저장하는 코드를 완성해 봅시다.

```java
// FileDownloader.java
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloader {

    public static void downloadFile(String fileURL, String saveDir) throws IOException {
        // Create a URL object from the file URL
        URL url = new URL(fileURL);
        
        // Open a connection to the URL
        URLConnection urlConnection = url.openConnection();
        
        // Get the input stream of the file
        try (InputStream inputStream = urlConnection.getInputStream()) {
            // Get the file name from the URL
            String fileName = "java-logo.svg";
            
            // Create the file from input stream to output stream
            // ...
        }
    }

    public static void main(String[] args) {
        // URL of the file to download
        String fileURL = "https://upload.wikimedia.org/wikipedia/en/3/30/Java_programming_language_logo.svg";
        
        // current directory
        String saveDir = "./";
        
        try {
            downloadFile(fileURL, saveDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
