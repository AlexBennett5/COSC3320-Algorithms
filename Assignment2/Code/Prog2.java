import java.util.Random;

public class Prog2 {

	public static void main (String[] args) {

		int n = Integer.parseInt(args[0]);

		int[][] M = new int[n][n];

		for (int k = 0; k < n; k++) {
			for (int q = 0; q < n; q++) {
				M[k][q] = 0;
			}
		}

		long start1 = System.currentTimeMillis();
		long m_1 = 1677721600;
		Random r = new Random();

		for (long k = 0; k < m_1; k++) {
			int x = r.nextInt(100) + 1;
			int j = r.nextInt(n);
			int i = r.nextInt(n);
			M[i][j] = M[i][j] + x;
		}

		long end1 = System.currentTimeMillis();

		long start2 = System.currentTimeMillis();
		long m_2 = 13421772800L;

		for (long k = 0; k < m_2; k++) {
			int x = r.nextInt(100) + 1;
			int j = r.nextInt(n);
			int i = r.nextInt(n);
			M[i][j] = M[i][j] + x;
		}

		long end2 = System.currentTimeMillis();

		System.out.println("Time elalpsed for n=" + n + " for m_1: " + (end1 - start1) + " milliseconds");
		System.out.println("Time elalpsed for n=" + n + " for m_2: " + (end2 - start2) + " milliseconds");


	}
}
