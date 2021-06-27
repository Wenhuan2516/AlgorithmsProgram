package CS401.Assignment1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestSoil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In("TestFile1.txt");
		int n = in.readInt();
	
		int[] elements = in.readAllInts();
		
		WQUwithPathCompression uf = new WQUwithPathCompression(n);

		for (int i = 0; i < elements.length - n; i++) {
			if (elements[i] == 1) {
				//if reach the end of each line, don't need to union(i, i+1)
				if ( i % (n-1) != 0 && elements[i+1] == 1) {
					uf.union(i, i+1);
				} 
				//if it is in the beginning of each line, don't need to union(i, i-1)
				if ( i % n != 0 && elements[i-1] == 1) {
					uf.union(i, i-1);
				}
				if ( elements[i+n] == 1) {
					uf.union(i, i+n);
				}
			}
		} 
		
		
		//test 
		boolean isConnected = uf.isTopBottomConnected();
		if (isConnected) {
			StdOut.println("Allow water to drain");
		} else {
			StdOut.println("Don't allow water to drain");
		}
	}
}
