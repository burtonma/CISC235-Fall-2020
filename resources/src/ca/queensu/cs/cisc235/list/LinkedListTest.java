package ca.queensu.cs.cisc235.list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LinkedListTest {
	
	static <E> void testSequenceEquals(List<E> exp, LinkedList<E> actual) {
		LinkedList.Node<E> n = actual.head();
		for (E elem : exp) {
			assertNotNull(n);
			E a = n.elem;
			assertEquals(elem, a);
			n = n.next;
		}
	}

	@Test
	void test_add() {
		List<String> exp = new ArrayList<>();
		LinkedList<String> t = new LinkedList<>();
		
		for (char c = 'A'; c < 'Z'; c += 1) {
			exp.add("" + c);
			t.add("" + c);
			assertEquals(c - 'A' + 1, t.size());
			testSequenceEquals(exp, t);
		}
	}

	@Test
	void test_get() {
		List<String> exp = new ArrayList<>();
		LinkedList<String> t = new LinkedList<>();
		
		for (char c = 'A'; c < 'Z'; c += 1) {
			exp.add("" + c);
			t.add("" + c);
		}
		for (int i = 0; i < exp.size(); i++) {
			String got = t.get(i);
			assertEquals(exp.get(i), got);
		}
	}

	
	@Test
	void test_set() {
		List<String> exp = new ArrayList<>();
		LinkedList<String> t = new LinkedList<>();
		
		for (char c = 'A'; c < 'Z'; c += 1) {
			exp.add("" + c);
			t.add("" + c);
		}
		for (int i = 0; i < exp.size(); i++) {
			String sexp = exp.get(i) + i;
			t.set(i, sexp);
			String got = t.get(i);
			assertEquals(sexp, got);
		}
	}
	
	@Test
	void test_addFront() {
		List<String> exp = new ArrayList<>();
		LinkedList<String> t = new LinkedList<>();
		
		for (char c = 'A'; c < 'Z'; c += 1) {
			exp.add(0, "" + c);
			t.addFront("" + c);
			assertEquals(c - 'A' + 1, t.size());
			testSequenceEquals(exp, t);
		}
	}
	
	
	@Test
	void test_addAtIndex() {
		List<String> start = Arrays.asList("A", "B", "C", "D");
		for (int i = 0; i <= start.size(); i++) {
			List<String> exp = new ArrayList<>(start);
			exp.add(i, "Z");
			
			LinkedList<String> got = new LinkedList<>();
			for (String s : start) {
				got.add(s);
			}
			got.add(i, "Z");
			testSequenceEquals(exp, got);
		}
	}
	
	@Test
	void test_removeFront() {
		List<String> exp = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
		LinkedList<String> got = new LinkedList<>();
		for (String s : exp) {
			got.add(s);
		}
		final int n = exp.size();
		for (int i = 0; i < n; i++) {
			String sExp = exp.remove(0);
			String sGot = got.removeFront();
			assertEquals(sExp, sGot);
			testSequenceEquals(exp, got);
		}
	}
	
	
	@Test
	void test_remove() {
		List<String> start = Arrays.asList("A", "B", "C", "D");
		for (int i = 0; i < start.size(); i++) {
			List<String> exp = new ArrayList<>(start);
			String sExp = exp.remove(i);
			
			LinkedList<String> got = new LinkedList<>();
			for (String s : start) {
				got.add(s);
			}
			String sGot = got.remove(i);
			assertEquals(sExp, sGot);
			testSequenceEquals(exp, got);
		}
	}
	
	@Test
	void test_removeAll_01() {
		List<Integer> start = Arrays.asList(1, 2, 3, 4); 
		LinkedList<Integer> t = new LinkedList<>();
		
		for (int i = 1; i <= 4; i++) {
			t.add(i);
		}
		boolean got = t.removeAll(5);
		assertEquals(false, got);
		assertEquals(4, t.size());
		testSequenceEquals(start, t);
	}
	
	@Test
	void test_removeAll_02() {
		List<Integer> start = Arrays.asList(1, 3, 4); 
		LinkedList<Integer> t = new LinkedList<>();
		
		for (int i = 1; i <= 4; i++) {
			t.add(i);
		}
		boolean got = t.removeAll(2);
		assertEquals(true, got);
		assertEquals(3, t.size());
		testSequenceEquals(start, t);
	}
	
	@Test
	void test_removeAll_03() {
		List<Integer> start = Arrays.asList(1, 2, 3); 
		LinkedList<Integer> t = new LinkedList<>();
		
		for (int i = 1; i <= 4; i++) {
			t.add(i);
		}
		boolean got = t.removeAll(4);
		assertEquals(true, got);
		assertEquals(3, t.size());
		testSequenceEquals(start, t);
	}
	
	@Test
	void test_removeAll_04() {
		List<Integer> start = Arrays.asList(1, 3); 
		LinkedList<Integer> t = new LinkedList<>();
		t.add(1);
		t.add(2);
		t.add(2);
		t.add(3);
		boolean got = t.removeAll(2);
		assertEquals(true, got);
		assertEquals(2, t.size());
		testSequenceEquals(start, t);
	}
	
	@Test
	void test_removeAll_05() {
		List<Integer> start = Arrays.asList(1); 
		LinkedList<Integer> t = new LinkedList<>();
		t.add(1);
		t.add(2);
		t.add(2);
		t.add(2);
		boolean got = t.removeAll(2);
		assertEquals(true, got);
		assertEquals(1, t.size());
		testSequenceEquals(start, t);
	}
	
	@Test
	void test_removeAll_06() {
		List<Integer> start = Arrays.asList(2, 3, 4); 
		LinkedList<Integer> t = new LinkedList<>();
		
		for (int i = 1; i <= 4; i++) {
			t.add(i);
		}
		boolean got = t.removeAll(1);
		assertEquals(true, got);
		assertEquals(3, t.size());
		testSequenceEquals(start, t);
	}
	
	/**
	 * Remove 1 element from the front of a list.
	 */
	@Test
	void test_removeAll_07() {
		List<Integer> start = Arrays.asList(4); 
		LinkedList<Integer> t = new LinkedList<>();
		t.add(1);
		t.add(1);
		t.add(1);
		t.add(4);
		boolean got = t.removeAll(1);
		assertEquals(true, got);
		assertEquals(1, t.size());
		testSequenceEquals(start, t);
	}
	
	/**
	 * Remove first and last elements of a list.
	 */
	@Test
	void test_removeAll_08() {
		List<Integer> start = Arrays.asList(0, 0); 
		LinkedList<Integer> t = new LinkedList<>();
		t.add(1);
		t.add(0);
		t.add(0);
		t.add(1);
		boolean got = t.removeAll(1);
		assertEquals(true, got);
		assertEquals(2, t.size());
		testSequenceEquals(start, t);
	}
	
	/**
	 * Remove all elements from a list.
	 */
	@Test
	void test_removeAll_09() {
		List<Integer> start = Arrays.asList(); 
		LinkedList<Integer> t = new LinkedList<>();
		t.add(2);
		t.add(2);
		t.add(2);
		t.add(2);
		boolean got = t.removeAll(2);
		assertEquals(true, got);
		assertEquals(0, t.size());
		testSequenceEquals(start, t);
	}
	
	@Test
	void testHashCode() {
		List<String> exp = new ArrayList<>();
		LinkedList<String> t = new LinkedList<>();
		
		for (char c = 'A'; c < 'Z'; c += 1) {
			exp.add("" + c);
			t.add("" + c);
			assertEquals(exp.hashCode(), t.hashCode());
		}
	}


	@Test
	void testEqualsObject() {
		LinkedList<String> exp = new LinkedList<>();
		LinkedList<String> t = new LinkedList<>();
		
		for (char c = 'A'; c < 'Z'; c += 1) {
			exp.add("" + c);
			t.add("" + c);
			assertEquals(exp, t);
		}
	}

	@Test
	void testToString() {
		List<String> exp = new ArrayList<>();
		LinkedList<String> t = new LinkedList<>();
		
		for (char c = 'A'; c < 'Z'; c += 1) {
			exp.add("" + c);
			t.add("" + c);
			assertEquals(exp.toString(), t.toString());
		}
	}

}
