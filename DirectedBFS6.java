import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//much the same as in assignment 3 the question of findind the shortest path between point a and b possible going through c
//in a diagraph boils down to finding the shortest path from a to c then c to b and add the two together.
public class DirectedBFS6 {

    public static void main(String[] args) throws FileNotFoundException {
        String file = "C:\\Users\\vicke\\IdeaProjects\\labb 4 data och algo\\src\\NYC.txt";
        Scanner myScanFile = new Scanner(new File(file));
        int V = myScanFile.nextInt();
        int E = myScanFile.nextInt();
        Diagraph g = new Diagraph(V);
        for(int i = 0; i < E; i++){
            int v = myScanFile.nextInt();
            int w = myScanFile.nextInt();
            myScanFile.nextInt();
            g.addEdge(v,w);
        }
        Scanner myScan = new Scanner(System.in);
        while (true) {
            System.out.println("where do you want to start? ");
            int s = myScan.nextInt();
            System.out.println("\n" + "Where do you want to end? ");
            int end = myScan.nextInt();
            DirectedBFS6 bfs1 = new DirectedBFS6(g, s);
            System.out.println("\n" + "Do you want to pass through an intermediate point? (j/n)");
            String answer = myScan.next();
            if(answer.equals("j")){
                System.out.println("\n" + "What point to you want to pass through on the way");
                int midPoint = myScan.nextInt();
                DirectedBFS6 bfs2 = new DirectedBFS6(g,midPoint);
                if (bfs1.hasPathTo(midPoint) && bfs2.hasPathTo(end)) {
                    for (int w : bfs1.pathTo(midPoint)) {
                        System.out.println(w);
                    }
                    for(int w: bfs2.pathTo(end)){
                        System.out.println(w);
                    }
                } else {
                    System.out.println("No such path exists");
                }
            }
            else{
                if(bfs1.hasPathTo(end)){
                    for(int w: bfs1.pathTo(end)){
                        System.out.println(w);
                    }
                }
                else{
                    System.out.println("no such path exists.");
                }
            }



        }

    }

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DirectedBFS6(Diagraph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        bfs(g,s);
    }

    private void bfs(Diagraph g, int s){
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;
        queue.enqueue(s);
        while(!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w: g.adj(v)){
                if(!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){return marked[v];}

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v))return null;
        Stack<Integer> path = new Stack<Integer>();
        for(int x = v; x!= s; x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
