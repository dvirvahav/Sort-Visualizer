import java.util.ArrayList;
import java.util.Random;


public class Util {
static boolean exit=false;
	//generate array with random numbers at length & max(numer) specified.
	public static void Random(ArrayList<Integer> arr,int length, int max) {
		
		Random rand = new Random();
		arr.clear();
		for(int i=0;i<length;i++)
			arr.add(rand.nextInt(max));
		
		
	}
	
public static void GotToSleep(int speed) {
	
    try {
		Thread.sleep(speed);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    
    
    
}


}