import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class AdjMatrix {

    public static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;

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

    int[][] matrix;
    int vertices;

    // Minimum Spanning Tree

    Edge[] edges;
    int edgeIndex = -1;

    AdjMatrix(int vertices) {
        this.vertices = vertices;
        matrix = new int[vertices][vertices];

        // Edges array to store list of Edge Objects
        edges = new Edge[vertices * (vertices - 1) / 2];

    }

    void addEdges(int u, int v, int w) {
        matrix[u][v] = w;
        matrix[v][u] = w;

        edges[++edgeIndex] = new Edge(u, v, w); // MST adding edge to list

    }

    // MST Start
    void KrushkalAlgorithm() {
        int parent[] = new int[vertices];

        int size[] = new int[vertices];

        int mst[][] = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            parent[i] = -1;
        }

        Arrays.sort(edges);

        int edgeTaken = 0;

        int edgeCounter = -1;

        // e=v-1
        while (edgeTaken < vertices) {

            Edge e = edges[++edgeCounter];
            int uabsroot = find(e.u, parent);
            int vabsroot = find(e.v, parent);

            if (uabsroot == vabsroot) {
                // Cycle Detected
                continue;
            }
            mst[e.u][e.v] = e.w;
            mst[e.v][e.u] = e.w;

            union(uabsroot, vabsroot, parent, size);

            edgeTaken++;
        }

    }

    void union(int uabsroot, int vabsroot, int parent[], int size[]) {
        if (size[uabsroot] > size[vabsroot]) {
            parent[vabsroot] = uabsroot;
        } else if (size[uabsroot] < size[vabsroot]) {
            parent[uabsroot] = vabsroot;

        } else {
            parent[uabsroot] = vabsroot;

            size[vabsroot]++;
        }
    }

    int find(int u, int parent[]) {
        if (parent[u] == -1) {
            return u;
        }

        return parent[u] = find(parent[u], parent);
    }

    // MST END

    void printGraph() {
        for (int i = 0; i < vertices; i++) {
            // row
            System.out.print(i + " is connected to ");
            for (int j = 0; j < vertices; j++) {
                // column
                if (matrix[i][j] != 0) {
                    // i,j are connected
                    System.out.print(j + " , ");
                }
            }
            System.out.println();
        }
    }

    List<Integer> getAdjNodes(int i) {

        List<Integer> adjnodes = new ArrayList<>();
        for (int j = 0; j < vertices; j++) {
            // column
            if (matrix[i][j] != 0) {
                // i,j are connected
                adjnodes.add(j);
            }
        }
        return adjnodes;
    }

    // print matrix
    void printMatrix() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // breadth first search

    void BFS(int source) {
        Queue q = new Queue(vertices);
        boolean visited[] = new boolean[vertices];
        q.add(source);
    }

    int dijkstra(int source, int destination) {
        int dist[] = new int[vertices];
        int prevpath[] = new int[vertices];
        boolean visited[] = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            prevpath[i] = -1;

        }

        dist[source] = 0;

        for (int i = 0; i < vertices; i++) {
            // find minimum vertex

            int minvertex = findMinvertex(dist, visited);
            visited[minvertex] = true;

            for (int j = 0; j < vertices; j++) {
                if (matrix[minvertex][j] != 0) {

                    if (!visited[j] && dist[minvertex] + matrix[minvertex][j] < dist[j]) {

                        dist[j] = dist[minvertex] + matrix[minvertex][j];

                        prevpath[j] = minvertex;
                    }

                    // PRIMS algorithm

                    if (!visited[j] && matrix[minvertex][j] < dist[j]) {

                        dist[j] = matrix[minvertex][j];

                        prevpath[j] = minvertex;
                    }
                }
            }

        }

        int x = destination;

        Stack<Integer> stk = new Stack<>();
        stk.push(x);

        while (prevpath[x] != -1) {
            x = prevpath[x];
            stk.push(x);
        }

        while (!stk.isEmpty()) {
            System.out.println(stk.pop());
        }

        return dist[destination];
    }

    int findMinvertex(int[] dist, boolean[] visited) {

        int min = -1;

        for (int i = 0; i < vertices; i++) {
            if (min == -1 && !visited[i] || dist[i] < dist[min] && !visited[i]) {
                min = i;
            }
        }
        return min;
    }

    public static void main(String[] args) {

        AdjMatrix adj = new AdjMatrix(6);
        adj.addEdges(0, 1, 10);
        adj.addEdges(0, 5, 100);
        adj.addEdges(0, 2, 5);
        adj.addEdges(1, 2, 2);
        adj.addEdges(1, 3, 5);
        adj.addEdges(2, 3, 10);
        adj.addEdges(2, 4, 40);
        adj.addEdges(3, 5, 2);
        adj.addEdges(4, 5, 5);

        adj.printGraph();
        adj.printMatrix();
        adj.getAdjNodes(0);

        System.out.println(adj.dijkstra(0, 1));
    }

}
