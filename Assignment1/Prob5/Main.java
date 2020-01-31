
public class Main {

	public static void main(String[] args) {
	
		testArr(128, 5);
		testArr(512, 5);
		testArr(2048, 5);
		testArr(8192, 5);
		testArr(32768, 5);
		testArr(131072, 5);
		testArr(524288, 5);
		testArr(2097152, 5);
	}

	public static int binarySearch(int arr[], int lo, int hi, int val) {
		while (lo <= hi) {
			int mid = lo + (hi - lo)/2;

			if (arr[mid] == val) return mid;
			if (arr[mid] < val) lo = mid + 1;
			else hi = mid - 1;
		}
		return -1;
	}

	public static void testArr(int size, int val) {
		int arr[] = new int[size];

		for (int i = 0; i < size; i++)
			arr[i] = val - 1;

		long start = System.currentTimeMillis();
		manySearch(arr, size, val);
		long end = System.currentTimeMillis();

		float sec = end - start;

		System.out.println("The time it took for the array of size " + size + " is " + sec + " milliseconds.");
	
	}

	public static void manySearch(int arr[], int size, int val) {
		int bound = 10000000;

		for (int i = 0; i < bound; i++)
			binarySearch(arr, 0, size - 1, val);

	}

}
