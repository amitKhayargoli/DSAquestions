import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, weight;

    Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight; // Sort edges in increasing order
    }
}

class DisjointSet {
    int[] parent, rank;

    DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY)
            return false;

        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}

public class UndirectedGraph {
    public static int findLargestK(int n, List<Edge> edges) {
        Collections.sort(edges); // Sort edges by weight

        DisjointSet dsu = new DisjointSet(n);
        int maxWeightInMST = 0;

        for (Edge edge : edges) {
            if (dsu.union(edge.u, edge.v)) { // If adding edge does not form a cycle
                maxWeightInMST = Math.max(maxWeightInMST, edge.weight);
            }
        }

        return maxWeightInMST;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 5));
        edges.add(new Edge(1, 2, 10));
        edges.add(new Edge(0, 2, 15));
        edges.add(new Edge(2, 3, 20));

        int n = 4; // Number of vertices
        System.out.println("Largest k: " + findLargestK(n, edges)); // Output: 15
    }
}
