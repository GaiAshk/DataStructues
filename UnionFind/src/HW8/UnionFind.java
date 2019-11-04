package HW8;


/**
 * Implementation of the Union-Find ADT.
 */
public class UnionFind {

    private int up[];
    private int weight[];
    int numSets;


    /**
     * Constructor - initializes up and weight arrays.
     *
     * @param numElements initial number of singleton sets.
     */
    public UnionFind (int numElements) {
        up = new int[numElements];
        weight = new int[numElements];
        numSets = numElements;
        for (int i = 0; i < numElements; i++) {
            up[i] = -1;
            weight[i] = 1;
        }
    }

    /**
     * Unites two sets using weigthed union.
     *
     * @param i representative of first set.
     * @param j representative of second set.
     */
    public void union (int i, int j) {
        if(weight[i] >= weight[j]) {
            weight[i] += weight[j];
            weight[j] = 0;
            up[j] = i;
        } else {
            weight[j] += weight[i];
            weight[i] = 0;
            up[i] = j;
        }
        numSets--;
    }

    /**
     * Finds the set representative, and applies path compression.
     *
     * @param i the element to search.
     * @return the representative of the group that contains i.
     */
    public int find (int i) {
        int r = i;
        //finding the root and setting it to i
        while (up[r] != -1){
            r = up[r];
        }
        //compression of the path, after the root is known
        if (i != r) {
            int k = up[i];
            while (k != r) {
                up[i] = r;
                i = k;
                k = up[k];
            }
        }
        return r;
    }

    /**
     * Find the current number of sets.
     *
     * @return the number of set.
     */
    public int getNumSets() {
        return numSets;
    }

    /**
     * Prints the contents of the up array.
     */
    private void debugPrintUp() {

        System.out.println ("");
        System.out.print ("up:     ");
        for (int i = 1; i < up.length; i++)
            System.out.print (up[i] + " ");
        System.out.println ("");
    }

    /**
     * Prints the results of running find on all elements.
     */
    private void debugPrintFind() {

        System.out.print ("find:   ");
        for (int i = 1; i < up.length; i++)
            System.out.print (find (i) + " ");
        System.out.println ("");
    }

    /**
     * Prints the contents of the weight array.
     */
    private void debugPrintWeight() {

        System.out.print ("weight: ");
        for (int i = 1; i < weight.length; i++)
            System.out.print (weight[i] + " ");
        System.out.println ("");
    }

    /**
     * Various tests for the Union-Find functionality.
     *
     * @param args command line arguments - not used.
     */
    public static void main (String[] args) {

        UnionFind uf = new UnionFind (10);

        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println ("Number of sets: " + uf.getNumSets());
        System.out.println ("");

        uf.union (2, 1);
        uf.union (3, 2);
        uf.union (4, 2);
        uf.union (5, 2);

        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println ("Number of sets: " + uf.getNumSets());
        System.out.println();

        uf.union (6, 7);
        uf.union (8, 9);
        uf.union (6, 8);

        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println ("Number of sets: " + uf.getNumSets());
        System.out.println();

        uf.union (6, 2);
        uf.find(9);

        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println ("Number of sets: " + uf.getNumSets());
        System.out.println();

    }
}
