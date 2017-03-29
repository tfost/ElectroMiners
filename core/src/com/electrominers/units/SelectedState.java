package com.electrominers.units;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.electrominers.controllers.Interactable;
import com.electrominers.controllers.MouseInputListener;
import com.electrominers.game.World;
import com.electrominers.tasks.Command;
import com.electrominers.tasks.MiningCommand;
import com.electrominers.tasks.MiningTask;
import com.electrominers.tasks.MovementTask;
import com.electrominers.tiles.MineableTile;

public class SelectedState implements UnitState, Interactable{
	
	private Unit unit;
	
	public SelectedState(Unit unit) {
		this.unit = unit;
		System.out.println("Selected" + unit);
	}
	
	@Override
	public void update() {
		
	}

	@Override
	// This handleInput function considers input handled if this unit was clicked on and state was changed.
	public boolean handleInput(MouseInputListener input, Camera c) {
		if (input.justPressed(1)) { // right click to move somewhere.
			World w = unit.getWorld();
			int clickX = input.getX();
			int clickY = input.getY();
			Vector3 worldCoords = w.getTileCoordsFromScreen(c, clickX, clickY);
			if (w.getTile((int) worldCoords.x, (int) worldCoords.y).canMoveInto()) {
				Command moveCommand = new Command();
				moveCommand.addTask(new MovementTask(unit, (int) worldCoords.x, (int) worldCoords.y));
				unit.setState(new ExecuteCommandState(unit, moveCommand));
				return true;
			} else if (w.getTile((int) worldCoords.x, (int) worldCoords.y) instanceof MineableTile) {
				Command mineCommand = new MiningCommand(unit, (int) worldCoords.x, (int) worldCoords.y);
				unit.setState(new ExecuteCommandState(unit, mineCommand));
				return true;
			}
		} else if (input.justPressed(0)) { // left click to deselect.
			unit.setState(new IdleState(unit));
			return true;
		}
		return false;
	}

}
