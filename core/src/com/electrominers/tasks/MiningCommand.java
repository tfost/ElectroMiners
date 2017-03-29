package com.electrominers.tasks;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.electrominers.units.PathFinder;
import com.electrominers.units.Unit;

/**
 * A MiningCommand is a command that tells a unit to move next to a rock and then mine it.
 * @author Tyler
 *
 */
public class MiningCommand extends Command{
	
	private Unit unit;
	private MovementTask moveTask;
	private MiningTask mineTask;
	private int minex;
	private int miney;
	private boolean isTaskDone;
	
	/**
	 * 
	 * @param u
	 * @param x x coordinate of rock to mine
	 * @param y y coordinate of rock to mine.
	 */
	public MiningCommand(Unit u, int x, int y) {
		this.unit = u;
		int startx = unit.getX();
		int starty = unit.getY();
		minex = x;
		miney = y;
		
		Stack<Point> path;
		PathFinder finder = new PathFinder();
		Set<Point> neighbors = finder.getNeighbors(new Point(minex, miney));
		List<Stack<Point>> paths = new ArrayList<Stack<Point>>();
		for(Point p : neighbors) {
			paths.add(finder.findPath(startx, starty, p.x, p.y, unit.getWorld()));
		}
		for (int i = paths.size() - 1; i >= 0; i--) {
			if (paths.get(i) == null) {
				paths.remove(i);
			}
		}
		if (!paths.isEmpty()) {
			path = paths.get(0);
			for (int i = 1; i < paths.size(); i++) {
				if (paths.get(i).size() < path.size()) {
					path = paths.get(i);
				}
			}
		} else {
			path = null;
		}
		moveTask = new MovementTask(unit, path);
		if (moveTask.isTaskDone()) {
			mineTask = new MiningTask(unit, minex, miney);
		}
		isTaskDone = false;
	}
	
	
	@Override
	public void executeCommand() {
		if (!moveTask.isTaskDone()) {
			moveTask.doTask();
			if (moveTask.isTaskDone()) {
				mineTask = new MiningTask(unit, minex, miney);
			}
		} else if (mineTask != null && !mineTask.isTaskDone()) {
			mineTask.doTask();
		} else {
			isTaskDone = true;
		}
	}
	
	@Override
	public boolean isCommandDone() {
		return isTaskDone;
	}
}
