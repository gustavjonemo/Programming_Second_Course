package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		for (int i = 0; i < 88; i++) {
			tree.add(i);
		}

		BSTVisualizer bst = new BSTVisualizer("Binary Tree", 1900, 1000);
		
//		bst.drawTree(tree);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		tree.rebuild();
		bst.drawTree(tree);
		tree.printTree();
	}

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {

	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			return search(x, root);

		}
	}

	private boolean search(E x, BinaryNode<E> n) {
		if (x.compareTo(n.element) == 0) {
			return false;
		} else if (x.compareTo(n.element) < 0) {
			if (n.left == null) {
				n.left = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return search(x, n.left);
			}

		} else {
			if (n.right == null) {
				n.right = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return search(x, n.right);
			}
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		if (size == 0) {
			return 0;
		} else {
			return height(root);
		}
	}

	private int height(BinaryNode<E> root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + Math.max(height(root.left), height(root.right));
		}
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		if (size == 0) {
			return;
		} else {
			pT(root);
		}
	}

	private void pT(BinaryNode<E> n) {
		if (n.left == null) {
			System.out.println(n.element);
		} else {
			pT(n.left);
		}
		if (n.right != null) {
			pT(n.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		if (root == null) {
			return;
		} else {
			E[] a = (E[]) new Comparable[size];
			int index = toArray(root, a, 0) - 1;
			root = buildTree(a, 0, index);
		}
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1
	 * (the first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if (n.left != null) {
			index = toArray(n.left, a, index);
		}

		a[index] = n.element;
		index++;
		if (n.right != null) {
			index = toArray(n.right, a, index);
		}
		return index;
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in
	 * the array a are assumed to be in ascending order. Returns the root of
	 * tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if (last < first) {
			return null;
		} else {
			int mid = first + (last - first) / 2;
			BinaryNode<E> b = new BinaryNode<E>(a[mid]);

			b.left = buildTree(a, first, mid - 1);
			b.right = buildTree(a, mid + 1, last);

			return b;
		}
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
