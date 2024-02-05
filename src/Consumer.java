
public final class Consumer implements Task {
	private int consumeTime;
	private static Object conKey = new Object();

	public Consumer(int consumeTime) {
		this.consumeTime = consumeTime;
	}
	
	@Override
	public void perform(ThreadManager manager) {
		while (true) {
			try {
				Thread.sleep(consumeTime);
			} catch (InterruptedException e) { }
			synchronized(conKey) {
				if (!manager.removeList()) {
					try {
						conKey.notifyAll();
						conKey.wait();
					} catch (InterruptedException e) { }
				}
				System.out.printf("Consumer %s has consumed.\n", Thread.currentThread().getName());
				conKey.notifyAll();
			}		
		}
	}
}
