package com.adventure.fun;

import com.adventure.fun.audio.AudioController;
import com.adventure.fun.objects.Ground;
import com.adventure.fun.objects.Player;
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
	SpriteBatch batch;

	WorldRender worldRender;
	AudioController audioController = new AudioController();

	public OrthographicCamera camera;
	public Box2DDebugRenderer debugRenderer;

	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		worldRender = new WorldRender();

		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/20 );

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);


	}

	@Override
	public void render() {


		camera.position.x = worldRender.player.sprite.getX();
		camera.position.y = worldRender.player.sprite.getY();
		camera.update();


		worldRender.updateWorld(Gdx.graphics.getDeltaTime());

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		worldRender.renderWorld(batch);
		batch.end();

		debugRenderer.render(worldRender.world, camera.combined);




	}
}
