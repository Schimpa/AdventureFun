package com.adventure.fun;

import com.adventure.fun.audio.AudioController;
import com.adventure.fun.objects.Ground;
import com.adventure.fun.objects.Player;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class AdventureFun extends ApplicationAdapter {
	private SpriteBatch batch;

	private WorldRender worldRender;
	private AudioController audioController = new AudioController();

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
		backgroundCamera.setToOrtho(false, Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/20 );

		batch = new SpriteBatch();


	}

	@Override
	public void render() {
		worldRender.getRenderer().setView(camera);
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.x = worldRender.getPlayer().getSprite().getX();
		camera.position.y = worldRender.getPlayer().getSprite().getY();
		backgroundCamera.position.x = worldRender.getPlayer().getSprite().getX() / 5;
		backgroundCamera.position.y = worldRender.getPlayer().getSprite().getY() / 5;
		camera.update();
		backgroundCamera.update();



		worldRender.updateWorld(Gdx.graphics.getDeltaTime());


		batch.begin();
		batch.setProjectionMatrix(backgroundCamera.combined);
		batch.draw(Textures.background, -20, -20, 50, 200);
		batch.setProjectionMatrix(camera.combined);
		worldRender.renderWorld(batch);
		batch.end();
		worldRender.getRenderer().render();

		//debugRenderer.render(worldRender.getWorld(),camera.combined);





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

	public AudioController getAudioController() {
		return audioController;
	}

	public void setAudioController(AudioController audioController) {
		this.audioController = audioController;
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
