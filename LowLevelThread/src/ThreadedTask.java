
public class ThreadedTask implements Runnable {
	
	private static int[] scores;
	private int[] source = {45, 52, 76, 98, 100, 68, 32, 99, 17, 86};
	
	public ThreadedTask(int numScores) {
		if(numScores > source.length) {
			numScores = source.length;
			System.out.format("Too many scores requsted, limiting to %d.\n", source.length);
		}
		scores = new int[numScores];
	}
	
	@Override
	public void run() {
		try {
			for(int i = 0; i < scores.length; i++) {
				System.out.println(i + 1);
				Thread.sleep(1000);
				scores[i] = source[i];
			}
			synchronized(this){ //synchronize the run methods
				this.notifyAll();
			}
		}
		catch(InterruptedException e) {
			// if this occurs, thread should terminate
			// and at this point, will do so.
		}
		
		
	}
	
	public synchronized int[] getScores() {
		return scores;
	}
	
}
