package ca.queensu.cs.cisc235.stack;

import java.util.ArrayList;

public class BadStack<E> extends ArrayList<E> implements Stack<E> {

	@Override
	public Stack<E> push(E elem) {
		this.add(elem);
		return this;
	}

	@Override
	public E pop() {
		if (this.isEmpty()) {
			throw new RuntimeException("popped an empty stack");
		}
		return this.remove(this.size() - 1);
	}

	@Override
	public E peek() {
		if (this.isEmpty()) {
			throw new RuntimeException("peeked at an empty stack");
		}
		return this.get(this.size() - 1);
	}

	/**
	 * Returns a string representation of this stack. The returned string starts
	 * with {@code BadStack:} followed by a tab character followed by the top
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
				b.append(this.get(i));
			}
		}
		return b.toString();
	}
	
	
	public static void main(String[] args) {
		Stack<Integer> t = new BadStack<>();
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
		
		// pop an empty stack
		t.pop();
	}
}
