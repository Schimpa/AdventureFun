package com.adventure.fun._main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;


/**
 * Created by Dennis on 14.11.2015.
 */
public class Cameras {

    //Gamecamera
    private OrthographicCamera playerCamera;

    //Backgroundcamera
    private OrthographicCamera backgroundCamera;

    private Stage hudStage;

    private MainWindow game;
    private WorldLoader worldLoader;
    private SpriteBatch batch;

    private Label score;
    private Label lives;

    private Button buttonJump;
    private Button buttonMoveRight;
    private Button buttonnachlinks;
    private Button nothingButton;
    private Button buttonShoot;

    public void dispose(){
        worldLoader.dispose();
        batch.dispose();
        hudStage.dispose();
    }

    public Cameras(MainWindow game,WorldLoader worldLoader,SpriteBatch batch){
        this.game = game;
        this.worldLoader = worldLoader;
        this.batch = batch;

        playerCamera = new OrthographicCamera();
        playerCamera.setToOrtho(false, 1.6f*21 , 0.9f*21 );

        backgroundCamera = new OrthographicCamera();
        backgroundCamera.setToOrtho(false, (Gdx.graphics.getWidth() / Gdx.graphics.getPpiX()) * 5, (Gdx.graphics.getHeight() / Gdx.graphics.getPpiY()) * 5);

        createHUD();


    }


    public void createInputListener(){
        buttonJump.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TouchDown:  " + event);

                if (worldLoader.getPlayer().getIsJumping() == false && worldLoader.getPlayer().getBody().getLinearVelocity().y <= 0.1f &&
                        worldLoader.getPlayer().getBody().getLinearVelocity().y >= -0.1f) {
                    worldLoader.getPlayer().setJumpTimer(0);
                    worldLoader.getPlayer().setIsJumping(true);
                    game.getAssets().getSound_jump().play(0.1f);
                }
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                System.out.println("TouchUp:  " + event);
                worldLoader.getPlayer().setIsJumping(false);

            }
        });

        buttonShoot.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TouchDown:  " + event);
                worldLoader.getPlayer().getBullet().setBulletShoot(true);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                System.out.println("TouchUp:  " + event);
            }
        });

        buttonMoveRight.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TouchDown:  " + event);
                worldLoader.getPlayer().setIsMovingRight(true);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                worldLoader.getPlayer().setIsMovingRight(false);
                System.out.println("TouchUp:  " + event);
            }
        });

        buttonnachlinks.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TouchDown:  " + event);
                worldLoader.getPlayer().setIsMovingLeft(true);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                worldLoader.getPlayer().setIsMovingLeft(false);
                System.out.println("TouchUp:  " + event);
            }
        });
    }

    public void createHUD(){
        hudStage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera()),batch);


        buttonJump = new TextButton("<>",game.getSkin(),"default");
        buttonJump.setColor(0.7f, 1, 0.4f, 0.5f);
        buttonJump.setSize(Gdx.graphics.getWidth() / 100 * 10, Gdx.graphics.getHeight() / 100 * 14);
        buttonJump.setPosition(Gdx.graphics.getWidth() / 100 * 2, Gdx.graphics.getHeight() / 100 * 4);

        buttonShoot = new TextButton("-D",game.getSkin(),"default");
        buttonShoot.setColor(0.7f, 1, 0.4f, 0.5f);
        buttonShoot.setSize(Gdx.graphics.getWidth() / 100 * 10, Gdx.graphics.getHeight() / 100 * 14);
        buttonShoot.setPosition(Gdx.graphics.getWidth() / 100 * 2, Gdx.graphics.getHeight() / 100 * 20);

        buttonnachlinks = new TextButton("<",game.getSkin(),"default");
        buttonnachlinks.setColor(0.7f, 1, 0.4f, 0.5f);
        buttonnachlinks.setSize(Gdx.graphics.getWidth() / 100 * 10, Gdx.graphics.getHeight() / 100 * 14);
        buttonnachlinks.setPosition(Gdx.graphics.getWidth() / 100 * 70, Gdx.graphics.getHeight() / 100 * 4);

        buttonMoveRight = new TextButton(">",game.getSkin(),"default");
        buttonMoveRight.setColor(0.7f, 1, 0.4f, 0.5f);
        buttonMoveRight.setSize(Gdx.graphics.getWidth() / 100 * 10, Gdx.graphics.getHeight() / 100 * 14);
        buttonMoveRight.setPosition(Gdx.graphics.getWidth() / 100 * 86, Gdx.graphics.getHeight() / 100 * 4);

        nothingButton = new TextButton("",game.getSkin(),"default");
        nothingButton.setColor(1,1,1,1);
        nothingButton.setSize(0,0);
        nothingButton.setPosition(-50,-50);

        score = new Label(Integer.toString(this.worldLoader.getPlayer().getScore()), new Label.LabelStyle(game.getFont(), Color.GREEN));
        score.setPosition(Gdx.graphics.getWidth() / 100 * 5, Gdx.graphics.getHeight() / 100 * 90);
        score.setFontScale(Gdx.graphics.getWidth() / 100 * 0.1f, Gdx.graphics.getHeight() / 100 * 0.1f);

        lives = new Label(Integer.toString(this.worldLoader.getPlayer().getLives()), new Label.LabelStyle(game.getFont(), Color.GREEN));
        lives.setPosition(Gdx.graphics.getWidth() / 100 * 5, Gdx.graphics.getHeight() / 100 * 85);
        lives.setFontScale(Gdx.graphics.getWidth() / 100 * 0.1f, Gdx.graphics.getHeight() / 100 * 0.1f);

        game.getFont().setColor(Color.WHITE);


        hudStage.addActor(buttonJump);
        hudStage.addActor(buttonShoot);
        hudStage.addActor(buttonMoveRight);
        hudStage.addActor(buttonnachlinks);
        hudStage.addActor(nothingButton);
        hudStage.addActor(score);
        hudStage.addActor(lives);


        createInputListener();
    }

    public void update(float deltaTime){

        score.setText(Integer.toString(worldLoader.getPlayer().getScore()));
        lives.setText(Integer.toString(worldLoader.getPlayer().getLives()));

        //Setzt Kamerapositionen des Spielers
        playerCamera.position.x = worldLoader.getPlayer().getBody().getPosition().x;
        playerCamera.position.y = worldLoader.getPlayer().getBody().getPosition().y + 2;

        //Setzt position des Hintergrundes
        backgroundCamera.position.x = worldLoader.getPlayer().getBody().getPosition().x / 10 ;
        backgroundCamera.position.y = worldLoader.getPlayer().getBody().getPosition().y / 10;

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

    public Button getButtonJump() {
        return buttonJump;
    }

    public void setButtonJump(Button buttonJump) {
        this.buttonJump = buttonJump;
    }


}
