/** This little program realizes the function of a trie 
	* insert: insert a string to a trie
	* search: search whether a string is in the trie. If hit, return the count of the string; if not, return 0
	* toStringList: return a list of strings that the trie stores. The strings are automatically sorted in the 
					dictionary order
	*/

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Trie {
	private final int CHILDREN_SIZE = 26;

	private class TrieNode {
		public int count;
		public TrieNode[] children;

		public TrieNode() {
			count = 0;
			children = new TrieNode[CHILDREN_SIZE];
			for (int i = 0; i < CHILDREN_SIZE; i++)
				children[i] = null;
		}
	}

	private TrieNode root;

	public Trie() { 
		root = new TrieNode(); 
	}

	public void insert(String word) {
		TrieNode node = root;
		for (char c : word.toCharArray()) {
			int dif = c - 'a';
			if (node.children[dif] == null)
				node.children[dif] = new TrieNode();
			node = node.children[dif];
		}
		node.count++;
	} 

	public int search(String word) {
		TrieNode node = root;
		for (char c : word.toCharArray()) {
			int dif = c - 'a';
			if (node.children[dif] == null) return 0;
			if (node.children[dif].count != 0) return node.children[dif].count;
			node = node.children[dif];
		}
		return node.count;
	}

	public List<String> toStringList() {
		List<String> words = new ArrayList<>();
		toStringHelper(root, new StringBuilder(), words);
		return words;
	}

	private void toStringHelper(TrieNode node, StringBuilder prefix, List<String> words) {
		if (node.count != 0) {
			words.add(prefix.toString());
			return;
		}
		for (int i = 0; i < CHILDREN_SIZE; i++)
			if (node.children[i] != null) {
				prefix.append((char)('a' + i));
				toStringHelper(node.children[i], prefix, words);
				prefix.deleteCharAt(prefix.length() - 1);
			}
	}

	public static void main(String[] args) {
		List<String> dict = new LinkedList<>();
		dict.add("cat");
		dict.add("dog");
		dict.add("man");
		dict.add("dog");

		Trie trie = new Trie();
		for (String word : dict)
			trie.insert(word);

		int x = trie.search("dog");
		int y = trie.search("dot");

		System.out.println(x + " " + y);

		for (String word : trie.toStringList()) 
			System.out.println(word);
	}
}
