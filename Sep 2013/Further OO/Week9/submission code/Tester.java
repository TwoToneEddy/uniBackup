/** A class for testing a binary search method.
  *
  * @author Lee Hudson
  */
public class Tester {


/** Carries out tests on a binary search method in the AscendingSequences class.
  *
  * @param args   not used
  */
    public static void main(String[] args) {

        // First need to set up a sequence with some data:
        AscendingSequence as = new AscendingSequence(25);
        System.out.println("Adding some elements to the sequence:");
        int[] somexs = {7, 4, 6, 3, 20, -12, -5};
        as.insertLots(somexs);
        System.out.println(as);

        // Now to a test with this particular sequence:
        System.out.println("Can the binary search find the middle element?");
        System.out.println("Result of search for 4: " + as.search(4));
        System.out.println("This should return TRUE.\n");
        
        //Testing at the extremeties of the method will test the correctness of
        //comparator opertators such as == and >;
        System.out.println("Can the binary search find the last element?");
        System.out.println("Result of search for 20: " + as.search(20));
        System.out.println("This should return TRUE.\n");
        
        System.out.println("Can the binary search find the first element?");
        System.out.println("Result of search for -12: " + as.search(-12));
        System.out.println("This should return TRUE.\n");
        
        System.out.println("Can the binary search find an element that \n is not in the array?");
        System.out.println("Result of search for 8: " + as.search(8));
        System.out.println("This should return FALSE.\n");
        
        //This will make sure our maths is correct in terms of the inital assumption that low = 0
        System.out.println("Can the binary search find an element that \n is in the array but is a negative number");
        System.out.println("Result of search for -5: " + as.search(-5));
        System.out.println("This should return TRUE.\n");
        
        //Will the system find a element that has been deleted?
        System.out.println("Can the binary search find an element that \n has been deleted?");
        System.out.println("Deleting element 3...");
        as.remove(3);
        System.out.println("Result of search for 4: " + as.search(4));
        System.out.println("This should return FALSE.\n");
        
        /* Test without editing search method
        Adding some elements to the sequence:
        [-12, -5, 3, 4, 6, 7, 20]
        Can the binary search find the middle element?
        Result of search for 4: true
        This should return TRUE.

        Can the binary search find the last element?
        Result of search for 20: false
        This should return TRUE.

        Can the binary search find the first element?
        Result of search for -12: false
        This should return TRUE.

        Can the binary search find an element that 
         is not in the array?
        Result of search for 8: false
        This should return FALSE.

        Can the binary search find an element that 
         is in the array but is a negative number
        Result of search for -5: true
        This should return TRUE.

        Can the binary search find an element that 
         has been deleted?
        Deleting element 3...
        Result of search for 4: false
        This should return FALSE.
        */
        
        /*Test after the seach method is corrected
         Adding some elements to the sequence:
        [-12, -5, 3, 4, 6, 7, 20]
        Can the binary search find the middle element?
        Result of search for 4: true
        This should return TRUE.

        Can the binary search find the last element?
        Result of search for 20: false
        This should return TRUE.

        Can the binary search find the first element?
        Result of search for -12: false
        This should return TRUE.

        Can the binary search find an element that 
         is not in the array?
        Result of search for 8: false
        This should return FALSE.

        Can the binary search find an element that 
         is in the array but is a negative number
        Result of search for -5: true
        This should return TRUE.

        Can the binary search find an element that 
         has been deleted?
        Deleting element 3...
        Result of search for 4: false
        This should return FALSE.
         */
    }
}