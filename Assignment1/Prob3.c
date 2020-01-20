
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void inefficient_array(int arr[], int n);
void optimal_array(int arr[], int n);
void quicksort(int arr[], int lo, int hi);
void swap(int arr[], int i, int j);
int partition(int arr[], int lo, int hi);

int main(int argc, char *argv[]) {
	char *arg = argv[1];
	int n = atoi(arg);
	
	int badarr[n];
	inefficient_array(badarr,n);
	int goodarr[n]; 
	optimal_array(goodarr,n);

	clock_t begin1, end1, begin2, end2;

	begin1 = clock();
	quicksort(goodarr, 0, n - 1);
	end1 = clock();

	int time_elapsed = (double)(end1 - begin1)/CLOCKS_PER_SEC;
	printf("It took %d seconds to quicksort the optimal array\n", time_elapsed);
	
	begin2 = clock();
	quicksort(badarr, 0, n - 1);
	end2 = clock();

	time_elapsed = (double)(end2 - begin2)/CLOCKS_PER_SEC;	
	printf("It took %d seconds to quicksort the inefficient array\n", time_elapsed);

	return 0;
}

void inefficient_array(int arr[], int n) {

	for (int i = 0; i < n; i++) {
		arr[i] = i;
	}
}

void optimal_array(int arr[], int n) {

	for (int i = 0; i < n; i++) {
		arr[i] = 10;
	}
}

void quicksort(int arr[], int lo, int hi) {

	if (lo < hi) {
		int pivot = partition(arr, lo, hi);
		quicksort(arr, lo, pivot);
		quicksort(arr, pivot + 1, hi);
	}
}

int partition(int arr[], int lo, int hi) {

	int pivot = arr[lo];
	int i = lo - 1;
	int j = hi + 1;

	while(1) {
		do {
			i++;
		} while (arr[i] < pivot);

		do {
			j--;
		} while (arr[j] > pivot);

		if (i >= j) return j;
		swap(arr, i, j);
	}
}

void swap(int arr[], int i, int j) {

	int temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;

}

