package ca.queensu.cs.cisc235.tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	enum LR {
		LEFT, RIGHT;
	}
	
	static <E> void add(BinaryNode<E> root, BinaryNode<E> node, LR... path) {
		BinaryNode<E> parent = root;
		for (int i = 0; i < path.length - 1; i++) {
			LR dir = path[i];
			if (dir == LR.LEFT) {
				parent = parent.left;
			}
			else {
				parent = parent.right;
			}
		}
		LR dir = path[path.length - 1];
		if (dir == LR.LEFT) {
			parent.setLeft(node);
		}
		else {
			parent.setRight(node);
		}
	}
	
	
	@Test
	void test_add01() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(0);
		assertEquals(1, t.size());
		BinaryNode<Integer> exp = new BinaryNode<>(0);
		assertEquals(exp, t.root);
	}
	
	@Test
	void test_add02() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(10);
		t.add(5);
		assertEquals(2, t.size());
		BinaryNode<Integer> exp = new BinaryNode<>(10);
		add(exp, new BinaryNode<Integer>(5), LR.LEFT);
		assertEquals(exp, t.root);
	}
	
	@Test
	void test_add03() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(10);
		t.add(15);
		assertEquals(2, t.size());
		BinaryNode<Integer> exp = new BinaryNode<>(10);
		add(exp, new BinaryNode<Integer>(15), LR.RIGHT);
		assertEquals(exp, t.root);
	}
	
	@Test
	void test_add04() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		assertEquals(10, t.size());
		BinaryNode<Integer> exp = new BinaryNode<>(5);
		add(exp, new BinaryNode<Integer>(3), LR.LEFT);
		add(exp, new BinaryNode<Integer>(1), LR.LEFT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(2), LR.LEFT, LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(4), LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(7), LR.RIGHT);
		add(exp, new BinaryNode<Integer>(6), LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(9), LR.RIGHT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(8), LR.RIGHT, LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(10), LR.RIGHT, LR.RIGHT, LR.RIGHT);
		assertEquals(exp, t.root);
	}

	@Test
	void test_contains01() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		assertFalse(t.contains(1));
	}
	
	@Test
	void test_contains02() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(0);
		assertTrue(t.contains(0));
		assertFalse(t.contains(1));
	}
	
	@Test
	void test_contains03() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		for (int i = 1; i <= 10; i++) {
			assertTrue(t.contains(i));
		}
		assertFalse(t.contains(0));
		assertFalse(t.contains(11));
	}

	@Test
	void test_minimumInSubtree01() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(0);
		assertEquals(0, BinarySearchTree.minimumInSubtree(t.root).elem);
	}
	
	@Test
	void test_minimumInSubtree02() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		assertEquals(1, BinarySearchTree.minimumInSubtree(t.root).elem);
		assertEquals(1, BinarySearchTree.minimumInSubtree(t.root.left).elem);
		assertEquals(1, BinarySearchTree.minimumInSubtree(t.root.left.left).elem);
		assertEquals(4, BinarySearchTree.minimumInSubtree(t.root.left.right).elem);
		assertEquals(2, BinarySearchTree.minimumInSubtree(t.root.left.left.right).elem);
		assertEquals(6, BinarySearchTree.minimumInSubtree(t.root.right).elem);
		assertEquals(6, BinarySearchTree.minimumInSubtree(t.root.right.left).elem);
		assertEquals(8, BinarySearchTree.minimumInSubtree(t.root.right.right).elem);
		assertEquals(8, BinarySearchTree.minimumInSubtree(t.root.right.right.left).elem);
		assertEquals(10, BinarySearchTree.minimumInSubtree(t.root.right.right.right).elem);
	}

	@Test
	void test_maximumInSubtree01() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(0);
		assertEquals(0, BinarySearchTree.maximumInSubtree(t.root).elem);
	}
	
	@Test
	void test_maximumInSubtree02() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		assertEquals(10, BinarySearchTree.maximumInSubtree(t.root).elem);
		assertEquals(4, BinarySearchTree.maximumInSubtree(t.root.left).elem);
		assertEquals(2, BinarySearchTree.maximumInSubtree(t.root.left.left).elem);
		assertEquals(4, BinarySearchTree.maximumInSubtree(t.root.left.right).elem);
		assertEquals(2, BinarySearchTree.maximumInSubtree(t.root.left.left.right).elem);
		assertEquals(10, BinarySearchTree.maximumInSubtree(t.root.right).elem);
		assertEquals(6, BinarySearchTree.maximumInSubtree(t.root.right.left).elem);
		assertEquals(10, BinarySearchTree.maximumInSubtree(t.root.right.right).elem);
		assertEquals(8, BinarySearchTree.maximumInSubtree(t.root.right.right.left).elem);
		assertEquals(10, BinarySearchTree.maximumInSubtree(t.root.right.right.right).elem);
	}

	@Test
	void test_successorInSubtree01() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(0);
		assertEquals(null, BinarySearchTree.successorInSubtree(t.root));
	}
	
	@Test
	void test_successorInSubtree02() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		assertEquals(6, BinarySearchTree.successorInSubtree(t.root).elem);
		assertEquals(4, BinarySearchTree.successorInSubtree(t.root.left).elem);
		assertEquals(2, BinarySearchTree.successorInSubtree(t.root.left.left).elem);
		assertEquals(null, BinarySearchTree.successorInSubtree(t.root.left.right));
		assertEquals(null, BinarySearchTree.successorInSubtree(t.root.left.left.right));
		assertEquals(8, BinarySearchTree.successorInSubtree(t.root.right).elem);
		assertEquals(null, BinarySearchTree.successorInSubtree(t.root.right.left));
		assertEquals(10, BinarySearchTree.successorInSubtree(t.root.right.right).elem);
		assertEquals(null, BinarySearchTree.successorInSubtree(t.root.right.right.left));
		assertEquals(null, BinarySearchTree.successorInSubtree(t.root.right.right.right));
	}
	
	@Test
	void test_predecessorInSubtree01() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(0);
		assertEquals(null, BinarySearchTree.predecessorInSubtree(t.root));
	}
	
	@Test
	void test_predecessorInSubtree02() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		assertEquals(4, BinarySearchTree.predecessorInSubtree(t.root).elem);
		assertEquals(2, BinarySearchTree.predecessorInSubtree(t.root.left).elem);
		assertEquals(null, BinarySearchTree.predecessorInSubtree(t.root.left.left));
		assertEquals(null, BinarySearchTree.predecessorInSubtree(t.root.left.right));
		assertEquals(null, BinarySearchTree.predecessorInSubtree(t.root.left.left.right));
		assertEquals(6, BinarySearchTree.predecessorInSubtree(t.root.right).elem);
		assertEquals(null, BinarySearchTree.predecessorInSubtree(t.root.right.left));
		assertEquals(8, BinarySearchTree.predecessorInSubtree(t.root.right.right).elem);
		assertEquals(null, BinarySearchTree.predecessorInSubtree(t.root.right.right.left));
		assertEquals(null, BinarySearchTree.predecessorInSubtree(t.root.right.right.right));
	}

	@Test
	void test_remove01() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		assertFalse(t.remove(0));
	}

	@Test
	void test_remove02() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(0);
		assertTrue(t.remove(0));
		assertEquals(0, t.size());
		assertNull(t.root);
	}
	
	@Test
	void test_remove03() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		boolean got = t.remove(2);
		assertTrue(got);
		BinaryNode<Integer> exp = new BinaryNode<>(5);
		add(exp, new BinaryNode<Integer>(3), LR.LEFT);
		add(exp, new BinaryNode<Integer>(1), LR.LEFT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(4), LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(7), LR.RIGHT);
		add(exp, new BinaryNode<Integer>(6), LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(9), LR.RIGHT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(8), LR.RIGHT, LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(10), LR.RIGHT, LR.RIGHT, LR.RIGHT);
		assertEquals(exp, t.root);
	}
	
	@Test
	void test_remove04() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		boolean got = t.remove(1);
		assertTrue(got);
		BinaryNode<Integer> exp = new BinaryNode<>(5);
		add(exp, new BinaryNode<Integer>(3), LR.LEFT);
		add(exp, new BinaryNode<Integer>(2), LR.LEFT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(4), LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(7), LR.RIGHT);
		add(exp, new BinaryNode<Integer>(6), LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(9), LR.RIGHT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(8), LR.RIGHT, LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(10), LR.RIGHT, LR.RIGHT, LR.RIGHT);
		assertEquals(exp, t.root);
	}
	
	
	@Test
	void test_remove05() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		boolean got = t.remove(3);
		assertTrue(got);
		BinaryNode<Integer> exp = new BinaryNode<>(5);
		add(exp, new BinaryNode<Integer>(2), LR.LEFT);
		add(exp, new BinaryNode<Integer>(1), LR.LEFT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(4), LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(7), LR.RIGHT);
		add(exp, new BinaryNode<Integer>(6), LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(9), LR.RIGHT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(8), LR.RIGHT, LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(10), LR.RIGHT, LR.RIGHT, LR.RIGHT);
		assertEquals(exp, t.root);
	}
	
	@Test
	void test_remove06() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		boolean got = t.remove(4);
		assertTrue(got);
		BinaryNode<Integer> exp = new BinaryNode<>(5);
		add(exp, new BinaryNode<Integer>(3), LR.LEFT);
		add(exp, new BinaryNode<Integer>(1), LR.LEFT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(2), LR.LEFT, LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(7), LR.RIGHT);
		add(exp, new BinaryNode<Integer>(6), LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(9), LR.RIGHT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(8), LR.RIGHT, LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(10), LR.RIGHT, LR.RIGHT, LR.RIGHT);
		assertEquals(exp, t.root);
	}
	
	@Test
	void test_remove07() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		boolean got = t.remove(5);
		assertTrue(got);
		BinaryNode<Integer> exp = new BinaryNode<>(4);
		add(exp, new BinaryNode<Integer>(3), LR.LEFT);
		add(exp, new BinaryNode<Integer>(1), LR.LEFT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(2), LR.LEFT, LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(7), LR.RIGHT);
		add(exp, new BinaryNode<Integer>(6), LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(9), LR.RIGHT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(8), LR.RIGHT, LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(10), LR.RIGHT, LR.RIGHT, LR.RIGHT);
		assertEquals(exp, t.root);
	}
	
	
	@Test
	void test_remove08() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		boolean got = t.remove(6);
		assertTrue(got);
		BinaryNode<Integer> exp = new BinaryNode<>(5);
		add(exp, new BinaryNode<Integer>(3), LR.LEFT);
		add(exp, new BinaryNode<Integer>(1), LR.LEFT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(2), LR.LEFT, LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(4), LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(7), LR.RIGHT);
		add(exp, new BinaryNode<Integer>(9), LR.RIGHT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(8), LR.RIGHT, LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(10), LR.RIGHT, LR.RIGHT, LR.RIGHT);
		assertEquals(exp, t.root);
	}
	
	@Test
	void test_remove09() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		boolean got = t.remove(7);
		assertTrue(got);
		BinaryNode<Integer> exp = new BinaryNode<>(5);
		add(exp, new BinaryNode<Integer>(3), LR.LEFT);
		add(exp, new BinaryNode<Integer>(1), LR.LEFT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(2), LR.LEFT, LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(4), LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(6), LR.RIGHT);
		add(exp, new BinaryNode<Integer>(9), LR.RIGHT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(8), LR.RIGHT, LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(10), LR.RIGHT, LR.RIGHT, LR.RIGHT);
		assertEquals(exp, t.root);
	}
	
	@Test
	void test_remove10() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		boolean got = t.remove(8);
		assertTrue(got);
		BinaryNode<Integer> exp = new BinaryNode<>(5);
		add(exp, new BinaryNode<Integer>(3), LR.LEFT);
		add(exp, new BinaryNode<Integer>(1), LR.LEFT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(2), LR.LEFT, LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(4), LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(7), LR.RIGHT);
		add(exp, new BinaryNode<Integer>(6), LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(9), LR.RIGHT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(10), LR.RIGHT, LR.RIGHT, LR.RIGHT);
		assertEquals(exp, t.root);
	}
	
	@Test
	void test_remove11() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		boolean got = t.remove(9);
		assertTrue(got);
		BinaryNode<Integer> exp = new BinaryNode<>(5);
		add(exp, new BinaryNode<Integer>(3), LR.LEFT);
		add(exp, new BinaryNode<Integer>(1), LR.LEFT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(2), LR.LEFT, LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(4), LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(7), LR.RIGHT);
		add(exp, new BinaryNode<Integer>(6), LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(8), LR.RIGHT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(10), LR.RIGHT, LR.RIGHT, LR.RIGHT);
		assertEquals(exp, t.root);
	}
	
	@Test
	void test_remove12() {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		t.add(5);
		t.add(3);
		t.add(1);
		t.add(4);
		t.add(2);
		t.add(7);
		t.add(6);
		t.add(9);
		t.add(8);
		t.add(10);
		boolean got = t.remove(10);
		assertTrue(got);
		BinaryNode<Integer> exp = new BinaryNode<>(5);
		add(exp, new BinaryNode<Integer>(3), LR.LEFT);
		add(exp, new BinaryNode<Integer>(1), LR.LEFT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(2), LR.LEFT, LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(4), LR.LEFT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(7), LR.RIGHT);
		add(exp, new BinaryNode<Integer>(6), LR.RIGHT, LR.LEFT);
		add(exp, new BinaryNode<Integer>(9), LR.RIGHT, LR.RIGHT);
		add(exp, new BinaryNode<Integer>(8), LR.RIGHT, LR.RIGHT, LR.LEFT);
		assertEquals(exp, t.root);
	}
}
