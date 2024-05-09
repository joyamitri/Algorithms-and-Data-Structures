package Connected;

public class Connected {
    private Graph g;

    public Graph getG() {
        return g;
    }

    public void setG(Graph g) {
        this.g = g;
    }
    public void findComponents() {
        int V=g.getV();
        LinkedList[] edges=g.getEdges();
        int counter=0;//to count connected components
        int [] visited=new int[V];
        for(int i=0;i<V;i++) {
            visited[i]=0;//not discovered
        }
        Stack s=new Stack();
        Stack s2;//to print the result
        for (int j=0;j<V;j++) {
            s2=new Stack();
            if(visited[j]!=0) {
                continue;
            }
            s.push(j);
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
            System.out.println("C"+counter+" : ");
            counter++;
            System.out.println(s2);
        }
    }
    public static void main(String [] args) {
        Graph g=new Graph(11);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(2, 3);
        g.addEdge(5, 4);
        g.addEdge(6, 5);
        g.addEdge(8, 7);
        g.addEdge(8, 9);
        g.addEdge(7, 10);
        Connected ex=new Connected();
        ex.setG(g);
        ex.findComponents();
    }
}
