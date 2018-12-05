package gna;

import libpract.StdIn;
import libpract.PriorityFunc;

class Main
{
	public static void main( String[] args )
	{
		int N = StdIn.readInt();
		int[][] tiles = new int[N][N];
		System.out.println("INIT OK");
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				tiles[i][j] = StdIn.readInt();
		System.out.println("BOARD INIT OK");
		Board initial = new Board(tiles);
		if (!initial.isSolvable())
		{
			System.out.println("No solution possible");
		}
		else
		{
			System.out.println("CURRENTLY IN SOLVER");
			Solver solver = new Solver(initial, PriorityFunc.MANHATTAN);
			for (Board board : solver.solution())
				System.out.println(board);

			System.out.println("Minimum number of moves = " + Integer.toString(solver.solution().size() - 1));
		}
	}
}


