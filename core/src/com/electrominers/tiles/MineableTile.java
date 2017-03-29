package com.electrominers.tiles;

import com.badlogic.gdx.graphics.Color;

public abstract class MineableTile extends Tile{
	
	private int strengthLeft;
	public MineableTile() {
		super();
		strengthLeft = 100;
	}
	
	public int getStrengthLeft() {
		return this.strengthLeft;
	}
	
	public void mine(int strength) {
		this.strengthLeft -= strength;
	}
	
	@Override
	public int movementCost() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public Color getDisplayColor() {
		// TODO Auto-generated method stub
		return Color.BROWN;
	}
	
	protected void setStrengthLeft(int strength) {
		this.strengthLeft = strength;
	}
	
	

}
