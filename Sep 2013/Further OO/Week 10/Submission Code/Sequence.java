/** Stores a sequence of integers, and provides operations on them.
  *
  * @author David Lightfoot 2010-11-15, updated by Sharon Curtis 2011-06-09,
  *         further updated by (your student name and number here)
  */
public class Sequence {

    private static long stepCount = 0;

    public static void clearNumSteps() {
        stepCount = 0;
    }

    public static long getNumSteps() {
        return stepCount;
    }
    public final int max;
    public int[] a;

    public Sequence(int max) {
        a = new int[max];
        this.max = max;
    }

    // pre 0 <= i && i < max
    // a[i] == x
    public void insertAt(int i, int x) {
        a[i] = x;
    }

    public String toString() {
        String out = "";
        for (int elem : a) {
            out += elem + "\n";
        }
        return out;
    }

    public boolean isAscending() {
        int i = 1;
        while (i < max && a[i - 1] <= a[i]) {
            i++;
        }
        return i >= max;
    }

    // pre true
    // post a[0..size-1] is ascending
    void insertionSort() {
        int i = 1;
        int x;
        int j;
        while (i < a.length) {
            stepCount++; // while loop accesses the array to determine the length
            // a[0..i-1] is ascending
            // insert value at a[i] into correct position in a[0..i-1]
            j = i;
            x = a[i]; // x is the value originally at a[i]
            stepCount++; // x=a[i] accesses element i.
            while (j != 0 && a[j - 1] > x) {
                stepCount++; // a[j-1]>x inspects a[j-1]
                // 'budge up' values that are bigger than the one at a[i]
                stepCount++; // a[j] accessing the array
                stepCount++;// a[j-1] accessing the array
                a[j] = a[j - 1];
                j--;
            }  // j == 0 OR a[j-1] <= x
            // 'drop in' x, the value that was at a[j]
            stepCount++;// a[j] accessing the array
            a[j] = x;
            i++; // advance to insert next value
        } // i >= a.length
    } // end of insertionSort

    void qSort(int low, int high) {
        int i = low, j = high, temp;
        int pivot = a[(low + high) / 2];
        stepCount++;//a[(low + high) / 2] is accessing the array

        while (i < j) {
            while (a[i] < pivot) {
                stepCount++;//a[i] < pivot is accessing the array
                i++;
            }
            while (a[j] > pivot) {
                stepCount++;//a[i] > pivot is accessing the array
                j--;
            }
            if (i <= j) {
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                stepCount++;//temp = a[i] is accessing the array
                stepCount++;//a[i] = a[j] is accessing the array twice
                stepCount++;
                stepCount++;//a[j] = temp is accessing the array
                i++;
                j--;
            }
        }
        if (low < j) {
            qSort(low, j); // recursive call
        }
        if (i < high) {
            qSort(i, high); // recursive call
        }
    } // end of insertionSort

    // pre true
    // post a[0..size-1] is ascending
    void quickSort() {
        qSort(0, max - 1);
    } // end of QSort
}
