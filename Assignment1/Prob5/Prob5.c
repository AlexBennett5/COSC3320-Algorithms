#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int binarysearch(int *arr, int lo, int hi, int val);
void testarr(int size, int val);
void manysearch(int *arr, int size, int val);

int main() {

	testarr(128, 5);
	testarr(512, 5);
	testarr(2048, 5);
	testarr(8192, 5);
	testarr(32768, 5);
	testarr(131072, 5);
	testarr(524288, 5);
	testarr(2097152, 5);

}

int binarysearch(int *arr, int lo, int hi, int val) {

	while (lo <= hi) {
		int mid = lo + (hi - lo)/2;

		if (arr[mid] == val) return mid;
		if (arr[mid] < val) lo = mid + 1;
		else hi = mid - 1;
	}
	return -1;
}

void testarr(int size, int val) {
	int *arr = (int*)malloc(size*sizeof(int));

	for (int i = 0; i < size; i++) {
		arr[i] = val - 1;
	}

	clock_t begin = clock();	
	manysearch(arr,size,val);
	clock_t end = clock();
	int time_elapsed = (double) (end - begin)/(CLOCKS_PER_SEC/1000);

	printf("The time it took for the array of size %d is %d milliseconds\n", size, time_elapsed);

	free(arr);
}

void manysearch(int *arr, int size, int val) {
	int bound = 10000000;

	for (int i = 0; i < bound; i++) 
		binarysearch(arr, 0, size - 1, val);
	
}

