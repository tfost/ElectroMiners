package com.electrominers.tasks;

import java.awt.Point;
import java.util.Stack;

import com.electrominers.units.IdleState;
import com.electrominers.units.PathFinder;
import com.electrominers.units.Unit;

/**
 * A movementTask is a task that helps a unit move throughout the world. 
 * @author Tyler
 *
 */
public class MovementTask implements Task{
	
	private Stack<Point> path;
	private Unit unit;
	
	/**
	 * Constructs a new movement task.
	 * @param u the unit that should be moved around
	 * @param x the x location the unit should move to
	 * @param y the y location the unit should move to. 
	 */
	public MovementTask(Unit u, int x, int y) {
		int startx = u.getX();
		int starty = u.getY();
		this.unit = u;
		PathFinder finder = new PathFinder();
		path = finder.findPath(startx, starty, x, y, u.getWorld());
		if (path == null || path.isEmpty()) {
			System.out.println("No path found.");
		}
	}
	
	public MovementTask(Unit u, Stack<Point> path) {
		this.unit = u;
		this.path = path;
		if (path == null || path.isEmpty()) {
			System.out.println("No path found.");
		}
	}

	@Override
	public void doTask() {
		if (!path.isEmpty()) {
			Point next = path.pop();
			unit.moveTo(next.x, next.y);
		}
	}

	@Override
	public boolean isTaskDone() {
		return path == null || path.isEmpty();
	}
	
	public Stack<Point> getPath() {
		Stack<Point> temp = new Stack<Point>();
		while (!path.isEmpty()) {
			temp.add(path.pop());
		}
		Stack<Point> result = new Stack<Point>();
		while (!temp.isEmpty()) {
			result.push(temp.peek());
			path.push(temp.pop());
		}
		return temp;
	}
	
	
	
	
	
}
