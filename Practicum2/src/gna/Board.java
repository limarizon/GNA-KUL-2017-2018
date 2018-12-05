package gna;

import java.util.Collection;
import java.util.Stack;

import libpract.PriorityFunc;

import java.math.BigInteger;
import java.util.Arrays;

public class Board {
	private int[][] tiles;

	// construct a board from an N-by-N array of tiles
	public Board(int[][] tiles) {
		boardSize = tiles.length;
		this.tiles = deepCopy(tiles);
	}

	private int boardSize;

	private int[][] deepCopy(int[][] array) {
		int[][] copy = new int[array.length][array[0].length];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				copy[i][j] = array[i][j];
			}
		}
		return copy;
	}

	// return number of blocks out of place
	public int hamming() {
		int costHam;
		int currentBoardPositionHam;
		int arrayPosHam;
		costHam = 0; //+1
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) { //NxN accesses due to innerloop going N, N times
				currentBoardPositionHam = tiles[i][j];
				if (currentBoardPositionHam == 0) {
					continue;
				}
				arrayPosHam = 1 + j + (i * this.boardSize);

				if (currentBoardPositionHam != arrayPosHam) {
					costHam++;
				}
			}
		}
		return costHam+moves;
	}

	// return sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int costMan = 0; //+1
		for (int i = 0; i < tiles.length; i++) { //For i to N
			for (int j = 0; j < tiles[i].length; j++) {//j to N, so both loops create NxN accesses
				int destination = this.tiles[i][j]; //+1
				int tileValue = (i * tiles.length + j) + 1;
				if (tileValue != destination && destination != 0) {
					int iteratorIdx = destination;
					int idxR = (iteratorIdx - 1) / (tiles.length);
					int idxC = (iteratorIdx - 1) - (idxR * (tiles.length));
					costMan += (Math.abs(i - idxR) + Math.abs(j - idxC));
				}
			}
		}

		return costMan + this.moves;
	}
	public boolean isSolved() {
		if (hamming() == moves) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object y) {
		if (!(y instanceof Board))
			return false;
		Board other = (Board) y;
		return Arrays.deepEquals(tiles, other.tiles);
	}
	
	public String toString() {
		String gameToString = "";
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				gameToString += tiles[i][j]+" ";
			}
			gameToString += "\n";
		}
		return gameToString;

	}


	// Since we override equals(), we must also override hashCode(). When two
	// objects are
	// equal according to equals() they must return the same hashCode. More info:
	// -
	// http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java/27609#27609
	// - http://www.ibm.com/developerworks/library/j-jtp05273/
	@Override
	public int hashCode() {
		return Arrays.deepHashCode(tiles);
	}

	// return a Collection of all neighboring board positions
	public Collection<Board> neighbors() {
		Stack<Board> boards = new Stack<Board>();
		for(int i = 0; i<tiles.length; i++) {
			for(int j = 0; j<tiles.length; j++) {
				if (tiles[i][j] == 0) {
					firstY = i;
					firstX = j;
				}
			}
		}
		if (firstX > 0) {
			Board left = new Board(swap(tiles, 0, -1));
			left.setMoves(this.moves+1);
			left.setPrevious(this);
			if (left != null && !this.isEqualToPrevBoard(left))
				boards.push(left);
		}

		if (firstX < boardSize - 1) {
			Board right = new Board(swap(tiles, 0, 1));
			right.setMoves(this.moves+1);
			right.setPrevious(this);
			if (right != null && !this.isEqualToPrevBoard(right))
				boards.push(right);
		}
		
		if (firstY > 0) {
			Board up = new Board(swap(tiles, -1, 0));
			
			up.setMoves(this.moves+1);
			up.setPrevious(this);
			if (up != null && !this.isEqualToPrevBoard(up))
				boards.push(up);
		}

		if (firstY < boardSize - 1) {
			Board down = new Board(swap(tiles, 1, 0));
			down.setMoves(this.moves+1);
			down.setPrevious(this);
			if (down != null && !this.isEqualToPrevBoard(down))
				boards.push(down);
		}
		
		for(Board board : boards) {//NEW
			board.setPriority(this.getPriority());
			if(board.getPriority() == PriorityFunc.HAMMING)
				board.setCost(board.hamming());
			else if(board.getPriority() == PriorityFunc.MANHATTAN)
				board.setCost(board.manhattan());
		}
		return boards;

	}
	
	private void setPrevious(Board board) {
		previous = board;
	}

	public boolean isEqualToPrevBoard(Board tmp){
		if(previous == null)
			return false;
		if (previous.equals(tmp))
			return true;
		return false;
	}
	
	private Board previous = null;
	
	
	public Board getPrevious() {
		return previous;
	}
	private int[][] swap(int[][] tiles2, int i, int j) {
		int [][]tmp = deepCopy(tiles2);
		tmp[firstY][firstX] = tiles2[firstY+i][firstX+j];
		tmp[firstY+i][firstX+j] = tiles2[firstY][firstX];
		
		return tmp;
	}

	private void setMoves(int i) {
		this.moves = i;
		
	}

	private int moves = 0;
	private int firstX;
	private int firstY;
	
	private PriorityFunc boardPriority;//NEW
	
	private PriorityFunc getPriority() {//NEW
		return this.boardPriority;
	}
	
	public void setPriority(PriorityFunc priority) {//NEW
		this.boardPriority = priority;
	}
	// is the initial board solvable? Note that the empty tile must
	// first be moved to its correct position.
	public boolean isSolvable() {
		Board board = getZeroConfiguredBoard(this); //Nkwad
		BigInteger numerator = BigInteger.valueOf(1);
		BigInteger denominator = BigInteger.valueOf(1);
		int i;
		int j;
		for (j = 1; j < board.boardSize * board.tiles[0].length; j++) {//((NxN-1)*(NxN))/2
			for (i = j-1; i > 0; i--) {
				BigInteger numMultiPl = BigInteger.valueOf((getTile(j, board) - getTile(i, board)));//Nkwad(Nkwad/2 for one getTile(.))
				BigInteger denomMultiPl = BigInteger.valueOf(j-i);
				
				numerator = numerator.multiply(numMultiPl);
				denominator = denominator.multiply(denomMultiPl);			
			}
		}
		System.out.println((numerator.divide(denominator)).intValue());	
		return ((numerator.divide(denominator)).intValue() >= 0);
	}

	private int getTile(int tileValue, Board board) {
		int i;
		int j;
		for(i = 0; i < board.boardSize; i++) {
			for(j = 0; j < board.tiles[0].length; j++) { //N kwad
				if(board.tiles[i][j] == tileValue)	//Nkwad at most, 1 at best
					return i*board.tiles[0].length+1+j;
			}
		}
		return -1;
	}

	//Note that we have a complexity of NxN in worst case to find 0 in our board.
	//
	//
	private Board getZeroConfiguredBoard(Board board) {
		int idxRow = 0;
		int idxCol = 0;
		Board tmpBoard = new Board(board.tiles);
		for(int i = 0; i < tmpBoard.boardSize; i++) {//i to N
			for(int j = 0; j < tmpBoard.boardSize; j++) {//jto N
				if(tmpBoard.tiles[i][j] == 0) {
					idxRow = i;
					idxCol = j;
					break;
				}
			}
		}
		while(idxRow != tmpBoard.boardSize-1) {
			//this is not true when we had worst case in first 
			//2 forloops. This will be worst case
			//if the first two forloops ended in their first iteration
			//This also holds still with our second while 
			//loop underneath for the collumn ordening.
			// Best case: 4 accesses per while loop
			//worst case: 2N accesses per while loop
			tmpBoard.tiles[idxRow][idxCol] = tmpBoard.tiles[idxRow +1][idxCol];
			tmpBoard.tiles[idxRow+1][idxCol] = 0;
			idxRow++;
		}
		while(idxCol != tmpBoard.tiles[0].length - 1) {
			tmpBoard.tiles[idxRow][idxCol] = tmpBoard.tiles[idxRow][idxCol+1];
			tmpBoard.tiles[idxRow][idxCol+1] = 0;
			idxCol++;
		}		
		return tmpBoard;
	}

	public int getCost() {//new
		return boardCost;
	}
	
	private int boardCost;//new
	private void setCost(int value) {
		boardCost = value;
	}
}
