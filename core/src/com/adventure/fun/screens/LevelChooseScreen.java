package com.adventure.fun.screens;

import com.adventure.fun._main.MainWindow;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Dennis on 04.12.2015.
 */
public class LevelChooseScreen implements Screen {

    private MainWindow game;
    private Stage stage;

    private TextButton buttonLevel01,buttonLevel02,buttonLevel03,buttonLevel04,buttonLevel05,buttonLevel06,buttonLevel07,buttonLevel08,buttonLevel09, buttonBack;

    public LevelChooseScreen(MainWindow game){
        this.game = game;
    }

    public TextButton createButton(String name,float fontScaleX,float fontScaleY,float positionX,float positionY,float scaleX, float scaleY){
        TextButton buttonCreate;

        buttonCreate = new TextButton(name,game.getSkin(),"default");
        buttonCreate.getLabel().setFontScale((Gdx.graphics.getWidth() / 100) * fontScaleX,(Gdx.graphics.getHeight() / 100) * fontScaleY);
        buttonCreate.setPosition((Gdx.graphics.getWidth() / 100) * positionX , (Gdx.graphics.getHeight() / 100) * positionY );
        buttonCreate.setSize((Gdx.graphics.getWidth() / 100 )* scaleX, (Gdx.graphics.getHeight() / 100) * scaleY);

        return buttonCreate;
    }

    public void createButtonListener(TextButton button,final String levelName,final boolean activateLights){
        button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.getMenuScreen().setGameScreen(new GameScreen(game,levelName,activateLights));
                game.setScreen(game.getMenuScreen().getGameScreen());
            }
        });
    }


    @Override
    public void show() {
        this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera()),game.getBatch());
        Gdx.input.setInputProcessor(this.stage);

        buttonLevel01 = createButton("1-1",0.1f,0.1f,5,75,25,20);
        buttonLevel02 = createButton("1-2",0.1f,0.1f,37.5f,75,25,20);
        buttonLevel03 = createButton("1-3",0.1f,0.1f,70,75,25,20);
        buttonLevel04 = createButton("1-4",0.1f,0.1f,5,45,25,20);
        buttonLevel05 = createButton("1-5",0.1f,0.1f,37.5f,45,25,20);
        buttonLevel06 = createButton("1-6",0.1f,0.1f,70,45,25,20);
        buttonLevel07 = createButton("1-7",0.1f,0.1f,5,15,25,20);
        buttonLevel08 = createButton("1-8",0.1f,0.1f,37.5f,15,25,20);
        buttonLevel09 = createButton("1-9",0.1f,0.1f,70,15,25,20);

        buttonBack = createButton("BACK",0.05f,0.05f,2,2,15,10);

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

        createButtonListener(buttonLevel01,"maps/earth.tmx",false);
        createButtonListener(buttonLevel02,"maps/mars.tmx",true);

        stage.addActor(buttonLevel01);
        stage.addActor(buttonLevel02);
        stage.addActor(buttonLevel03);
        stage.addActor(buttonLevel04);
        stage.addActor(buttonLevel05);
        stage.addActor(buttonLevel06);
        stage.addActor(buttonLevel07);
        stage.addActor(buttonLevel08);
        stage.addActor(buttonLevel09);
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
        stage.getViewport().update(width,height);
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
