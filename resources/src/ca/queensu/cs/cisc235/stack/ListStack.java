package ca.queensu.cs.cisc235.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@code List}-based implementation of a stack. This class imposes no
 * restrictions on the number of elements and allows {@code null} elements.
 *
 * @param <E> the type of elements in this stack
 */
public class ListStack<E> implements Stack<E> {

	private List<E> list;

	/**
	 * Initializes this stack to the empty stack.
	 */
	public ListStack() {
		this.list = new ArrayList<E>();
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public Stack<E> push(E elem) {
		this.list.add(elem);
		return this;
	}

	@Override
	public E pop() {
		if (this.isEmpty()) {
			throw new RuntimeException("popped an empty stack");
		}
		return this.list.remove(this.list.size() - 1);
	}

	@Override
	public E peek() {
		if (this.isEmpty()) {
			throw new RuntimeException("peeked at an empty stack");
		}
		return this.list.get(this.list.size() - 1);
	}

	/**
	 * Compares the specified object with this stack for equality. Returns true if
	 * and only if the specified object is also a {@code ListStack}, both stacks
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
		if (!(obj instanceof ListStack<?>)) {
			return false;
		}
		ListStack<?> other = (ListStack<?>) obj;
		return this.list.equals(other.list);
	}

	/**
	 * Returns a hash code for this stack.
	 * 
	 * @return a hash code for this stack
	 */
	@Override
	public int hashCode() {
		return this.list.hashCode();
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
		StringBuilder b = new StringBuilder("BadStack:");
		if (!this.isEmpty()) {
			for (int i = this.size() - 1; i >= 0; i--) {
				b.append('\n');
				b.append(this.list.get(i));
			}
		}
		return b.toString();
	}

	
	public static void main(String[] args) {
		Stack<Integer> t = new ListStack<>();
		t.push(5).
			push(4).
			push(3).
			push(2).
			push(1);
		
		Stack<Integer> t2 = new ListStack<>();
		t2.push(5).
			push(4).
			push(3).
			push(2).
			push(1);
		System.out.println(t.equals(t2));
		
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
