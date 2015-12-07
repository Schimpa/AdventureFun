package com.adventure.fun._main;

import com.adventure.fun.audio.AudioController;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
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

    private WorldLoader worldLoader;
    private SpriteBatch batch;

    private Label score;
    private Label lives;
    private Skin skin;
    private BitmapFont font;

    private Button buttonJump;
    private Button buttonMoveRight;
    private Button buttonMoveLeft;

    public void dispose(){
        worldLoader.dispose();
        batch.dispose();
        hudStage.dispose();
    }


    public Cameras(WorldLoader worldLoader,SpriteBatch batch){
        this.worldLoader = worldLoader;
        this.batch = batch;

        playerCamera = new OrthographicCamera();
        playerCamera.setToOrtho(false, 1.6f*21 , 0.9f*21 );

        backgroundCamera = new OrthographicCamera();
        backgroundCamera.setToOrtho(false, (Gdx.graphics.getWidth() / Gdx.graphics.getPpiX()) * 5, (Gdx.graphics.getHeight() / Gdx.graphics.getPpiY()) * 5);

        createHUD();


    }

    public void createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/fonts/Arcon.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.size = 24;
        param.color = Color.BLACK;
        font = generator.generateFont(param);
    }

    public void createInputListener(){
        buttonJump.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TouchDown:  " + event);
                if (worldLoader.getPlayer().getBody().getLinearVelocity().y <= 0.1f && worldLoader.getPlayer().getBody().getLinearVelocity().y >= -0.1f) {
                    worldLoader.getPlayer().setIsJumping(false);
                }
                if (worldLoader.getPlayer().getIsJumping() == false) {
                    worldLoader.getPlayer().setIsJumping(true);
                    AudioController.sound_jump.play(0.1f);
                    worldLoader.getPlayer().getBody().setLinearVelocity(worldLoader.getPlayer().getBody().getLinearVelocity().x, 10);
                }
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
        createFont();
        this.skin = new Skin();
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal("ui/uiskin/uiskin.atlas")));
        this.skin.add("default-font", font);
        this.skin.load(Gdx.files.internal("ui/uiskin/uiskin.json"));

        buttonJump = new TextButton("<>",skin,"default");
        buttonJump.setSize(Gdx.graphics.getWidth() / 100 * 12, Gdx.graphics.getHeight() / 100 * 16);
        buttonJump.setPosition(Gdx.graphics.getWidth() / 100 * 2, Gdx.graphics.getHeight() / 100 * 4 );

        buttonMoveRight = new TextButton(">",skin,"default");
        buttonMoveRight.setSize(Gdx.graphics.getWidth() / 100 * 12, Gdx.graphics.getHeight() / 100 * 16);
        buttonMoveRight.setPosition(Gdx.graphics.getWidth() / 100 * 86, Gdx.graphics.getHeight() / 100 * 4 );

        buttonMoveLeft = new TextButton("<",skin,"default");
        buttonMoveLeft.setSize(Gdx.graphics.getWidth() / 100 * 12, Gdx.graphics.getHeight() / 100 * 16);
        buttonMoveLeft.setPosition(Gdx.graphics.getWidth() / 100 * 70, Gdx.graphics.getHeight() / 100 * 4 );

        score = new Label(String.format("%03d", this.worldLoader.getPlayer().getScore()), new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        lives = new Label(String.format("%03d", this.worldLoader.getPlayer().getLives()), new Label.LabelStyle(new BitmapFont(), Color.CYAN));

        score.setFontScale(5,5);
        lives.setFontScale(5,5);



        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.padTop(10);
        table.add(score);
        table.row();
        table.add(lives);

        hudStage.addActor(table);
        hudStage.addActor(buttonJump);
        hudStage.addActor(buttonMoveRight);
        hudStage.addActor(buttonMoveLeft);

        createInputListener();
    }

    public void update(float deltaTime){

        score.setText(Integer.toString(worldLoader.getPlayer().getScore()));
        lives.setText(Integer.toString(worldLoader.getPlayer().getLives()));

        //Setzt Kamerapositionen des Spielers
        playerCamera.position.x = worldLoader.getPlayer().getBody().getPosition().x;
        playerCamera.position.y = worldLoader.getPlayer().getBody().getPosition().y;

        //Setzt position des Hintergrundes
        backgroundCamera.position.x = worldLoader.getPlayer().getBody().getPosition().x / 10;
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
