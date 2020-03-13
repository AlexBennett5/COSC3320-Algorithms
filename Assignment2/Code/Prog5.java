
import java.util.Scanner;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Node {
	char ch;
	int freq;
	Node left;
	Node right;
}

class HuffComparator implements Comparator<Node> {
	public int compare(Node a, Node b) {
		return a.freq - b.freq;
	}
}

public class Prog5 {

	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);
		ArrayList<Character> chars = new ArrayList<Character>();
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		boolean check = true;
		
		while(check) {
			System.out.println("Enter character (to exit enter '0')");
			char elem = reader.nextLine().charAt(0);
			
			if (elem == '0') {
				check = false;
			} else {
				System.out.println("What is the frequency for the character?");
				int freq = Integer.parseInt(reader.nextLine());
				chars.add(elem);
				frequency.add(freq);
			}
		}
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>(chars.size(), new HuffComparator());

		for (int i = 0; i < chars.size(); i++) {
			Node newNode = new Node();
			
			newNode.ch = chars.get(i);
			newNode.freq = frequency.get(i);
			newNode.right = null;
			newNode.left = null;

			queue.add(newNode);
		}
		
		Node root = null;

		while (queue.size() > 1) {

			Node a = queue.peek();
			queue.poll();
			Node b = queue.peek();
			queue.poll();

			Node c = new Node();

			c.freq = a.freq + b.freq;
			c.ch = '-';
			c.left = a;
			c.right = b;
			root = c;

			queue.add(c);

		}

		printCode(root, "");

	}

	public static void printCode(Node root, String code) {
		if (root.left == null && root.right == null && Character.isLetter(root.ch)) {
			System.out.println(root.ch + ": " + code);
			return;
		}

		printCode(root.left, code + "0");
		printCode(root.right, code + "1");
	}

}

