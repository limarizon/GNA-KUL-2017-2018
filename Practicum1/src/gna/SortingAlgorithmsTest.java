package gna;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;

import org.junit.Test;

/**
 * Tests that test whether the implementations of the sorting algorithms do sort.
 */
public class SortingAlgorithmsTest {

	@Test
	public void my_first_test() {
	//	fail("Je moet nog testen schrijven"); // TODO
	}

    @Before
    public void initialise() throws Exception {
        array = new Comparable[SIZE];
        Random generator = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = generator.nextInt(MAX);
        }
    }

    //QuickSort
    @Test
    public void testNullQS() {
        QuickSort Arrays = new QuickSort();
        try {
        	Arrays.sort(null);
        }
        catch(Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }

    @Test
    public void testEmptyQS() {
        QuickSort Arrays = new QuickSort();
        Comparable[] array = new Comparable[0];
        try {
        	Arrays.sort(array);
        }
        catch(Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }

    @Test
    public void testOneElementQS() {
        QuickSort sorter = new QuickSort();
        Comparable[] test = new Comparable[1];
        test[0] = 5;
        sorter.sort(test);
    }

    @Test
    public void testSpecialArrayQS() {
        QuickSort sorter = new QuickSort();
        Comparable[] test = { 3, 3, 2, 4, 4, 4, 5, 5, 2, 3, 4, 3, 3, 2 };
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        printResult(test);
    }

    @Test
    public void testQuickSort() {
        long startTime = System.currentTimeMillis();

        QuickSort sorter = new QuickSort();
        sorter.quickSort(array, 0, array.length-1);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
		for (Object i : array) {
            //System.out.println(i + " ");
        }
        //System.out.println("Quicksort " + elapsedTime);

        if (!validate(array)) {
			
            fail("Should not happen");
        }
        assertTrue(true);
    }
    
    //InsertionSort
    
    @Test
    public void testNullIS() {
        InsertionSort Arrays = new InsertionSort();
        try {
        	Arrays.sort(null);
        }
        catch(Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }

    @Test
    public void testEmptyIS() {
        InsertionSort Arrays = new InsertionSort();
        Comparable[] array = new Comparable[0];
        try {
        	Arrays.sort(array);
        }
        catch(Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }

    @Test
    public void testOneElementIS() {
    	InsertionSort sorter = new InsertionSort();
        Comparable[] test = new Comparable[1];
        test[0] = 5;
        sorter.sort(test);
    }

    @Test
    public void testSpecialArrayIS() {
        InsertionSort sorter = new InsertionSort();
        Comparable[] test = { 3, 3, 2, 4, 4, 4, 5, 5, 2, 3, 4, 3, 3, 2 };
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        printResult(test);
    }

    @Test
    public void testInsertionSort() {
        long startTime = System.currentTimeMillis();

        InsertionSort sorter = new InsertionSort();
        sorter.insertionSort(array, 0, array.length);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
		for (Object i : array) {
            //System.out.println(i + " ");
        }
        //System.out.println("InsertionSort " + elapsedTime);

        if (!validate(array)) {
			
            fail("Should not happen");
        }
        assertTrue(true);
    }
    
    // SelectionSort
    
    @Test
    public void testNullSS() {
        SelectionSort Arrays = new SelectionSort();
        try {
        	Arrays.sort(null);
        }
        catch(Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }

    @Test
    public void testEmptySS() {
    	SelectionSort Arrays = new SelectionSort();
    	Comparable[] array = new Comparable[0];
        try {
        	Arrays.sort(array);
        }
        catch(Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }
    @Test
    public void testOneElementSS() {
        Comparable[] test = new Comparable[1];
        SelectionSort sorter = new SelectionSort();
        test[0] = 5;
        sorter.sort(test);
    }

    @Test
    public void testSpecialArraySS() {
    	SelectionSort sorter = new SelectionSort();
        Comparable[] test = { 3, 3, 2, 4, 4, 4, 5, 5, 2, 3, 4, 3, 3, 2 };
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        printResult(test);
    }

    @Test
    public void testSelectionSort() {
        long startTime = System.currentTimeMillis();

        SelectionSort sorter = new SelectionSort();
        sorter.selectionSort(array);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
		//for (Object i : array) {
           // System.out.println(i + " ");
        //}
        //System.out.println("SelectionSort " + elapsedTime);
        System.out.println(sorter.getCompares() + "Compares SelectionSort");
        if (!validate(array)) {
			
            fail("Should not happen");
        }
        assertTrue(true);
    }
    
    // Helper functions
    private boolean validate(Comparable[] array) {
        for (int i = 0; i < array.length - 1; i++) {
			//System.out.println(array[i] + " " + array[i+1] +" "+ array[i].compareTo(array[i + 1]));
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    private void printResult(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }
    
    //Doubling experiments
    public double timeTrialQS(int n) {
    		QuickSort sorter = new QuickSort();
    		Comparable[] arrayQs = new Comparable[n];
    		Random generator = new Random();
            for (int i = 0; i < n; i++) {
                arrayQs[i] = generator.nextInt(MAX);
            }
            long startTime = System.currentTimeMillis();
            sorter.quickSort(arrayQs, 0, arrayQs.length-1);
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println(sorter.getCompares());
            return elapsedTime;
            
    }
    @Test
    public void doublingRatioQs() {
    	double prev = timeTrialQS(100);
    	System.out.println("QUICKSORT");
    	for(int n = 200; n < 20000000; n*=2) {
    		double time = timeTrialQS(n);
    		System.out.printf("%7d %7.1f", n, time);
    		System.out.printf("%5.1f\n", time/prev);
    		prev = time; 
    	}
    }
    
    public long getComparesQS(QuickSort sorter) {
    	return sorter.getCompares();
    }
     
    public double timeTrialIS(int n) {
		InsertionSort sorter = new InsertionSort();
		Comparable[] arrayIs = new Comparable[n];
		Random generator = new Random();
        for (int i = 0; i < n; i++) {
            arrayIs[i] = generator.nextInt(MAX);
        }
        long startTime = System.currentTimeMillis();
        sorter.insertionSort(arrayIs, 0, arrayIs.length-1);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(getComparesIS(sorter));
        return elapsedTime;
        
    }
    @Test
    public void doubleRatioExpIS() {
    	double prev = timeTrialIS(100);
    	System.out.println("INSERTIONSORT");
    	for(int n = 200; n < 12801; n*=2) {
    		double time = timeTrialIS(n);
    		System.out.printf("%7d %7.1f", n, time);
    		System.out.printf("%5.1f\n", time/prev);
    		prev = time; 
    	}
    	
    }
    
    public long getComparesIS(InsertionSort sorter) {
    	return sorter.getCompares();
    }
    
    public double timeTrialSS(int n) {
		SelectionSort sorter = new SelectionSort();
		Comparable[] arraySs = new Comparable[n];
		Random generator = new Random();
        for (int i = 0; i < n; i++) {
            arraySs[i] = generator.nextInt(MAX);
        }
        long startTime = System.currentTimeMillis();
        sorter.selectionSort(arraySs);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(getComparesSS(sorter));
        return elapsedTime;
        
}
    @Test
    public void doubleRatioExpSS() {
    	double prev = timeTrialSS(100);
    	System.out.println("SELECTIONSORT");
    	for(int n = 200; n < 12801; n*=2) {
    		double time = timeTrialSS(n);
    		System.out.printf("%7d %7.1f", n, time);
    		System.out.printf("%5.1f\n", time/prev);
    		prev = time; 
    	}
    	
    }
    
    //SelectionSort compares
    public long getComparesSS(SelectionSort sorter) {
    	return sorter.getCompares();
    }
    
    //All constants used
    private Comparable[] array;
    private static int SIZE = 250;
    private final static int MAX = 90;
}
