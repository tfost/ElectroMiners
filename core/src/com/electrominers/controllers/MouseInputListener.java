package com.electrominers.controllers;

import com.badlogic.gdx.Gdx;

/**
 * A basic mouse input listener. Assumes mice used by game have 3 buttons that ill be used.
 * @author Tyler
 *
 */
public class MouseInputListener {

	private enum ButtonState {RELEASED, PRESSED, JUSTPRESSED};
	private ButtonState[] buttons; // contains current state of buttons 0-2. NOTE: left = 0, right = 1, middle = 2.
	private int mouseX;
	private int mouseY;
	
	public MouseInputListener() {
		buttons = new ButtonState[3];
	}

	public void poll() {
		for (int i = 0; i < buttons.length; i++) {
			if (Gdx.input.isButtonPressed(i)) {
				if (buttons[i] == ButtonState.RELEASED) {
					buttons[i] = ButtonState.JUSTPRESSED;
				} else {
					buttons[i] = ButtonState.PRESSED;
				}
			} else {
				buttons[i] = ButtonState.RELEASED;
			}
		}
		mouseX = Gdx.input.getX();
		mouseY = Gdx.input.getY();
	}
	
	public boolean isPressed(int button) {
		return buttons[button] == ButtonState.PRESSED;
	}
	
	public boolean justPressed(int button) {
		return buttons[button] == ButtonState.JUSTPRESSED;
	}
	
	public boolean isReleassed(int button) {
		return buttons[button] == ButtonState.RELEASED;
	}
	
	public int getX() {
		return mouseX;
	}
	
	public int getY() {
		return mouseY;
	}
	
}
