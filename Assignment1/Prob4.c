
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int main(int argc, char *argv[] ) {

	char *arg = argv[1];
	int m = atoi(arg);

	int *arrs_800[3*m];	

	clock_t begin1 = clock();

	for (int i = 0; i < (3*m); i++) {
		int *temp = (int *)malloc(800000);
		arrs_800[i] = temp;
	}

	clock_t end1 = clock();
	int time_elapsed = (double)(end1 - begin1) /(CLOCKS_PER_SEC/1000);
	printf("Time elapsed for first allocation is %d milliseconds\n", time_elapsed);

	for (int i = 0; i < (3*m); i+= 2) {
		free(arrs_800[i]);
	}

	int *arrs_mil[m];
	
	clock_t begin2 = clock();

	for (int i = 0; i < m; i++) {
		int *temp = (int *)malloc(1000000);
		arrs_mil[i] = temp;
	}

	clock_t end2 = clock();
	time_elapsed = (double)(end2 - begin2) / (CLOCKS_PER_SEC/1000);
	printf("Time elapsed for first allocation is %d milliseconds\n", time_elapsed);


}

