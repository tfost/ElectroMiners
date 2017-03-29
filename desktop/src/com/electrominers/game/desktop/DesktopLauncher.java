package com.electrominers.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.electrominers.game.ElectroMinersMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "ElectroMiners Prototype";
		config.width = 768;
		config.height = 768;
		new LwjglApplication(new ElectroMinersMain(), config);
	}
}
