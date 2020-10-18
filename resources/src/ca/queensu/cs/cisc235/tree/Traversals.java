package ca.queensu.cs.cisc235.tree;

import java.util.ArrayList;
import java.util.List;

import ca.queensu.cs.cisc235.queue.Queue;
import ca.queensu.cs.cisc235.stack.ArrayStack;
import ca.queensu.cs.cisc235.stack.Stack;
import ca.queensu.cs.cisc235.queue.LinkedQueue;


public class Traversals {

	/**
	 * Inorder traversal of a binary tree.
	 * 
	 * @param <E> the type of the elements of the tree
	 * @param t a binary tree
	 * @return a list of the elements of the tree 
	 */
	public static <E> List<E> inorder(BinaryTree<E> t) {
		if (t.isEmpty()) {
			return new ArrayList<>();
		}
		return Traversals.inorder(t.root());
	}

	private static <E> List<E> inorder(BinaryNode<E> n) {
		List<E> result = new ArrayList<>();
		if (n == null) {
			return result;
		}

		List<E> goLeft = Traversals.inorder(n.left);
		List<E> goRight = Traversals.inorder(n.right);
		result.addAll(goLeft);
		result.add(n.elem);
		result.addAll(goRight);
		return result;
	}

	/**
	 * Preorder traversal of a binary tree.
	 * 
	 * @param <E> the type of the elements of the tree
	 * @param t a binary tree
	 * @return a list of the elements of the tree 
	 */
	public static <E> List<E> preorder(BinaryTree<E> t) {
		if (t.isEmpty()) {
			return new ArrayList<>();
		}
		return Traversals.preorder(t.root());
	}

	private static <E> List<E> preorder(BinaryNode<E> n) {
		List<E> result = new ArrayList<>();
		if (n == null) {
			return result;
		}

		result.add(n.elem);
		List<E> goLeft = Traversals.preorder(n.left);
		List<E> goRight = Traversals.preorder(n.right);

		result.addAll(goLeft);
		result.addAll(goRight);

		return result;
	}

	
	/**
	 * Postorder traversal of a binary tree.
	 * 
	 * @param <E> the type of the elements of the tree
	 * @param t a binary tree
	 * @return a list of the elements of the tree 
	 */
	public static <E> List<E> postorder(BinaryTree<E> t) {
		if (t.isEmpty()) {
			return new ArrayList<>();
		}
		return Traversals.postorder(t.root());
	}

	private static <E> List<E> postorder(BinaryNode<E> n) {
		List<E> result = new ArrayList<>();
		if (n == null) {
			return result;
		}

		List<E> goLeft = Traversals.postorder(n.left);
		List<E> goRight = Traversals.postorder(n.right);

		result.addAll(goLeft);
		result.addAll(goRight);
		result.add(n.elem);

		return result;
	}
	
	/**
	 * Depth first traversal of a binary tree using a stack.
	 * Produces an identical traversal as preorder traversal.
	 * 
	 * @param <E> the type of the elements of the tree
	 * @param t a binary tree
	 * @return a list of the elements of the tree 
	 */
	public static <E> List<E> depthFirst(BinaryTree<E> t) {
		List<E> result = new ArrayList<>();
		if (t.isEmpty()) {
			return result;
		}
		BinaryNode<E> root = t.root();
		Stack<BinaryNode<E>> st = new ArrayStack<>();
		st.push(root);
		while (!st.isEmpty()) {
			BinaryNode<E> n = st.pop();
			result.add(n.elem);
			if (n.hasRight()) {
				st.push(n.right);
			}
			if (n.hasLeft()) {
				st.push(n.left);
			}
		}
		return result;
	}
	
	
	/**
	 * Left to right breadth first traversal of a binary tree.
	 * 
	 * @param <E> the type of the elements of the tree
	 * @param t a binary tree
	 * @return a list of the elements of the tree 
	 */
	public static <E> List<E> breadthFirst(BinaryTree<E> t) {
		List<E> result = new ArrayList<>();
		if (t.isEmpty()) {
			return result;
		}
		BinaryNode<E> root = t.root();
		Queue<BinaryNode<E>> q = new LinkedQueue<>();
		q.enqueue(root);
		while (!q.isEmpty()) {
			BinaryNode<E> n = q.dequeue();
			result.add(n.elem);
			if (n.hasLeft()) {
				q.enqueue(n.left);
			}
			if (n.hasRight()) {
				q.enqueue(n.right);
			}
		}

		return result;
	}
	
	
	/**
	 * Returns the string representation of a binary tree. 
	 * 
	 * @param <E>
	 * @param t
	 * @return
	 */
	public static <E> String toString(BinaryTree<E> t) {
		if (t.isEmpty()) {
			return "";
		}
		return toString("", t.root(), false, true);
	}
	
	
	static <E> String toString(String prefix, BinaryNode<E> n, boolean isLeft, boolean isRoot) {
		StringBuilder b = new StringBuilder();
		if (n != null) {
			if (isRoot) {
				b.append(n.elem);
			}
			else {
				b.append(prefix + (isLeft ? "L-- " : "R-- ") + n.elem);
			}
            b.append('\n');
            b.append(toString(prefix + (isLeft ? "|   " : "    "), n.left, true, false));
            b.append(toString(prefix + (isLeft ? "|   " : "    "), n.right, false, false));
        }
		return b.toString();
	}
	
}
