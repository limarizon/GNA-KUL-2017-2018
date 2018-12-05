package gna;

import libpract.PriorityFunc;

import org.junit.Test;

/**
 * A number of JUnit tests for Solver.
 *
 * Feel free to modify these to automatically test puzzles or other functionality
 */
public class UnitTests {
  
  @Test
  public void test28() {
	  long startTime = System.currentTimeMillis();
	  Board board1 = new Board(new int[][]{{7, 8, 5}, {4, 0, 2}, {3, 6, 1}});
	  long initTime = System.currentTimeMillis();
	  System.out.println(initTime-startTime);
	  
	  if (board1.isSolvable()){
//		  Solver solver = new Solver(board1, PriorityFunc.HAMMING);
//		  for (Board board : solver.solution())
//				System.out.println(board);
//		  
//		  long puzzle1Time = System.currentTimeMillis();
//		  System.out.println(puzzle1Time-startTime);
		  
		  Solver solver2 = new Solver(board1, PriorityFunc.MANHATTAN);
		  for (Board board : solver2.solution())
				System.out.println(board);
		  
		  long stopTime = System.currentTimeMillis();
		  System.out.println("28" + (stopTime-startTime));
	  }
  }
  @Test
  public void test42() {
	  long startTime = System.currentTimeMillis();
	  Board board1 = new Board(new int[][]{{2, 8,  7, 11}, {5,  0,  4, 15}, {13,  9, 14,  3}, {1, 10,  6, 12}});
	  long initTime = System.currentTimeMillis();
	  System.out.println(initTime-startTime);
	  
//	  Solver solver = new Solver(board1, PriorityFunc.HAMMING);
//	  for (Board board : solver.solution())
//			System.out.println(board);
	  
//	  long puzzle1Time = System.currentTimeMillis();
//	  System.out.println(puzzle1Time-startTime);
	  if (board1.isSolvable()) {
		  Solver solver2 = new Solver(board1, PriorityFunc.MANHATTAN);
		  for (Board board : solver2.solution())
				System.out.println(board);
		  
		  long stopTime = System.currentTimeMillis();
		  System.out.println("moves: " + (solver2.solution().size()-1));
		  System.out.println(stopTime-startTime);
	  }
  }


   @Test
   public void test36() {
 	  long startTime = System.currentTimeMillis();
 	  Board board1 = new Board(new int[][]{{5, 3, 1, 4},{10, 2, 8, 7},{14, 13, 0, 11},{6, 9, 15, 12}});
 	  long initTime = System.currentTimeMillis();
 	  System.out.println(initTime-startTime);
 	  
 	  if (board1.isSolvable()) {
 		  Solver solver2 = new Solver(board1, PriorityFunc.MANHATTAN);
 		  for (Board board : solver2.solution())
 				System.out.println(board);
 		  
 		  long stopTime = System.currentTimeMillis();
 		  System.out.println("moves: " + (solver2.solution().size()-1));
 		  System.out.println(stopTime-startTime);
 	  }
   }
   @Test
   public void test30() {
 	  long startTime = System.currentTimeMillis();
 	  Board board1 = new Board(new int[][]{{8, 4, 7},{1, 5, 6},{3, 2, 0}});
 	  long initTime = System.currentTimeMillis();
 	  System.out.println(initTime-startTime);
 	  
 	  if (board1.isSolvable()) {
 		  Solver solver2 = new Solver(board1, PriorityFunc.MANHATTAN);
 		  for (Board board : solver2.solution())
 				System.out.println(board);
 		  
 		  long stopTime = System.currentTimeMillis();
 		  System.out.println("moves: " + (solver2.solution().size()-1));
 		  System.out.println(stopTime-startTime);
 	  }
   }
   
   @Test
   public void test32() {
 	  long startTime = System.currentTimeMillis();
 	  Board board1 = new Board(new int[][]{{3, 1, 6, 4},{5, 0, 9, 7},{10, 2, 11, 8},{13, 15, 14, 12}});
 	  long initTime = System.currentTimeMillis();
 	  System.out.println(initTime-startTime);
 	  
 	  if (board1.isSolvable()) {
 		  Solver solver2 = new Solver(board1, PriorityFunc.MANHATTAN);
 		  for (Board board : solver2.solution())
 				System.out.println(board);
 		  
 		  long stopTime = System.currentTimeMillis();
 		  System.out.println("moves: " + (solver2.solution().size()-1));
 		  System.out.println(stopTime-startTime);
 	  }
   }
   
   @Test
   public void test34() {
 	  long startTime = System.currentTimeMillis();
 	  Board board1 = new Board(new int[][]{{6, 9, 7, 4},{2, 5, 10, 8},{3, 11, 1, 12},{13, 14, 15, 0}});
 	  long initTime = System.currentTimeMillis();
 	  System.out.println(initTime-startTime);
 	  
 	  if (board1.isSolvable()) {
 		  Solver solver2 = new Solver(board1, PriorityFunc.MANHATTAN);
 		  for (Board board : solver2.solution())
 				System.out.println(board);
 		  
 		  long stopTime = System.currentTimeMillis();
 		  System.out.println("moves: " + (solver2.solution().size()-1));
 		  System.out.println(stopTime-startTime);
 	  }
   }

   @Test
   public void test38() {
 	  long startTime = System.currentTimeMillis();
 	  Board board1 = new Board(new int[][]{{13, 1, 5, 4},{2, 3, 6, 8},{7, 10, 0, 9},{11, 14, 15, 12}});
 	  long initTime = System.currentTimeMillis();
 	  System.out.println(initTime-startTime);
 	  
 	  if (board1.isSolvable()) {
 		  Solver solver2 = new Solver(board1, PriorityFunc.MANHATTAN);
 		  for (Board board : solver2.solution())
 				System.out.println(board);
 		  
 		  long stopTime = System.currentTimeMillis();
 		  System.out.println("moves: " + (solver2.solution().size()-1));
 		  System.out.println(stopTime-startTime);
 	  }
   }
   
   @Test
   public void test40() {
 	  long startTime = System.currentTimeMillis();
 	  Board board1 = new Board(new int[][]{{6, 5, 11, 4},{10, 13, 2, 1},{9, 15, 7, 3},{14, 12, 8, 0}});
 	  long initTime = System.currentTimeMillis();
 	  System.out.println(initTime-startTime);
 	  
 	  if (board1.isSolvable()) {
 		  Solver solver2 = new Solver(board1, PriorityFunc.MANHATTAN);
 		  for (Board board : solver2.solution())
 				System.out.println(board);
 		  
 		  long stopTime = System.currentTimeMillis();
 		  System.out.println("moves: " + (solver2.solution().size()-1));
 		  System.out.println(stopTime-startTime);
 	  }
   }
}
