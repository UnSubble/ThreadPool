
public class Main {
	public static void main(String[] args) {
		Producer producer1 = new Producer(1000);
		Producer producer2 = new Producer(3000);
		Consumer consumer1 = new Consumer(2000);
		
		ThreadManager tm = new ThreadManager(10);
		
		tm.add(producer1);
		tm.add(producer2);
		tm.add(consumer1);
	}
}
