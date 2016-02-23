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
public class MenuScreen implements Screen {

    private MainWindow game;
    private boolean isActivated;

    private Stage stage;

    private Texture backgroundImage;

    private TextButton buttonPlay;
    private TextButton buttonSettings;
    private TextButton buttonEnd;

    private GameScreen gameScreen;

    private float x,y;

    boolean xFlag;
    boolean yFlag;

    public TextButton createButton(String name,float fontScaleX,float fontScaleY,float positionX,float positionY,float scaleX, float scaleY){
        TextButton buttonCreate;

        buttonCreate = new TextButton(name,game.getSkin(),"default");
        //buttonCreate.getLabel().setFontScale((Gdx.graphics.getWidth() / 100) * fontScaleX,(Gdx.graphics.getHeight() / 100) * fontScaleY);
        buttonCreate.setPosition((Gdx.graphics.getWidth() / 100) * positionX , (Gdx.graphics.getHeight() / 100) * positionY );
        buttonCreate.setSize((Gdx.graphics.getWidth() / 100 )* scaleX, (Gdx.graphics.getHeight() / 100) * scaleY);

        return buttonCreate;
    }

    public MenuScreen(MainWindow game){
        this.x = 0;
        this.y = 0;
        this.game = game;
    }




    @Override
    public void show() {

        isActivated = false;

        xFlag = false;
        yFlag = false;



        backgroundImage = game.getAssets().getBackgroundMenu_02();

        this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera()),game.getBatch());

        buttonPlay = new TextButton("PLAY",game.getSkin(),"default");
        //buttonPlay.getLabel().setFontScale((Gdx.graphics.getWidth() / 100) * 0.1f,(Gdx.graphics.getHeight() / 100) * 0.1f);
        buttonPlay.setPosition((Gdx.graphics.getWidth() / 100) * 30, (Gdx.graphics.getHeight() / 100) * 75);
        buttonPlay.setSize((Gdx.graphics.getWidth() / 100 )* 40, (Gdx.graphics.getHeight() / 100) * 20);

        buttonSettings = new TextButton("OPTIONS",game.getSkin(),"default");
        //buttonSettings.getLabel().setFontScale((Gdx.graphics.getWidth() / 100) * 0.1f,  (Gdx.graphics.getHeight() / 100) * 0.1f);
        buttonSettings.setPosition((Gdx.graphics.getWidth() / 100) * 30, (Gdx.graphics.getHeight() / 100) * 45);
        buttonSettings.setSize((Gdx.graphics.getWidth() / 100) * 40, (Gdx.graphics.getHeight() / 100) * 20);

        buttonEnd = new TextButton("END",game.getSkin(),"default");
        //buttonEnd.getLabel().setFontScale((Gdx.graphics.getWidth() / 100) * 0.1f, (Gdx.graphics.getHeight() / 100) * 0.1f);
        buttonEnd.setPosition((Gdx.graphics.getWidth() / 100) * 30, (Gdx.graphics.getHeight() / 100) * 15);
        buttonEnd.setSize((Gdx.graphics.getWidth() / 100) * 40, (Gdx.graphics.getHeight() / 100) * 20);

        buttonSettings.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("lolö");
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.getAssets().getSound_click_in().play(1f);
                game.setScreen(new OptionScreen(game));
            }
        });

        buttonPlay.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("lolö");
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.getAssets().getSound_click_in().play(1f);
                game.setScreen(new LevelChooseScreen(game));
            }
        });

        buttonEnd.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("lolö");
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.getAssets().getSound_click_out().play(0.5f);
                Gdx.app.exit();
            }
        });

        stage.addActor(buttonPlay);
        stage.addActor(buttonSettings);
        stage.addActor(buttonEnd);

        Gdx.input.setInputProcessor(stage);

    }

    public void moveBackground(){
        if (x <= -stage.getWidth()){
            xFlag = true;
        }else if (x >= 0){
            xFlag = false;
        }

        if (y <= -stage.getHeight()){
            yFlag = true;
        }else if (y >= 0){
            yFlag = false;
        }

        if (xFlag == false){
            x -= Gdx.graphics.getDeltaTime() * 25;
        }else if (xFlag == true){
            x += Gdx.graphics.getDeltaTime() * 25;
        }

        if (yFlag == false) {
            y -= Gdx.graphics.getDeltaTime() * 25 / 4;
        }else if (yFlag == true){
            y += Gdx.graphics.getDeltaTime() * 25 / 4;
        }
    }


    @Override
    public void render(float delta) {
        if (isActivated != true){
            Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            moveBackground();

            game.getBatch().begin();
            game.getBatch().setProjectionMatrix(stage.getCamera().combined);
            game.getBatch().draw(backgroundImage,x,y,stage.getWidth() * 2,stage.getHeight() * 2);
            game.getBatch().end();

            stage.draw();
        }
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


    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isxFlag() {
        return xFlag;
    }

    public void setxFlag(boolean xFlag) {
        this.xFlag = xFlag;
    }

    public boolean isyFlag() {
        return yFlag;
    }

    public void setyFlag(boolean yFlag) {
        this.yFlag = yFlag;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Texture getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Texture backgroundImage) {
        this.backgroundImage = backgroundImage;
    }


}

