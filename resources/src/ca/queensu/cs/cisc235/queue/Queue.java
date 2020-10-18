package ca.queensu.cs.cisc235.queue;


public interface Queue<E> {

	/**
	 * Returns the number of elements in this queue.
	 * 
	 * @return the number of elements in this queue
	 */
	public int size();

	/**
	 * Returns {@code true} if this queue contains no elements. The default
	 * implementation simply returns {@code size() == 0}.
	 * 
	 * @return true if this queue contains no elements
	 */
	default boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Addes the specified element to the back of this queue.
	 * 
	 * @param elem the element to be added to the back of this queue
	 * @return a reference to this queue
	 */
	public Queue<E> enqueue(E elem);

	/**
	 * Removes the element from the front this queue and returns the element.
	 * 
	 * @return the front element of this queue
	 */
	public E dequeue();

	/**
	 * Looks at the element at the front this queue without removing it from the
	 * queue.
	 * 
	 * @return the element at the front of this queue
	 */
	public E front();
	
	/**
	 * Looks at the element at the back this queue without removing it from the
	 * queue.
	 * 
	 * @return the element at the back of this queue
	 */
	public E back();
}
