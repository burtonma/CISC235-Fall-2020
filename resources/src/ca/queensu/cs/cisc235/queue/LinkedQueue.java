package ca.queensu.cs.cisc235.queue;


public class LinkedQueue<E> implements Queue<E> {

	private static class Node<E> {
		private E elem;
		private Node<E> next;
		
		private Node(E elem, Node<E> next) {
			this.elem = elem;
			this.next = next;
		}
	}
	
	private Node<E> front;
	private Node<E> back;
	private int size;
	
	public LinkedQueue() {
		this.front = null;
		this.back = null;
		this.size = 0;
	}
	
	@Override
	public int size() {
		return this.size;
	}

	
	private Queue<E> enqueueEmpty(E elem) {
		// make a new node to hold the element
		Node<E> node = new Node<>(elem, null);
		this.front = node;
		this.back = node;
		this.size = 1;
		return this;
	}
	
	@Override
	public Queue<E> enqueue(E elem) {
		if (this.isEmpty()) {
			return this.enqueueEmpty(elem);
		}
		// make a new node to hold the element
		Node<E> node = new Node<>(elem, null);
		
		// link this.back to the new node
		this.back.next = node;
		
		// update this.back to refer to the new back of the queue
		this.back = node;
		
		this.size++;
		return this;
	}

	private E dequeueToEmpty() {
		E elem = this.front();
		this.front = null;
		this.back = null;
		this.size = 0;
		return elem;
	}
	
	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			throw new RuntimeException("dequeued an empty queue");
		}
		if (this.size == 1) {
			return this.dequeueToEmpty();
		}
		// get front element
		E elem = this.front();
		
		// move front node to next node
		this.front = this.front.next;
		
		this.size--;
		return elem;
	}

	@Override
	public E front() {
		if (this.isEmpty()) {
			throw new RuntimeException("no front element in an empty queue");
		}
		return this.front.elem;
	}

	@Override
	public E back() {
		if (this.isEmpty()) {
			throw new RuntimeException("no back element in an empty queue");
		}
		return this.back.elem;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof LinkedQueue<?>)) {
			return false;
		}
		LinkedQueue<?> other = (LinkedQueue<?>) obj;
		if (this.size() != other.size()) {
			return false;
		}
		// compare the corresponding elements node by node
		Node<E> node = this.front;
		Node<?> otherNode = other.front;
		while (node != null) {
			assert otherNode != null;
			
			if (!node.elem.equals(otherNode.elem)) {
				return false;
			}
			node = node.next;
			otherNode = otherNode.next;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		if (this.isEmpty()) {
			return 0;
		}
		Node<E> node = this.front;
		assert node.elem != null;
		
		int result = node.elem.hashCode();
		// incorporate the hash code for the remaining elements
		for (int i = 1; i < this.size(); i++) {
			assert node.next != null;
			
			node = node.next;
			assert node.elem != null;
			
			int c = node.elem.hashCode();
			result = 31 * result + c;
		}
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder("[");
		if (!this.isEmpty()) {
			Node<E> node = this.front;
			
			for (int i = 0; i < this.size() - 1; i++) {
				assert node.elem != null;
				
				b.append(node.elem.toString());
				b.append(", ");
				
				node = node.next;
				assert node != null;
			}
			b.append(node.elem.toString());
		}
		b.append("]");
		return b.toString();
	}
	
	public static void main(String[] args) {
		Queue<Integer> q = new LinkedQueue<>();
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
