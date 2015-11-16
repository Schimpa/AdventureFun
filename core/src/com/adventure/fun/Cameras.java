package com.adventure.fun;

import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javafx.scene.image.ImageView;


/**
 * Created by Dennis on 14.11.2015.
 */
public class Cameras {


    //Gamecamera
    private OrthographicCamera playerCamera;

    //Backgroundcamera
    private OrthographicCamera backgroundCamera;

    private Stage hudStage;
    private Viewport viewport;

    private WorldLoader worldLoader;
    private SpriteBatch batch;

    private Label score;
    private Label lives;


    public Cameras(WorldLoader worldLoader,SpriteBatch batch){

        this.worldLoader = worldLoader;
        this.batch = batch;

        playerCamera = new OrthographicCamera();
        playerCamera.setToOrtho(false, Gdx.graphics.getWidth() / 40, Gdx.graphics.getHeight() / 40);

        backgroundCamera = new OrthographicCamera();
        backgroundCamera.setToOrtho(false, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 20);

        createHUD();

    }

    public void createHUD(){
        viewport = new FitViewport(500,500,new OrthographicCamera());
        hudStage = new Stage(viewport,batch);

        score = new Label(String.format("%03d", this.worldLoader.getPlayer().getScore()), new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        lives = new Label(String.format("%03d", this.worldLoader.getPlayer().getLives()), new Label.LabelStyle(new BitmapFont(), Color.CYAN));

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.padTop(10);
        table.add(score);
        table.row();
        table.add(lives);

        hudStage.addActor(table);
    }

    public void update(){

        score.setText(Integer.toString(worldLoader.getPlayer().getScore()));
        lives.setText(Integer.toString(worldLoader.getPlayer().getLives()));

        //Setzt Kamerapositionen des Spielers
        playerCamera.position.x = worldLoader.getPlayer().getSprite().getX();
        playerCamera.position.y = worldLoader.getPlayer().getSprite().getY();

        //Setzt position des Hintergrundes
        backgroundCamera.position.x = worldLoader.getPlayer().getSprite().getX() / 10;
        backgroundCamera.position.y = worldLoader.getPlayer().getSprite().getY() / 10;

        //Aktualisiert Kameras
        playerCamera.update();
        backgroundCamera.update();

    }


    public Stage getHudStage() {
        return hudStage;
    }

    public void setHudStage(Stage hudStage) {
        this.hudStage = hudStage;
    }

    public OrthographicCamera getPlayerCamera() {
        return playerCamera;
    }

    public void setPlayerCamera(OrthographicCamera playerCamera) {
        this.playerCamera = playerCamera;
    }

    public OrthographicCamera getBackgroundCamera() {
        return backgroundCamera;
    }

    public void setBackgroundCamera(OrthographicCamera backgroundCamera) {
        this.backgroundCamera = backgroundCamera;
    }
}
