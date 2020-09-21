import java.util.ArrayList;

public class SelectionSort implements Runnable {
	private CustomLayout.drawPanel centerPanel;
	private ArrayList<Integer> arr;
	private int speed;

	public SelectionSort(ArrayList<Integer> arr, CustomLayout.drawPanel centerPanel, int speed) {
		this.speed = speed;
		this.arr = arr;
		this.centerPanel = centerPanel;

	}

	@Override
	public void run() {
		sort(arr);

	}

	void sort(ArrayList<Integer> arr2) {
		int n = arr2.size();

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			 if(Util.exit)
		     		return;
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (arr2.get(j) < arr2.get(min_idx))
					min_idx = j;

			// Swap the found minimum element with the first
			// element
			int temp = arr2.get(min_idx);
			arr2.set(min_idx, arr2.get(i));
			arr2.set(i, temp);
			Util.GotToSleep(speed);
			centerPanel.repaint();
		}
	}

	
}
