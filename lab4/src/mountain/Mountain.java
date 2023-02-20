package mountain;

import java.util.HashMap;

import fractal.*;

public class Mountain extends Fractal {
	private Point p1;
	private Point p2;
	private Point p3;
	double dev;
	HashMap<Side, Point> hm;

	/**
	 * Creates an object that handles Koch's fractal.
	 * 
	 * @param length
	 *            the length of the triangle side
	 */
	public Mountain(Point p1, Point p2, Point p3, double dev) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.dev = dev;
		hm = new HashMap<Side, Point>();
	}

	/**
	 * Returns the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return "Bergsmassiv";
	}

	/**
	 * Draws the fractal.
	 * 
	 * @param turtle
	 *            the turtle graphic object
	 */
	public void draw(TurtleGraphics turtle) {
		// turtle.moveTo(p1.getX(), p1.getY());
		fractalLine(turtle, order, p1, p2, p3, dev);
	}

	/*
	 * Recursive method: Draws a recursive line of the triangle.
	 */
	private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
		if (order == 0) {
			turtle.penDown();
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			Point ab;
			Point bc;
			Point ca;
			Side s_ab = new Side(a, b);
			Side s_bc = new Side(b, c);
			Side s_ca = new Side(c, a);

			if (hm.containsKey(s_ab)) {
				ab = hm.get(s_ab);
				hm.remove(s_ab, ab);
			} else {
				ab = randMid(a, b, dev);
				hm.put(s_ab, ab);
			}

			if (hm.containsKey(s_bc)) {
				bc = hm.get(s_bc);
				hm.remove(s_bc, bc);
			} else {
				bc = randMid(b, c, dev);
				hm.put(s_bc, bc);
			}

			if (hm.containsKey(s_ca)) {
				ca = hm.get(s_ca);
				hm.remove(s_ca, ca);
			} else {
				ca = randMid(c, a, dev);
				hm.put(s_ca, ca);
			}

			dev = dev / 2;
			fractalLine(turtle, order - 1, a, ab, ca, dev);
			fractalLine(turtle, order - 1, b, bc, ab, dev);
			fractalLine(turtle, order - 1, c, ca, bc, dev);
			fractalLine(turtle, order - 1, ab, bc, ca, dev);
		}
	}

	private Point randMid(Point A, Point B, double dev2) {
		double r = RandomUtilities.randFunc(dev2);
		Point p = new Point((A.getX() + B.getX()) / 2, (int) ((double) (A.getY() + B.getY()) / 2 + r));
		return p;
	}
}
