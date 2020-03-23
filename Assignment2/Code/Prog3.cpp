
#include <math.h>
#include <time.h>
#include <algorithm>
#include <stdlib.h>

using namespace std;

class AVLtree {

private:
	int M_0 = pow(2, 20);
       	int M_1 = pow(2, 19) + pow(2, 18);
	int M_2 = pow(2, 18) + pow(2, 17);
	int inserts = 0;
	int deletes = 0;
	int nodes = 0;

	struct Node {
		int val;
		int* matrix;
		Node* left;
		Node* right;
		int height;
	};
	
	Node* root;

	Node* insert(int a, Node* b) {
		
		int mod = a % 3;

		if (b == NULL) {
			inserts++;
			b = new Node;
			nodes++;

			b->height = 0;
			b->left = NULL;
			b->right = NULL;

			if (mod == 0) {
				b->matrix = new int[M_0];
			} else if (mod == 1) {
				b->matrix = new int[M_1];
			} else {
				b->matrix = new int[M_2];
			}
		} else if (a < b->val) {
			b->left = insert(a, b->left);
			if (height(b->left) - height(b->right) == 2) {
				if (a < b->left->val) b = sing_right_rotate(b);
				else b = doub_right_rotate(b);
			}
		} else if (a > b->val) {
			b->right = insert(a, b->right);
			if (height(b->right) - height(b->left) == 2) {
				if (a > b->right->val) b = sing_left_rotate(b);
				else b = doub_right_rotate(b);
			}
		}

		b->height = max(height(b->left), height(b->right)) + 1;
		return b;
	}

	Node* sing_right_rotate(Node* &b) {
		
		if (b == NULL || b->left == NULL) {
			return b;
		} else {
			Node* temp = b->left;
			b->left = temp->right;
			temp->right = b;
			b->height = max(height(b->left), height(b->right)) + 1;
			temp->height = max(height(temp->left), b->height) + 1;
			return temp;
		}
		return b;
	}

	Node* sing_left_rotate(Node* &b) {
		
		if (b == NULL || b->right == NULL) {
			return b;
		} else {
			Node* temp = b->right;
			b->right = temp->left;
			temp->left = b;
			b->height = max(height(b->left), height(b->right)) + 1;
			temp->height = max(height(b->right), b->height) + 1;
			return temp;
		}
		return b;
	}

	Node* doub_left_rotate(Node* &b) {
		b->right = sing_right_rotate(b->right);
		return sing_left_rotate(b);
	}

	Node* doub_right_rotate(Node* &b) {
		b->left = sing_left_rotate(b->left);
		return sing_right_rotate(b);
	}

	Node* remove(int a, Node* b) {
		Node* temp;

		if (b == NULL) return NULL;
		else if (a < b->val) b->left = remove(a, b->left);
		else if (a > b->val) b->right = remove(a, b->right);
		else if (b->left && b->right) {
			temp = find_min(b->right);
			b->val = temp->val;
			b->right = remove(b->val, b->right);
		} else {
			temp = b;
			if (b->left == NULL) b = b->right;
			else if (b->right == NULL) b = b->left;

			delete[] temp->matrix;
			delete temp;
			deletes++;
			nodes--;
		}
		if (b == NULL) return b;

		b->height = max(height(b->left), height(b->right)) + 1;

		if (height(b->left) - height(b->right) == 2) {
			if (height(b->left->left) - height(b->left->right) == 1) return sing_left_rotate(b);
			else return doub_left_rotate(b);
		} else if (height(b->right) - height(b->left) == 2) {
			if (height(b->right->right) - height(b->right->left) == 1) return sing_right_rotate(b);
			else return doub_right_rotate(b);
		}
		return b;
	}

	Node* find_min(Node* b) {
		if (b == NULL) return NULL;
		else if (b->left == NULL) return b;
		else return find_min(b->left);
	}

	Node* find_max(Node* b) {
		if (b == NULL) return NULL;
		else if (b->right == NULL) return b;
		else return find_max(b->right);
	}

	int height(Node* b) {
		return (b == NULL? -1 : b->height);
	}

public:
	AVLtree() {
		root = NULL;
	}

	void insert(int a) {
		root = insert(a, root);
	}

	void remove(int a) {
		root = remove(a, root);
	}

	int nodecount() {
		return nodes;
	}

	int insertcount() {
		return inserts;
	}

	int deletecount() {
		return deletes;
	}

	void resetcount() {
		inserts = 0;
	}

};

int main() {
			
	AVLtree tree;
	double begin = clock();

	srand(time(0));

	while(tree.nodecount() < 50) {
		tree.insert(rand() % 299 + 1);
	}

	double end = clock();

	double initial_time = end - begin;
	tree.resetcount();

	double insert_time = 0;
	double remove_time = 0;

	for (int i = 0; i < 100000; i++) {

		while (tree.nodecount() < 50) {
			begin = clock();
			tree.insert(rand() % 299 + 1);
			end = clock();
		}

		insert_time += (end - begin);
		
		while (tree.nodecount() >= 50) {
			begin = clock();
			tree.remove(rand() % 299 + 1);
			end = clock();
		}

		remove_time += (end - begin);

	}

	int time1 = (double)(initial_time/(CLOCKS_PER_SEC/1000));
	int time2 = (double)(insert_time/(CLOCKS_PER_SEC/1000));
	int time3 = (double)(remove_time/(CLOCKS_PER_SEC/1000));

	printf("Average time for initial insertion: %d ms \n", time1 / 50);
	printf("Average time for insertions: %d ms \n", time2 / tree.insertcount());
	printf("Average time for deletion: %d ms \n", time3 / tree.deletecount());

}

