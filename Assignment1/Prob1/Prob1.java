import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.File;

public class Prob1 {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream o = new PrintStream(new File("output.txt"));
		PrintStream console = System.out;
		System.setOut(o);
		
		try {
			int n = Integer.parseInt(args[0]);
			if (n > 0) {
				System.out.println("Moves for " + n + " disks:\n" + "********************");
				hanoi_total(n, "Aux1", "Aux2", "Aux3", "Aux4", 1);
				System.out.println("********************");
			} else {
				System.out.println("Invalid input for n: n must be positive");
			}

		} catch (NumberFormatException nexc) {
			System.setOut(console);
			System.out.println("Number Format Exception: " + nexc.getMessage());			
		}

	}

	public static int hanoi_1(int n, String A1, String A2, String A3, String A4, int movenum) {
		if (n == 1) {
			movenum = move(n, A1, A2, movenum);
			return movenum;
		} else {
			movenum = hanoi_2(n-1, A1, A2, A3, A4, movenum);
			movenum = move(n, A1, A2, movenum);
			return hanoi_3(n-1, A3, A4, A1, A2, movenum);
		}
	}

	public static int hanoi_2(int n, String A1, String A2, String A3, String A4, int movenum) {
		if (n == 1) {
			movenum = move(n, A1, A2, movenum);
			return move(n, A2, A3, movenum);
		} else {
			movenum = hanoi_2(n-1, A1, A2, A3, A4, movenum);
			movenum = move(n, A1, A2, movenum);
			movenum = hanoi_2(n-1, A3, A4, A1, A2, movenum);
			movenum = move(n, A2, A3, movenum);
			return hanoi_2(n-1, A1, A2, A3, A2, movenum);
		}
	}

	public static int hanoi_3(int n, String A1, String A2, String A3, String A4, int movenum) {
		if (n == 1) {
			movenum = move(n, A1, A2, movenum);
			movenum = move(n, A2, A3, movenum);
			return move(n, A3, A4, movenum);
		} else {
			movenum = hanoi_2(n-1, A1, A2, A3, A4, movenum);
			movenum = move(n, A1, A2, movenum);
			movenum = hanoi_2(n-1, A3, A4, A1, A2, movenum);
			movenum = move(n, A1, A3, movenum);
			movenum = move(n, A3, A4, movenum);
			return hanoi_3(n-1, A1, A2, A3, A4, movenum);
		}
	}

	public static int hanoi_start(int n, String A1, String A2, String A3, String A4, int movenum) {
		if (n == 1) {
			movenum = move(n, "Start", A1, movenum);
			movenum = move(n, A1, A2, movenum);
			movenum = move(n, A2, A3, movenum);
			return move(n, A3, A4, movenum);
		} else {
			movenum = hanoi_start(n-1, A1, A2, A3, A4, movenum);
			movenum = move(n, "Start", A1, movenum);	
			movenum = move(n, A1, A2, movenum);
			movenum = move(n, A2, A3, movenum);
			movenum = hanoi_2(n-1, A4, A1, A2, A3, movenum);
			movenum = move(n, A3, A4, movenum);
			return hanoi_2(n-1, A2, A3, A4, A1, movenum);
		}
	}

	public static int hanoi_dest(int n, String A1, String A2, String A3, String A4, int movenum) {
		if (n == 1) {
			return move(n, A1, "Dest", movenum);
		} else {
			for (int i = 0; i < n-1; i++) {
				movenum = hanoi_3(n-1-i, A1, A2, A3, A4, movenum);
				movenum = move(n-i, A1, "Dest", movenum);
				movenum = hanoi_1(n-1-i, A4, A1, A2, A3, movenum);
			}
			return move(1, A1, "Dest", movenum);
		}
	}

	public static int hanoi_total(int n, String A1, String A2, String A3, String A4, int movenum) {
	       	if (n == 1) {
	       		movenum = move(n, "Start", A1, movenum);
	 		return move(n, A1, "Dest", movenum);
		} else {
			movenum = hanoi_start(n-1, A1, A2, A3, A4, movenum);
			movenum = move(n, "Start", A1, movenum);
			movenum = move(n, A1, "Dest", movenum);
			movenum = hanoi_1(n-1, A4, A1, A2, A3, movenum);
			movenum = hanoi_dest(n-1, A1, A2, A3, A4, movenum);
			return movenum;
		}
	}

	public static int move(int disk, String source, String dest, int movenum) {
		System.out.println("Move " + movenum + ": Disk " + disk + " from " + source + " to " + dest);
		return movenum + 1;
	}

}
