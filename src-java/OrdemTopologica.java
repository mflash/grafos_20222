
public class OrdemTopologica {
    private boolean[] marked;
    private Queue<Integer> preOrder;
    private Queue<Integer> postOrder;
    private Stack<Integer> reversePost; // esta é que a que precisamos

    public OrdemTopologica(Digraph g) {
        marked = new boolean[g.V()];
        preOrder = new Queue<>();
        postOrder = new Queue<>();
        reversePost = new Stack<>();
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s])
                dfs(g, s);
        }
    }

    public void dfs(Digraph g, int v) {
        marked[v] = true;
        preOrder.enqueue(v);
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        postOrder.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> getPreOrder() {
        return preOrder;
    }

    public Iterable<Integer> getPostOrder() {
        return postOrder;
    }

    public Iterable<Integer> getReversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        In in = new In("tinyDAG.txt");
        Digraph g = new Digraph(in);
        System.out.println("Vértices: " + g.V());
        for (int v = 0; v < g.V(); v++) {
            System.out.print(v + ": ");
            for (int w : g.adj(v))
                System.out.print(w + " ");
            System.out.println();
        }
        System.out.println();
        System.out.println(g.toDot());
        OrdemTopologica ot = new OrdemTopologica(g);
        System.out.println(ot.getPreOrder());
        System.out.println(ot.getPostOrder());
        System.out.println(ot.getReversePost());
    }
}
