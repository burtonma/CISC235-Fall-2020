package ca.queensu.cs.cisc235.stack;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {

	// the initial capacity of the stack
	private static final int DEFAULT_CAPACITY = 16;

	// the array that stores the stack
	private E[] arr;

	// the index of the top of the stack (equal to -1 for an empty stack)
	private int topIndex;

	/**
	 * Initializes this stack to be an empty stack.
	 */
	public ArrayStack() {
		this.arr = (E[]) new Object[ArrayStack.DEFAULT_CAPACITY];
		this.topIndex = -1;
	}

	@Override
	public int size() {
		return this.topIndex + 1;
	}

	@Override
	public Stack<E> push(E elem) {
		// is there capacity for one more element?
		if (this.topIndex < this.arr.length - 1) {
			// increment the top of stack index and insert the element
			this.topIndex++;
			this.arr[this.topIndex] = elem;
		} else {
			E[] newStack = Arrays.copyOf(this.arr, this.arr.length * 2);
			// refer to the new array and push the element onto the stack
			this.arr = newStack;
			this.push(elem);
		}
		return this;
	}

	@Override
	public E pop() {
		// is the stack empty?
		if (this.isEmpty()) {
			throw new RuntimeException("popped an empty stack");
		}
		// get the element at the top of the stack
		E element = this.arr[this.topIndex];
		
		// null out the value stored in the array
		this.arr[this.topIndex] = null;

		// adjust the top of stack index
		this.topIndex--;

		// return the element that was on the top of the stack
		return element;
	}

	@Override
	public E peek() {
		if (this.isEmpty()) {
			throw new RuntimeException("peeked at an empty stack");
		}
		// get the element at the top of the stack
		E element = this.arr[this.topIndex];
		
		return element;
	}

	/**
	 * Compares the specified object with this stack for equality. Returns true if
	 * and only if the specified object is also an {@code ArrayStack}, both stacks
	 * have the same size, and all corresponding pairs of elements in the two stacks
	 * are equal. In other words, two stacks are defined to be equal if they contain
	 * the same elements in the same order.
	 * 
	 * @param obj the object to be compared for equality with this stack
	 * @return true if the specified object is equal to this stack
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ArrayStack<?>)) {
			return false;
		}
		ArrayStack<?> other = (ArrayStack<?>) obj;
		return Arrays.deepEquals(this.arr, other.arr);
	}
	
	/**
	 * Returns a hash code for this stack.
	 * 
	 * @return a hash code for this stack
	 */
	@Override
	public int hashCode() {
		return Arrays.deepHashCode(this.arr);
	}

	/**
	 * Returns a string representation of this stack. The returned string starts
	 * with {@code ListStack:} followed by a tab character followed by the top
	 * element of the stack. The remainder of the string consists of the remaining
	 * elements of the stack each on a new line where each new line begins with a
	 * tab character.
	 *
	 * @return a string representation of this stack
	 */
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder("ListStack:");
		for (int i = this.size() - 1; i > 0; i--) {
			b.append("\t");
			b.append(this.arr[i]);
			b.append('\n');
		}
		if (!this.isEmpty()) {
			b.append("\t");
			b.append(this.arr[0]);
		}
		return b.toString();
	}
	
	
	public static void main(String[] args) {
		Stack<Integer> t = new ArrayStack<>();
		t.push(5).
			push(4).
			push(3).
			push(2).
			push(1);
		System.out.println(t);
		System.out.println();
		
		
		while (!t.isEmpty()) {
			System.out.println("peek   : " + t.peek());
			Integer i = t.pop();
			System.out.println("popped : " + i);
			System.out.println(t);
			System.out.println();
		}
		t.pop();
	}
}
