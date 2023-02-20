package bst;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {
	private BinarySearchTree<Integer> tree;

	@Before
	public void setUp() throws Exception {
		tree = new BinarySearchTree<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		tree = null;
	}

	@Test
	public void testHeightEmptyTree() {
		assertTrue("Wrong heigth of the tree", tree.height() == 0);
	}

	@Test
	public void testHeightTree() {
		tree.add(10);
		tree.add(8);
		tree.add(9);
		tree.add(7);
		tree.add(6);
		tree.add(23);
		assertTrue("Wrong height of the tree", tree.height() == 4);
	}

	@Test
	public void testAddSame() {
		tree.add(10);
		tree.add(7);
		tree.add(8);
		tree.add(8);
		assertFalse("Able to add same integer", tree.size() == 4);
	}

	@Test
	public void testSizeEmpty() {
		assertTrue("Wrong size of the tree", tree.size() == 0);
	}

	@Test
	public void testSize() {
		tree.add(10);
		tree.add(8);
		tree.add(9);
		tree.add(7);
		tree.add(6);
		tree.add(23);
		assertTrue("Wrong size of the tree", tree.size == 6);
	}

}
