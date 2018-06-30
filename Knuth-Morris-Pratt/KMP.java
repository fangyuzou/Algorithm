public class KMP {
	private final int R = 256;
	private int M;
	private int[][] dfa;

	// Build the DFA 
	public KMP(String pat) {
		if (pat == null) throw new IllegalArgumentException("Pattern is NULL!");
		M = pat.length();
		dfa = new int[R][M];
		dfa[pat.charAt(0)][0] = 1;
		for (int X = 0, j = 1; j < M; j++) {
			for (int i = 0; i < R; i++)
				dfa[i][j] = dfa[i][X];
			dfa[pat.charAt(j)][j] = j+1;
			X = dfa[pat.charAt(j)][X];
		} 
	}

	public int search(String txt) {
		if (txt == null) throw new IllegalArgumentException("Text is NULL!");
		int i, j;
		for (i = 0, j = 0; i < txt.length() && j < M; i++) 
			j = dfa[txt.charAt(i)][j];
		if (j == M) return i - M;	// match (hit the end of pattern)
		return txt.length();		// dismatch (hit the end of text)
	}

	public static void main(String[] args) {
		String pat = args[0];
		String txt = args[1];
		KMP kmp = new KMP(pat);

		System.out.println("Text:    " + txt);
		int offset = kmp.search(txt);
		System.out.println(offset);
		System.out.print("Pattern: ");
		for (int i = 0; i < offset; i++)
			System.out.print(" ");
		System.out.println(pat);
	}
}
