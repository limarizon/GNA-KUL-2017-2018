package gna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import libpract.PriorityFunc;

public class SolverOG
{
	/**
	 * Finds a solution to the initial board.
	 *
	 * @param priority is either PriorityFunc.HAMMING or PriorityFunc.MANHATTAN
	 */
	public SolverOG(Board initial, PriorityFunc priority)
	{
		// Use the given priority function (either PriorityFunc.HAMMING
		// or PriorityFunc.MANHATTAN) to solve the puzzle.
		if (priority == PriorityFunc.HAMMING) {
			priorityQueue = new PriorityQueue<Board>(boardCompHamming);
		} else if (priority == PriorityFunc.MANHATTAN) {
			priorityQueue = new PriorityQueue<Board>(boardCompManhattan);
		} else {
			throw new IllegalArgumentException("Priority function not supported");
		}
		Board initialB = initial;
		if(!initialB.isSolved()) {
			priorityQueue.add(initialB);
			priorityQueue.addAll(initialB.neighbors());
			prevBoard = priorityQueue.peek();
			while(!prevBoard.isSolved()) {
				Board mostOptimal = priorityQueue.poll();
				for (Board board : mostOptimal.neighbors()) {
					priorityQueue.add(board);
				}
				prevBoard = mostOptimal;
			}
		}
	}
	private Board prevBoard;
	
	private PriorityQueue<Board> priorityQueue;
	private static Comparator<Board> boardCompHamming= new Comparator<Board>() {
		@Override
		public int compare(Board o1, Board o2) {
			int result1 = o1.hamming();
			int result2 = o2.hamming();
			return (result1 - result2);
		};
	};
	
	private static Comparator<Board> boardCompManhattan= new Comparator<Board>() {
		@Override
		public int compare(Board o1, Board o2) {
			int result1 = o1.manhattan();
			int result2 = o2.manhattan();
			return (result1 - result2);
		};
	};
	/**
	 * Returns a List of board positions as the solution. It should contain the initial
	 * Board as well as the solution (if these are equal only one Board is returned).
	 */
	public List<Board> solution()
	{
		//elk prevboard heeft een verwijzing naar vorige board ik roep deze steeds op.
		ArrayList<Board> list = new ArrayList<Board>();
		list.add(prevBoard);
		while(prevBoard.getPrevious() != null) { //iterate back to first Board, which its prev has no prev so ==null
			list.add(prevBoard.getPrevious());
			prevBoard = prevBoard.getPrevious();
		}
		prevBoard = list.get(0);
		Collections.reverse(list);
		//System.out.println("moves;" + list.size());
		return list;
	}
}



