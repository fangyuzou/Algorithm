public class UF {
    private int[] id;
    private int[] sz;
    private int count;

    public UF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
    
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];    //path compressing
            i = id[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {      //adding the smaller tree to the larger tree
            id[i] = j; sz[j] += sz[i]; count--;
        }
        else {
            id[j] = i; sz[i] += sz[j]; count--;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int count() {        //number of components
        return count;
    }

    public static void main(String[] args) {
        UF uf = new UF(10);
        uf.union(1, 2);
        uf.union(3, 7);
        uf.union(2, 8);
        uf.union(9, 1);
        uf.union(4, 6);
        uf.union(5, 2);

        System.out.println(uf.count());
        System.out.println(uf.connected(1, 4));    
    }
}
