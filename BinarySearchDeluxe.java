
import java.util.Arrays;
import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class BinarySearchDeluxe {
    // Returns the index of the first key in a that equals the search key, or -1, according to
    // the order induced by the comparator c.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> c) {

        if (a == null || key == null || c == null) {
            throw new NullPointerException("Null a, key, or c");
        }

        int low = 0; // low number
        int length = a.length; // length
        int high = length -1; // high number

        if (length == 0) {
            return -1; // if a is [], then length is 0, then return -1
        }

        if (c.compare(a[0], key) == 0) {
            return 0;
        }

        while (low <= high) { // Binary Search
            int mid = low + (high - low)/2; // mid number

            if (c.compare(key, a[mid]) < 0) {
                high = mid -1;
            } else if (c.compare(key, a[mid]) > 0) {
                low = mid +1;
            } else if (c.compare(a[mid-1], a[mid]) == 0) {
                high = mid-1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // Returns the index of the first key in a that equals the search key, or -1, according to
    // the order induced by the comparator c.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> c) {

        if (a == null || key == null || c == null) {
            throw new NullPointerException("Null a, key, or c");
        }

        int low = 0; // low number
        int length = a.length; // length
        int high = length -1; // high number

        if (length == 0) {
            return -1; // if a is [], then length is 0, then return -1
        }

        if (c.compare(a[high], key) == 0) {
            return high; // opposite binary search
        }

        while (low <= high) { // Binary Search
            int mid = low + (high - low)/2; // mid number

            if (c.compare(key, a[mid]) < 0) {
                high = mid -1;
            } else if (c.compare(key, a[mid]) > 0) {
                low = mid +1;
            } else if (c.compare(a[mid+1], a[mid]) == 0) {
                low = mid+1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // Unit tests the library. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        String prefix = args[1];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        Arrays.sort(terms);
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int count = i == -1 && j == -1 ? 0 : j - i + 1;
        StdOut.println("firstIndexOf(" + prefix + ") = " + i);
        StdOut.println("lastIndexOf(" + prefix + ")  = " + j);
        StdOut.println("frequency(" + prefix + ")    = " + count);
    }
}
