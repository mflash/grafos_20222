from graph import Graph
import queue

class BFS:

    def __init__(self, graph, s):
        self.graph = graph
        self.s = 0

        self.marked = [False] * len(graph.verts)
        self.edgeTo = [-1] * len(graph.verts)
        self.distTo = [0] * len(graph.verts)

        self.bfs(s)

    def bfs(self, s):
        q = queue.Queue()
        q.put(s)
        self.marked[s] = True
        self.distTo[s] = 0
        self.edgeTo[s] = -1
        while not q.empty():
            v = q.get()
            for w in self.graph.adj(v):
                if not self.marked[w]:
                    self.marked[w] = True
                    self.edgeTo[w] = v
                    self.distTo[w] = self.distTo[v] + 1
                    q.put(w)

    def hasPathTo(self, v):
        return self.marked[v]

    def pathTo(self, w):
        if not self.hasPathTo(w):
            return None
        path = []
        while w != self.s:
            path.insert(0,w)
            w = self.edgeTo[w]
        path.insert(0,self.s)
        return path

        
if __name__ == "__main__":

    g = Graph('tinyG.txt')
    #g = Graph()

    #g.addEdge(0, 2);
    #g.addEdge(2, 1);
    #g.addEdge(2, 4);
    #g.addEdge(1, 3);
    #g.addEdge(3, 4);

    g.toDot()

    bfs = BFS(g, 0)

    for v in range(g.totalVerts):
        if bfs.hasPathTo(v):
            print(f"{v}: {bfs.pathTo(v)}")



