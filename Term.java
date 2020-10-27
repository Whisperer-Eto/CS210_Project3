import java.util.Arrays;
import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class Term implements Comparable<Term> {
    private final String query; // query
    private final long weight; // weight

    // Constructs a term given the associated query string, having weight 0.
    public Term(String query) {
        if (query == null) {
            throw new NullPointerException("Null query");
        }
        this.query = query;
        this.weight = 0;
    }

    // Constructs a term given the associated query string and weight.
    public Term(String query, long weight) {
        if (query == null) {
            throw new NullPointerException("Null query");
        } else if (weight < 0) {
            throw new IllegalArgumentException("Illegal weight");
        }
        this.query = query;
        this.weight = weight;
    }

    // Returns a string representation of this term.
    public String toString() {
        // use "\t" as tab
        return weight + "\t" + query;
    }

    // Returns a comparison of this term and other by query.
    public int compareTo(Term other) {
        // return Double.compare((double) this.weight, (double) other.weight);
        return this.query.compareTo(other.query);
        // return String.CASE_INSENSITIVE_ORDER.compare(this.query, other.query);
        // change long to double;
    }

    // Returns a comparator for comparing two terms in reverse order of their weights.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    // Returns a comparator for comparing two terms by their prefixes of length r.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw  new IllegalArgumentException("Illegal r");
        }
        return new PrefixOrder(r);
    }

    // Reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {
        // Returns a comparison of terms v and w by their weights in reverse order.
        public int compare(Term v, Term w) {
            double d1 = (double) v.weight;
            double d2 = (double) w.weight;
            return Double.compare(d2, d1);
        }
    }

    // Prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {
        private final int r; // length

        // Constructs a new prefix order given the prefix length.
        PrefixOrder(int r) {
            this.r = r;
        }

        // Returns a comparison of terms v and w by their prefixes of length r.
        public int compare(Term v, Term w) {
            int a = Math.min(r, v.query.length());
            int b = Math.min(r, w.query.length());
            return (v.query.substring(0, a).compareTo(w.query.substring(0, b)));
        }
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        StdOut.printf("Top %d by lexicographic order:\n", k);
        Arrays.sort(terms);
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
        StdOut.printf("Top %d by reverse-weight order:\n", k);
        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
    }
}
