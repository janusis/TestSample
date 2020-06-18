import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Thread0616 {
	private static int value = 10;

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		Callable<Integer> task = new Callable<Integer>() {

			public Integer call() throws Exception {
				int num = 0;
				value = num;
				return value;
			}
		};

		Future<Integer> future = executorService.submit(task);

		try {
			int number = future.get();
			System.out.println(number);
		} catch (Exception e) {
			e.printStackTrace();
		}

		executorService.shutdown();
	}
}