package gna;

/**
 * Performs sort by using the Selection Sort algorithm.
 * 
 */
public class SelectionSort extends SortingAlgorithm {
	
	/**
	 * Sorts the given array using selection sort.
	 * 
	 * @see super
	 */
	public long sort(Comparable[] array) throws IllegalArgumentException {
		if (array == null) {
			throw new IllegalArgumentException("argument 'array' must not be null.");
		}
		selectionSort(array);
		return 0;
	}

	 /**
     * Rearranges the array in ascending order
     * @param array The array to sort
     */
    public void selectionSort(Comparable[] array)
    {
         int smallnb;
         for(int i = 0; i < array.length; i++)
         {
              smallnb = i;
              for(int j = i + 1; j < array.length; j++) { // Look for smaller than smallnb
            	  counter++;
                  if(array[j].compareTo(array[smallnb]) < 0) {
                       smallnb = j;
                  }
              }
              if(smallnb != i) {
                   swap(array, smallnb, i);
              }
         }
    }

    /**
     * @param array The array to sort
     * @param first Index of the first element
     * @param second Index of the second element
     * @post Two elements will be swapped in the given array.
     */
    public void swap(Object[] array, int first, int second)
    {
    	 Object tmp = array[first];
         array[first] = array[second];
         array[second] = tmp;
         
    }
    
    public long getCompares() {
    	long tmp = counter;
    	counter = 0;
    	return tmp;
    }
    
    private int counter;
    
	/**
	 * Constructor.
	 */
	public SelectionSort() {
	}
}
