package com.adventure.fun;

import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class AdventureFun extends ApplicationAdapter {
	private SpriteBatch batch;

	private WorldRender worldRender;

	private OrthographicCamera camera;
	private OrthographicCamera backgroundCamera;
	private Box2DDebugRenderer debugRenderer;
	float x;



	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);


		worldRender = new WorldRender();

		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/20 );
		backgroundCamera = new OrthographicCamera();
		backgroundCamera.setToOrtho(false, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 20);

		batch = new SpriteBatch();


	}

	@Override
	public void render() {
		worldRender.getRenderer().setView(camera);


		//Reinigt Bildschirm
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		//Setzt Kamerapositionen
		camera.position.x = worldRender.getPlayer().getSprite().getX();
		camera.position.y = worldRender.getPlayer().getSprite().getY();

		backgroundCamera.position.x = worldRender.getPlayer().getSprite().getX() / 5;
		backgroundCamera.position.y = worldRender.getPlayer().getSprite().getY() / 5;

		//Aktualisiert Kameras
		camera.update();
		backgroundCamera.update();


		//Aktualisiert Logik
		worldRender.updateWorld(Gdx.graphics.getDeltaTime());


		//Rendert alle Objeckte innerhalb des batchs
		batch.begin();
		batch.setProjectionMatrix(backgroundCamera.combined);
		batch.draw(Textures.background, -20, -20, 50, 50);
		batch.setProjectionMatrix(camera.combined);
		worldRender.renderObjects(batch);
		batch.end();

		//Rendert tmx map
		worldRender.renderMap();

		debugRenderer.render(worldRender.getWorld(),camera.combined);





	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public WorldRender getWorldRender() {
		return worldRender;
	}

	public void setWorldRender(WorldRender worldRender) {
		this.worldRender = worldRender;
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
