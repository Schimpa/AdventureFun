package com.adventure.fun.screens;

import com.adventure.fun._main.MainWindow;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Dennis on 04.12.2015.
 */
public class MenuScreen implements Screen {

    private MainWindow game;
    private boolean isActivated;
    private BitmapFont font;
    private OrthographicCamera camera;

    private Viewport viewport;
    private Stage stage;
    private Skin skin;
    private TextButton buttonPlay;

    TextureAtlas uiSkin;

    public MenuScreen(MainWindow game){
        this.game = game;
    }



    @Override
    public void show() {
        isActivated = false;
        camera = new OrthographicCamera(500,500);
        initFont();


        viewport = new FitViewport(500,500,camera);
        viewport.apply();

        this.stage = new Stage(viewport,game.getBatch());

        this.skin = new Skin();
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal("ui/uiskin/uiskin.atlas")));
        this.skin.add("default-font", font);
        this.skin.load(Gdx.files.internal("ui/uiskin/uiskin.json"));

        buttonPlay = new TextButton("Play",skin,"default");
        buttonPlay.setPosition(100, 100);
        buttonPlay.setSize(200, 200);


        buttonPlay.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                isActivated = true;
            }
        });



        stage.addActor(buttonPlay);
        Gdx.input.setInputProcessor(stage);

    }

    public void initFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/fonts/Arcon.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.size = 24;
        param.color = Color.BLACK;

        font = generator.generateFont(param);
    }

    @Override
    public void render(float delta) {
        if (isActivated != true){
            Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.getBatch().begin();
            game.getBatch().setProjectionMatrix(camera.combined);
            font.draw(game.getBatch(), "WAS GEHT AB", 50, 50);
            game.getBatch().end();
            stage.draw();
        }
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
