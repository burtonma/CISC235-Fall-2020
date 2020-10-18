package ca.queensu.cs.cisc235.list;

import java.util.Arrays;

/**
 * A minimal implementation of a fixed-size list of strings backed by an array.
 *
 */
public class FixedIntegerArrayList {

	/**
	 * The array of strings in the list.
	 */
	private Integer[] arr;
	
	/**
	 * Index of the last element in the list; equal to -1 for an empty list.
	 */
	private int back;      
	
	/**
	 * Initializes this list to an empty list of capacity 16.
	 */
	public FixedIntegerArrayList() {
		this.arr = new Integer[16];
		this.back = -1;
	}
	
	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list
	 */
	public int size() {
		return this.back + 1;
	}
	
	/**
	 * Test if an index is valid for this list.
	 * @param index
	 */
	private void checkIndex(int index) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException(index + " out of bounds");
		}
	}
	
	/**
	 * Returns the element at the specified index of this list.
	 * 
	 * @param index the index of the element to get
	 * @return the element at the specified index of this list
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public Integer get(int index) {
		this.checkIndex(index);
		return this.arr[index];
	}
	
	/**
	 * Sets the element at the specified index to the specified element in this
	 * list returning the old element.
	 *   
	 * @param index the index of the element to set
	 * @param elem the new element
	 * @return the old element
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public Integer set(int index, Integer elem) {
		this.checkIndex(index);
		Integer old = this.arr[index];
		this.arr[index] = elem;
		return old;
	}
	
	/**
	 * Adds the specified element to the end of this list.
	 * 
	 * @param elem the element to add
	 * @throws RuntimeException if the list is full
	 */
	public void add(Integer elem) {
		if (this.size() == 16) {
			throw new RuntimeException("list is full");
		}
		// adjust back
		this.back++;
		this.arr[this.back] = elem;
	}
	
	/**
	 * Removes the element at the specified index of this list returning 
	 * the element that was removed. All elements after the specified index
	 * are shifted one position to the front of the list.
	 * 
	 * @param index the index of the element to remove
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public Integer remove(int index) {
		this.checkIndex(index);
		Integer old = this.arr[index];
		
		// shift elements from (index + 1) down one index
		for (int i = index + 1; i <= this.back; i++) {
			this.arr[i - 1] = this.arr[i]; 
		}
		
		// null out old back element, otherwise it can't be garbage collected
		this.arr[this.back] = null;
		
		// adjust back
		this.back--;
		
		return old;
	}
	
	
	
	public static void main(String[] args) {
		FixedIntegerArrayList t = new FixedIntegerArrayList();
		for (char c = 'a'; c < 'a' + 16; c++) {
			t.add(c + 0);
		}
		System.out.println(Arrays.toString(t.arr));
		
		for (int i = 0; i < 16; i++) {
			System.out.println(t.get(i));
		}
		
		for (int i = 0; i < 16; i++) {
			Integer s = t.get(i);
			t.set(i, s + s);
		}
		System.out.println(Arrays.toString(t.arr));
		
		for (int i = 0; i < 16; i++) {
			t.remove(0);
			System.out.println(Arrays.toString(t.arr));
		}
		
		// force an exception
		t.set(0, 1000);
	}
}
