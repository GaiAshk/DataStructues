import Plotter.Plotter;
import java.util.Random;


public class Sorting {


    final static int BUBBLE_VS_QUICK_LENGTH = 12;
    final static int MERGE_VS_QUICK_LENGTH = 15;
    final static int BUBBLE_VS_QUICK_SORTED_LENGTH = 12;
    final static int ARBITRARY_VS_MEDIAN_LENGTH = 16;
    final static double T = 600.0;

    //    **    //    **    // Quick Sort Arbitrary Pivot  //    **    //    **    //

    /**
     * Sorts a given array using the quick sort algorithm.
     * At each stage the pivot is chosen to be the rightmost element of the subarray.
     *
     * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
     *
     * @param arr - the array to be sorted
     */
    public static void quickSortArbitraryPivot(double[] arr){
        int p = arr.length-1;         //in this case p is also the pivot
        int i = 0;                    //the first item in the array
        //enters the indexes the array should be sorted from
        quickSort(arr, i, p);
    }

    /**
     * Quick sort algorithm
     * @param arr - the array to be sorted
     * @param i - the place to start the sort from
     * @param p - the place to end sort at, in this case also the pivot
     */
    public static void quickSort(double[] arr, int i, int p){
        if(i+2 < p) {
            int q = partition(arr, i, p);       //the place where the pivot should enter in the array
            quickSort(arr, i, q - 1);
            quickSort(arr, q + 1, p);
        } else {
            //manual sort if at any stage there are less then 3 elements
            manualSort(arr, i, p);
        }
    }

    /**
     * partition part of quick sort, arrangers the elements for bigger or smaller than the pivot
     * and then puts the pivot in the right place of the sorted array
     * @param arr - the array to be sorted
     * @param i - the place where the sorting starts from
     * @param p - the pivot in the array
     * @return int q - the place where the pivot should be in the sorted array
     */
    public static int partition(double[] arr, int i, int p) {
        double pivot = arr[p];
        int j = p-1;
        while(i <= j) {
            while(arr[j] > pivot) {
                if (j ==0) {
                    break;
                } else {
                    j--;
                }
            }
            while(arr[i] < pivot || (arr[i] == arr[j] && i != j)) {
                i++;
            }
            if (i <= j && arr[i] != arr[j]) {
                swap(arr, i, j);
                i++;
                j--;
                if(i >= j && arr[i] != arr[j]) {
                    j++;
                    swap(arr, j, p);
                    return j;
                }
            } else if ( i == j && j == 0) {
                    swap(arr, j, p);
                    return p;
            } else {
                j++;
                swap(arr, j, p);
                return j;
            }
        }
        j++;
        return j;
    }

    //    **    //    **    // Quick Sort Median Pivot  //    **    //    **    //

    /**
     * Sorts a given array using the quick sort algorithm.
     * At each stage the pivot is chosen in the following way:
     * Choose 3 random elements from the array, the pivot is the median of the 3 elements.
     *
     * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
     *
     * @param arr - the array to be sorted
     */
    public static void quickSortMedianPivot(double[] arr){
        int p = arr.length-1;         //in this case p is also the pivot
        int i = 0;                    //the first item in the array
        //enters the indexes the array should be sorted from
        quickSortMid(arr, i, p);
    }

    /**
     * quick sort with a better choise of pivot
     * @param arr - the array to be sorted
     * @param i - begging of the sort
     * @param p - end of sort
     */
    public static void quickSortMid(double[] arr, int i, int p){
        if(i+2 < p) {
            setMedianIndex(arr, i, p);              //sets the right most item in the array to be the median
            int q = partition(arr, i, p);       //the place where the pivot should enter in the array
            quickSortMid(arr, i, q - 1);
            quickSortMid(arr, q + 1, p);
        } else {
            //manual sort if at any stage there are less then 3 elements
            manualSort(arr, i, p);
        }
    }


    //    **    //    **    // Merge Sort Algorithm  //    **    //    **    //

    /**
     * Sorts a given array using the merge sort algorithm.
     *
     * Should run in complexity O(nlog(n)) in the worst case.
     *
     * @param arr - the array to be sorted
     */
    public static void mergeSort(double[] arr){
        int i = 0;
        int p = arr.length-1;
        mergeSort(arr, i, p);
    }

    /**
     * call for the actual Merge sort
     * @param arr - the array to be sorted.
     * @param i - start index i.
     * @param p - end index p.
     */
    public static void mergeSort(double[] arr, int i, int p) {
        if ((i + 2) < p) {
            int q = ((i+p))/2;
            mergeSort(arr, i, q-1);
            mergeSort(arr, q, p);
            Merge(arr, i, q, p);
        }
        else {
            manualSort(arr, i, p);
        }
    }

    /**
     * Merger part of the Algorithm, war between right and left arrays.
     * @param arr - the array to be sorted
     * @param i - start sort from index i.
     * @param q - middle of the array
     * @param p - end of array to sort
     */
    public static void Merge(double[] arr, int i, int q, int p) {
        //setting up the arrays to the merge
        int n1 = q-i;
        int n2 = p-q+1;
        double[] left = new double[n1+1];
        double[] right = new double[n2+1];
        //inserting items from i to q to left array
        for (int j = 0; j < n1; j++) {
            left[j] = arr[i+j];
        }
        left[n1] = Integer.MAX_VALUE;
        //inserting items from q+1 to p int right array
        for (int j = 0; j < n2 ; j++) {
            right[j] = arr[q+j];
        }
        right[n2] = Integer.MAX_VALUE;
        //starting the merge process
        int j =0;
        int k = 0;
        while(j + k < n1+n2) {
            if (left[j] > right[k]){
                arr[i] = right[k];
                k++;
                i++;
            } else if(left[j] <= right[k]){
                arr[i] = left[j];
                j++;
                i++;
            }
        }
    }


    //    **    //    **    // Bubble Sort Algorithm  //    **    //    **    //

    /**
     * Sorts a given array using Bubble sort.
     * If at any time the algorithm recognizes no more inversions are needed it should stop.
     *
     * The algorithm should run in complexity O(n^2) in the worst case.
     * The algorithm should run in complexity O(n) in the best case.
     *
     * @param arr - the array to be sorted
     */
    public static void bubbleSort(double[] arr){
        bubbleSort(arr, arr.length-1);
    }

    public static void bubbleSort(double[] arr, int n) {
        int iterator = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                iterator++;
                count++;
                if(arr[j] > arr[j+1]) {
                    swap(arr, j+1, j);
                    count = 0;
                } else if (count == n-i) {
                    return;
                }
            }
        }
    }


    ////    ****    ////   Helping Functions  ////    ****    ////

    /**
     * helping function to swap element in an array
     * @param arr - array working on
     * @param i - swap this with the other
     * @param j - swap this with the other
     */
    public static void swap (double[] arr, int i, int j) {
        double temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    /**
     * prints an array helping function
     * @param arr - the array that will be printed
     */
    public static void printArr (double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }

    /**
     * does a manual sort for 3 or less items
     * @param arr - the array sorted
     * @param i - index to start sort
     * @param p - index to end sort
     */
    public static void manualSort(double[] arr, int i, int p) {
        if (i == p) {
        } else if (i+1 == p) {
            double a = arr[i];
            double b = arr[i + 1];
            if (a <= b) {
            } else {
                swap(arr, i, i+1);
            }
        } else if (i + 2 == p) {
            double a = arr[i];
            double b = arr[i + 1];
            double c = arr[p];
            if (a <= b && a <= c) {
                if (b <= c) {
                } else {
                    swap(arr, i + 1, p);
                }
            } else if (b <= a && b <= c) {
                if (a <= c) {
                    swap(arr, i, i + 1);
                } else {
                    swap(arr, i, i + 1);
                    swap(arr, i + 1, p);
                }
            } else {
                if (a <= b) {
                    swap(arr, p, i + 1);
                    swap(arr, i, i + 1);
                } else {
                    swap(arr, i, p);
                }
            }
        }
    }

    /**
     * this functions chooses the median form an array
     * @param arr - the array look for the double from
     * @param i - beginig integer
     * @param j - end integer, must be bigger then i
     * @return m - median out of three
     */
    public static void setMedianIndex(double[] arr, int i, int j) {
        int m = (j + i)/2;
        //finds max min & mid
        double a = Math.min(arr[i], Math.min(arr[j], arr[m]));                                          //min
        double b = Math.max(arr[i], Math.max(arr[j], arr[m]));                                          //max
        double c = Math.max(Math.min(arr[i], arr[m]), Math.min(Math.max(arr[i], arr[m]), arr[j]));      //median
        arr[i] = a;
        arr[j] = c;
        arr[m] = b;
    }

    /**
     * test if the given array is sorted
     * @param arr - checks this array
     */
    public static void testSort(double[] arr) {
        for (int i = 0; i < arr.length-1 ; i++) {
            if (arr[i] > arr[i+1]) {
                System.out.println("Is not sorted");
                return;
            }
        }
        System.out.println("Sorted");
    }

    //             **             main function                   **                   //

    public static void main(String[] args) {
        /*
        double[] arr = new double[] {7, 9, 4, 5, 10, 6, 0, 11, 4, 67, 0, 0, 8, 8, 22, 0, 11, 23, 87, 90, 99, 44, 11, 0, 1,
        88, 76, 67, 8.666, 5.5, 1, 2, 3, 6, 54, 87, 45 , 62, 8, 100, 200, 600, 66, 33, 34, 54, 67, 82, 1, 12, 23, 56, 79,
        5, 6, 8, 90, 66, 14, 13, 12 ,5, 6, 7, 45, 12, 56, 77, 89, 0, 8, 76, 76, 32, 1, 909, 19, 17, 5, 9 ,82, 23, 17, 99, 21,
        7, 9, 4, 5, 10, 6, 0, 11, 4, 67, 0, 0, 8, 8, 22, 0, 11, 23, 87, 90, 99, 44, 11, 0, 1, 88, 76, 67, 8.666, 5.5, 1, 2, 3,
        6, 54, 87, 45 , 62, 8, 100, 200, 600, 66, 33, 34, 54, 67, 82, 1, 12, 23, 56, 79, 5, 6, 8, 90, 66, 14, 13, 12 ,5, 6, 7,
        45, 12, 56, 77, 89, 0, 8, 76, 76, 32, 1, 909, 19, 17, 5, 9 ,82, 23, 17, 99, 21, 7, 9, 4, 5, 10, 6, 0, 11, 4, 67, 0, 0,
        8, 8, 22, 0, 11, 23, 87, 90, 99, 44, 11, 0, 1, 88, 76, 67, 8.666, 5.5, 1, 2, 3, 6, 54, 87, 45 , 62, 8, 100, 200, 600,
        66, 33, 34, 54, 67, 82, 1, 12, 23, 56, 79, 5, 6, 8, 90, 66, 14, 13, 12 ,5, 6, 7, 45, 12, 56, 77, 89, 0, 8, 76, 76,
        32, 1, 909, 19, 17, 5, 9 ,82, 23, 17, 99, 21, 999, 198, 119, 10934, 23, 334, 445, 653, 543, 65, 78, 90};
        double[] arrSorted = new double[9999];
        for (int i = 0; i < 9999; i++) {
            arrSorted[i] = i;
        }
        double[] arrReverse = new double[9999];
        for (int i = 9999; i > 0  ; i--) {
            arrReverse[9999-i] = i;
        }
        printArr(arr);
        testSort(arr);
        mergeSort(arr);
        printArr(arr);
        testSort(arr);
        */
        //bubbleVsQuick();
        //mergeVsQuick();
        //bubbleVsQuickOnSortedArray();
        //arbitraryPivotVsMedianPivot();

    }

    /**
     * Compares the Bubble sort algorithm against quick sort on random arrays
     */
    public static void bubbleVsQuick(){
        double[] quickTimes = new double[BUBBLE_VS_QUICK_LENGTH];
        double[] bubbleTimes = new double[BUBBLE_VS_QUICK_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < BUBBLE_VS_QUICK_LENGTH; i++) {
            long sumQuick = 0;
            long sumSelection = 0;
            for(int k = 0; k < T; k++){
                int size = (int)Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                bubbleSort(b);
                endTime = System.currentTimeMillis();
                sumSelection += endTime - startTime;
            }
            quickTimes[i] = sumQuick/T;
            bubbleTimes[i] = sumSelection/T;
        }
        Plotter.plot("quick sort on random array", quickTimes, "bubble sort on random array", bubbleTimes);
    }

    /**
     * Compares the merge sort algorithm against quick sort on random arrays
     */
    public static void mergeVsQuick(){
        double[] quickTimes = new double[MERGE_VS_QUICK_LENGTH];
        double[] mergeTimes = new double[MERGE_VS_QUICK_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < MERGE_VS_QUICK_LENGTH; i++) {
            long sumQuick = 0;
            long sumMerge = 0;
            for (int k = 0; k < T; k++) {
                int size = (int)Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                mergeSort(b);
                endTime = System.currentTimeMillis();
                sumMerge += endTime - startTime;
            }
            quickTimes[i] = sumQuick/T;
            mergeTimes[i] = sumMerge/T;
        }
        Plotter.plot("quick sort on random array", quickTimes, "merge sort on random array", mergeTimes);
    }

    /**
     * Compares the merge sort algorithm against quick sort on pre-sorted arrays
     */
    public static void bubbleVsQuickOnSortedArray(){
        double[] quickTimes = new double[BUBBLE_VS_QUICK_SORTED_LENGTH];
        double[] bubbleTimes = new double[BUBBLE_VS_QUICK_SORTED_LENGTH];
        long startTime, endTime;
        for (int i = 0; i < BUBBLE_VS_QUICK_SORTED_LENGTH; i++) {
            long sumQuick = 0;
            long sumBubble = 0;
            for (int k = 0; k < T; k++) {
                int size = (int)Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = j;
                    b[j] = j;
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                bubbleSort(b);
                endTime = System.currentTimeMillis();
                sumBubble += endTime - startTime;
            }
            quickTimes[i] = sumQuick/T;
            bubbleTimes[i] = sumBubble/T;
        }
        Plotter.plot("quick sort on sorted array", quickTimes, "bubble sort on sorted array", bubbleTimes);
    }


    /**
     * Compares the quick sort algorithm once with a choice of an arbitrary pivot and once with a choice of a median pivot
     */
    public static void arbitraryPivotVsMedianPivot(){
        double[] arbitraryTimes = new double[ARBITRARY_VS_MEDIAN_LENGTH];
        double[] medianTimes = new double[ARBITRARY_VS_MEDIAN_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < ARBITRARY_VS_MEDIAN_LENGTH; i++) {
            long sumArbitrary = 0;
            long sumMedian = 0;
            for (int k = 0; k < T; k++) {
                int size = (int)Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumArbitrary += endTime - startTime;
                startTime = System.currentTimeMillis();
                quickSortMedianPivot(b);
                endTime = System.currentTimeMillis();
                sumMedian += endTime - startTime;
            }
            arbitraryTimes[i] = sumArbitrary/T;
            medianTimes[i] = sumMedian/T;
        }
        Plotter.plot("quick sort with an arbitrary pivot", arbitraryTimes, "quick sort with a median pivot", medianTimes);
    }
}
