package com.adventure.fun.desktop;

import com.adventure.fun._main.MainWindow;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class DesktopLauncher {
	public static void main (String[] arg) {

		//createTextureAtlas();


		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 800;
		config.height = 600;
		// fullscreen
		config.fullscreen = false;
		// vSync
		config.vSyncEnabled = false;
		//System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		new LwjglApplication(new MainWindow(), config);
	}



	public static void createTextureAtlas(){
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxWidth = 1024;
		settings.maxHeight = 1024;
		settings.duplicatePadding = false;
		settings.debug = false;
		TexturePacker.processIfModified(settings,"texture_data","texture_data","images.pack");
	}

}
