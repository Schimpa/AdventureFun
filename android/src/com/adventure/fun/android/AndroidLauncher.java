package com.adventure.fun.android;

import android.os.Bundle;

import com.adventure.fun._main.MainWindow;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;

		config.useImmersiveMode = true;
		config.hideStatusBar = true;
		initialize(new MainWindow(), config);
	}
}
