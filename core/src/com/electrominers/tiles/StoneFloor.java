package com.electrominers.tiles;

import com.badlogic.gdx.graphics.Color;

public class StoneFloor extends Tile{

	@Override
	public Color getDisplayColor() {
		return Color.LIGHT_GRAY;
	}
	
	@Override
	public boolean canMoveInto() {
		return true;
	}

	@Override
	public int movementCost() {
		return 1;
	}

}
