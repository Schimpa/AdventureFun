package com.adventure.fun._main;

import com.adventure.fun.screens.GameScreen;
import com.adventure.fun.screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sweta on 30.11.2015.
 */
public class MainWindow extends Game{
    private boolean startMenuScreen;
    private boolean startGameScreen;
    private SpriteBatch batch;


    @Override
    public void create() {
        batch = new SpriteBatch();
        startMenuScreen = true;
        startGameScreen = false;

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
        if (startMenuScreen == true) {
            startMenuScreen = false;
            this.setScreen(new MenuScreen(this));
        }else if (startGameScreen == true){
            startMenuScreen = false;
            this.setScreen(new GameScreen(this));

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

    public boolean isStartMenuScreen() {
        return startMenuScreen;
    }

    public void setStartMenuScreen(boolean startMenuScreen) {
        this.startMenuScreen = startMenuScreen;
    }

    public boolean isStartGameScreen() {
        return startGameScreen;
    }

    public void setStartGameScreen(boolean startGameScreen) {
        this.startGameScreen = startGameScreen;
    }
}
