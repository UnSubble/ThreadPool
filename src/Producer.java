public final class Producer implements Task{
	private int produceTime;
	private static Object proKey = new Object();

	public Producer(int produceTime) {
		this.produceTime = produceTime;
	}
	
	@Override
	public void perform(ThreadManager manager) {
		while (true) {
			try {
				Thread.sleep(produceTime);
			} catch (InterruptedException e) { }
			synchronized (proKey) {
				if (!manager.addList()) {
					try {
						proKey.wait();
					} catch (InterruptedException e) { }
				}
				System.out.printf("Producer %s has produced.\n", Thread.currentThread().getName());
				proKey.notify();
			}
		}
	}
}
