package ca.queensu.cs.cisc235.tree;

import java.util.Random;

public class RandomBinaryTree<E> extends BinaryTree<E> implements Tree<E> {

	private Random rng;

	/**
	 * Initializes this tree to be empty.
	 */
	public RandomBinaryTree() {
		super();
		this.rng = new Random();
	}

	/**
	 * Initializes this tree to be empty and sets the seed for the random number
	 * generator to the specified seed.
	 * 
	 * @param seed a seed for the random number generator
	 */
	public RandomBinaryTree(long seed) {
		super();
		this.rng = new Random(seed);
	}

	/**
	 * Adds a element to this tree. The element is added by constructing a path to a
	 * node where a leaf node can be added to the node. The path is constructed by
	 * starting at the root node and randomly choosing between the left and right
	 * child at each level of the tree until a {@code null} child is chosen. A new
	 * node containing the element replaces the {@code null} child.
	 * 
	 * <p>
	 * If the tree is empty, then the element is stored in the root of the tree.
	 * 
	 * @param elem an element to add to this tree
	 * @throws NullPointerException if elem is null
	 */
	public void add(E elem) {
		if (elem == null) {
			throw new NullPointerException("tree cannot have null elements");
		}
		if (this.isEmpty()) {
			this.root = new BinaryNode<>(elem);
		} else {
			// randomly choose the left or right child until we find
			// a null child
			BinaryNode<E> parent = this.root;
			while (true) {
				boolean b = this.rng.nextBoolean();
				if (b) {
					if (!parent.hasLeft()) {
						parent.left = new BinaryNode<>(elem, parent, null, null);
						break;
					}
					parent = parent.left;
				} else {
					if (!parent.hasRight()) {
						parent.right = new BinaryNode<>(elem, parent, null, null);
						break;
					}
					parent = parent.right;
				}
			}

		}
		this.size++;
	}
	
	/**
	 * Adds a element to this tree. The element is added by constructing a path to a
	 * node where a leaf node can be added to the node. The path is constructed by
	 * starting at the root node and randomly choosing between the left and right
	 * child at each level of the tree until a {@code null} child is chosen. A new
	 * node containing the element replaces the {@code null} child.
	 * 
	 * <p>
	 * If the tree is empty, then the element is stored in the root of the tree.
	 * 
	 * @param elem an element to add to this tree
	 * @throws NullPointerException if elem is null
	 */
	public void add2(E elem) {
		if (elem == null) {
			throw new NullPointerException("tree cannot have null elements");
		}
		if (this.isEmpty()) {
			this.root = new BinaryNode<>(elem);
		} else {
			this.addRecursive(elem, this.root());
		}
		this.size++;
	}
	
	void addRecursive(E elem, BinaryNode<E> parent) {
		boolean goLeft = this.rng.nextBoolean();
		if (goLeft) {
			if (parent.hasLeft()) {
				this.addRecursive(elem, parent.left);
			}
			else {
				parent.setLeft(new BinaryNode<>(elem));
			}
		}
		else {
			if (parent.hasRight()) {
				this.addRecursive(elem, parent.right);
			}
			else {
				parent.setRight(new BinaryNode<>(elem));
			}
		}
	}

	/**
	 * Returns {@code true} if an element in this tree is equal to the specified
	 * element, {@code false} otherwise.
	 * 
	 * @param elem an element to search for
	 * @return {@code true} if an element in this tree is equal to the specified
	 *         element, {@code false} otherwise
	 */
	@Override
	public boolean contains(E elem) {
		if (elem == null) {
			return false;
		}
		return RandomBinaryTree.contains(elem, this.root);
	}

	static <E> boolean contains(E elem, BinaryNode<E> n) {
		if (n == null) {
			return false;
		}
		if (elem.equals(n.elem)) {
			return true;
		}
		boolean inLeftTree = contains(elem, n.left);
		if (inLeftTree) {
			return true;
		}
		boolean inRightTree = contains(elem, n.right);
		return inRightTree;
	}

	@Override
	public boolean remove(E elem) {
		throw new UnsupportedOperationException();
	}


	@Override
	public String toString() {
		return Traversals.toString(this);
	}
	
	public static void main(String[] args) {
		RandomBinaryTree<Integer> t = new RandomBinaryTree<>(1);
		for (int i = 0; i < 10; i++) {
			t.add(i);
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(t.contains(i));
		}
		System.out.println(t.contains(10));
	}

	
}
