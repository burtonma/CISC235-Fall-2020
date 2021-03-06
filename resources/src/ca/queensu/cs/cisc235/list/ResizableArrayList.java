package ca.queensu.cs.cisc235.list;

import java.util.Arrays;
import java.util.Objects;

/**
 * A minimal implementation of a fixed-size list of elements backed by an array.
 *
 * @param <E> the element type of this list
 */
public class ResizableArrayList<E> {

	/**
	 * The array of strings in the list.
	 */
	private E[] arr;
	
	/**
	 * Index of the last element in the list; equal to -1 for an empty list.
	 */
	private int back;      
	
	/**
	 * Initializes this list to an empty list of capacity 16.
	 */
	public ResizableArrayList() {
		this.arr = (E[]) (new Object[16]);
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
	public E get(int index) {
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
	public E set(int index, E elem) {
		this.checkIndex(index);
		E old = this.arr[index];
		this.arr[index] = elem;
		return old;
	}
	
	/**
	 * Adds the specified element to the end of this list.
	 * 
	 * @param elem the element to add
	 */
	public void add(E elem) {
		if (this.size() == this.arr.length) {
			E[] bigger = Arrays.copyOf(this.arr, 2 * this.size());
			this.arr = bigger;
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
	public E remove(int index) {
		this.checkIndex(index);
		E old = this.arr[index];
		
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
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(arr);
		result = prime * result + Objects.hash(back);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ResizableArrayList<?>)) {
			return false;
		}
		ResizableArrayList<?> other = (ResizableArrayList<?>) obj;
		return Arrays.deepEquals(arr, other.arr) && back == other.back;
	}

	public static void main(String[] args) {
		ResizableArrayList<String> t = new ResizableArrayList<>();
		for (char c = 'a'; c < 'a' + 32; c++) {
			t.add("" + c);
		}
		System.out.println(Arrays.toString(t.arr));
		
		ResizableArrayList<Integer> u = new ResizableArrayList<>();
		for (char c = 'a'; c < 'a' + 16; c++) {
			u.add(c + 0);
		}
		System.out.println(t.equals(u));
		
		for (int i = 0; i < 16; i++) {
			System.out.println(t.get(i));
		}
		
		for (int i = 0; i < 16; i++) {
			String s = t.get(i);
			t.set(i, s + s);
		}
		System.out.println(Arrays.toString(t.arr));
		
		for (int i = 0; i < 16; i++) {
			t.remove(0);
			System.out.println(Arrays.toString(t.arr));
		}
		
		// force an exception
		t.set(0, "");
	}
}
