package com.electrominers.units;

import com.badlogic.gdx.graphics.Camera;
import com.electrominers.controllers.Interactable;
import com.electrominers.controllers.MouseInputListener;
import com.electrominers.game.World;
import com.electrominers.tasks.Command;
import com.electrominers.tasks.Task;

public class ExecuteCommandState implements UnitState, Interactable{

	private Unit unit;
	private Command command;
	
	public ExecuteCommandState(Unit unit, Command command) {
		this.unit = unit;
		this.command = command;
		System.out.println(unit + " Is executing a command");
	}
	
	@Override
	public void update() {
		if (!command.isCommandDone()) {
			command.executeCommand();
		} else {
			unit.setState(new IdleState(unit));
		}
		
	}

	@Override
	public boolean handleInput(MouseInputListener input, Camera c) {
		return false; // nothing of note has happened, don't block input by returning true.
	}
	
	
}
