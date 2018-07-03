private class Digraph {
    private int V;
    private int E;
    private List<Integer>[] adj;
        
    public Digraph(int N) {
        V = N;
        E = 0;
        adj = new List[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<Integer>();
    }
        
    public void addEdge(int v, int w) { adj[v].add(w); E++; }     
    public int V() { return V; }
    public int E() { return E; }
    public List<Integer> adj(int v) { return adj[v]; }
}
