
/** A Java implementation of the Nondeterministic Finite Automaton */

import java.util.Stack;
import java.util.Set;
import java.util.HashSet;

public class NFA {
	char[] re;	// match transitions
	Digraph G;	// epsilon transitions
	int M;		// max number of states

  // build the Digraph of epsilon-transitions
	public NFA(String regexp) {
		Stack<Integer> ops = new Stack<>();
		this.re = regexp.toCharArray();
		this.M = this.re.length;
		this.G = new Digraph(this.M + 1);

		for (int i = 0; i < M; i++) {
			int lp = i;
			if (re[i] == '(' || re[i] == '|')
				ops.push(i);
			else if (re[i] == ')') {		
				int or = ops.pop();
				if (re[or] == '|') {
					lp = ops.pop();
					G.addEdge(lp, or + 1);
					G.addEdge(or, i);
				}
				else lp = or;
			}

			if (i < M-1 && re[i+1] == '*') { 	
				G.addEdge(lp, i+1);
				G.addEdge(i+1, lp);			// the case (abc)*
			}

			if (re[i] == '(' || re[i] == '*' || re[i] == ')')
				G.addEdge(i, i+1); 
		}
	}

  // match the string
	public boolean recognizes(String txt) {
		Set<Integer> pc = new HashSet<>();
		pc.add(0);
		bfs(pc);

		for (int i = 0; i < txt.length(); i++) {
			System.out.println("The current states are: ");
			for (int v : pc) System.out.print(v + " ");
			System.out.println(); 
			Set<Integer> match = new HashSet<>();
			for (int v : pc) 
				if (v < M && (re[v] == txt.charAt(i) || txt.charAt(i) == '.'))
					match.add(v+1);

			pc = match;
			bfs(pc);
		}

		for (int v : pc) if (v == M) return true;
		return false;
	}

	private void bfs(Set<Integer> pc) {
		boolean[] marked = new boolean[G.V()];
		
		Set<Integer> current = new HashSet<>();
		current.addAll(pc);

		while (!current.isEmpty()) {
			Set<Integer> next = new HashSet<>();
			for (int v : current) {
				marked[v] = true;
				for (int w : G.adj(v))
					if (!marked[w]) {
						marked[w] = true;
						next.add(w);
					}
				pc.addAll(next);
				current = next;
			}
		}
	}

	public static void main(String[] args) {
		String txt = args[0];
		String regexp = "((a*b|ac)d)";
		NFA nfa = new NFA(regexp);
		System.out.println("The regular expression is:" + regexp);
		System.out.println(txt + " matches the regular expression: " + nfa.recognizes(txt));
	}
}
