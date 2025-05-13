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

        List<String> processed = words.stream()
            .map(word -> word.toUpperCase())
            .map(word -> word.length() > 5 ? word.substring(0, 5) : word)
            .collect(Collectors.toList());

        System.out.println(processed);
    }
}
```

### Multi threading & Collections

- synchronized를 이용하는 경우:

```java
// Main.java
public class Main {
    private static int counter = 0;
    private static Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                synchronized(monitor) {
                	counter++;
            	}
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final counter: " + counter);  // counter < 2000
    }
}
```

- lock을 이용하는 경우:

```java
// Main.java
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static int counter = 0;
	private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
		        try {
		            counter++;
		        } finally {
		            lock.unlock();
		        }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

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
            try (FileOutputStream outputStream = new FileOutputStream(saveDir + File.separator + fileName)) {
                // Buffer to read the file in chunks
                byte[] buffer = new byte[4096];
                int bytesRead;
                
                // Read the file and write it to disk
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
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