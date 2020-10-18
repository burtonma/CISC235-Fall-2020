package ca.queensu.cs.cisc235.list;

import java.util.NoSuchElementException;

/**
 * A node-based linked list. The list allows elements to be equal to {@code null}.
 */
public class LinkedList<E> {

	static class Node<E> {
		E elem;
		Node<E> next;

		/**
		 * Initializes a node to refer to the specified element and node.
		 * 
		 * @param c a character
		 */
		public Node(E elem, Node<E> node) {
			this.elem = elem;
			this.next = node;
		}
	}

	/**
	 * The number of elements in the linked list.
	 */
	private int size;

	/**
	 * The first node of the linked list; will be <code>null</code> for an empty
	 * list.
	 */
	private Node<E> head;

	/**
	 * The last node of the linked list; will be <code>null</code> for an empty
	 * list.
	 */
	private Node<E> tail;

	
	/**
	 * Returns the head node of this list.
	 * 
	 * @return the head node of this list
	 */
	Node<E> head() {
		return this.head;
	}
	
	/**
	 * Returns the tail node of this list.
	 * 
	 * @return the tail node of this list
	 */
	Node<E> tail() {
		return this.tail;
	}
	
	
	/**
	 * Initialize an empty list.
	 */
	public LinkedList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	/**
	 * Get the number of elements in the list.
	 * 
	 * @return the number of elements in the list.
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Adds the given element to the end of the list.
	 * 
	 * @param elem The character to add
	 */
	public void add(E elem) {
		if (this.size == 0) {
			this.head = new Node<>(elem, null);
			this.tail = this.head;
		} else {
			Node<E> n = new Node<>(elem, null);
			this.tail.next = n;
			this.tail = n;
		}
		this.size++;
	}

	/**
	 * Validates the specified index.
	 * 
	 * @param index an index
	 * @throws IndexOutOfBoundsException if
	 *                                   {@code index < 0 || index >= this.size()}
	 */
	void validate(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("index out of bounds: " + index);
		}
	}

	/**
	 * Returns the node at the specified index. Assumes that the index is valid for
	 * this list to avoid re-validating the index.
	 * 
	 * @param index a valid index for this list
	 * @return the node at the specified index
	 */
	Node<E> moveTo(int index) {
		Node<E> n = this.head;
		for (int i = 0; i < index; i++) {
			n = n.next;
		}
		return n;
	}

	/**
	 * Returns the element at the specified position in the list.
	 * 
	 * @param index index of the element to return
	 * @return the element at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of the range
	 *                                   {@code (index < 0 || index >= size())}
	 */
	public E get(int index) {
		this.validate(index);
		Node<E> n = this.moveTo(index);
		return n.elem;
	}

	/**
	 * Sets the element at the specified position in the list.
	 * 
	 * @param index index of the element to set
	 * @param c     new value of element
	 * @throws IndexOutOfBoundsException if the index is out of the range
	 *                                   {@code (index < 0 || index >= size())}
	 */
	public E set(int index, E elem) {
		this.validate(index);
		Node<E> n = this.moveTo(index);
		E old = n.elem;
		n.elem = elem;
		return old;
	}

	/**
	 * Adds an element to the front of this list.
	 * 
	 * @param elem an element to add
	 */
	public void addFront(E elem) {
		Node<E> toAdd = new Node<>(elem, null);
		toAdd.next = this.head;
		this.head = toAdd;
		this.size++;
	}

	/**
	 * Adds an element at the specified index of this list. Shifts the element
	 * currently at that position (if any) and any subsequent elements to the right.
	 * 
	 * @param index the index at which to add the element
	 * @param elem  the element to add
	 * @throws IndexOutOfBoundsException if the index is out of the range
	 *                                   {@code (index < 0 || index > size())}
	 */
	public void add(int index, E elem) {
		if (index == this.size) {
			// add to end
			this.add(elem);
		}
		// must validate after the previous if statement otherwise
		// an exception will be thrown because this.size is not a valid index
		this.validate(index);

		if (index == 0) {
			this.addFront(elem);
		} else {
			Node<E> n = this.moveTo(index - 1);
			Node<E> toAdd = new Node<>(elem, null);
			Node<E> after = n.next;
			n.next = toAdd;
			toAdd.next = after;
			this.size++;
		}
	}

	/**
	 * Removes the first element of this list and returns the element.
	 * 
	 * @return the removed element
	 * @throws NoSuchElementException if the list is empty
	 */
	public E removeFront() {
		if (this.size == 0) {
			throw new NoSuchElementException("list is empty");
		}
		Node<E> toRemove = this.head;
		this.head = toRemove.next;
		// special case of removing from a list of size 1
		if (this.size == 0) {
			this.tail = null;
		}
		this.size--;
		return toRemove.elem;
	}

	/**
	 * Removes the element at the specified index of this list, shifts the any
	 * subsequent elements to the left (subtracts one to their indices), and returns
	 * a reference to the removed element.
	 * 
	 * @param index the index of the element to remove
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the index is out of the range
	 *                                   {@code (index < 0 || index >= size())}
	 */
	public E remove(int index) {
		if (index == 0) {
			return this.removeFront();
		}
		this.validate(index);
		Node<E> n = this.moveTo(index - 1);
		Node<E> toRemove = n.next;
		Node<E> after = toRemove.next;
		toRemove.next = null;
		n.next = after;
		// special case where the last element was removed
		if (index == this.size - 1) {
			this.tail = n;
		}
		this.size--;
		return toRemove.elem;
	}
	
	public boolean removeAll(E elem) {
		if (this.size == 0) {
			throw new NoSuchElementException("cannot remove from an empty list");
		}
		boolean result = false;
		// do we need to remove the first element?
		Node<E> n = this.head;
		boolean removeHead = n.elem.equals(elem);
			
		Node<E> prev = n;
		n = n.next;
		while (n != null) {
			// do we need to remove n?
			if (n.elem.equals(elem)) {
				result = true;
				Node<E> after = n.next;
				n.next = null;
				prev.next = after;
				this.size--;
				if (after == null) {
					// we removed the tail node
					this.tail = prev;
				}
				// don't advance prev, set n to after
				n = after;
			}
			else {
				prev = n;
				n = n.next;
			}
		}
		if (removeHead) {
			this.removeFront();
		}
		return result || removeHead;
	}

	/**
	 * Compares this list to the specified object for equality. Returns {@code true}
	 * if and only if the specified object is a {@code LinkedList}, both lists have
	 * the same size, and all corresponding pairs of elements in the two lists are
	 * equal. (Two elements {@code e1} and {@code e2} are equal if
	 * {@code (e1==null ? e2==null : e1.equals(e2))}.) In other words, two lists are
	 * defined to be equal if they contain the same elements in the same order.
	 * 
	 * @param obj an object to compare to this list
	 * @return true if the specified object is equal to this list
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof LinkedList<?>)) {
			return false;
		}
		LinkedList<?> other = (LinkedList<?>) obj;
		if (this.size != other.size) {
			return false;
		}
		Node<E> n = this.head;
		Node<?> o = other.head;
		while (n != null) {
			if (n.elem == null && o.elem != null) {
				return false;
			}
			if (!n.elem.equals(o.elem)) {
				return false;
			}
			n = n.next;
			o = o.next;
		}
		return true;
	}
	
	/**
	 * Returns a hash code for this list. The returned hash code is equal to that
	 * specified by {@code java.util.List}.
	 * 
	 * @return a hash code for this list
	 */
	@Override
	public int hashCode() {
		int result = 1;
		Node<E> n = this.head;
		while (n != null) {
			int c = n.elem == null ? 0 : n.elem.hashCode();
			result = 31 * result + c;
			n = n.next;
		}
		return result;
	}

	/**
	 * Returns a string representation of the list. The string representation
	 * consists of a list of the collection's elements enclosed in square brackets
	 * ("[]"). Adjacent elements are separated by the characters ", " (comma and
	 * space).
	 * 
	 * @return a string representation of this list
	 */
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append('[');
		Node<E> n = this.head;
		if (this.size > 0) {
			b.append(n.elem);
			while (n.next != null) {
				n = n.next;
				b.append(", ");
				b.append(n.elem);
			}
		}
		b.append(']');
		return b.toString();
	}

	public static void main(String[] args) {
		
	}
}
