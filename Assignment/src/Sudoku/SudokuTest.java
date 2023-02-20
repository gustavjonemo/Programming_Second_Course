package Sudoku;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SudokuTest {
	Board b;

	@Before
	public void setUp() throws Exception {
		b = new Board();
		b.add(5, 2, 0);
		b.add(6, 3, 2);
		b.add(7, 5, 7);
		b.add(8, 4, 6);
		b.add(9, 7, 3);
	}

	@After
	public void tearDown() throws Exception {
		for(int i1 = 0; i1<9; i1++){
			for(int i2 = 0; i2<9; i2++){
				b.remove(i1, i2);
			}
		}
	}

	@Test
	public void get() {
		StringBuilder sb = new StringBuilder();
		sb.append(b.get(2, 0));
		sb.append(b.get(3, 2));
		sb.append(b.get(5, 7));
		sb.append(b.get(4, 6));
		sb.append(b.get(7, 3));
		
		assertTrue("Går ej att hämta siffror", sb.toString().equals("56789"));
	}

	@Test
	public void add() {
		b.add(1, 1, 0);
		b.add(2, 2, 2);
		b.add(3, 4, 7);
		b.add(4, 3, 6);
		b.add(5, 8, 3);
		
		StringBuilder sb = new StringBuilder();
		sb.append(b.get(1, 0));
		sb.append(b.get(2, 2));
		sb.append(b.get(4, 7));
		sb.append(b.get(3, 6));
		sb.append(b.get(8, 3));
		
		assertTrue("Går ej att lägga till siffror", sb.toString().equals("12345"));
	}
	
	@Test
	public void remove() throws InterruptedException {
		b.remove(7, 3);
		assertTrue("Går ej att radera siffror", b.get(7, 3) == 0);
	}

	@Test
	public void solveable_empty() throws InterruptedException {
		b.remove(2, 0);
		b.remove(3, 2);
		b.remove(5, 7);
		b.remove(4, 6);
		b.remove(7, 3);
		assertTrue("Kan ej lösa ett tomt Sudoku", b.solve());
	}
	
	@Test
	public void solveable() throws InterruptedException {
		assertTrue("Kan ej lösa ett lösbart Sudoku", b.solve());
	}

	@Test
	public void unsolveable() throws InterruptedException {
		b.add(5, 3, 0);
		assertFalse("Sudokut ska vara olösbart", b.solve());
	}
}
