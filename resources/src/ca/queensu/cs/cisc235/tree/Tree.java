package ca.queensu.cs.cisc235.tree;

public interface Tree<E> {

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size();
	
	/**
	 * Returns {@code true} if this tree is empty.
	 * 
	 * @return true if this tree is empty 
	 */
	default public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Adds an element to this tree. Some tree types may not allow {@code null}
	 * elements.
	 * 
	 * @param elem the element to add
	 * @throws NullPointerException if the specified element is null and this tree
	 *                              does not permit null elements
	 */
	public void add(E elem);

	/**
	 * Removes one occurrence of the specified element from this tree if it is
	 * present (optional operation). More specifically, one element {@code e} will
	 * be removed if {@code e.equals(o)} is {@code true}. If {@code null} elements
	 * are allowed and {@code o} is {@code null} then one element {@code e} will be
	 * removed if {@code e == null} is {@code true}.
	 * 
	 * @param elem the element to remove
	 * @throws NullPointerException          if the specified element is null and
	 *                                       this tree does not permit null elements
	 * @throws UnsupportedOperationException if the remove operation is not
	 *                                       supported
	 */
	public boolean remove(E elem);

	/**
	 * Returns {@code true} if this tree contains the specified element. More
	 * specifically, {@code true} is returned if an element {@code e} is in the tree
	 * and {@code e.equals(o)} is {@code true}. If {@code null} elements are allowed
	 * and {@code o} is {@code null} then {@code true} is returned if a {@code null}
	 * element is in the tree.
	 * 
	 * @param elem the element to search for
	 * @return true if the specified element is in this tree
	 * @throws NullPointerException          if the specified element is null and
	 *                                       this tree does not permit null elements
	 */
	public boolean contains(E elem);
}
