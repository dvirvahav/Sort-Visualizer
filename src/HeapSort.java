import java.util.ArrayList;

public class HeapSort implements Runnable{
	private CustomLayout.drawPanel centerPanel;
	private ArrayList<Integer> arr;
	private int speed;
	
	public HeapSort(ArrayList<Integer> arr,CustomLayout.drawPanel centerPanel, int speed)  {
		this.speed=speed;
		this.arr=arr;
		this.centerPanel=centerPanel;
		
		
	}
	@Override
	public void run() {

	    
	        int n = arr.size(); 
	  
	        // Build heap (rearrange array) 
	        for (int i = n / 2 - 1; i >= 0; i--) 
	        {
	        	if(Util.exit)
	        		return;
	        	centerPanel.repaint();
                Util.GotToSleep(speed);
                heapify(arr, n, i); 
	        }
	            
	  
	        // One by one extract an element from heap 
	        for (int i=n-1; i>0&& !Util.exit; i--) 
	        { 
	        	if(Util.exit)
	        		return;
	            // Move current root to end 
	            int temp = arr.get(0); 
	            arr.set(0, arr.get(i));  
	            arr.set(i, temp); 
	            
	            // call max heapify on the reduced heap 
	            heapify(arr, i, 0); 
	        } 
	  
	

	
	
}
	
	 void heapify(ArrayList<Integer> arr2, int n, int i) 
	    { 
		 if(Util.exit)
	     		return;
		 centerPanel.repaint();
         Util.GotToSleep(speed);
         
	        int largest = i; // Initialize largest as root 
	        int l = 2*i + 1; // left = 2*i + 1 
	        int r = 2*i + 2; // right = 2*i + 2 
	  
	        // If left child is larger than root 
	        if (l < n && arr.get(l) > arr.get(largest)) 
	            largest = l; 
	  
	        // If right child is larger than largest so far 
	        if (r < n && arr.get(r) > arr.get(largest)) 
	            largest = r; 
	  
	        // If largest is not root 
	        if (largest != i) 
	        { 
	            int swap = arr2.get(i); 
	            arr2.set(i,arr2.get(largest));
	            arr2.set(largest,swap); 
	  
	            // Recursively heapify the affected sub-tree 
	            heapify(arr2, n, largest); 
	        } 
	    } 	
	
	
}