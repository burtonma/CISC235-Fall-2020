package ca.queensu.cs.cisc235.stack;


public class LinkedStack<E> implements Stack<E> {

	private static class Node<E> {
		private E elem;
		private Node<E> next;
		
		private Node(E elem, Node<E> next) {
			this.elem = elem;
			this.next = next;
		}
	}
	
	private Node<E> top;
	private int size;
	
	public LinkedStack() {
		this.top = null;
		this.size = 0;
	}
	
	@Override
	public int size() {
		assert this.size >= 0;
		return this.size;
	}

	@Override
	public Stack<E> push(E elem) {
		Node<E> oldTop = this.top;
		top = new Node<>(elem, oldTop);
		this.size++;
		return this;
	}

	@Override
	public E pop() {
		if (this.isEmpty()) {
			throw new RuntimeException("popped an empty stack");
		}
		assert this.top != null;
		
		Node<E> oldTop = this.top;
		top = oldTop.next;
		this.size--;
		return oldTop.elem;
	}

	@Override
	public E peek() {
		if (this.isEmpty()) {
			throw new RuntimeException("peeked at an empty stack");
		}
		assert this.top != null;
		
		return top.elem;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof LinkedStack<?>)) {
			return false;
		}
		LinkedStack<?> other = (LinkedStack<?>) obj;
		if (this.size() != other.size()) {
			return false;
		}
		// compare the corresponding elements node by node
		Node<E> node = this.top;
		Node<?> otherNode = other.top;
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
		Node<E> node = this.top;
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
		StringBuilder b = new StringBuilder("LinkedStack:");
		if (!this.isEmpty()) {
			Node<E> node = this.top;
			
			for (int i = 0; i < this.size(); i++) {
				assert node.elem != null;
				b.append('\n');
				b.append("\t");
				b.append(node.elem.toString());
				node = node.next;
			}
		}
		return b.toString();
	}
	
	public static void main(String[] args) {
		Stack<Integer> t = new LinkedStack<>();
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
		
		// force an exception
		t.pop();
	}
}
