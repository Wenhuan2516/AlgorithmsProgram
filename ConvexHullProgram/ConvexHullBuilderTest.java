package CS401.Assignment2;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;


import org.junit.jupiter.api.Test;
import edu.princeton.cs.algs4.Point2D;

class ConvexHullBuilderTest {
	static ArrayList<Point2D> list;
	
	@Test
	void test() {
		list = new ArrayList<>();
		Point2D p1 = new Point2D(242.5, 354.8);
		Point2D p2 = new Point2D(112.3, 258.6);
		Point2D p3 = new Point2D(162.8, 318.9);
		Point2D p4 = new Point2D(225.7, 234.8);
		Point2D p5 = new Point2D(129.9, 144.7);
		Point2D p6 = new Point2D(332.3, 54.6);
		Point2D p7 = new Point2D(212.6, 84.8);
		Point2D p8 = new Point2D(572.1, 654.5);
		Point2D p9 = new Point2D(452.2, 434.4);
		Point2D p10 = new Point2D(364.4, 115.9);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
		list.add(p9);
		list.add(p10);
		ConvexHullBuilder convexBuilder = new ConvexHullBuilder(list);
		Point2D p0 = convexBuilder.p0;
		Point2D expected = list.get(5);
		assertEquals(expected, p0);
		
		for (Point2D p : convexBuilder.hull()) {
			System.out.println("(" + p.x() + ", " + p.y() + ")");
		}
		
	}
	


}
