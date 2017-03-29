package com.electrominers.controllers;

import com.badlogic.gdx.graphics.Camera;

/**
 * The Interactable Interface is used to reference any object in the game that is interactable - one that
 * can handle input. This interface contains a single method, handleInput, which controls the input taken by the 
 * implementer.
 * @author Tyler
 *
 */
public interface Interactable {
	
	/**
	 * handleInput tells an object to handle given mouse inputs. The method then returns whether or not
	 * the game should continue handling input, or if it was used up already for that frame. 
	 * @param input
	 * @param c
	 * @return
	 */
	public boolean handleInput(MouseInputListener input, Camera c);
	
}
