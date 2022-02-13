import java.util.Scanner;

public class Graph {
    //number of vertices which in this case cannot change after instantiation and number of edges as well as an array of
    //bags representing the adjecent vertices to any given vertice.
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    //Creates a graph with v vertice but no edges an creates all the adjecent bags needed to represent said edges.
    public Graph(int V){
        this.V = V; this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++){
            adj[v] = new Bag<Integer>();
        }
    }


    public Graph(Scanner myscan){
        this.V = myscan.nextInt();
        int E = myscan.nextInt();
        for(int i = 0; i<E; i++){
            int v = myscan.nextInt();
            int w = myscan.nextInt();
            addEdge(v,w);
        }
    }

    //returns the number of Vertices and edges respectivley.
    public int V(){return V;}
    public int E(){return E;}

    //adds a connection between v and w and w and v which makes this graph undirected.
    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    //returns one of the adj iterators so we can iterate through the adjecent bags.
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}
