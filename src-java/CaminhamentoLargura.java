import java.util.ArrayList;

public class CaminhamentoLargura {
    private boolean marked[];
    private int edgeTo[];
    private int distTo[];
    private int s;
    private Graph g;

    public CaminhamentoLargura(Graph g, int s) {
        this.s = s;
        this.g = g;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        bfs(s);
    }

    private void bfs(int s) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        marked[s] = true;
        distTo[s] = 0;
        edgeTo[s] = -1; // ninguém antes
        // System.out.println("Estou no " + s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            // System.out.println("Estou no " + v);
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public ArrayList<Integer> pathTo(int v) {
        if (!hasPathTo(v)) // verifica se há caminho antes
            return null;
        ArrayList<Integer> path = new ArrayList<>();
        while (v != s) {
            path.add(0, v); // insere no início do arraylist
            v = edgeTo[v];
        }
        path.add(0, s); // adiciona o vértice inicial
        return path;
    }

    public void exibe() {
        for (int v = 0; v < g.V(); v++) {
            System.out.println(v + ": " + marked[v] + " - " + edgeTo[v] + " - " + distTo[v]);
        }
    }

    public static void main(String[] args) {
        In in = new In("tinyG.txt");
        Graph g = new Graph(in);
        System.out.println("Vértices: " + g.V());
        for (int v = 0; v < g.V(); v++) {
            System.out.print(v + ": ");
            for (int w : g.adj(v))
                System.out.print(w + " ");
            System.out.println();
        }
        System.out.println();
        CaminhamentoLargura cl = new CaminhamentoLargura(g, 0);
        for (int v = 0; v < g.V(); v++) {
            if (cl.hasPathTo(v)) {
                System.out.println(v + " -> " + cl.pathTo(v));
            }
        }
    }
}
