package remainingQuestions;

import java.util.Arrays;

public class KruskalDisconnect {
    static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return o.w - this.w; // Sort in descending order
        }
    }

    int vertices;
    Edge[] edges;
    int edgeIndex = -1;

    static int[] parent;
    static int[] size;

    KruskalDisconnect(int vertices) {
        this.vertices = vertices;
        edges = new Edge[vertices * (vertices - 1) / 2];
        parent = new int[vertices];
        size = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            parent[i] = i; // Initialize each node as its own parent
            size[i] = 1;
        }
    }

    void addEdge(int u, int v, int w) {
        edges[++edgeIndex] = new Edge(u, v, w);
    }

    int findLargestKForDisconnection() {
        Arrays.sort(edges, 0, edgeIndex + 1); // Sort edges by weight descending
        int components = vertices;

        for (int i = 0; i <= edgeIndex; i++) {
            Edge e = edges[i];

            int uRoot = find(e.u);
            int vRoot = find(e.v);

            if (uRoot != vRoot) {
                union(uRoot, vRoot);
                components--;
            }

            // If removing this edge causes disconnection, return its weight
            if (components > 1) {
                return e.w;
            }
        }
        return -1; // Should never reach here since the graph starts connected
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
        if (parent[u] != u) {
            parent[u] = find(parent[u]); // Path compression
        }
        return parent[u];
    }

    public static void main(String[] args) {
        KruskalDisconnect graph = new KruskalDisconnect(6);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 4, 2);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 5, 2);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 5, 3);
        graph.addEdge(4, 2, 2);

        int largestK = graph.findLargestKForDisconnection();
        System.out.println("Largest k that disconnects the graph: " + largestK);
    }
}