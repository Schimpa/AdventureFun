package com.adventure.fun._main;

import com.adventure.fun.screens.GameScreen;
import com.adventure.fun.screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sweta on 30.11.2015.
 */
public class MainWindow extends Game{

    private boolean isLoaded;
    private Assets assets;
    private SpriteBatch batch;


    @Override
    public void create() {
        batch = new SpriteBatch();
        assets = new Assets();
        isLoaded = false;
    }


    @Override
    public void dispose() {
        super.dispose();

    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        super.render();
        if (assets.getAssetManager().update() && isLoaded == false) {
            this.assets.done();
            isLoaded = true;
            this.setScreen(new MenuScreen(this));
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }



































    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }


    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }
}
