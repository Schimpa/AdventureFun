package com.adventure.fun.desktop;

import com.adventure.fun._main.MainWindow;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import javax.swing.text.View;

public class DesktopLauncher {
	public static void main (String[] arg) {

		createTextureAtlas();


		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.resizable = false;
		config.width = 1600;
		config.height = 900;
		config.fullscreen = false;
		// vSync
		config.vSyncEnabled = true;
		//System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		new LwjglApplication(new MainWindow(), config);
	}



	public static void createTextureAtlas(){
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxWidth = 2048;
		settings.maxHeight = 2048;
		settings.duplicatePadding = false;
		settings.debug = false;
		TexturePacker.processIfModified(settings,"texture_data","texture_data","images.pack");
	}

}
