package com.electrominers.units;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.electrominers.controllers.MouseInputListener;
import com.electrominers.game.World;

/**
 * A Unitstate is a state a unit can take that determines what it should be doing at a given time. 
 * @author Tyler
 *
 */
public interface UnitState {
	
	public void update();
	
	public boolean handleInput(MouseInputListener input, Camera camera);

}
