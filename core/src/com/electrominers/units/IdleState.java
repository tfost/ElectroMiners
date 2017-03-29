package com.electrominers.units;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.electrominers.controllers.MouseInputListener;
import com.electrominers.game.World;

public class IdleState implements UnitState {
	
	private Unit unit;
	private boolean selected = false;
	
	public IdleState(Unit u) {
		System.out.println(u + " is now idle");
		this.unit = u;
	}
	
	@Override
	public void update() {

	}

	@Override
	public boolean handleInput(MouseInputListener input, Camera c) {
		if (input.justPressed(0)) {
			Vector3 tilecoords = unit.getWorld().getTileCoordsFromScreen(c, input.getX(), input.getY());
			if (tilecoords.x == unit.getX() && tilecoords.y == unit.getY()) {
				System.out.println("Clicked on unit");
				unit.setState(new SelectedState(unit));
				return true;
			}
		}
		return false;
	}

}
