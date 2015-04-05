import java.util.*;

public class ThreadPrint implements Runnable{
	String name;
	String message;
	int time;
	Random r = new Random();
		
	public ThreadPrint(String strname, String strmsg){
		name = strname;
		message = strmsg;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i=0; i<5; i++){
			System.out.println(name+" says "+message);
		}
	}
	

}
