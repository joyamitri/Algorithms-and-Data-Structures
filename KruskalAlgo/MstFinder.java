package KruskalAlgo;

import java.util.ArrayList;
import java.util.Iterator;

public class MstFinder {
	public int[] V;
	
	public Edge[] edges;
	
	
	// Union Find
	private int[] id;
	
	private int getRoot(int i) {
		// O(log v)
		while( i != id[i]) i = id[i];
		return i;
	}
	private boolean find(int u, int v) {
		// O(log v)
		return getRoot(u) == getRoot(v);
	}
	private void union(int u, int v) {
		// O(log v)
		int uId = getRoot(u);
		int vId = getRoot(v);
		if(uId == vId) {
			return;
		}
		id[uId] = vId;
	}
	

	public MstFinder(int[] v) {
		V = v;
		id = new int[v.length];
		for (int i = 0; i < v.length; i++) {
			id[i] = i;
		}
	}
		
	public void findMST() {
		
		// store the edges of mst
		// can also use a queue instead of the ArrayList
		ArrayList<Edge> mst = new ArrayList<Edge>();
		
		
		// O(Elog(E))   
		MinHeap pq = new MinHeap();
		pq.buildMinHeap(edges);
		
		
		
		// O(E Log(E)) WORST CASE 
		while(!pq.isEmpty() && mst.size() < V.length - 1) {
			
			// O(log(E))
			// Greedy
			Edge min_crossing_edge = pq.extractMin();
			
			// O(log(E))
			// create a cycle, ignore it
			if(find(min_crossing_edge.u, min_crossing_edge.v)) continue;
			
			
			// O(Log(E))
			// good min_crossing_edge add it
			union(min_crossing_edge.u, min_crossing_edge.v);
			mst.add(min_crossing_edge);
		}
		
		
		
		// Just to print the MST
		for (Iterator<Edge> iterator = mst.iterator(); iterator.hasNext();) {
			Edge edge = (Edge) iterator.next();
			System.out.println(edge);
		}
		
	}

	public void addEdges(Edge[] edges) {
		this.edges = edges;
	}
	
	
	

}