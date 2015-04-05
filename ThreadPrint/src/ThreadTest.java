
public class ThreadTest {
	public static void main(String[] args){
		
		Thread t1 = new Thread(new ThreadPrint("one", "John"));
		Thread t2 = new Thread(new ThreadPrint("two", "Smith"));
		Thread t3 = new Thread(new ThreadPrint("three", "1234567"));
		
		t1.start();
		t2.start();
		t3.start();
	}

}
