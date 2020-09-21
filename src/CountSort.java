import java.util.ArrayList;

public class CountSort implements Runnable {
	private CustomLayout.drawPanel centerPanel;
	private ArrayList<Integer> arr;
	private int speed;

	public CountSort(ArrayList<Integer> arr, CustomLayout.drawPanel centerPanel, int speed) {
		this.speed = speed;
		this.arr = arr;
		this.centerPanel = centerPanel;

	}

	@Override
	public void run() {
		countSort(arr, arr.size());

	}

	void countSort(ArrayList<Integer> arr2, int size) {
		int[] output = new int[size + 1];

		// Find the largest element of the array
		int max = arr2.get(0);
		for (int i = 1; i < size && !Util.exit; i++) {
			if (arr2.get(i) > max)
				max = arr2.get(i);
		}
		int[] count = new int[max + 1];

		// Initialize count array with all zeros.
		for (int i = 0; i < max&& !Util.exit; ++i) {
			count[i] = 0;
		}

		// Store the count of each element
		for (int i = 0; i < size && !Util.exit; i++) {
			count[arr2.get(i)]++;
		}

		// Store the cumulative count of each array
		for (int i = 1; i <= max && !Util.exit; i++) {
			count[i] += count[i - 1];
		}

		// Find the index of each element of the original array in count array, and
		// place the elements in output array
		for (int i = size - 1; i >= 0 && !Util.exit; i--) {

			output[count[arr2.get(i)] - 1] = arr2.get(i);
			count[arr2.get(i)]--;
		}

		// Copy the sorted elements into original array
		for (int i = 0; i < size && !Util.exit; i++) {
			centerPanel.repaint();
			Util.GotToSleep(speed);
			arr2.set(i, output[i]);

		}
		centerPanel.repaint();
	}

}
