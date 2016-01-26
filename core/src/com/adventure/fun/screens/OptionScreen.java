package com.adventure.fun.screens;

import com.adventure.fun._main.MainWindow;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by Sweta on 20.01.2016.
 */
public class OptionScreen implements Screen {

    private TextButton buttonBack;
    private MainWindow game;
    private Stage stage;

    public OptionScreen(MainWindow game){
        this.game = game;
    }

    @Override
    public void show() {

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera()),game.getBatch());
        Gdx.input.setInputProcessor(this.stage);

        buttonBack = game.getMenuScreen().createButton("BACK",0.05f,0.05f,2,2,15,10);
        buttonBack.getLabel().setFontScale(game.getFont().getScaleX() / 2,game.getFont().getScaleY() / 2);

        buttonBack.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(game.getMenuScreen());
            }
        });

        stage.addActor(buttonBack);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getMenuScreen().moveBackground();
        game.getBatch().begin();
        game.getBatch().draw(game.getMenuScreen().getBackgroundImage(), game.getMenuScreen().getX(), game.getMenuScreen().getY(), stage.getWidth() * 2,stage.getHeight() * 2);
        game.getBatch().end();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
