
public class LowLevelMain {
	public static void main(String[] args) throws InterruptedException {
		ThreadedTask thread = new ThreadedTask(11);
		
		new Thread(thread).start();
		synchronized(thread) { // will lock the thread
			thread.wait(); //waiting for thread to notice us
		}
		int[] currentScores = thread.getScores();
		for(int score : currentScores) {
			System.out.print(score + " ");
		}
	}
}

