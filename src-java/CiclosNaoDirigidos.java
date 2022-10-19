import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CiclosNaoDirigidos {
    private boolean[] marked;
    private boolean hasCycle;
    private Set<String> edgeSet;

    public CiclosNaoDirigidos(Graph g) {
        marked = new boolean[g.V()];
        hasCycle = false;
        edgeSet = new HashSet<>();
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v])
                dfs(g, v);
            if (hasCycle)
                return;
        }
    }

    public void dfs(Graph g, int v) {
        marked[v] = true;
        for (int u : g.adj(v)) {
            if (hasCycle)
                return;
            if (!marked[u]) {
                String e;
                if (v > u)
                    e = u + "-" + v;
                else
                    e = v + "-" + u;
                edgeSet.add(e);
                dfs(g, u);
            } else {
                String e;
                if (v > u)
                    e = u + "-" + v;
                else
                    e = v + "-" + u;
                if (!edgeSet.contains(e)) {
                    hasCycle = true;
                    System.out.println("Ciclo detectado! " + e);
                    return;
                }
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public boolean isMarked(int v) {
        return marked[v];
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
        CiclosNaoDirigidos c = new CiclosNaoDirigidos(g);
        if (c.hasCycle())
            System.out.println("Tem ciclo!!!!");
        else
            System.out.println("Não tem ciclo...");

        for (int v = 0; v < g.V(); v++)
            if (c.isMarked(v))
                System.out.println(v + " foi visitado");

    }
}
