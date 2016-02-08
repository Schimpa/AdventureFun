package com.adventure.fun._main;

import com.adventure.fun.screens.GameScreen;
import com.adventure.fun.screens.LevelChooseScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
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
    private Stage gameOverStage;

    private MainWindow game;
    private WorldLoader worldLoader;
    private SpriteBatch batch;

    private Label score;
    private Label lives;

    private Label gameOverText;

    private TextButton buttonRetry;
    private TextButton buttonBack;

    //HUD
    private TextButton buttonJump;
    private TextButton buttonMoveRight;
    private TextButton buttonMoveLeft;
    private TextButton nothingButton;
    private TextButton buttonShoot;

    //GAMEOVERSCREEN
    private Image gameOverOverlay;

    private Vector2 screenRatio;

    float posX,posY,sizeX,sizeY;

    public void dispose(){
        worldLoader.dispose();
        batch.dispose();
        hudStage.dispose();
        gameOverStage.dispose();
    }

    public Cameras(MainWindow game,WorldLoader worldLoader,SpriteBatch batch){
        this.game = game;
        this.worldLoader = worldLoader;
        this.batch = batch;

        //Berechnet das Seitenverhältniss
        screenRatio = new Vector2((Gdx.graphics.getWidth() / ((ggt(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())))), Gdx.graphics.getHeight() / ((ggt(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()))));
        if (screenRatio.x == 8 && screenRatio.y == 5){
            screenRatio.x *= 2;
            screenRatio.y *= 2;
        }

        System.out.println("X:" + screenRatio.x + "|" + "Y:" +  screenRatio.y);

        System.out.println("X:" + screenRatio.x + "|" + "Y:" +  screenRatio.y);

        backgroundCamera = new OrthographicCamera();
        backgroundCamera.setToOrtho(false, (Gdx.graphics.getWidth() / Gdx.graphics.getPpiX()) * 5f, (Gdx.graphics.getHeight() / Gdx.graphics.getPpiY()) * 5f);

        gameOverStage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera()),batch);



        playerCamera = new OrthographicCamera();


        if (screenRatio.x == 16f && screenRatio.y == 9f){
            playerCamera.setToOrtho(false, screenRatio.x * 1.5f , screenRatio.y * 1.5f );
            createHUD_16_9();
        } else if (screenRatio.x == 16f && screenRatio.y == 10f){

            playerCamera.setToOrtho(false, screenRatio.x * 1.5f , screenRatio.y * 1.5f );
            createHUD_16_9();
        }
        else if (screenRatio.x == 4f && screenRatio.y == 3f){
            playerCamera.setToOrtho(false, screenRatio.x * 5.5f , screenRatio.y * 5.5f );
            createHUD_4_3();
        }else if (screenRatio.x == 5f && screenRatio.y == 4f){
            playerCamera.setToOrtho(false, screenRatio.x * 3.5f , screenRatio.y * 3.5f );
            createHUD_4_3();
        }else if (screenRatio.x == 5f && screenRatio.y == 3f){
            playerCamera.setToOrtho(false, screenRatio.x * 4f , screenRatio.y * 4f );
            createHUD_5_3();
        }else{
            playerCamera.setToOrtho(false, screenRatio.x * 5.5f , screenRatio.y * 5.5f );
            createHUD_16_9();
        }

        createGameOverScreen();



        game.getFont().setColor(Color.WHITE);

        hudStage.addActor(buttonJump);
        hudStage.addActor(buttonShoot);
        hudStage.addActor(buttonMoveRight);
        hudStage.addActor(buttonMoveLeft);
        hudStage.addActor(nothingButton);
        hudStage.addActor(score);


        createInputListener();
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

    public void createGameOverInputListener(){
        buttonRetry.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TouchDown:  " + event);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.getMenuScreen().setGameScreen(new GameScreen(game, game.getMenuScreen().getGameScreen().getLevelName(),
                        game.getMenuScreen().getGameScreen().isActivateLights()));
                game.setScreen(game.getMenuScreen().getGameScreen());
                System.out.println("TouchUp:  " + event);
            }
        });

        buttonBack.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TouchDown:  " + event);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new LevelChooseScreen(game));
                System.out.println("TouchUpTROLOLOL:  " + event);
            }
        });
    }

    public void createHUD_16_9(){
        hudStage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera()),batch);

        posX = Gdx.graphics.getWidth() / 100f * 2f;
        posY = Gdx.graphics.getHeight() / 100f * 90f;
        sizeX = Gdx.graphics.getWidth() / 100f * 5f;
        sizeY = Gdx.graphics.getWidth() / 100f * 5f;

        buttonJump = new TextButton("<>",game.getSkin(),"default");
        buttonJump.getLabel().setFontScale((Gdx.graphics.getWidth() / 100f) * 0.08f,  (Gdx.graphics.getHeight() / 100f) * 0.08f);
        buttonJump.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonJump.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100f * 19f);
        buttonJump.setPosition(Gdx.graphics.getWidth() / 100f * 2f, Gdx.graphics.getHeight() / 100f * 2f);

        buttonShoot = new TextButton("-D",game.getSkin(),"default");
        buttonShoot.getLabel().setFontScale((Gdx.graphics.getWidth() / 100f) * 0.1f,  (Gdx.graphics.getHeight() / 100f) * 0.1f);
        buttonShoot.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonShoot.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100f * 19f);
        buttonShoot.setPosition(Gdx.graphics.getWidth() / 100f * 2f, Gdx.graphics.getHeight() / 100f * 24f);

        buttonMoveLeft = new TextButton("<",game.getSkin(),"default");
        buttonMoveLeft.getLabel().setFontScale((Gdx.graphics.getWidth() / 100) * 0.1f,  (Gdx.graphics.getHeight() / 100) * 0.1f);
        buttonMoveLeft.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonMoveLeft.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100f * 19f);
        buttonMoveLeft.setPosition(Gdx.graphics.getWidth() / 100f * 67f, Gdx.graphics.getHeight() / 100f * 4f);

        buttonMoveRight = new TextButton(">",game.getSkin(),"default");
        buttonMoveRight.getLabel().setFontScale((Gdx.graphics.getWidth() / 100) * 0.1f,  (Gdx.graphics.getHeight() / 100) * 0.1f);
        buttonMoveRight.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonMoveRight.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100f * 19f);
        buttonMoveRight.setPosition(Gdx.graphics.getWidth() / 100f * 84f, Gdx.graphics.getHeight() / 100f * 4f);

        nothingButton = new TextButton("",game.getSkin(),"default");
        nothingButton.setColor(1,1,1,1);
        nothingButton.setSize(0, 0);
        nothingButton.setPosition(-50, -50);

        score = new Label(Integer.toString(this.worldLoader.getPlayer().getScore()), new Label.LabelStyle(game.getFont(), Color.GREEN));
        score.setPosition(Gdx.graphics.getWidth() / 100f * 40f, Gdx.graphics.getHeight() / 100f * 83f);

        lives = new Label(Integer.toString(this.worldLoader.getPlayer().getLives()), new Label.LabelStyle(game.getFont(), Color.RED));
        lives.setPosition(Gdx.graphics.getWidth() / 100f * 2f, Gdx.graphics.getHeight() / 100f * 83f);

    }

    public void createHUD_4_3(){
        posX = Gdx.graphics.getWidth() / 100f * 2f;
        posY = Gdx.graphics.getHeight() / 100f * 90f;
        sizeX = Gdx.graphics.getWidth() / 100f * 5f;
        sizeY = Gdx.graphics.getWidth() / 100f * 5f;

        hudStage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera()),batch);

        buttonJump = new TextButton("<>",game.getSkin(),"default");
        buttonJump.getLabel().setFontScale((Gdx.graphics.getWidth() / 100f) * 0.08f, (Gdx.graphics.getHeight() / 100f) * 0.08f);
        buttonJump.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonJump.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100 * 17);
        buttonJump.setPosition(Gdx.graphics.getWidth() / 100f * 2f, Gdx.graphics.getHeight() / 100 * 4);

        buttonShoot = new TextButton("-D",game.getSkin(),"default");
        buttonShoot.getLabel().setFontScale((Gdx.graphics.getWidth() / 100f) * 0.1f, (Gdx.graphics.getHeight() / 100f) * 0.1f);
        buttonShoot.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonShoot.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100f * 17f);
        buttonShoot.setPosition(Gdx.graphics.getWidth() / 100f * 2f, Gdx.graphics.getHeight() / 100f * 24f);

        buttonMoveLeft = new TextButton("<",game.getSkin(),"default");
        buttonMoveLeft.getLabel().setFontScale((Gdx.graphics.getWidth() / 100f) * 0.1f, (Gdx.graphics.getHeight() / 100f) * 0.1f);
        buttonMoveLeft.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonMoveLeft.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100f * 17f);
        buttonMoveLeft.setPosition(Gdx.graphics.getWidth() / 100f * 67f, Gdx.graphics.getHeight() / 100f * 4f);

        buttonMoveRight = new TextButton(">",game.getSkin(),"default");
        buttonMoveRight.getLabel().setFontScale((Gdx.graphics.getWidth() / 100f) * 0.1f, (Gdx.graphics.getHeight() / 100f) * 0.1f);
        buttonMoveRight.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonMoveRight.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100f * 17f);
        buttonMoveRight.setPosition((Gdx.graphics.getWidth() / 100f) * 84f, Gdx.graphics.getHeight() / 100f * 4f);

        nothingButton = new TextButton("",game.getSkin(),"default");
        nothingButton.setColor(1, 1, 1, 1);
        nothingButton.setSize(0,0);
        nothingButton.setPosition(-50, -50);

        score = new Label(Integer.toString(this.worldLoader.getPlayer().getScore()), new Label.LabelStyle(game.getFont(), Color.GREEN));
        score.setPosition(Gdx.graphics.getWidth() / 100f * 40f, Gdx.graphics.getHeight() / 100f * 86f);

    }

    public void createHUD_5_3(){
        posX = Gdx.graphics.getWidth() / 100f * 2f;
        posY = Gdx.graphics.getHeight() / 100f * 90f;
        sizeX = Gdx.graphics.getWidth() / 100f * 5f;
        sizeY = Gdx.graphics.getWidth() / 100f * 5f;

        hudStage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera()),batch);

        buttonJump = new TextButton("<>",game.getSkin(),"default");
        buttonJump.getLabel().setFontScale((Gdx.graphics.getWidth() / 100f) * 0.08f, (Gdx.graphics.getHeight() / 100f) * 0.08f);
        buttonJump.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonJump.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100 * 20);
        buttonJump.setPosition(Gdx.graphics.getWidth() / 100f * 2f, Gdx.graphics.getHeight() / 100 * 4);

        buttonShoot = new TextButton("-D",game.getSkin(),"default");
        buttonShoot.getLabel().setFontScale((Gdx.graphics.getWidth() / 100f) * 0.1f, (Gdx.graphics.getHeight() / 100f) * 0.1f);
        buttonShoot.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonShoot.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100f * 17f);
        buttonShoot.setPosition(Gdx.graphics.getWidth() / 100f * 2f, Gdx.graphics.getHeight() / 100f * 24f);

        buttonMoveLeft = new TextButton("<",game.getSkin(),"default");
        buttonMoveLeft.getLabel().setFontScale((Gdx.graphics.getWidth() / 100f) * 0.1f, (Gdx.graphics.getHeight() / 100f) * 0.1f);
        buttonMoveLeft.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonMoveLeft.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100f * 17f);
        buttonMoveLeft.setPosition(Gdx.graphics.getWidth() / 100f * 67f, Gdx.graphics.getHeight() / 100f * 4f);

        buttonMoveRight = new TextButton(">",game.getSkin(),"default");
        buttonMoveRight.getLabel().setFontScale((Gdx.graphics.getWidth() / 100f) * 0.1f, (Gdx.graphics.getHeight() / 100f) * 0.1f);
        buttonMoveRight.setColor(0.7f, 1, 0.4f, 0.3f);
        buttonMoveRight.setSize(Gdx.graphics.getWidth() / 100f * 15f, Gdx.graphics.getHeight() / 100f * 17f);
        buttonMoveRight.setPosition((Gdx.graphics.getWidth() / 100f) * 84f, Gdx.graphics.getHeight() / 100f * 4f);

        nothingButton = new TextButton("",game.getSkin(),"default");
        nothingButton.setColor(1, 1, 1, 1);
        nothingButton.setSize(0,0);
        nothingButton.setPosition(-50, -50);

        score = new Label(Integer.toString(this.worldLoader.getPlayer().getScore()), new Label.LabelStyle(game.getFont(), Color.GREEN));
        score.setPosition(Gdx.graphics.getWidth() / 100f * 40f, Gdx.graphics.getHeight() / 100f * 84f);
    }

    public void createGameOverScreen(){
        buttonRetry = new TextButton("Retry",game.getSkin(),"default");
        buttonRetry.setColor(0.7f, 1, 0.4f, 0.5f);
        buttonRetry.setSize(Gdx.graphics.getWidth() / 100 * 40, Gdx.graphics.getHeight() / 100 * 30);
        buttonRetry.setPosition(Gdx.graphics.getWidth() / 100 * 30, Gdx.graphics.getHeight() / 100 * 50);

        buttonBack = new TextButton("Back",game.getSkin(),"default");
        buttonBack.setColor(0.7f, 1, 0.4f, 0.5f);
        buttonBack.setSize(Gdx.graphics.getWidth() / 100 * 40, Gdx.graphics.getHeight() / 100 * 30);
        buttonBack.setPosition(Gdx.graphics.getWidth() / 100 * 30, Gdx.graphics.getHeight() / 100 * 10);

        gameOverOverlay = new Image();
        gameOverOverlay.setScale(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameOverOverlay.setColor(0.2f, 0.9f, 0.2f, 0.5f);

        gameOverStage.addActor(gameOverOverlay);
        gameOverStage.addActor(buttonBack);
        gameOverStage.addActor(buttonRetry);


        createGameOverInputListener();
    }

    public void update(float deltaTime){
        score.setText(Integer.toString(worldLoader.getPlayer().getScore()));

        //Setzt Kamerapositionen des Spielers
        playerCamera.position.x = worldLoader.getPlayer().getBody().getPosition().x;
        playerCamera.position.y = worldLoader.getPlayer().getBody().getPosition().y + 2;

        //Setzt position des Hintergrundes
        backgroundCamera.position.x = worldLoader.getPlayer().getBody().getPosition().x / 10;
        backgroundCamera.position.y = worldLoader.getPlayer().getBody().getPosition().y / 10;

        //Aktualisiert Kameras
        playerCamera.update();
        backgroundCamera.update();

        gameOverStage.act(deltaTime);
    }

    public void drawHealthIcons(){
        batch.begin();

        if (game.getMenuScreen().getGameScreen().getWorldLoader().getPlayer().getLives() > 0){
            batch.setColor(Color.WHITE);
        }else{
            batch.setColor(0.8f,0.8f,0.8f,0.4f);
        }
        batch.draw(game.getAssets().getIcon_Health(),posX,posY,sizeX,sizeY);
        if (game.getMenuScreen().getGameScreen().getWorldLoader().getPlayer().getLives() > 1){
            batch.setColor(Color.WHITE);
        }else{
            batch.setColor(0.8f,0.8f,0.8f,0.4f);
        }
        batch.draw(game.getAssets().getIcon_Health(),posX*4,posY,sizeX,sizeY);
        if (game.getMenuScreen().getGameScreen().getWorldLoader().getPlayer().getLives() > 2){
            batch.setColor(Color.WHITE);
        }else{
            batch.setColor(0.8f,0.8f,0.8f,0.4f);
        }
        batch.draw(game.getAssets().getIcon_Health(), posX * 7, posY, sizeX, sizeY);
        if (game.getMenuScreen().getGameScreen().getWorldLoader().getPlayer().getLives() > 3){
            batch.setColor(Color.WHITE);
        }else{
            batch.setColor(0.8f,0.8f,0.8f,0.4f);
        }
        batch.draw(game.getAssets().getIcon_Health(),posX*10,posY,sizeX,sizeY);
        if (game.getMenuScreen().getGameScreen().getWorldLoader().getPlayer().getLives() > 4){
            batch.setColor(Color.WHITE);
        }else{
            batch.setColor(0.8f,0.8f,0.8f,0.4f);
        }
        batch.draw(game.getAssets().getIcon_Health(), posX * 13, posY, sizeX, sizeY);

        batch.setColor(Color.WHITE);

        batch.end();
    }

    public void render(SpriteBatch batch){
        hudStage.draw();
        drawHealthIcons();
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

    public Stage getGameOverStage() {
        return gameOverStage;
    }

    public void setGameOverStage(Stage gameOverStage) {
        this.gameOverStage = gameOverStage;
    }

    public MainWindow getGame() {
        return game;
    }

    public void setGame(MainWindow game) {
        this.game = game;
    }

    public WorldLoader getWorldLoader() {
        return worldLoader;
    }

    public void setWorldLoader(WorldLoader worldLoader) {
        this.worldLoader = worldLoader;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Label getScore() {
        return score;
    }

    public void setScore(Label score) {
        this.score = score;
    }

    public Label getLives() {
        return lives;
    }

    public void setLives(Label lives) {
        this.lives = lives;
    }

    public Label getGameOverText() {
        return gameOverText;
    }

    public void setGameOverText(Label gameOverText) {
        this.gameOverText = gameOverText;
    }

    public TextButton getButtonRetry() {
        return buttonRetry;
    }

    public void setButtonRetry(TextButton buttonRetry) {
        this.buttonRetry = buttonRetry;
    }

    public TextButton getButtonBack() {
        return buttonBack;
    }

    public void setButtonBack(TextButton buttonBack) {
        this.buttonBack = buttonBack;
    }

    public TextButton getButtonMoveRight() {
        return buttonMoveRight;
    }

    public void setButtonMoveRight(TextButton buttonMoveRight) {
        this.buttonMoveRight = buttonMoveRight;
    }

    public TextButton getButtonMoveLeft() {
        return buttonMoveLeft;
    }

    public void setButtonMoveLeft(TextButton buttonMoveLeft) {
        this.buttonMoveLeft = buttonMoveLeft;
    }

    public TextButton getNothingButton() {
        return nothingButton;
    }

    public void setNothingButton(TextButton nothingButton) {
        this.nothingButton = nothingButton;
    }

    public TextButton getButtonShoot() {
        return buttonShoot;
    }

    public void setButtonShoot(TextButton buttonShoot) {
        this.buttonShoot = buttonShoot;
    }

    public Image getGameOverOverlay() {
        return gameOverOverlay;
    }

    public void setGameOverOverlay(Image gameOverOverlay) {
        this.gameOverOverlay = gameOverOverlay;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getSizeX() {
        return sizeX;
    }

    public void setSizeX(float sizeX) {
        this.sizeX = sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public void setSizeY(float sizeY) {
        this.sizeY = sizeY;
    }
}
