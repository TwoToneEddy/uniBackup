

import java.util.*; // this package provides random number generating facilities

/** This class contains some code
  *
  * @author David Lightfoot 2010-11-15, updated by Sharon Curtis 2011-06-09,
  *         further updated by (your student name and number here)
  */
public class SortExplore {

    public static void main(String[] args) {

        int numElements = 10000; // edit this to change number of elements in array

        // first to set up a sequence with random numbers
        Random generator = new Random();
        Sequence s = new Sequence(numElements);
        for (int i = 0; i < numElements; i++) {
            s.insertAt(i, generator.nextInt());
        }

        Sequence oldS = new Sequence(numElements); // will hold original sequence
        // now to make oldS a copy of s
        for (int i = 0; i < numElements; i++) {
            oldS.a[i] = s.a[i];
        }



        System.out.println("Before sorting by Insertion Sort");
        System.out.println("sequence is ascending?:  " + s.isAscending());
        // System.out.println(s);
        Sequence.clearNumSteps();
        System.out.println("STARTING Insertion Sort");
        s.insertionSort();
        System.out.println("Insertion Sort done");

        System.out.println("After sorting by Insertion Sort ");
        System.out.println("sequence is ascending?:  " + s.isAscending());
        System.out.println("Number of steps is: " + Sequence.getNumSteps()+"\n");
        
        System.out.println("Before sorting by Insertion Sort");
        System.out.println("sequence is ascending?:  " + s.isAscending());
        // System.out.println(s);
        Sequence.clearNumSteps();
        System.out.println("STARTING Insertion Sort");
        s.insertionSort();
        System.out.println("Insertion Sort done");

        System.out.println("After sorting by Insertion Sort ");
        System.out.println("sequence is ascending?:  " + s.isAscending());
        System.out.println("Number of steps is: " + Sequence.getNumSteps()+"\n");
        //  System.out.println(s);


        Sequence.clearNumSteps();
        System.out.println("Restoring unsorted sequence ");
        for (int i = 0; i < numElements; i++) { // restoring original s
            s.a[i] = oldS.a[i];
        }
        System.out.println("STARTING QuickSort");
        s.quickSort();
        System.out.println("QuickSort done");

        System.out.println("After sorting by QuickSort ");
        System.out.println("sequence is ascending?:  " + s.isAscending());
        System.out.println("Number of steps is: " + Sequence.getNumSteps()+"\n");
        
         Sequence.clearNumSteps();
        System.out.println("Restoring unsorted sequence ");
        for (int i = 0; i < numElements; i++) { // restoring original s
            s.a[i] = oldS.a[i];
        }
        System.out.println("STARTING QuickSort");
        s.quickSort();
        System.out.println("QuickSort done");

        System.out.println("After sorting by QuickSort ");
        System.out.println("sequence is ascending?:  " + s.isAscending());
        System.out.println("Number of steps is: " + Sequence.getNumSteps()+"\n");
    }
    
    /*
     Before sorting by Insertion Sort
        sequence is ascending?:  false
        STARTING Insertion Sort
        Insertion Sort done
        After sorting by Insertion Sort 
        sequence is ascending?:  true
        Number of steps is: 74979702

        Before sorting by Insertion Sort
        sequence is ascending?:  true
        STARTING Insertion Sort
        Insertion Sort done
        After sorting by Insertion Sort 
        sequence is ascending?:  true
        Number of steps is: 29997

        Restoring unsorted sequence 
        STARTING QuickSort
        QuickSort done
        After sorting by QuickSort 
        sequence is ascending?:  true
        Number of steps is: 252545

        Restoring unsorted sequence 
        STARTING QuickSort
        QuickSort done
        After sorting by QuickSort 
        sequence is ascending?:  true
        Number of steps is: 252545
     */
}
