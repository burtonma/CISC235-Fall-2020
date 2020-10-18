package ca.queensu.cs.cisc235.queue;

import java.util.ArrayList;

public class BadQueue<E> extends ArrayList<E> implements Queue<E> {

	@Override
	public Queue<E> enqueue(E elem) {
		this.add(elem);
		return this;
	}

	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			throw new RuntimeException("dequeued an empty queue");
		}
		E elem = this.remove(0);
		return elem;
	}

	@Override
	public E front() {
		if (this.isEmpty()) {
			throw new RuntimeException("no front element in an empty queue");
		}
		return this.get(0);
	}

	@Override
	public E back() {
		if (this.isEmpty()) {
			throw new RuntimeException("no back element in an empty queue");
		}
		return this.get(this.size() - 1);
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
	}
}
