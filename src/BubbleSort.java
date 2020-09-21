import java.util.ArrayList;



public class BubbleSort implements Runnable{
	private CustomLayout.drawPanel centerPanel;
	private ArrayList<Integer> arr;
	private int speed;

	public BubbleSort(ArrayList<Integer> arr,CustomLayout.drawPanel centerPanel, int speed)  {
		this.speed=speed;
		this.arr=arr;
		this.centerPanel=centerPanel;
		
		
	}
	
	@Override
	public void run() {
		int n = arr.size(); 
	
	    for (int i = 0; i < n-1&& !Util.exit; i++) 
	        for (int j = 0; j < n-i-1 && !Util.exit; j++) 
	        {
	        	
	            if (arr.get(j) > arr.get(j+1) && !Util.exit) 
	            { 
	                // swap arr[j+1] and arr[i] 
	                int temp = arr.get(j); 
	                arr.set(j, arr.get(j+1));
	                arr.set(j+1, temp);
					Util.GotToSleep(speed);
					centerPanel.repaint();
	               
	            } 
	        }
		
	}
	 

}
