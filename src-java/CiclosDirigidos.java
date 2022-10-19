import java.util.ArrayList;

public class CiclosDirigidos {

    private enum Estado {
        WHITE, GRAY, BLACK
    }

    private Estado[] marked;
    private boolean hasCycle;

    public CiclosDirigidos(Digraph g) {
        marked = new Estado[g.V()];
        for (int v = 0; v < g.V(); v++)
            marked[v] = Estado.WHITE;
        for (int v = 0; v < g.V(); v++) {
            if (marked[v] == Estado.WHITE) {
                if (visit(g, v)) {
                    hasCycle = true;
                    return;
                }
            }
        }
        hasCycle = false;
    }

    private boolean visit(Digraph g, int v) {
        marked[v] = Estado.GRAY;
        for (int u : g.adj(v)) {
            if (marked[u] == Estado.GRAY) {
                System.out.println("Ciclo detectado em " + v + "->" + u);
                return true;
            } else if (marked[u] == Estado.WHITE)
                if (visit(g, u))
                    return true;
        }
        marked[v] = Estado.BLACK;
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        In in = new In("tinyDG.txt");
        Digraph g = new Digraph(in);
        System.out.println(g.toDot());
        System.out.println();
        CiclosDirigidos c = new CiclosDirigidos(g);
        if (c.hasCycle())
            System.out.println("Tem ciclo!!!!");
        else
            System.out.println("Não tem ciclo...");
    }
}
