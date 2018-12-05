package gna;
import java.util.*;

/**
 * Performs sort by using the Quick Sort algorithm.
 *
 */
public class QuickSort extends SortingAlgorithm{
	/**
	 * Sorts the given array using quick sort.
	 * 
	 * @see super
	 */
	public long sort(Comparable[] array) throws IllegalArgumentException {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("argument 'array' must not be null.");
		}
		if(array.length <= 1){
			return 0;
			}
		quickSort(array, 0, array.length-1);
		return 0;
	}
	
	/**
	 * Rearranges the array in ascending order
     * @param array The array to sort
     * @param low Index of the first element
     * @param high Index of the last element
     */
	public void quickSort(Comparable[] array, int low, int high) {
		if (high <= low) {
			return;
		}
		int j = partition(array, low, high);
		quickSort(array, low, j-1);
		quickSort(array, j+1, high);
	}
	
	/**
     * @param array The array to sort
     * @param low Index of the first element
     * @param high Index of the last element
     * @post All elements smaller than pivot appear on the left of pivot. All elements higher than pivot appear on the right of pivot.
     */
	public int partition(Comparable[] array, int low, int high) {
		Comparable pivot = array[low];
		int i = low + 1, j = high;
		while(i <= j) {
            while(array[i].compareTo(pivot) < 0) { 
                i++; 
                counter++;
                if(i == high)
                	break;
            }
            while(array[j].compareTo(pivot) > 0) { 
                j--;
                counter++;
                if(j == low)
                	break;
            }
            if(i >= j) {
                break;	
            }
            exchange(array, i, j);
    		i++;
    		j--;
        }
		exchange(array, low, j);
        return j;
    }
	
	/**
     * @param array The array to sort
     * @param i Index of the first element
     * @param j Index of the second element
     * @post Two elements will be swapped in the given array.
     */
    private void exchange(Comparable[] array, int i, int j) {
    	Comparable tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        return;
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
	public QuickSort() {
		
	}
}
