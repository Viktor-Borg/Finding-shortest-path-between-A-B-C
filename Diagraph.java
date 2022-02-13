//acts the exact same way as a non directed graph expect the add edge method(See Graph for more info).
public class Diagraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Diagraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++){
            adj[v] = new Bag<Integer>();
        }
    }

    public int V(){return V;}
    public int E(){return E;}
    //only adds to edge v to w not w to v which makes this connection one way aka directed.
    public void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}
