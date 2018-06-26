/** A Java implementation of the Directed Graph (Digraph) data structure 
 */

import java.util.Set;
import java.util.HashSet;

public class Digraph {
 	private int V;
 	private int E;
 	private Set<Integer>[] adj;

 	public Digraph(int V) {
 		this.V = V;
 		this.E = 0;
    
        // Java does not support the array of generic type. Therefore we 
        // need to cast it to the type we want. This will trigger a warning.
 		this.adj = (Set<Integer>[]) new Set[V];
    
 		for (int i = 0; i < V; i++) {
 			adj[i] = new HashSet<Integer>();
 		}
 	}

 	public void addEdge(int v, int w) {
 		adj[v].add(w);
 		E++;
 	}

 	public int V() {
 		return V;
 	}

 	public int E() {
 		return E;
 	}

 	public Set<Integer> adj(int v) {
 		return adj[v];
 	}

 	public Digraph reverse() {
 		Digraph R = new Digraph(V);
 		for (int v = 0; v < V; v++)
 			for (int w : adj[v])
 				R.addEdge(w, v);
 		return R;
 	}

 	public void print() {
 		for (int v = 0; v < V; v++) {
 			System.out.print("Neighbours of vertex " + v + ":");
 			for (int w : adj[v])
 				System.out.print(w + " ");
 			System.out.println();
 		}
 	}

 	public static void main(String[] args) {
 		int V = 5;
 		int[][] edges = new int[][]{{1,2}, {3,4}, {0,2}, {1,4}, {2,3}};
 		Digraph G = new Digraph(V);
 		for (int[] edge : edges)
 			G.addEdge(edge[0], edge[1]);
 		System.out.println("Number of vertices: " + G.V());
 		System.out.println("Number of edges: " + G.E());
 		G.print();

 		Digraph R =G.reverse();
 		R.print();
 	}
 }
