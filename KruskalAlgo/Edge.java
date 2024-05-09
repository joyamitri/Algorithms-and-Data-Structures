package KruskalAlgo;

public class Edge {
    public int u;
	public int v;
	public int w;
	public Edge(int u, int v, int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}
	
	public int compareTo(Edge other) {
		if(this.w == other.w ) return 0;
		if(this.w < other.w) return -1;
		return 1;
		
	}
	
	public String toString() {
		return u + " " + v + " " + w;
	}
}
