package KruskalAlgo;

public class Main {
    public static void main(String[] args) {
		
		
		// Start vertices from 0
		// otherwise we need hashing
		MstFinder mf = new MstFinder(new int[] {0, 1, 2, 3, 4, 5, 6, 7});
		
		
		mf.addEdges(new Edge[] {
				new Edge(1, 5, 32),
				new Edge(1, 3, 29),
				new Edge(5, 7, 28),
				new Edge(1, 7, 19),
				new Edge(1, 2, 36),
				new Edge(3, 2, 17),
				new Edge(3, 6, 52),
				new Edge(7, 2, 34),
				new Edge(7, 0, 16),
				new Edge(5, 4, 35),
				new Edge(4, 7, 37),
				new Edge(4, 0, 38),
				new Edge(2, 0, 26),
				new Edge(2, 6, 40),
				new Edge(0, 6, 58),
				new Edge(4, 6, 53)
		});
		
		// O(E log(V))
		// prints the output
		mf.findMST();
	}
}
