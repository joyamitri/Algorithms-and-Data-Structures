package DFSStack;

import java.util.Arrays;
// import java.util.Stack;

public class Graph {
    private int V;//number of vertices
    private int E;//number of Edges
    private int[]vertices;//the vertices
    private LinkedList[] edges;//the edges
    public Graph(int n) {
        V=n;
        vertices=new int[n];
        E=0;
        edges=new LinkedList[n];
        for (int i=0;i<V;i++) {
            vertices[i]=i;
            edges[i]=new LinkedList();
        }	
    }
    public int getV() {
        return V;
    }
    public void setV(int v) {
        V = v;
    }
    public int getE() {
        return E;
    }
    public void setE(int e) {
        E = e;
    }
    public int[] getVertices() {
        return vertices;
    }
    public void setVertices(int[] vertices) {
        this.vertices = vertices;
    }

    public LinkedList[] getEdges() {
        return edges;
    }
    public void addEdge(int u,int v) {
        E++;
        edges[u].add(v);
        edges[v].add(u);
    }
    public boolean findEdge(int u,int v) {
        if (edges[u].find(v))
            return true;
        return false;
    }
    public void deleteEdge(int u,int v) {
        if (edges[u].delete(v)) {
            edges[v].delete(u);
            E--;
        }
        
    }
    @Override
    public String toString() {
        return "Graph\n\tVertices=" + Arrays.toString(vertices) + "\n\tEdges=\n\t" + Arrays.toString(edges);
    }

    public void DFS() {
        int [] visited=new int[V];
        for(int i=0;i<V;i++) {
            visited[i]=0;//not discovered
        }
        Stack s=new Stack();
        Stack s2=new Stack();//to print the result
        s.push(0);
        while(!s.empty()) {
            int u=s.pop();
            if (visited[u]!=2) {//if not visited
                visited[u]=2;//visited
                s2.push(u);
                for(int i=0;i<V;i++) {
                    if(edges[u].find(i)&&visited[i]==0) {//if neighbour not discovered yet
                        visited[i]=1;//discovered but not visited
                        s.push(i);
                    }
                }
            }
        }
        System.out.println(s2);
    }

    public static void main(String [] args) {
        Graph g=new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        g.DFS();
        //the stack size is V since each vertex is visited once.
    }
}
