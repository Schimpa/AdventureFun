package com.adventure.fun._main;

import com.adventure.fun.screens.GameScreen;
import com.adventure.fun.screens.LevelChooseScreen;
import com.adventure.fun.screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Sweta on 30.11.2015.
 */
public class MainWindow extends Game{

    private boolean isLoaded;
    private Assets assets;
    private SpriteBatch batch;

    private Skin skin;
    private BitmapFont font;

    private MenuScreen menuScreen;



    @Override
    public void create() {
        Gdx.graphics.setTitle("Planet Escape");
        initFont();
        initSkin();
        batch = new SpriteBatch();
        assets = new Assets(true);
        isLoaded = false;
    }


    @Override
    public void dispose() {
        super.dispose();

    }

    public void initSkin(){
        this.skin = new Skin();
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal("ui/uiskin/uiskin.atlas")));
        this.skin.add("default-font", font);
        this.skin.load(Gdx.files.internal("ui/uiskin/uiskin.json"));
    }

    public void initFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/fonts/pricedown.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.size = Gdx.graphics.getWidth() * 128 / 1280;
        param.color = Color.WHITE;

        font = generator.generateFont(param);
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
        if (assets.getAssetManager().update() && isLoaded == false) {
            this.assets.done();
            isLoaded = true;
            this.setScreen(menuScreen = new MenuScreen(this));
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }



































    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public MenuScreen getMenuScreen() {
        return menuScreen;
    }

    public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setIsLoaded(boolean isLoaded) {
        this.isLoaded = isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }
}
