package com.electrominers.tasks;

/**
 * A Task is a small, easily achievable goal a unit can achieve. Examples
 * might be moveTo(x, y) or pickUpItem(x,y). 
 * 
 * Tasks update themselves however they must, and let the users know when they are done so
 * the unit can move on to the next task.
 * @author Tyler
 *
 */
public interface Task {

	/**
	 * updates the task to do a part or the entire part of its task.
	 */
	public void doTask();
	
	/**
	 * @return if the task is completed.
	 */
	public boolean isTaskDone();
}
