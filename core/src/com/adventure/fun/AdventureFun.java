package com.adventure.fun;

import com.adventure.fun.controls.Controls;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class AdventureFun extends Game {
	private SpriteBatch batch;

	private WorldLoader worldLoader;

	private OrthographicCamera camera;
	private OrthographicCamera backgroundCamera;
	private Box2DDebugRenderer debugRenderer;

	Controls controls;
	float x;



	
	@Override
	public void create () {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		worldLoader = new WorldLoader();
		debugRenderer = new Box2DDebugRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth()/40,Gdx.graphics.getHeight()/40 );


		backgroundCamera = new OrthographicCamera();
		backgroundCamera.setToOrtho(false, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 20);

		batch = new SpriteBatch();

		controls = new Controls(worldLoader);

		Gdx.input.setInputProcessor(controls);


	}

	@Override
	public void render() {

		controls.movementControls();

		worldLoader.getRenderer().setView(camera);

		//Reinigt Bildschirm
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		//Setzt Kamerapositionen
		camera.position.x = worldLoader.getPlayer().getSprite().getX();
		camera.position.y = worldLoader.getPlayer().getSprite().getY();

		backgroundCamera.position.x = worldLoader.getPlayer().getSprite().getX() / 10;
		backgroundCamera.position.y = worldLoader.getPlayer().getSprite().getY() / 10;

		//Aktualisiert Kameras
		camera.update();
		backgroundCamera.update();


		//Aktualisiert Logik
		worldLoader.updateWorld(Gdx.graphics.getDeltaTime());


		//Rendert alle Objeckte innerhalb des batchs

		batch.begin();
		batch.setProjectionMatrix(backgroundCamera.combined);
		batch.draw(Textures.background, -20, -20, 50, 50);

		batch.end();
		batch.setProjectionMatrix(camera.combined);
		//Rendert tmx map
		worldLoader.renderMap(batch);




		debugRenderer.render(worldLoader.getWorld(),camera.combined);





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


	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public Box2DDebugRenderer getDebugRenderer() {
		return debugRenderer;
	}

	public void setDebugRenderer(Box2DDebugRenderer debugRenderer) {
		this.debugRenderer = debugRenderer;
	}
}
