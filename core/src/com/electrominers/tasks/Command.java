package com.electrominers.tasks;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A Command is a sequence of tasks that a unit can excecute. 
 * @author Tyler
 *
 */
public class Command {

	private Queue<Task> tasklist;
	
	public Command() {
		tasklist = new LinkedList<Task>();
	}
	
	public void addTask(Task t) {
		tasklist.add(t);
	}
	
	public void executeCommand() {
		Task current = tasklist.peek();
		if (!current.isTaskDone()) {
			current.doTask();
		} else {
			tasklist.poll();
		}
	}
	
	public boolean isCommandDone() {
		return tasklist.isEmpty();
	}
}
