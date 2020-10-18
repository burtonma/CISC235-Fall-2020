package ca.queensu.cs.cisc235.tree;

/**
 * Base class for node-based binary tree structures to expose the root node
 * for other classes in the package.
 *
 * @param <E> the element type stored in this tree
 */
public abstract class BinaryTree<E> implements Tree<E> {

	/**
	 * The number of elements in this tree.
	 */
	protected int size;
	
	
	/**
	 * The root node of this tree. The root node has a {@code null} parent. 
	 */
	protected BinaryNode<E> root;
	
	/**
	 * Initialize an empty tree. The root node is {@code null} and the size
	 * of the tree is 0.
	 */
	protected BinaryTree() {
		this.root = null;
		this.size = 0;
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Returns a reference to the root node.
	 * 
	 * @return a reference to the root node
	 */
	protected BinaryNode<E> root() {
		return this.root;
	}
	
	/**
	 * Sets the root node of this tree to the specified node. Sets the
	 * parent of the specified node to {@code null} if the root is
	 * not equal to {@code null}.
	 * 
	 * @param root the root node of this tree
	 */
	protected void setRoot(BinaryNode<E> root) {
		this.root = root;
		if (root != null) {
			this.root.parent = null;
		}
	}

}
