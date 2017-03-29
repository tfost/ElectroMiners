package com.electrominers.tasks;

import com.electrominers.units.Unit;

public class MiningTask implements Task{
	
	private Unit unit;
	private int x;
	private int y;
	
	public MiningTask(Unit unit, int x, int y) {
		this.unit = unit;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void doTask() {
		if (!isTaskDone()) {
			unit.getWorld().mineTile(unit, x, y);
		}
	}

	@Override
	public boolean isTaskDone() {
		return !unit.getWorld().canMine(unit, x, y);
	}

}
