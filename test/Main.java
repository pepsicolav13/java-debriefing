import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static int counter = 0;
    private static Object o = new Object();
    private static ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
              synchronized(o){
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
