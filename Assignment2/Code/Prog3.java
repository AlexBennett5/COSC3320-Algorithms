import java.util.Random;

class Node {
	int val;
	int[] matrix;
	Node left;
	Node right;
	int height;
}

class AVLtree {
	int M_0 = (int) Math.pow(2, 20);
	int M_1 = (int) (Math.pow(2, 19) + Math.pow(2, 18));
	int M_2 = (int) (Math.pow(2, 18) + Math.pow(2, 17));
	int inserts = 0;
	int deletes = 0;
	int nodes = 0;
	Node root;

	public void AVLtree() {
		this.root = null;
	}

	public void AVL_insert(int a) {
		this.root = insert(a, this.root);
	}

	public void AVL_remove(int a) {
		this.root = remove(a, this.root);
	}

	public int getNodeCount() {
		return this.nodes;
	}

	public int getInserts() {
		return this.inserts;
	}

	public int getDeletes() {
		return this.deletes;
	}

	public void resetInsertCount() {
		this.inserts = 0;
	}

	private Node insert(int a, Node b) {
		int mod = a % 3;

		if (b == null) {
			this.inserts++;
			b = new Node();
			this.nodes++;

			b.height = 0;
			b.left = null;
			b.right = null;

			if (mod == 0) {
				b.matrix = new int[M_0];
			} else if (mod == 1) {
				b.matrix = new int[M_1];
			} else {
				b.matrix = new int[M_2];
			}
		} else if (a < b.val) {
			b.left = insert(a, b.left);

			if (height(b.left) - height(b.right) == 2) {
				if (a < b.left.val) b = singRightRotate(b);
				else b = doubRightRotate(b);
			}
		} else if (a > b.val) {
			b.right = insert(a, b.right);
			if (height(b.right) - height(b.left) == 2) {
				if (a > b.right.val) b = singLeftRotate(b);
				else b = doubLeftRotate(b);
			}
		}

		b.height = Math.max(height(b.left), height(b.right)) + 1;
		return b;
	}

	private Node singRightRotate(Node b) {
	
		if (b == null || b.left == null) {
			return b;
		} else {
			Node temp = b.left;
			b.left = temp.right;
			temp.right = b;
			b.height = Math.max(height(b.left), height(b.right)) + 1;
			temp.height = Math.max(height(temp.left), b.height) + 1;
			return temp;
		}
	}

	private Node singLeftRotate(Node b) {

		if (b == null || b.right == null) {
			return b;
		} else {
			Node temp = b.right;
			b.right = temp.left;
			temp.left = b;
			b.height = Math.max(height(b.left), height(b.right)) + 1;
			temp.height = Math.max(height(b.right), b.height) + 1;
			return temp;
		}
	}

	private Node doubLeftRotate(Node b) {
		b.right = singRightRotate(b.right);
		return singLeftRotate(b);
	}

	private Node doubRightRotate(Node b) {
		b.left = singLeftRotate(b.left);
		return singRightRotate(b);
	}

	private Node remove(int a, Node b) {
		Node temp;

		if (b == null) return null;
		else if (a < b.val) b.left = remove(a, b.left);
		else if (a > b.val) b.right = remove(a, b.right);
		else if (b.left != null && b.right != null) {
			temp = findMin(b.right);
			b.val = temp.val;
			b.right = remove(b.val, b.right);
		} else {
			temp = b;
			if (b.left == null) b = b.right;
			else if (b.right == null) b = b.left;

			this.nodes--;
			this.deletes++;
		}
		if (b == null) return b;

		b.height = Math.max(height(b.left), height(b.right)) + 1;

		if ((height(b.left) - height(b.right)) == 2) {
			if ((height(b.left.left) - height(b.left.right)) == 1) return singLeftRotate(b);
			else return doubLeftRotate(b);
		} else if ((height(b.right) - height(b.left)) == 2) {
			if ((height(b.right.right) - height(b.right.left)) == 1) return singRightRotate(b);
			else return doubRightRotate(b);
		}
		return b;
	}

	private Node findMin(Node b) {
		if (b == null) return null;
		else if (b.left == null) return b;
		else return findMin(b.left);
	}

	private int height(Node b) {
		return (b == null ? -1 : b.height);
	}
}

public class Prog3 {

	public static void main(String[] args) {
	
		AVLtree tree = new AVLtree();
		Random r = new Random();

		double begin = System.currentTimeMillis();

		while(tree.getNodeCount() < 50) {
			tree.AVL_insert(r.nextInt(299));
		}

		double initialTime = System.currentTimeMillis() - begin;

		double insertTime, removeTime;
		insertTime = removeTime = 0;

		tree.resetInsertCount();

		for (int i = 0; i < 100000; i++) {

			while (tree.getNodeCount() < 50) {
				begin = System.currentTimeMillis();
				tree.AVL_insert(r.nextInt(299));
				insertTime += (System.currentTimeMillis() - begin);
			}

			while (tree.getNodeCount() >= 50) {
				begin = System.currentTimeMillis();
				tree.AVL_remove(r.nextInt(299));
				removeTime += (System.currentTimeMillis() - begin);
			}
		}

		System.out.println("Average time for initial insertion: " + (initialTime/50) + " ms");
		System.out.println("Average time for insertions: " + (insertTime/tree.getInserts()) + " ms");
		System.out.println("Average time for removals: " + (removeTime/tree.getDeletes()) + " ms");
	}
}


