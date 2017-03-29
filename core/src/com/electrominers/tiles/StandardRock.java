package com.electrominers.tiles;

import com.badlogic.gdx.graphics.Color;

public class StandardRock extends MineableTile{
	
	/**
	 * @see Tile.getDisplayColor
	 */
	@Override
	public Color getDisplayColor() {
		return Color.FIREBRICK;
	}
	
	@Override
	public boolean canMoveInto() {
		return false;
	}
	
	public StandardRock() {
		super();
		this.setStrengthLeft(200);
	}
	

}
