package gna;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import libpract.*;

public class Stitcher
{

	int width;
	int height;
	public List<Position> seam(int[][] image1, int[][] image2) {
		width = image1[0].length;
		height = image1.length;
		
		Comparator<Vertex> comparator = new Comparator<Vertex>(){
			public int compare(Vertex vert1, Vertex vert2) {
				return Integer.compare(vert1.getTotalCost(), vert2.getTotalCost());
			}
		};
				
		realPosition begin = new realPosition(0,0);//linksboven
		realPosition goal = new realPosition(width - 1, height - 1);
		PriorityQueue<Vertex> priorityQueue = new PriorityQueue<Vertex>(comparator);
		HashMap<Position, Vertex> hashMap = new HashMap<Position, Vertex>();
		HashSet<Position> alreadyVisited = new HashSet<Position>();
		ArrayList<Position> seam = new ArrayList<Position>();

		Vertex init = new Vertex(null, begin, image1[0][0], image2[0][0]);//Vertex previous, Position newPos, images...)
		priorityQueue.add(init);
		hashMap.put(begin, init);
		
		while(!priorityQueue.peek().getPosition().equals(goal)) {//Worst case NxN, best case sqrt(NXN + NXN)
			Vertex currentPos = priorityQueue.poll();
			hashMap.remove(currentPos.getPosition());
			
			if (!alreadyVisited.contains(currentPos.getPosition())) {
				alreadyVisited.add(currentPos.getPosition());
				
				for (realPosition position : getNeighbors(currentPos.getPosition(), height, width)) {//8+ 9fromGetNeighb
					Vertex neighbor = new Vertex(currentPos, position, image1[position.getY()][position.getX()],image2[position.getY()][position.getX()]);
					
					if (!alreadyVisited.contains(position)) {
						
						if (hashMap.containsKey(position)) {
							
							if (neighbor.getTotalCost() < hashMap.get(position).getTotalCost()) {
								hashMap.put(position, neighbor);
								priorityQueue.remove(hashMap.get(position));
								priorityQueue.add(neighbor);
							}
							
						} else {
							
							hashMap.put(position, neighbor);
							priorityQueue.add(neighbor);
							
						}
						
					}
					
				}
				
			}
			
		}
		
		Vertex previous = priorityQueue.poll();
		
		while(previous != null) {
			seam.add(previous.getPosition());
			previous = previous.getPrevious();
		}
		
		Collections.reverse(seam);
		return seam;
	}
	
	public static HashSet<realPosition> getNeighbors(realPosition position, int height, int width) {
		HashSet<realPosition> neighbors = new HashSet<realPosition>();
		for (int idxX = position.getX()-1; idxX <= position.getX()+1; idxX++) {
			
			for (int idxY = position.getY()-1; idxY <= position.getY()+1; idxY++) {
				
				if (0 <= idxX && width > idxX && height > idxY && 0 <= idxY && !(idxX == position.getX() && idxY == position.getY())) {
					neighbors.add(new realPosition(idxX,idxY));
				}
				
			}
			
		}
		return neighbors;
	}

	public void floodfill(Stitch[][] mask) {
		int width = mask[0].length;
		int height = mask.length;
		Stack<Position> stack = new Stack<Position>();
		Position begin = new realPosition(0,height-1);
		stack.add(begin);

		HashSet<Position> alreadyVisited = new HashSet<Position>();		
		while(!stack.isEmpty()) {
			Position current = stack.pop();
			fill(mask, current);
			alreadyVisited.add(current);
			
			for (Position o : getAllignedNeighbors(current, height, width)) {
				
				if (!(alreadyVisited.contains(o))) {
					
					if (mask[o.getY()][o.getX()] != Stitch.SEAM) {
						stack.add(o);
					}
					
				}
				
			}
			
		}
		
		for (int i = 0; i < height; i++) {
			
			for (int j = 0; j < width; j++) {
				
				if (mask[i][j] != Stitch.SEAM && mask[i][j] != Stitch.IMAGE1) 
					mask[i][j] = Stitch.IMAGE2;
			}
			
		}
		
	}
	
	 public void fill(Stitch[][] mask, Position position){
        mask[position.getY()][position.getX()] = Stitch.IMAGE1;
    }
	
	public static HashSet<Position> getAllignedNeighbors(Position current, int height, int width) {
		HashSet<Position> neighbors = new HashSet<Position>();
		if (current.getX() != 0) 
			neighbors.add(new realPosition(current.getX()-1, current.getY()));
		
		if (current.getY() != 0) 
			neighbors.add(new realPosition(current.getX(), current.getY()-1));
		
		if (current.getX() != width-1) 
			neighbors.add(new realPosition(current.getX()+1, current.getY()));
		
		if (current.getY() != height-1) 
			neighbors.add(new realPosition(current.getX(), current.getY()+1));	
		
		return neighbors;
	}

	public Stitch[][] stitch(int[][] image1, int[][] image2) {
		List<Position> seamPositions = seam(image1,image2);
		Stitch[][] totalPic = new Stitch[image1.length][image1[0].length];
		
		for (Position position : seamPositions) {
			totalPic[position.getY()][position.getX()] = Stitch.SEAM;
		}
		
		floodfill(totalPic);
		return totalPic;
	}
}

class realPosition extends Position{
	public realPosition(int arg1, int arg2){
		super(arg2, arg1);
	}
	
}


class Vertex {
	public Vertex(Vertex previous, realPosition newPos, int image1, int image2) {
		this.previous = previous;
		this.currentCost = ImageCompositor.pixelSqDistance(image1, image2);
		if (this.previous != null) {
			this.totalCost = this.getPrevious().getTotalCost() + this.currentCost;
		} 
		else {
			this.totalCost = this.currentCost;
		}
		this.position = newPos;
		
	}
	
	public int getCost(){
		return this.currentCost;
	}
	
	public int getTotalCost(){
		return this.totalCost;
	}
	
	public Vertex getPrevious(){
		return this.previous;
	}
	
	public realPosition getPosition(){
		return this.position;
	}
	
	@Override
	public String toString() {
		return "Position: " + this.getPosition().toString() + "\nTotalCost: " + String.valueOf(getTotalCost()) + " \n(current cost = " + String.valueOf(this.getCost()) + ")";
	}
	
	private Vertex previous;
	private realPosition position;
	private int currentCost;
	private int totalCost;
}