package com.electrominers.ui;

import com.electrominers.game.Level;;
/**
 * A SimpleUI is a ui for the level that displays information and handles button presses throughout it. This UI
 * displays useful items such as Resources owned, miners in existence, etc. 
 * @author Tyler
 *
 */
public class SimpleUI {

	private Level level; // the world that is being reflected in this ui.
	
	public SimpleUI(Level level) {
		this.level = level;
	}
	
	// draws the UI to the screen. should be called later in the call sequence.
	public void render() {
		
	}
	
}
