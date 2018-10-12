import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class SPT {
  private int source;
  private final HashMap<Integer, Integer> distTo;
  private final HashMap<Integer, int[]> edgeTo;
  
  public SPT(int[][] edges, int source) {
    this.source = source;
    distTo = new HashMap<>();
    edgeTo = new HashMap<>();
    
    // build graph
    HashMap<Integer, List<int[]>> graph = new HashMap<>();
    for (int[] edge : edges) {
      graph.computeIfAbsent(edge[0], K -> new ArrayList<int[]>()).add(new int[]{edge[0], edge[1], edge[2]});
      graph.computeIfAbsent(edge[1], K -> new ArrayList<int[]>()).add(new int[]{edge[1], edge[0], edge[2]});
    }
    
    // initialize distTo
    for (Integer v : graph.keySet()) {
      distTo.put(v, Integer.MAX_VALUE);
    }
    distTo.put(source, 0);
    
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
    pq.offer(new int[]{source, 0});

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      if (distTo.containsKey(cur[0])) {
        continue;
      }
      distTo.put(cur[0], cur[1]);
      for (int[] edge : graph.get(cur[0])) {
        int w = edge[1];
        int wight = edge[2];
        if (cur[1] + weight < distTo.get(w)) {
          edgeTo.put(w, edge);
          pq.offer(new int[]{w, cur[1] + weight});
        }                   
      }
    }
  }
  
  public int distTo(int v) {
    return distTo.get(v);
  }
    
  public boolean hasPathTo(int v) {
    return edgeTo.containsKey(v);
  }
  
  public Iterable<int[]> pathTo(int v) {
    if (!hasPathTo(v)) {
      return null;
    }
    Stack<int[]> path = new Stack<int[]>();
    for (int[] e = edgeTo.get(v); e != null; e = edgeTo.get(e[0])) {
      path.push(e);
    }
    return path;
  }
  
  public static void main(String[] args) {
    int[][] edges = {{0, 1, 8}, {0, 4, 4}, {1, 3, 2}, {1, 2, 9}, {2, 4, 3}, {2, 4, 6}, {3, 4, 5}, {5, 6, 10}};
    SPT t = new SPT(edges, 0);
    for (int i = 0; i < 7; i++)
      System.out.println(t.distTo(i));
  }
}
