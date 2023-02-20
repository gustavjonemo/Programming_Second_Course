package gustaventerprises.sudoku;

public class Board {
    private int[][] b;


    /**Spelbrädet skapas.*/
    public Board() {
        this.b = new int[9][9];
    }

    /**En siffra förs in på en given plats på brädet.
     *
     * @param n heltalssiffran som förs in
     * @param x positionen i x-led som siffran förs in i
     * @param y positionen i y-led som siffran förs in i*/
    public void add(int n, int x, int y) {
        b[x][y] = n;
    }


    /**En siffra tas bort från en given plats på brädet.
     *
     * @param x positionen i x-led som siffran tas bort från
     * @param y positionen i y-led som siffran tas bort från*/
    public void remove(int x, int y) {
        b[x][y] = 0;
    }


    /**En siffra hämtas från en given plats på brädet.
     *
     * @param x positionen i x-led som siffran hämtas från
     * @param y positionen i y-led som siffran hämtas från
     * @return  heltalssiffran på den givna positionen*/
    public int get(int x, int y) {
        return b[x][y];
    }


    /**Avgör om en siffra får sättas in på en given plats utifrån reglerna i sudoku.
     *
     * @param n heltalssiffran som skall föras in
     * @param x positionen i x-led som siffran skall föras in i
     * @param y positionen i y-led som siffran skall föras in i
     * @return  true om siffran får sättas in, annars false*/
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


    /**Försöker lösa spelbrädet med de värden som tidigare matats in i varje ruta.
     * Uppdaterar dessutom brädet med erhållen lösning, om sådan finns.
     *
     * @return  true om brädet kan lösas, annars false*/
    public boolean solve() {
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

    private boolean solve(int x, int y) {
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
        }
        return false;
    }
}
