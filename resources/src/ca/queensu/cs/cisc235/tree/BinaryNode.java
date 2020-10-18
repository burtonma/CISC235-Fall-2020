package ca.queensu.cs.cisc235.tree;

import ca.queensu.cs.cisc235.stack.ArrayStack;
import ca.queensu.cs.cisc235.stack.Stack;

/**
 * Node class for binary trees.
 *
 * @param <E> the element type stored by the node
 */
class BinaryNode<E> {
	E elem;
	BinaryNode<E> parent;
	BinaryNode<E> left;
	BinaryNode<E> right;

	/**
	 * Initialize this node so that it holds a reference to the
	 * specified element and the parent, left, and right child references are null.
	 * 
	 * @param elem the element to store in this node
	 */
	BinaryNode(E elem) {
		this.elem = elem;
		this.parent = null;
		this.left = null;
		this.right = null;
	}

	/**
	 * Initialize this node so that it holds references to the
	 * specified element, parent node, left child node, and right child node.
	 * 
	 * @param elem the element to store in this node
	 * @param parent the parent of this node
	 * @param left the left child of this node
	 * @param right the right child of this node
	 */
	BinaryNode(E elem, BinaryNode<E> parent, BinaryNode<E> left, BinaryNode<E> right) {
		this.elem = elem;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	
	/**
	 * Sets the left child of this node to the specified node and sets the
	 * parent of the specified node to this node if the specified node is not
	 * {@code null}.
	 * 
	 * @param n the left child of this node
	 */
	void setLeft(BinaryNode<E> n) {
		this.left = n;
		if (n != null) {
			n.parent = this;
		}
	}
	
	/**
	 * Sets the right child of this node to the specified node and sets the
	 * parent of the specified node to this node if the specified node is not
	 * {@code null}.
	 * 
	 * @param n the right child of this node
	 */
	void setRight(BinaryNode<E> n) {
		this.right = n;
		if (n != null) {
			n.parent = this;
		}
	}
	
	/**
	 * Returns {@code true} if this node has a left child, {@code false} otherwise.
	 * 
	 * @return {@code true} if this node has a left child, {@code false} otherwise
	 */
	boolean hasLeft() {
		return this.left != null;
	}
	
	/**
	 * Returns {@code true} if this node has a right child, {@code false} otherwise.
	 * 
	 * @return {@code true} if this node has a right child, {@code false} otherwise
	 */
	boolean hasRight() {
		return this.right != null;
	}
	
	/**
	 * Returns {@code true} if this node has a parent, {@code false} otherwise.
	 * 
	 * @return {@code true} if this node has a parent, {@code false} otherwise
	 */
	boolean hasParent() {
		return this.parent != null;
	}
	
	/**
	 * Returns {@code true} if this node is a leaf node, {@code false} otherwise.
	 * A node is a leaf node if it has no children.
	 * 
	 * @return {@code true} if this node is a leaf node, {@code false} otherwise
	 */
	boolean isLeaf() {
		return !(this.hasLeft() || this.hasRight());
	}
	
	/**
	 * Returns {@code true} if this node has exactly one child, {@code false} otherwise.
	 * 
	 * @return {@code true} if this node has exactly one child, {@code false} otherwise
	 */
	boolean hasOneChild() {
		return (this.hasLeft() && !this.hasRight()) || (!this.hasLeft() && this.hasRight());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BinaryNode<?>)) {
			return false;
		}
		BinaryNode<?> other = (BinaryNode<?>) obj;
		BinaryNode<E> root1 = this;
		BinaryNode<?> root2 = other;
		Stack<BinaryNode<E>> st1 = new ArrayStack<>();
		Stack<BinaryNode<?>> st2 = new ArrayStack<>();
		st1.push(root1);
		st2.push(root2);
		while (!st1.isEmpty() && !st2.isEmpty()) {
			BinaryNode<E> n1 = st1.pop();
			BinaryNode<?> n2 = st2.pop();
			if (!n1.elem.equals(n2.elem)) {
				return false;
			}
			if (n1.hasLeft() && !n2.hasLeft()) {
				return false;
			}
			if (n2.hasLeft() && !n1.hasLeft()) {
				return false;
			}
			if (n1.hasRight() && !n2.hasRight()) {
				return false;
			}
			if (n2.hasRight() && !n1.hasRight()) {
				return false;
			}
			if (n1.hasLeft()) {
				st1.push(n1.left);
				st2.push(n2.left);
			}
			if (n1.hasRight()) {
				st1.push(n1.right);
				st2.push(n2.right);
			}
		}
		if (st1.isEmpty() && !st2.isEmpty()) {
			return false;
		}
		if (st2.isEmpty() && !st1.isEmpty()) {
			return false;
		}
		return true;
	}
	
	@Override 
	public String toString() {
		return Traversals.toString("", this, false, true);
	}
}
