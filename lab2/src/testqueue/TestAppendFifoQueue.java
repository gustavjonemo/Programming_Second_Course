package testqueue;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import queue_singlelinkedlist.FifoQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAppendFifoQueue {
	FifoQueue<Integer> q1 = new FifoQueue<Integer>();
	FifoQueue<Integer> q2 = new FifoQueue<Integer>();
	
	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	@Test
	public void empty() {
		q1.append(q2);
		assertTrue(q1.size() == 0);
		assertTrue(q2.size() == 0);
	}

	@Test
	public void emptyToActive() {
		for (int i = 1; i <= 3; i++) {
			q2.offer(i + 3);
		}
		q1.append(q2);
		assertTrue(q1.size() == 3);
		assertTrue(q2.size() == 0);
		for (int i = 4; i <= 6; i++) {
			int k = q1.poll();
			assertEquals(i, k);
		}
	}

	@Test
	public void activeToEmpty() {
		
		for (int i = 1; i <= 3; i++) {
			q1.offer(i);
		}
		
		q2.append(q1);
		assertTrue(q1.size() == 0);
		assertTrue(q2.size() == 3);
		for (int i = 1; i <= 3; i++) {
			int k = q2.poll();
			assertEquals(i, k);
		}
	}

	@Test
	public void active() {
		for (int i = 1; i < 4; i++) {
			q1.offer(i);
			q2.offer(i + 3);
		}
		q1.append(q2);
		assertTrue(q1.size() == 6);
		assertTrue(q2.size() == 0);
		for (int i = 1; i <= 6; i++) {
			int k = q1.poll();
			assertEquals(i, k);
		}

	}

	@Test
	public void same() {
		try {
			q1.append(q1);
			fail("Skitsamma");
		} catch (IllegalArgumentException e) {

		}

	}

}
