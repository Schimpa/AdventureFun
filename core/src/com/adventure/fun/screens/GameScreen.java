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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class GameScreen implements Screen {
	private MainWindow game;

	private WorldLoader worldLoader;

	private Box2DDebugRenderer debugRenderer;

	private boolean showGameOverScreen;


	private Cameras camera;

	private String levelName;

	private boolean activateLights;

	public GameScreen(MainWindow game,String levelName,boolean activateLights){
		this.game = game;

		this.levelName = levelName;

		showGameOverScreen = false;

		this.activateLights = activateLights;

		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		debugRenderer = new Box2DDebugRenderer();

		worldLoader = new WorldLoader(game,this);

		camera = new Cameras(game,worldLoader,game.getBatch());

		InputMultiplexer multiplexer = new InputMultiplexer();

		multiplexer.addProcessor(camera.getHudStage());
		multiplexer.addProcessor(worldLoader.getControls());

		Gdx.input.setInputProcessor(multiplexer);
	}


	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		//Reinigt Bildschirm
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update(delta);

		//Aktualisiert Logik
		worldLoader.getRenderer().setView(camera.getPlayerCamera());
		worldLoader.updateWorld(delta);

		if (showGameOverScreen == true){
			camera.createGameOverScreen();
			camera.getHudStage().dispose();
			Gdx.input.setInputProcessor(camera.getGameOverStage());
			showGameOverScreen = false;
		}


		//Rendert alle Objeckte innerhalb des batchs
		game.getBatch().begin();
		game.getBatch().setProjectionMatrix(camera.getBackgroundCamera().combined);
		game.getBatch().draw(game.getAssets().getBackgroundMars(), -20, -20, 50, 50);
		game.getBatch().end();

		//Rendert tmx map
		game.getBatch().setProjectionMatrix(camera.getPlayerCamera().combined);
		worldLoader.renderMap(game.getBatch());

		//Rendert Physik-Debug Texturen
		//debugRenderer.render(worldLoader.getWorld(), camera.getPlayerCamera().combined);

		//Renderd HUD
		camera.getHudStage().draw();
		camera.getGameOverStage().draw();
	}

	@Override
	public void resize(int width,int height){
		//camera.getPlayerCamera().setToOrtho(false, (Gdx.graphics.getWidth() / Gdx.graphics.getPpiX()) * 2, (Gdx.graphics.getHeight() / Gdx.graphics.getPpiY() )* 2 );
		camera.getBackgroundCamera().setToOrtho(false, (Gdx.graphics.getWidth() / Gdx.graphics.getPpiX()) * 2, (Gdx.graphics.getHeight() / Gdx.graphics.getPpiY())* 2 );
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
}
