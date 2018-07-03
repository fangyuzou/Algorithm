/** Given a directed graph, it is a DAG (Directd Acylic Gaph) if it has no cycles. A DAG gives a partial order on the set of 
  * vertices. We can topological sort the vertices such that the parent vertices are always before their children. DFS can be 
  * implemented to such a topological sort. The topological sort is equivalent to the reverse postorder transverse. (Using a 
  * stack and push the vertices to the stack after the recursion.)
  *
  * We want to do the cycle detection together with the topological sort. Using vertex coloring would be most clear way: 
  *     WHITE if a vertex has not been visited
  *     GRAY  if a vertex has been visited but we havn't visited all the vertices in the subtree (we are still in the 
  *           recursion)
  *     BLACK if a vertex and all vertices in its subtree have been visited (we leave the recursion) 
  * 
  * A cycle is detected when we encounter a GRAY vertex.
  *
  */
  
public class TopoSort {
    private final int WHITE = 0;
    private final int GRAY = 1;
    private final int BLACK = 2;
    private final Digraph G;
    private int[] color;
    private Stack<Integer> stack;
    
    public TopoSort(Digraph G) {
        this.G = G;
        color = new int[G.V()];
        stack = new Stack<Integer>();
    }
    
    /* if it is DAG, return the topological order; else, return empty list */
    public List<Integer> topOrder() {
        List<Integer> order = new ArrayList<>();
        for (int v = 0; v < G.V(); v++)
            if (color[v] == WHITE && !dfs(G, v)) return order;      // dfs returns false, cycle detected
        while (!stack.isEmpty())  order.add(stack.pop());
        return order;
    }
    
    private boolean dfs(Digraph G, int v) {
        color[v] = GRAY;        // first time visit v
        for (int w : G.adj(v)) {
            if (color[w] == BLACK) continue;
            if (color[w] == GRAY || !dfs(G, w)) return false;       // cycle detected
        }
        color[v] = BLACK;       // leaving the recursion of v
        stack.push(v);
        return true;
    }
}
