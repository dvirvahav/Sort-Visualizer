import java.util.ArrayList;
/* This function takes last element as pivot, 
places the pivot element at its correct 
position in sorted array, and places all 
smaller (smaller than pivot) to left of 
pivot and all greater elements to right 
of pivot */
public class QuickSort implements Runnable{
	private CustomLayout.drawPanel centerPanel;
	private ArrayList<Integer> arr;
	private int speed,low,high;
	
	public QuickSort(ArrayList<Integer> arr,CustomLayout.drawPanel centerPanel, int speed)  {
		this.speed=speed;
		this.arr=arr;
		this.centerPanel=centerPanel;
		this.low=0;
		this.high=arr.size()-1;
	}
	
 


@Override
public void run() {
	sort(arr,low,high);
	centerPanel.repaint();
    Util.GotToSleep(speed);
}
	 
void sort(ArrayList<Integer> arr2, int low, int high) 
{ 
    if (low < high) 
    { 
        /* pi is partitioning index, arr[pi] is  
          now at right place */
    	 if(Util.exit)
	     		return;
        int pi = partition(arr2, low, high); 
        if(Util.exit)
     		return;
        // Recursively sort elements before 
        // partition and after partition 
        sort(arr2, low, pi-1); 
        
        sort(arr2, pi+1, high); 
        
        
    } 
} 





int partition(ArrayList<Integer> arr2, int low, int high) 
{ 
    int pivot = arr2.get(high);  
    int i = (low-1); // index of smaller element 
    for (int j=low; j<high; j++) 
    { 
    	if(Util.exit)
     		return 0;
        // If current element is smaller than the pivot 
        if (arr2.get(j) < pivot) 
        { 
            i++; 
            if(Util.exit)
	     		return 0;
            // swap arr[i] and arr[j] 
            int temp = arr2.get(i); 
            arr2.set(i, arr2.get(j));
            arr2.set(j, temp);
            centerPanel.repaint();
            Util.GotToSleep(speed);
        } 
        centerPanel.repaint();
        Util.GotToSleep(speed);
    } 

    // swap arr[i+1] and arr[high] (or pivot) 
    int temp = arr2.get(i+1); 
    arr2.set(i+1, arr2.get(high));
    arr2.set(high,temp); 
    centerPanel.repaint();
    Util.GotToSleep(speed);
    return i+1; 
} 


/* The main function that implements QuickSort() 
  arr[] --> Array to be sorted, 
  low  --> Starting index, 
  high  --> Ending index */



}
