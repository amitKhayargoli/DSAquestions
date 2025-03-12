import java.util.Arrays;

public class Kruskal {
    static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    int vertices;
    Edge[] edges;
    int edgeIndex = -1;

    static int[] parent;
    static int[] size;

    Kruskal(int vertices) {
        this.vertices = vertices;
        edges = new Edge[vertices * (vertices - 1) / 2];
        parent = new int[vertices];
        size = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            parent[i] = -1;
            size[i] = 1;
        }
    }

    void addEdges(int u, int v, int w) {
        edges[++edgeIndex] = new Edge(u, v, w);
    }

    // MST Start
    void KruskalAlgorithm() {
        int mstEdges = 0;

        // Sort edges by weight
        Arrays.sort(edges, 0, edgeIndex + 1);

        System.out.println("Edges in the Minimum Spanning Tree:");

        for (int i = 0; i <= edgeIndex && mstEdges < vertices - 1; i++) {
            Edge e = edges[i];

            int uRoot = find(e.u);
            int vRoot = find(e.v);

            if (uRoot != vRoot) {
                System.out.println(e.u + " -- " + e.v + " == " + e.w);
                union(uRoot, vRoot);
                mstEdges++;
            }
        }
    }

    static void union(int uRoot, int vRoot) {
        if (size[uRoot] > size[vRoot]) {
            parent[vRoot] = uRoot;
        } else if (size[uRoot] < size[vRoot]) {
            parent[uRoot] = vRoot;
        } else {
            parent[uRoot] = vRoot;
            size[vRoot]++;
        }
    }

    static int find(int u) {
        if (parent[u] == -1) {
            return u;
        }
        return parent[u] = find(parent[u]);
    }

    public static void main(String[] args) {
        Kruskal graph = new Kruskal(6);

        graph.addEdges(0, 1, 2); // A -> B
        graph.addEdges(0, 4, 2); // A -> E
        graph.addEdges(1, 2, 5); // B -> C
        graph.addEdges(1, 4, 1); // B -> E
        graph.addEdges(2, 5, 2); // C -> Z
        graph.addEdges(2, 3, 5); // C -> D
        graph.addEdges(3, 5, 3); // D -> Z
        graph.addEdges(4, 2, 2); // E -> C

        graph.KruskalAlgorithm();
    }
}
