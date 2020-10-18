package ca.queensu.cs.cisc235.queue;

import java.util.Arrays;

public class FixedArrayQueue<E> implements Queue<E> {

	// the default capacity of the queue
	private static final int DEFAULT_CAPACITY = 16;

	// the array that stores the queue
	private E[] arr;

	// the index of the front of the queue
	private int fIndex;
	
	// the index of the back of the queue
	private int bIndex;

	// the number of elements in the queue
	private int size;
	
	public FixedArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public FixedArrayQueue(int capacity) {
		if (capacity < 0) {
			throw new RuntimeException("negative capacity");
		}
		this.arr = (E[]) new Object[capacity];
		this.fIndex = 0;
		this.bIndex = 0;
		this.size = 0;
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	public int capacity() {
		return this.arr.length;
	}

	private int wrapIndex(int index) {
		return Math.floorMod(index, this.capacity());
	}

	@Override
	public Queue<E> enqueue(E elem) {
		if (this.size == this.capacity()) {
			throw new RuntimeException("size == capacity");
		}
		if (this.isEmpty()) {
			assert this.fIndex == this.bIndex;
			this.arr[this.bIndex] = elem;
		}
		else {
			this.bIndex = this.wrapIndex(this.bIndex + 1);
			this.arr[this.bIndex] = elem;
		}
		this.size++;
		return this;
	}

	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			throw new RuntimeException("dequeued an empty queue");
		}
		E elem = this.arr[this.fIndex];
		
		// null out the value stored in the array
		this.arr[this.fIndex] = null;
		
		this.fIndex = this.wrapIndex(this.fIndex + 1);
		this.size--;
		return elem;
	}

	@Override
	public E front() {
		if (this.isEmpty()) {
			throw new RuntimeException("no front element in an empty queue");
		}
		return this.arr[this.fIndex];
	}

	@Override
	public E back() {
		if (this.isEmpty()) {
			throw new RuntimeException("no back element in an empty queue");
		}
		return this.arr[this.bIndex];
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder("[");
		for (int i = 0; i < this.size - 1; i++) {
			int index = this.wrapIndex(this.fIndex + i);
			b.append(this.arr[index].toString());
			b.append(", ");
		}
		b.append(this.arr[this.bIndex]);
		b.append("]");
		return b.toString();
	}
	
	public static void main(String[] args) {
		Queue<Integer> q = new FixedArrayQueue<>(4);
		q.enqueue(1).
			enqueue(2).
			enqueue(3).
			enqueue(4);
		for (int i = 0; i < 4; i++) {
			System.out.println(q);
			System.out.println("front: " + q.front());
			System.out.println("back : " + q.back());
			System.out.println();
			
			System.out.println("dequeue: " + q.dequeue());
			System.out.println(q);
			System.out.println();
			
			System.out.printf("enqueue(%s)%n", i + 5);
			q.enqueue(i + 5);
		}
		System.out.println(q);
		System.out.println();
		
		while (!q.isEmpty()) {
			System.out.println("dequeue: " + q.dequeue());
			System.out.println(q);
			System.out.println();
		}
	}
}
