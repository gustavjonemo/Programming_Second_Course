package Sudoku;

public class Board {
	private int[][] b;
	//private int delay = 0;

	public Board() {
		this.b = new int[9][9];
	}

	public void add(int n, int x, int y) {
		b[x][y] = n;
	}

	public void remove(int x, int y) {
		b[x][y] = 0;
	}

	public int get(int x, int y) {
		return b[x][y];
	}

	private boolean allowed(int n, int x, int y) {
		// System.out.println("Allowed check");
		for (int i = 0; i < 9; i++) {
			if (n == b[x][i] || n == b[i][y]) {
				return false;
			}
		}
		int newx = (x / 3) * 3;
		int newy = (y / 3) * 3;
		for (int i2 = newy; i2 < (newy + 3); i2++) {
			for (int i1 = newx; i1 < (newx + 3); i1++) {
				if (n == b[i1][i2])
					return false;
			}
		}
		return true;
	}

	public boolean solve() throws InterruptedException {
		for (int i2 = 0; i2 < 9; i2++) {
			for (int i1 = 0; i1 < 9; i1++) {
				int temp = b[i1][i2];
				if (temp != 0) {
					remove(i1, i2);
					if (allowed(temp, i1, i2)) {
						add(temp, i1, i2);
					} else {
						//System.out.println("Brädet är ogiltigt");
						return false;
					}
				}
			}
		}
		if(solve(0, 0)){
			//System.out.println("Löst");
			return true;
		} else {
			//System.out.println("Olösbart");
			return false;
		}
	}

	public boolean solve(int x, int y) throws InterruptedException {
		if (x == 0 && y == 9) {
			return true;
		}
		if (b[x][y] != 0) {
			if (x < 8) {
				return solve(x + 1, y);
			} else {
				return solve(0, y + 1);
			}
		}
		for (int i = 1; i < 10; i++) {
			if (allowed(i, x, y)) {
				add(i, x, y);
//				System.out.println("|" + b[0][0] + "|" + b[1][0] + "|" + b[2][0] + "|" + b[3][0] + "|" + b[4][0] + "|"
//						+ b[5][0] + "|" + b[6][0] + "|" + b[7][0] + "|" + b[8][0] + "\n|" + b[0][1] + "|" + b[1][1]
//						+ "|" + b[2][1] + "|" + b[3][1] + "|" + b[4][1] + "|" + b[5][1] + "|" + b[6][1] + "|" + b[7][1]
//						+ "|" + b[8][1] + "\n|" + b[0][2] + "|" + b[1][2] + "|" + b[2][2] + "|" + b[3][2] + "|"
//						+ b[4][2] + "|" + b[5][2] + "|" + b[6][2] + "|" + b[7][2] + "|" + b[8][2] + "\n");
//				Thread.sleep(delay);
				if (x < 8) {
					if (solve(x + 1, y)) {
						return true;
					}
				} else {
					if (solve(0, y + 1)) {
						return true;
					}
				}
			}
			remove(x, y);
//			System.out.println("|" + b[0][0] + "|" + b[1][0] + "|" + b[2][0] + "|" + b[3][0] + "|" + b[4][0] + "|"
//					+ b[5][0] + "|" + b[6][0] + "|" + b[7][0] + "|" + b[8][0] + "\n|" + b[0][1] + "|" + b[1][1] + "|"
//					+ b[2][1] + "|" + b[3][1] + "|" + b[4][1] + "|" + b[5][1] + "|" + b[6][1] + "|" + b[7][1] + "|"
//					+ b[8][1] + "\n|" + b[0][2] + "|" + b[1][2] + "|" + b[2][2] + "|" + b[3][2] + "|" + b[4][2] + "|"
//					+ b[5][2] + "|" + b[6][2] + "|" + b[7][2] + "|" + b[8][2] + "\n");
//			Thread.sleep(delay);
		}
		return false;
	}
}
