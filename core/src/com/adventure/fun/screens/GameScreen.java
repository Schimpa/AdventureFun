package com.adventure.fun.screens;

import com.adventure.fun._main.Cameras;
import com.adventure.fun._main.MainWindow;
import com.adventure.fun._main.WorldLoader;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class GameScreen implements Screen {
	private MainWindow game;

	private WorldLoader worldLoader;

	private Box2DDebugRenderer debugRenderer;

	private boolean showGameOverScreen;
	private boolean showMenuScreen;

	private Cameras camera;

	private String levelName;

	private boolean activateLights;

	private Texture gameBackground;
	private Texture gameMiddleground;

	InputMultiplexer multiplexer;

	public GameScreen(MainWindow game,String levelName,boolean activateLights){
		this.game = game;

		this.levelName = levelName;

		showGameOverScreen = false;

		this.activateLights = activateLights;

		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		debugRenderer = new Box2DDebugRenderer();

		worldLoader = new WorldLoader(game,this);

		camera = new Cameras(game,worldLoader,game.getBatch());

		multiplexer = new InputMultiplexer();

		multiplexer.addProcessor(camera.getHudStage());
		multiplexer.addProcessor(worldLoader.getControls());

		Gdx.input.setInputProcessor(multiplexer);

		chooseBackground();
	}

	public void chooseBackground(){
		if (levelName.equals("maps/level_one.tmx")){
			gameBackground = game.getAssets().getBackground();
			gameMiddleground = game.getAssets().getMiddleground_01();
		}else{
			gameBackground = game.getAssets().getBackground();
			gameMiddleground = game.getAssets().getMiddleground_01();
		}
	}


	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		//Reinigt Bildschirm
		Gdx.gl.glClearColor(0.9f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Aktualisiert Logik
		worldLoader.getRenderer().setView(camera.getPlayerCamera());
		worldLoader.updateWorld(delta);

		//Rendert alle Objeckte innerhalb des batchs
		game.getBatch().begin();
		game.getBatch().setProjectionMatrix(camera.getBackgroundCamera().combined);
		game.getBatch().draw(gameBackground, -15, -10, gameBackground.getWidth() / 40, gameBackground.getHeight() / 30);
		game.getBatch().draw(gameBackground, -15 + gameBackground.getWidth() / 40, -10, gameBackground.getWidth() / 40, gameBackground.getHeight() / 30,0,0,gameBackground.getWidth(),gameBackground.getHeight(),true,false);
		game.getBatch().setProjectionMatrix(camera.getMiddlegroundCamera().combined);
		game.getBatch().draw(gameMiddleground, -15, -10, gameBackground.getWidth() / 40, gameBackground.getHeight() / 30);
		game.getBatch().draw(gameMiddleground, -15 + gameBackground.getWidth() / 40, -10f, gameBackground.getWidth() / 40, gameBackground.getHeight() / 30,0,0,gameBackground.getWidth(),gameBackground.getHeight(),true,false);

		game.getBatch().end();

		camera.update(delta);
		//Rendert tmx map
		game.getBatch().setProjectionMatrix(camera.getPlayerCamera().combined);
		worldLoader.renderMap(game.getBatch());

		//Rendert Physik-Debug Texturen
		//debugRenderer.render(worldLoader.getWorld(), camera.getPlayerCamera().combined);

		if (showGameOverScreen == true){
			//camera.getGameOverStage().getBatch().setColor(Color.RED);
			game.getBatch().begin();
			game.getBatch().setColor(0.9f,0.1f,0.1f,0.6f);
			/*
			game.getBatch().draw(game.getAssets().getWhiteColor(),0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			*/
			game.getBatch().end();
			camera.getGameOverStage().draw();

		}else if (showMenuScreen == true){
			camera.getMenuStage().draw();
		}else{
			camera.render(game.getBatch());
		}


	}

	@Override
	public void resize(int width,int height){
		//camera.getPlayerCamera().setToOrtho(false, (Gdx.graphics.getWidth() / Gdx.graphics.getPpiX()) * 2, (Gdx.graphics.getHeight() / Gdx.graphics.getPpiY() )* 2 );
		camera.getBackgroundCamera().setToOrtho(false, (Gdx.graphics.getWidth() / Gdx.graphics.getPpiX()) * 2, (Gdx.graphics.getHeight() / Gdx.graphics.getPpiY())* 2 );
		camera.getMiddlegroundCamera().setToOrtho(false, (Gdx.graphics.getWidth() / Gdx.graphics.getPpiX()) , (Gdx.graphics.getHeight() / Gdx.graphics.getPpiY()) );
		camera.getHudStage().getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.getGameOverStage().getViewport().update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

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
		worldLoader.dispose();
		debugRenderer.dispose();
		camera.dispose();
		game.dispose();
	}


	public Texture getGameBackground() {
		return gameBackground;
	}

	public void setGameBackground(Texture gameBackground) {
		this.gameBackground = gameBackground;
	}

	public Texture getGameMiddleground() {
		return gameMiddleground;
	}

	public void setGameMiddleground(Texture gameMiddleground) {
		this.gameMiddleground = gameMiddleground;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public WorldLoader getWorldLoader() {
		return worldLoader;
	}

	public void setWorldLoader(WorldLoader worldLoader) {
		this.worldLoader = worldLoader;
	}

	public Box2DDebugRenderer getDebugRenderer() {
		return debugRenderer;
	}

	public void setDebugRenderer(Box2DDebugRenderer debugRenderer) {
		this.debugRenderer = debugRenderer;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(MainWindow game) {
		this.game = game;
	}

	public Cameras getCamera() {
		return camera;
	}

	public void setCamera(Cameras camera) {
		this.camera = camera;
	}

	public boolean isActivateLights() {
		return activateLights;
	}

	public void setActivateLights(boolean activateLights) {
		this.activateLights = activateLights;
	}

	public boolean isShowGameOverScreen() {
		return showGameOverScreen;
	}

	public void setShowGameOverScreen(boolean showGameOverScreen) {
		this.showGameOverScreen = showGameOverScreen;
	}

	public InputMultiplexer getMultiplexer() {
		return multiplexer;
	}

	public void setMultiplexer(InputMultiplexer multiplexer) {
		this.multiplexer = multiplexer;
	}

	public boolean isShowMenuScreen() {
		return showMenuScreen;
	}

	public void setShowMenuScreen(boolean showMenuScreen) {
		this.showMenuScreen = showMenuScreen;
	}
}
