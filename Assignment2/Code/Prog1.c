#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {

	char *arg = argv[1];
	int n = atoi(arg);

	int* arr[n];
	int** A = arr;
	int** B = arr;
	int** C = arr;

	int arr2[n];

	//Initialize A,B,C
	for (int i = 0; i < n; i++) {
		int arr2[n];
		A[i] = arr2;
		B[i] = arr2;
		C[i] = arr2;
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {

			A[i][j] = 1;
			B[i][j] = 1;
		}
	}
	
	//Version 1
	clock_t begin1 = clock();
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			C[i][j] = A[i][j] + B[i][j];
		}
	}
	clock_t end1 = clock();

	//Version 2
	clock_t begin2 = clock();
	for (int j = 0; j < n; j++) {
		for (int i = 0; i < n; i++) {
			C[i][j] = A[i][j] + B[i][j];
		}
	}
	clock_t end2 = clock();

	int time1 = (double)(end1 - begin1)/(CLOCKS_PER_SEC/1000);
	int time2 = (double)(end2 - begin2)/(CLOCKS_PER_SEC/1000);

	printf("Version 1: %d milliseconds\n", time1);
	printf("Version 2: %d milliseconds\n", time2);

}
