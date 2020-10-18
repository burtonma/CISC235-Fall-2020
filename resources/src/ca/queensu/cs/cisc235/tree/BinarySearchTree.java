package ca.queensu.cs.cisc235.tree;

/**
 * A binary search tree class.
 * 
 * <p>
 * A binary search tree is a binary tree where values are stored in the tree
 * according to three rules:
 * 
 * <ol>
 * <li>values in the left subtree are less than values in the root node
 * <li>values in the right subtree are greater than or equal to values in the
 * root node
 * <li>rules 1 and 2 apply recursively to every subtree
 * </ol>
 * 
 * 
 * @param <E> the type of elements in this tree
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	/**
	 * Initialize an empty binary search tree.
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * Adds an element to this tree. The element is inserted into the tree in a
	 * position that preserves the definition of a binary search tree.
	 * 
	 * @param element the element to add to the tree
	 */
	public void add(E element) {
		if (this.isEmpty()) {
			this.setRoot(new BinaryNode<E>(element));
		} else {
			BinarySearchTree.add(element, this.root());
		}
		this.size++;
	}

	/**
	 * Add an element to the subtree rooted at {@code root}. The element is inserted
	 * into the tree in a position that preserves the definition of a binary search
	 * tree.
	 * 
	 * @param element the element to add to the subtree
	 * @param root    the root of the subtree
	 */
	static <E extends Comparable<E>> void add(E element, BinaryNode<E> root) {
		if (element.compareTo(root.elem) < 0) {
			if (!root.hasLeft()) {
				root.setLeft(new BinaryNode<E>(element));
			} else {
				BinarySearchTree.add(element, root.left);
			}
		} else {
			if (!root.hasRight()) {
				root.setRight(new BinaryNode<E>(element));
			} else {
				BinarySearchTree.add(element, root.right);
			}
		}
	}

	/**
	 * Returns {@code true} if the tree contains the given element, {@code false}
	 * otherwise.
	 * 
	 * @param element the element to search for
	 * @return {@code true} if the tree contains the given element, {@code false}
	 *         otherwise
	 */
	public boolean contains(E element) {
		return contains(element, this.root());
	}

	/**
	 * Returns {@code true} if the subtree rooted at {@code root} contains the given
	 * element, {@code false} otherwise.
	 * 
	 * @param element the element to search for
	 * @param root    the root of the subtree
	 * @return {@code true} if the subtree rooted at {@code root} contains the given
	 *         element, {@code false} otherwise
	 */
	static <E extends Comparable<E>> boolean contains(E element, BinaryNode<E> root) {
		if (root == null) {
			return false;
		}
		if (element.equals(root.elem)) {
			return true;
		}
		boolean result;
		if (element.compareTo(root.elem) < 0) {
			result = contains(element, root.left);
		} else {
			result = contains(element, root.right);
		}
		return result;
	}

	/**
	 * Return a string representation of the tree.
	 * 
	 * <p>
	 * The string is made up of the elements stored in the tree separated by commas;
	 * the entire list of elements is enclosed in braces. The elements are in
	 * ascending sorted order.
	 * 
	 * @see java.lang.Object#toString()
	 *
	 * @return a string representation of the tree
	 */
	@Override
	public String toString() {
		return "{" + BinarySearchTree.toString(this.root()) + "}";
	}

	/**
	 * Return a string representation of the subtree rooted at {@code root}.
	 * 
	 * <p>
	 * The string is made up of the elements stored in the tree separated by commas
	 * where the elements are in ascending sorted order.
	 * 
	 * <p>
	 * The string is generated using inorder processing of the subtree:
	 * 
	 * <ol>
	 * <li>the string corresponding to {@code root.left} is computed
	 * <li>the string corresponding to {@code root.elem} is computed
	 * <li>the string corresponding to {@code root.right} is computed
	 * </ol>
	 * 
	 * <p>
	 * The returned string is the concatenation of the three strings computed by the
	 * inorder processing of the subtree.
	 * 
	 * @param root the root of the subtree
	 * @return the string representation of the subtree
	 */
	static <E extends Comparable<E>> String toString(BinaryNode<E> root) {
		if (root == null) {
			return "";
		}
		String left = BinarySearchTree.toString(root.left);
		if (!left.isEmpty()) {
			left = left + ", ";
		}
		String right = BinarySearchTree.toString(root.right);
		if (!right.isEmpty()) {
			right = ", " + right;
		}
		return left + root.elem + right;
	}

	/**
	 * Returns a node in the tree having data equal to {@code element}. Returns null
	 * if no node in the tree has data equal to {@code element}.
	 * 
	 * @param element the element to search for
	 * @return a node in the tree having data equal to element
	 */
	BinaryNode<E> getNode(E element) {
		BinaryNode<E> n = this.root();
		boolean done = false;
		while (!done) {
			if (n == null) {
				done = true;
			} else if (n.elem.equals(element)) {
				done = true;
			} else if (n.elem.compareTo(element) > 0) {
				n = n.left;
			} else {
				n = n.right;
			}
		}
		return n;
	}

	/**
	 * Find the node in a subtree that has the smallest data element.
	 * 
	 * @param root the root of a tree
	 * @return the node in a subtree that has the smallest data element
	 */
	static <E extends Comparable<E>> BinaryNode<E> minimumInSubtree(BinaryNode<E> root) {
		if (!root.hasLeft()) {
			return root;
		}
		return BinarySearchTree.minimumInSubtree(root.left);
	}

	/**
	 * Find the node in a subtree that has the largest data element.
	 * 
	 * @param root the root of a tree
	 * @return the node in a subtree that has the largest data element
	 */
	static <E extends Comparable<E>> BinaryNode<E> maximumInSubtree(BinaryNode<E> root) {
		if (!root.hasRight()) {
			return root;
		}
		return BinarySearchTree.maximumInSubtree(root.right);
	}

	/**
	 * Find the node in a subtree that is the predecessor to the root of the
	 * subtree. If the predecessor node exists, then it is the node that has the
	 * largest data element in the left subtree of {@code root}.
	 * 
	 * @param root the root of a tree
	 * @return the predecessor node of root if the predecessor exists, null
	 *         otherwise
	 */
	static <E extends Comparable<E>> BinaryNode<E> predecessorInSubtree(BinaryNode<E> root) {
		if (!root.hasLeft()) {
			return null;
		}
		return BinarySearchTree.maximumInSubtree(root.left);
	}

	/**
	 * Find the node in a subtree that is the successor to the root of the subtree.
	 * If the successor node exists, then it is the node that has the smallest data
	 * element in the right subtree of {@code root}.
	 * 
	 * @param root the root of a tree
	 * @return the successor node of root if the successor exists, null otherwise
	 */
	static <E extends Comparable<E>> BinaryNode<E> successorInSubtree(BinaryNode<E> root) {
		if (!root.hasRight()) {
			return null;
		}
		return BinarySearchTree.minimumInSubtree(root.right);
	}

	/**
	 * Remove an element from the tree returning a reference to the element to the
	 * caller. This method removes exactly one element {@code x} such that
	 * {@code x.equals(elem)} is equal to {@code true}, or returns {@code false} if
	 * {@code elem} is not equal to any value stored in the tree.
	 * 
	 * @param elem an element to remove from the tree
	 * @return a referent to the removed element
	 */
	@Override
	public boolean remove(E elem) {

		// get the node to remove
		BinaryNode<E> node = this.getNode(elem);
		if (node == null) {
			return false;
		}
		this.removeNode(node);
		this.size--;
		return true;
	}

	/**
	 * Replaces the node {@code node} with {@code replace} by adjusting the appropriate
	 * reference in the parent of {@code node} and adjusting the parent
	 * reference of {@code replace}.
	 * 
	 * @param node the node to replace
	 * @param replace the replacement node
	 */
	void replaceNode(BinaryNode<E> node, BinaryNode<E> replace) {
		BinaryNode<E> parent = node.parent;
		if (parent == null) {
			// replacing the root node
			this.setRoot(replace);
		}
		else if (node == parent.left) {
			parent.setLeft(replace);
		} else {
			parent.setRight(replace);
		}
	}

	/**
	 * Removes a node from the tree. The specified node must not be equal to
	 * {@code null}.
	 * 
	 * @param node the node to remove
	 */
	void removeNode(BinaryNode<E> node) {
		if (node.isLeaf()) {
			replaceNode(node, null);   // replace the node with null
		} else if (node.hasOneChild()) {
			if (node.hasLeft()) {
				replaceNode(node, node.left);   // replace node with its left child
			} else {
				replaceNode(node, node.right);   // replace node with its right child
			}
		} else {
			// find predecessor/successor of node that we want to remove
			BinaryNode<E> y = BinarySearchTree.predecessorInSubtree(node);
			
			// copy y.data to node.data
			node.elem = y.elem;

			// remove y
			this.removeNode(y);
		}
	}

	
	
	public static void main(String[] args) {
		BinarySearchTree<String> t = new BinarySearchTree<>();
		t.add("melon");
		t.add("banana");
		t.add("watermelon");
		t.add("grapefruit");
		t.add("cranberry");
		t.add("persimmon");
		t.add("lemon");
		t.add("kumquat");
		t.add("lime");
		t.add("mango");
		t.add("apple");
		t.add("pear");
		t.add("mangosteen");
		System.out.println(Traversals.toString(t));
		
		BinarySearchTree<Integer> u = new BinarySearchTree<>();
		u.add(50);
		u.add(27);
		u.add(73);
		u.add(8);
		u.add(44);
		u.add(83);
		u.add(74);
		u.add(93);
		System.out.println(Traversals.breadthFirst(u));
		System.out.println(Traversals.breadthFirst(u));
		
		System.out.println(Traversals.preorder(u));
		System.out.println(Traversals.depthFirst(u));
	}
}