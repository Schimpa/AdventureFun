package com.adventure.fun.desktop;

import com.adventure.fun._main.MainWindow;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.adventure.fun._main.AdventureFun;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class DesktopLauncher {
	public static void main (String[] arg) {

		//createTextureAtlas();


		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;


		// fullscreen
		config.fullscreen = true;
		// vSync
		config.vSyncEnabled = true;
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
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
