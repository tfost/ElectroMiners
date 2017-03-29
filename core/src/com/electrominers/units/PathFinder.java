package com.electrominers.units;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.electrominers.game.World;


/**
 * Contains methods for finding a path between two points in a World
 * @author Tyler
 *
 */
public class PathFinder {
	
	/**
	 * Finds a path between two points in a world. 
	 * @requires the point (destx, desty) and (srcx, srcy) can be moved into.
	 * @param srcx starting x
	 * @param srcy starting y coordinate
	 * @param destx destination x coordinate
	 * @param desty destination y coordinate
	 * @param world the world this path is happening in. 
	 * @return a Stack of Points to follow to advance along the path, or null
	 * 			if no path is found.
	 */
	public Stack<Point> findPath(int srcx, int srcy, int destx, int desty, World world) {
		List<PathNode> openList = new ArrayList<PathNode>(); // nodes to explore more.
		Set<Point> closedList = new HashSet<Point>(); // visited nodes
		PathNode start = new PathNode(srcx, srcy, null, world, destx, desty);
		openList.add(start);
		while (!openList.isEmpty()) {
			PathNode minSoFar = openList.get(0); // next node to check out should have lowest f cost
			for (PathNode node : openList) {
				if (node.f < minSoFar.f) {
					minSoFar = node;
				}
			}
			if (minSoFar.coords.x == destx && minSoFar.coords.y == desty) {
				return assemblePath(minSoFar);
			}
			openList.remove(minSoFar);
			closedList.add(minSoFar.coords);
			for (Point neighbor : getNeighbors(minSoFar.coords)) {
				if (!closedList.contains(neighbor) && world.getTile(neighbor.x, neighbor.y).canMoveInto()) {
					PathNode neighborNode = new PathNode(neighbor.x, neighbor.y, minSoFar, world, destx, desty);
					if (openList.contains(neighborNode)) {
						PathNode openVersion = openList.get(openList.indexOf(neighborNode));
						if (neighborNode.g < openVersion.g) {
							openVersion.g = neighborNode.g;
							openVersion.previous = neighborNode.previous;
						}
					} else {
						openList.add(neighborNode);
					}
					
				}
			}
			
		}
		
		return null;
	}
	
	/**
	 * Finds the neighbors 
	 * @param src the starting point.
	 * @return a set containing points that are all considered neighbors. 
	 */
	public Set<Point> getNeighbors(Point src) {
		Set<Point> neighbors = new HashSet<Point>();
		neighbors.add(new Point(src.x + 1, src.y));
		neighbors.add(new Point(src.x - 1, src.y));
		neighbors.add(new Point(src.x, src.y - 1));
		neighbors.add(new Point(src.x, src.y + 1));
		return neighbors;
	}
	
	private Stack<Point> assemblePath(PathNode destination) {
		Stack<Point> path = new Stack<Point>();
		while (destination.previous != null) {
			path.add(destination.coords);
			destination = destination.previous;
		}
		return path;
	}

	/**
	 * A class representing important utility information for a* to work.
	 * @author Tyler
	 *
	 */
	private class PathNode {
		/*
		 * Note: Pathnodes should only exist for tile coordinates that can be
		 * moved into, ie those with positive movment costs. Otherwise, there will
		 * be an issue. 
		 */
		public PathNode previous;
		public Point coords;
		public int g; // base score of node; cost of moving from start to this node.
		public int h;
		public int f;
		
		
		public PathNode (int x, int y, PathNode previous, World world, int destx, int desty) {
			this.previous = previous;
			this.coords = new Point(x, y);
			if (previous == null) {
				g = 0;
			} else {
				g = previous.g + world.getTile(x, y).movementCost();
			}
			h = Math.abs(coords.x - destx) + Math.abs(coords.y - desty);
			f = g + h;
		}


		@Override
		public boolean equals(Object other) {
			if (! (other instanceof PathNode)) {
				return false;
			} else {
				PathNode o = (PathNode) other;
				return o.coords.equals(this.coords);
			}
		}
		
		
	}
	
}
