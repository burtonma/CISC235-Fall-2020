package ca.queensu.cs.cisc235.stack;

/**
 * The {@code Stack} interface represents a last-in-first-out (LIFO) stack of
 * elements. In addition to the usual push and pop methods, this interface
 * allows the user to get the number of elements in a stack, get the top element
 * of the stack without popping the stack, and to get a string representation of
 * the stack.
 * 
 * <p>
 * Some stack implementations may restrict the number of elements in a stack.
 * Such implementations should throw an exception if an element is pushed
 * on to a full stack.
 * 
 * @param <E> the type of elements in the stack
 */
public interface Stack<E> {

	/**
	 * Returns the number of elements in this stack.
	 * 
	 * @return the number of elements in this stack
	 */
	public int size();

	/**
	 * Returns {@code true} if this stack contains no elements. The default
	 * implementation simply returns {@code size() == 0}.
	 * 
	 * @return true if this stack contains no elements
	 */
	public default boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Pushes the specified element on to the top of this stack.
	 * 
	 * @param elem the element to be pushed on to the top of this stack
	 * @return a reference to this stack
	 * @throws RuntimeException if the stack has fixed size and is full
	 */
	public Stack<E> push(E elem);

	/**
	 * Removes the element on the top of this stack and returns the element.
	 * 
	 * @return the top element of this stack
	 * @throws RuntimeException if the stack is empty
	 */
	public E pop();

	/**
	 * Looks at the element on the top of this stack without removing it from the
	 * stack.
	 * 
	 * @return the element on the top of this stack
	 * @throws RuntimeException if the stack is empty
	 */
	public E peek();
}
