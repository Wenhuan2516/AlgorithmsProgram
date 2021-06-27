package CS401.Assignment2;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;

public class ConvexHullBuilder {
	private Point2D[] points;
	public Point2D p0;
	
	public ConvexHullBuilder(ArrayList<Point2D> list) {
		int n = list.size();
		points = new Point2D[n];
		for (int i = 0; i < n; i++) {
			points[i] = list.get(i);
		}
		Arrays.sort(points); 
		p0 = points[0];  //find the bottom most and left most point
		//sort the array by polarOrder()
		Arrays.sort(points, 1, n, p0.polarOrder()); 
	}
	
	public Iterable<Point2D> hull() {
		Stack<Point2D> stack = new Stack<>();
		stack.push(p0); //p0 is guaranteed to be in the stack
		
		int size = points.length;
		if ( size <= 2) return stack;  // if less than 2 elements, then return
		stack.push(points[1]);
		
		for (int i =2; i < size; i++) {
			Point2D next = points[i];
			Point2D top = stack.pop();
			
			while ( stack.size() > 1 && 
					Point2D.ccw(stack.peek(), top, next) <= 0) {
				top = stack.pop(); //delete points that create clockwise turn
			} 
			stack.push(top);
			stack.push(points[i]);
		}
		
		Point2D p  = stack.pop(); // last point pushed in could have been collinear
		if (Point2D.ccw(stack.peek(), p, p0) > 0) {
			stack.push(p); //put back
		}
		return stack;
	}
	
	
}
