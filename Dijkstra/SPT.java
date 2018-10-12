import java.util.HashMap;
import java.util.ArrayList;

public class SPT {
  private int source;
  private final HashMap<Integer, int[]> spt;
  private final HashMap<Integer, ArrayList<int[]>> graph;
  
  public SPT(int[][] edges, int source) {
    this.source = source;
    
    // build graph
    for (int[] edge : edges) {
      graph.computeIfAbsent(edge[0], K -> new ArrayList<int[]>()).add(edge);
      graph.computeIfAbsent(edge[1], K -> new ArrayList<int[]>()).add(new int[]{edge[1], edge[0], edge[2]});
    }
    
    spt = new HashMap<Integer, int[]>();
    PriorityQueue<int[]> pq = new PriorityQueue<>();
    
    // initialize pq
    pq.offer(source, 0);
    for (int v : graph.keySet()) if (v != source) {
      pq.offer(v, Integer.MAX_VALUE);
    }
    
    int prev = source;
    while (!pq.isEmpty()) {
      int[] w = pq.poll();
      if (spt.containsKey(w[0])) {
        continue;
      }
      spt.put(new int[]{w[0], w[1], prev});
      prev = w[0];
      for (int[] edge : graph.get(w[0])) {
        pq.offer(new int[]{edge[1], edge[2] + spt.get(w[0])[1]);
      }
    }
  }
  
  public int distance(int target) {
    return spt.get(target).[1];
  }
}
