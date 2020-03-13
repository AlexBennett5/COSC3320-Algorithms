
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <iostream>

using namespace std;

int main(int argc, char *argv[]) {

	char *arg = argv[1];
	int n = atoi(arg);

	int i,j,x;

	// Initialize matrix M
	
	int** M = new int*[n];

	for (int k = 0; k < n; k++) {
		M[k] = new int [k];
	}
	for (int k = 0; k < n; k++) {
		for (int q = 0; q < n; q++) {
			M[k][q] = 0;
		}
	}

	size_t m_1 = 1677721600;
	size_t m_2 = 13421772800;
	time_t time1 = clock();
	for (int k = 0; k < m; k++) {
		x = rand() % 100 + 1;
		j = rand() % n;
		i = rand() % n;
		M[i][j] = M[i][j] + x;
	}
	time1 = clock() - time1;

	time_t time2 = clock();
	for (int k = 0; k < m_2; k++) {
		x = rand() % 100 + 1;
		j = rand() % n;
		i = rand() % n;
		M[i][j] = M[i][j] + x;
	}
	time2 = clock() - time2;

	int time_elapsed1 = (double)(time1/(CLOCKS_PER_SEC/1000));
	int time_elapsed2 = (double)(time2/(CLOCKS_PER_SEC/1000));
	printf("Clock time for size %d with m_1: %d ms \n", n, time_elapsed1);
	printf("Clock time for size %d with m_2: %d ms \n", n, time_elapsed2);

}


