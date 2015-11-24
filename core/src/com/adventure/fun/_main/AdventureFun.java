package com.adventure.fun._main;

import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class AdventureFun extends Game {
	private SpriteBatch batch;

	private WorldLoader worldLoader;

	private Box2DDebugRenderer debugRenderer;

	private Cameras camera;

	float x;



	
	@Override
	public void create () {


		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		debugRenderer = new Box2DDebugRenderer();

		batch = new SpriteBatch();

		worldLoader = new WorldLoader();

		camera = new Cameras(worldLoader,batch);

		Gdx.input.setInputProcessor(worldLoader.getControls());


	}

	@Override
	public void render() {
		//Reinigt Bildschirm
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		camera.update(Gdx.graphics.getDeltaTime());

		//Aktualisiert Logik
		worldLoader.getRenderer().setView(camera.getPlayerCamera());
		worldLoader.updateWorld(Gdx.graphics.getDeltaTime());


		//Rendert alle Objeckte innerhalb des batchs
		batch.begin();
		batch.setProjectionMatrix(camera.getBackgroundCamera().combined);
		batch.draw(Textures.background, -20, -20, 50, 50);
		batch.end();

		//Rendert tmx map
		batch.setProjectionMatrix(camera.getPlayerCamera().combined);
		worldLoader.renderMap(batch);

		//Rendert Physik-Debug Texturen
		debugRenderer.render(worldLoader.getWorld(),camera.getPlayerCamera().combined);

		//Renderd HUD
		camera.getHudStage().draw();
	}

	@Override
	public void resize(int width,int height){

	}















































	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
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
}
