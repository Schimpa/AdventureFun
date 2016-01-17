package com.adventure.fun._main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    //HUD
    private TextButton buttonJump;
    private TextButton buttonMoveRight;
    private TextButton buttonMoveLeft;
    private TextButton nothingButton;
    private TextButton buttonShoot;

    //GAMEOVERSCREEN
    private Image gameOverOverlay;

    private Vector2 screenRatio;

    public void dispose(){
        worldLoader.dispose();
        batch.dispose();
        hudStage.dispose();
    }

    public Cameras(MainWindow game,WorldLoader worldLoader,SpriteBatch batch){
        this.game = game;
        this.worldLoader = worldLoader;
        this.batch = batch;
        screenRatio = new Vector2();
        screenRatio.set(Gdx.graphics.getWidth() / ((ggt(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()))),Gdx.graphics.getHeight() / ((ggt(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()))));

        System.out.println(screenRatio.x);
        System.out.println(screenRatio.y);

        System.out.println(ggt(1280,720));
        playerCamera = new OrthographicCamera();
        if (screenRatio.x == 16 && screenRatio.y == 9){
            playerCamera.setToOrtho(false, screenRatio.x * 1.5f , screenRatio.y * 1.5f );
        } else if (screenRatio.x == 4 && screenRatio.y == 3){
            playerCamera.setToOrtho(false, screenRatio.x * 5.5f , screenRatio.y * 5.5f );
        }


        backgroundCamera = new OrthographicCamera();
        backgroundCamera.setToOrtho(false, (Gdx.graphics.getWidth() / Gdx.graphics.getPpiX()) * 5, (Gdx.graphics.getHeight() / Gdx.graphics.getPpiY()) * 5);

        createHUD();


    }
    private static float ggt(float zahl1, float zahl2) {
        while (zahl2 != 0) {
            if (zahl1 > zahl2) {
                zahl1 = zahl1 - zahl2;
            } else {
                zahl2 = zahl2 - zahl1;
            }
        }
        return zahl1;
    }

    public static Vector2 updateScreenRatio(){
        Vector2 screenRatio = new Vector2(Gdx.graphics.getWidth() / (ggt(Gdx.graphics.getWidth(),Gdx.graphics.getHeight())*10),Gdx.graphics.getHeight() / (ggt(Gdx.graphics.getWidth(),Gdx.graphics.getHeight())*10));
        return (screenRatio);
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

        buttonMoveLeft.addListener(new ClickListener() {
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
        buttonJump.getLabel().setFontScale((Gdx.graphics.getWidth() / 100) * 0.08f,  (Gdx.graphics.getHeight() / 100) * 0.08f);
        buttonJump.setColor(0.7f, 1, 0.4f, 0.5f);
        buttonJump.setSize(Gdx.graphics.getWidth() / 100 * 10, Gdx.graphics.getHeight() / 100 * 14);
        buttonJump.setPosition(Gdx.graphics.getWidth() / 100 * 2, Gdx.graphics.getHeight() / 100 * 4);

        buttonShoot = new TextButton("-D",game.getSkin(),"default");
        buttonShoot.getLabel().setFontScale((Gdx.graphics.getWidth() / 100) * 0.1f,  (Gdx.graphics.getHeight() / 100) * 0.1f);
        buttonShoot.setColor(0.7f, 1, 0.4f, 0.5f);
        buttonShoot.setSize(Gdx.graphics.getWidth() / 100 * 10, Gdx.graphics.getHeight() / 100 * 14);
        buttonShoot.setPosition(Gdx.graphics.getWidth() / 100 * 2, Gdx.graphics.getHeight() / 100 * 20);

        buttonMoveLeft = new TextButton("<",game.getSkin(),"default");
        buttonMoveLeft.getLabel().setFontScale((Gdx.graphics.getWidth() / 100) * 0.1f,  (Gdx.graphics.getHeight() / 100) * 0.1f);
        buttonMoveLeft.setColor(0.7f, 1, 0.4f, 0.5f);
        buttonMoveLeft.setSize(Gdx.graphics.getWidth() / 100 * 10, Gdx.graphics.getHeight() / 100 * 14);
        buttonMoveLeft.setPosition(Gdx.graphics.getWidth() / 100 * 70, Gdx.graphics.getHeight() / 100 * 4);

        buttonMoveRight = new TextButton(">",game.getSkin(),"default");
        buttonMoveRight.getLabel().setFontScale((Gdx.graphics.getWidth() / 100) * 0.1f,  (Gdx.graphics.getHeight() / 100) * 0.1f);
        buttonMoveRight.setColor(0.7f, 1, 0.4f, 0.5f);
        buttonMoveRight.setSize(Gdx.graphics.getWidth() / 100 * 10, Gdx.graphics.getHeight() / 100 * 14);
        buttonMoveRight.setPosition(Gdx.graphics.getWidth() / 100 * 86, Gdx.graphics.getHeight() / 100 * 4);

        nothingButton = new TextButton("",game.getSkin(),"default");
        nothingButton.setColor(1,1,1,1);
        nothingButton.setSize(0,0);
        nothingButton.setPosition(-50,-50);

        score = new Label(Integer.toString(this.worldLoader.getPlayer().getScore()), new Label.LabelStyle(game.getFont(), Color.GREEN));
        score.setPosition(Gdx.graphics.getWidth() / 100 * 5, Gdx.graphics.getHeight() / 100 * 82);
        score.setFontScale(Gdx.graphics.getWidth() / 100 * 0.1f, Gdx.graphics.getHeight() / 100 * 0.1f);

        lives = new Label(Integer.toString(this.worldLoader.getPlayer().getLives()), new Label.LabelStyle(game.getFont(), Color.GREEN));
        lives.setPosition(Gdx.graphics.getWidth() / 100 * 5, Gdx.graphics.getHeight() / 100 * 72);
        lives.setFontScale(Gdx.graphics.getWidth() / 100 * 0.1f, Gdx.graphics.getHeight() / 100 * 0.1f);

        game.getFont().setColor(Color.WHITE);


        hudStage.addActor(buttonJump);
        hudStage.addActor(buttonShoot);
        hudStage.addActor(buttonMoveRight);
        hudStage.addActor(buttonMoveLeft);
        hudStage.addActor(nothingButton);
        hudStage.addActor(score);
        hudStage.addActor(lives);


        createInputListener();
    }

    public void createGameOverScreen(){
        gameOverOverlay = new Image();
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

    public TextButton getButtonJump() {
        return buttonJump;
    }

    public void setButtonJump(TextButton buttonJump) {
        this.buttonJump = buttonJump;
    }

    public Vector2 getScreenRatio() {
        return screenRatio;
    }

    public void setScreenRatio(Vector2 screenRatio) {
        this.screenRatio = screenRatio;
    }

}
