import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ThreadManager {
	private List<Integer> list;
	private ExecutorService service;
	private int limit;
	private Random random;
	
	public ThreadManager(int listSizeLimit) {
		list = new ArrayList<>();
		service = Executors.newCachedThreadPool();
		random = new Random();
		this.limit = listSizeLimit;
	}
	
	public List<Integer> getUnModifiableList() {
		return Collections.unmodifiableList(list);
	}
	
	public boolean addList() {
		if (getList().size() < limit) {
			getList().add(random.nextInt());
			return true;
		}
		return false;
	}
	
	public boolean removeList() {
		if (!getList().isEmpty()) {
			getList().remove(0);
			return true;
		}
		return false;
	}
	
	private synchronized List<Integer> getList() {
		return list;
	}
	
	public synchronized void add(Task tasker) {
		service.submit(() -> tasker.perform(this));
	}
}
