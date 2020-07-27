

/** Holds an ascending sequence, and provides various handy methods
  * to initialise and manipulate the sequence.
  *
  * The sequence can vary in length, up to a maximum capacity max.
  *
  * The datatype invariant for this ascending sequence is as follows:
  *     0 <= size <= max
  *     and for all j in the range 1 to size-1,  a[j-1] <= a[j]
  *
  * @authors David Lightfoot, Ian Bayley, Sharon Curtis
  */
public class AscendingSequence {


    private int[] a;  // note the datatype invariant above
    private int max;  // for storing the maximum length of the sequence
    private int size; // the current length of the sequence

    /** Constructs an empty ascending sequence.
      *
      * @param max   the max capacity of this sequence
      * @pre         max >= 0
      * @post        empty ascending sequence of capacity max has been created
      */
    public AscendingSequence(int max) {
        this.max = max;
        a = new int[max];
        size = 0;
    }

    /** Inserts an integer into the correct place in the sequence.
      *
      * @param x   the integer to be inserted
      * @pre       size != max
      * @post      x has been inserted into correct place in a
      */
    public void insert(int x) {
        assert size != max;
        int i = size;
        while (i != 0 && a[i - 1] > x) {
            a[i] = a[i - 1];
            i--;
        }
        assert i == 0 || a[i - 1] <= x;
        a[i] = x;
        size++;
    }

    /** Empties the sequence.
      *
      * @pre   true
      * @post  sequence is now empty
      */
    public void clear() {
        size = 0;
    }

    /** Inserts all integers from an array into the sequence.
      *
      * @param xs    the array containing the integers to insert
      * @pre         size + xs.length <= max
      * @post        all integers from xs have been inserted into the correct places in a
      */
    public void insertLots(int[] xs) {
        assert size + xs.length <= max;
        int i = 0;
        while (i != xs.length) {
            this.insert(xs[i]);
            i++;
        }
    }

    /** Removes the element at a given position.
      *
      * @param i     the index from which to remove the element
      * @pre         0 <= i && i < size
      * @post        element at position i has been removed from a
      */
    public void remove(int i) {
        assert 0 <= i && i < size;
        while (i < size - 1) {
            a[i] = a[i + 1];
            i++;
        }
        size--;
    }

    /** Returns a string representation of this sequence.
      *
      * @pre    true
      * @post   returned value is string representation of this
      * @return a string representation of this sequence
      */
    public String toString() {
        int i = 0;
        String out = "[";
        while (i < size - 1) {
            out += a[i] + ", ";
            i++;
        }
        if (i == size - 1) {
            out += a[i];
        }
        return out + "]";
    }

    /** Returns a string representation of the entire array, not just
      * the sequence within the array.
      *
      * @pre     true
      * @post    returned value is string representation of entire array
      * @return  a string representation of the entire array
      */
    public String containerArrayAsString() {
        int i;
        String out;
        i = 0;
        out = "Container array: max=" + max + " and size=" + size + "\n [";
        while (i < max - 1) {
            out += a[i] + ", ";
            i++;
        }
        if (i == max - 1) {
            out += a[i];
        }
        return out + "]";
    }

    /* Searches for a given value in the sequence.
     *
     * @param val   the value to search for in the sequence
     * @pre         true
     * @post        the return value indicates whether val is in the sequence
     * @return      true precisely when the sought value is in the sequence
     */
    public boolean search(int val) {
    // uses a binary search to look for the value

        int low = 0;
        int high = size-1;
        int middle = -1;
        boolean found = false;

        
        //While loop condition changed from low = high to low <= high
        while (!found && low <= high) {

            middle = low + (high-low)/2;
       
            if (a[middle]==val)
               found = true;
            else if (a[middle] < val)
               low = middle + 1;
            else // (a[middle] > val)
               high = middle - 1;
        }

        return found;
    }

}

