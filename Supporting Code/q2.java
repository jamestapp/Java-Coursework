import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q2{

    // variables to count the number of comparisions and swaps within the algorithm
    public static int comparisons = 0;
    public static int swaps = 0;

    public static void resetVars(){
        comparisons=0;
        swaps = 0;
    }

    public static void printVars(){
        System.out.println("comparisons: " + comparisons);
        System.out.println("swaps: " + swaps);
    }

    public static long getAverageTime(int iterations, ArrayList<String> array, int arrayLength){
        long startTime = System.nanoTime();
        ArrayList<String> arrayCopy = (ArrayList)array.clone();
        for(int i=0; i<iterations; i++){
            mergeSort(arrayCopy, 0, arrayLength);
        }
        long endTime = System.nanoTime();

        return ((endTime-startTime)/iterations);
    }

    public static void mergeSort(ArrayList<String> arr, int start_index, int end_index){
        // this algorithm is called to sort an arraylist of strings into alphabetical order
        // it also calls merge function as part of the algorithm

        if(start_index<end_index){ 
            int mid_index = (start_index+end_index)/2;
            // find the middle index of the given array

            // sort a left array and a right array
            mergeSort(arr, start_index, mid_index);
            mergeSort(arr, mid_index + 1, end_index);

            // create a new array of the left hand side of the given array
            ArrayList<String> left_arr = new ArrayList<>(mid_index - start_index);
            for(int i=start_index; i<mid_index+1; i++){
                left_arr.add(arr.get(i));
            }

            // create a new array of the right hand side of the given array
            ArrayList<String> right_arr = new ArrayList<>(end_index - mid_index);
            for(int i=mid_index + 1; i<end_index+1; i++){
                right_arr.add(arr.get(i));
            }           

            // merge our sorted left and right arrays together
            merge(arr, left_arr, right_arr, start_index, end_index);

        }

    }    
    public static void merge(ArrayList<String> arr , ArrayList<String> left_arr, ArrayList<String> right_arr, int start_index, int end_index){
        // index variables for the left and right hand sides of the array respectively
        int i = 0;
        int j = 0;

        // merge the left and right hand 

        // while merging from both arrays
        while(i<left_arr.size() && j < right_arr.size()){
            // this is the only point in the code where we are directly comparing list elements
            // increment comparison counter
            comparisons ++;

            if(left_arr.get(i).compareTo(right_arr.get(j)) < 0){
                arr.set(start_index, left_arr.get(i));
                i++;
                start_index++;            

            } else{
                // this condition is where swaps in the order of the list happen. 
                // ie, where there is an element in the right array that needs inserting before some element in the left array
                //increment swap counter
                swaps ++;

                arr.set(start_index, right_arr.get(j));
                j++;
                start_index++;
            }
        }
        
        // if the left array has more elements to be merged 
        while(i<left_arr.size()){
            arr.set(start_index, left_arr.get(i));
            i++;
            start_index++;
        }
        
        // if the right array has more elements to be merged 
        while(j<right_arr.size()){
            arr.set(start_index, right_arr.get(j));
            j++;
            start_index++;
        }

    }
    public static void main(String[] args){

        ArrayList<String> q1String = q1.getVocab();
        ArrayList<String> q1_100 = new ArrayList<String> (q1String.subList(0, 100));
        ArrayList<String> q1_200 = new ArrayList<String> (q1String.subList(0, 200));
        ArrayList<String> q1_300 = new ArrayList<String> (q1String.subList(0, 300));
        ArrayList<String> q1_400 = new ArrayList<String> (q1String.subList(0, 400));
        ArrayList<String> q1_432 = new ArrayList<String> (q1String.subList(0, 432));

        System.out.println("Mergesort 100 elements");
        mergeSort(q1_100, 0, 99);
        printVars();
        resetVars();

        System.out.println("Mergesort 200 elements");
        mergeSort(q1_200, 0, 199);
        printVars();
        resetVars();

        System.out.println("Mergesort 300 elements");
        mergeSort(q1_300, 0, 299);
        printVars();
        resetVars();

        System.out.println("Mergesort 400 elements");
        mergeSort(q1_400, 0, 399);
        printVars();
        resetVars();

        System.out.println("Mergesort 432 elements");
        mergeSort(q1_432, 0, 431);
        printVars();
        resetVars();

        System.out.println("Average Time over 1000 runs for first 100 elements:");
        System.out.println(getAverageTime(1000, q1_100, 99));
        System.out.println("Average Time over 1000 runs for first 200 elements:");
        System.out.println(getAverageTime(1000, q1_200, 199));
        System.out.println("Average Time over 1000 runs for first 300 elements:");
        System.out.println(getAverageTime(1000, q1_300, 299));
        System.out.println("Average Time over 1000 runs for first 400 elements:");
        System.out.println(getAverageTime(1000, q1_400, 399));
        System.out.println("Average Time over 1000 runs for first 432 elements:");
        System.out.println(getAverageTime(1000, q1_432, 431));
    }
}