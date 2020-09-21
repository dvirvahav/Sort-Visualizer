import java.util.ArrayList;

public class MergeSort implements Runnable {
	private CustomLayout.drawPanel centerPanel;
	private ArrayList<Integer> arr;
	private int speed;

	public MergeSort(ArrayList<Integer> arr, CustomLayout.drawPanel centerPanel, int speed) {
		this.speed = speed;
		this.arr = arr;
		this.centerPanel = centerPanel;

	}

	@Override
	public void run() {
		sort(arr, 0, arr.size() - 1);

	}

	void merge(ArrayList<Integer> arr2, int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		ArrayList<Integer> L = new ArrayList<Integer>();
		;
		ArrayList<Integer> R = new ArrayList<Integer>();
		;

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i) {
			 if(Util.exit)
		     		return;
			L.add(i, arr.get(l + i));
			centerPanel.repaint();
			Util.GotToSleep(speed);
		}
		for (int j = 0; j < n2; ++j) {
			 if(Util.exit)
		     		return;
			R.add(j, arr.get(m + 1 + j));
			centerPanel.repaint();
			Util.GotToSleep(speed);

		}
		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			 if(Util.exit)
		     		return;
			if (L.get(i) <= R.get(j)) {
				arr.set(k, L.get(i));
				i++;
			} else {
				arr.set(k, R.get(j));
				j++;
			}
			k++;
			centerPanel.repaint();
            Util.GotToSleep(speed);
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			 if(Util.exit)
		     		return;
			arr.set(k, L.get(i));
			i++;
			k++;
			centerPanel.repaint();
            Util.GotToSleep(speed);
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			 if(Util.exit)
		     		return;
			arr.set(k, R.get(j));
			j++;
			k++;
			centerPanel.repaint();
            Util.GotToSleep(speed);
		}
	}

	// Main function that sorts arr[l..r] using
	// merge()
	void sort(ArrayList<Integer> arr2, int l, int r) {
		if (l < r) {
			 if(Util.exit)
		     		return;
			// Find the middle point
			int m = (l + r) / 2;

			// Sort first and second halves
			sort(arr2, l, m);
			sort(arr2, m + 1, r);

			// Merge the sorted halves
			merge(arr2, l, m, r);
		}
	}

}
