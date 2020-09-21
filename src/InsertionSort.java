import java.util.ArrayList;

public class InsertionSort implements Runnable{
	private CustomLayout.drawPanel centerPanel;
	private ArrayList<Integer> arr;
	private int speed;

	public InsertionSort(ArrayList<Integer> arr,CustomLayout.drawPanel centerPanel, int speed)  {
		this.speed=speed;
		this.arr=arr;
		this.centerPanel=centerPanel;
		
		
	}

	@Override
	public void run() {
		sort(arr);
		
	}
	
	
	void sort(ArrayList<Integer> arr2) 
    { 
        int n = arr2.size(); 
        for (int i = 1; i < n ; ++i) { 
        	 if(Util.exit)
 	     		return;
            int key = arr2.get(i); 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr2.get(j) > key) { 
            	 if(Util.exit)
     	     		return;
            	arr2.set(j + 1,arr2.get(j));
                j = j - 1; 
                Util.GotToSleep(speed);
                centerPanel.repaint();
            } 
            arr2.set(j+1,key);
            Util.GotToSleep(speed);
            centerPanel.repaint();
        } 
    } 
	
}
