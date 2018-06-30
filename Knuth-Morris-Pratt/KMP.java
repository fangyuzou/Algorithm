public class KMP {
  private final int R = 256;
  private int M;        // number of states
  private int[][] dfa;  // deterministic finiate antomaton
  
  // Build the DFA
  public KMP(String pat) {
      M = pat.length();
      dfa = new int[R][M];
      dfa[pat.charAt(0)][0] = 1;
      for (int X = 0, j = 1; j < M; j++) {    // X backup state, j current state
          for (int i = 0; i < R; i++)
              dfa[i][j] = dfa[X][j];              // mismatch
          dfa[pat.charAt(j)][j] = j+1;          // match
          X = dfa[pat.charAt(j)][X];            // update the backup state
      }
  }
  
  // Search the string
  public int search(String txt) {
      if (txt == null) return -1;
      for (int i = 0; i < txt.length(); i++)
          if (dfa[txt.charAt(i)][M-1] == M) return i - M;
      return txt.length();
  }
  
  public static void main(String[] args) {
      String pat = args[0];
      String txt = args[1];
      KMP kmp = new KMP(pat);
      System.out.println("Text:    " + txt);
      int offset = kmp.search(txt);
      System.out.print("Pattern: ");
      for (int i = 0; i < offset; i++)
          System.out.print(" ");
      System.out.println(pat);
  }
