package com.adventure.fun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.adventure.fun.AdventureFun;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class DesktopLauncher {
	public static void main (String[] arg) {

		createTextureAtlas();


		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new AdventureFun(), config);
	}




	public static void createTextureAtlas(){
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxWidth = 1024;
		settings.maxHeight = 1024;
		settings.duplicatePadding = false;
		settings.debug = false;
		TexturePacker.processIfModified(settings,"android/assets/textures","android/assets/textures","images.pack");
	}
}
