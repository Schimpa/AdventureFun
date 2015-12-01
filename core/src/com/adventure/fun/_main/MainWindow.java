package com.adventure.fun._main;

import com.badlogic.gdx.Game;

/**
 * Created by Sweta on 30.11.2015.
 */
public class MainWindow extends Game {
    private Game game;
    private boolean isActivated;

    @Override
    public void create() {
    }

    public MainWindow() {
        super();
        game = this;
        isActivated = false;
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
        if (isActivated != true){
            game.setScreen(new AdventureFun(game));
            isActivated = true;
        }

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
