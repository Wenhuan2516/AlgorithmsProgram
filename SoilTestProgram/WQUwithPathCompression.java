package CS401.Assignment1;
import edu.princeton.cs.algs4.StdOut;

public class WQUwithPathCompression {
	private int[] id; 
	private int[] sz;
	private int size;
	//to keep track of the level, if level == size -1, then reach the bottom
	private int level; 
	
	
	
	public WQUwithPathCompression(int N) {
		id = new int[N*N];
		sz = new int[N*N];

		size = N;
		level = 0;
		
		for (int i = 0; i < N*N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	
	private int root(int i) {
		while ( i != id[i]) {
			id[i] = id[id[i]]; // path compression
			i = id[i];
		}
		return i;
	}
	
	public boolean connected(int p, int q) {
		
		return root(p) == root(q);
	}
	
	
	public void union(int p, int q) {
		int rootP = root(p);
		int rootQ= root(q);
		if (rootP == rootQ) return;
		if (sz[rootP] < sz[rootQ]) {
			id[rootP] = rootQ;
			sz[rootQ] += sz[rootP];
		} else {
			id[rootQ] = rootP;
			sz[rootP] += sz[rootQ];
		}
		
		if (Math.abs(p-q) == size && level == Math.min(p/size, q/size)) { 
			level++;
			StdOut.println("Level: " + level);
		}
	}
	
	public boolean isTopBottomConnected() {
		return level == size - 1;
	}
}
