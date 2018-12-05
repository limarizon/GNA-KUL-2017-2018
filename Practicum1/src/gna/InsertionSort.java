package gna;

/**
 * Performs sort by using the Insertion Sort algorithm.
 * 
 */
public class InsertionSort extends SortingAlgorithm {
	/**
	 * Sorts the given array using insertion sort.
	 * 
	 * @see super
	 */
	public long sort(Comparable[] array) throws IllegalArgumentException {
		if (array == null) {
			throw new IllegalArgumentException("argument 'array' must not be null.");
		}
		if(array.length <= 1){
			return 0;
			}
		insertionSort(array, 0, array.length);
		return 0;
	}

	/**
     * Rearranges the array in ascending order
     * @param array the array to sort
     * @param low left arraybound
     * @param high right arraybound
     */
    public void insertionSort(Comparable[] array, int low, int high) {
        for (int i = low; i < high; i++) {
            for (int j = i; j > low && less(array[j], array[j-1]); j--, counter++) {
                exch(array, j, j-1);
            }
        }
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    // exchange a[i] with a[j]
    private static void exch(Object[] array, int i, int j) {
       	Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    
    public long getCompares() {
    	long tmp = counter;
    	counter = 0;
    	return tmp;
    }
    
    private long counter;
    
    
	/**
	 * Constructor.
	 */
	public InsertionSort() {
	}
}
