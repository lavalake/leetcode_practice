import java.util.*;

/**
 * Created by jian1.w on 12/19/18.
 * add all edges to priorityQueue
 * get minmum edge from queue and add to result
 * update all adjacent vertex value
 * until we get V-1 edges in result
 */
public class MiminumSpanningTree {
    int[] parent;
    static public class Node implements Comparable<Node> {
        int source;
        int des;
        int weight;
        public Node(int source, int des, int weight) {
            this.source = source;
            this.des = des;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node nodes) {
            return this.weight - nodes.weight;
        }
    }

    public List<Node> MST(int V, int[][] edges) {
        List<List<Node>> nodes = new ArrayList<>();
        List<Node> result = new ArrayList<>();
        parent = new int[V];
        for (int i=0; i<V; i++){
            nodes.add(new ArrayList<Node>());
            parent[i] = i;
        }
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int[] e : edges) {
            Node n = new Node(e[0], e[1], e[2]);
            queue.offer(n);
            nodes.get(e[0]).add(n);
        }
        while (result.size() < V && !queue.isEmpty()) {
            Node n = queue.poll();
            if (find(n.source) != find(n.des)) {
                result.add(n);
                union(n.source, n.des);
            }
        }
        Iterator<Node> itr = result.iterator();
        while (itr.hasNext()) {
            Node n = itr.next();
            System.out.println(n.source + "  --  " + n.des + " == " + n.weight);
        }
        return result;
    }

    private int find(int v) {
        while (parent[v] != v) {
            v = parent[v];
        }
        return v;
    }

    private void union(int src, int des) {
        parent[find(src)] = find(des);
    }

}
