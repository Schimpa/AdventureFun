package com.adventure.fun;

import com.adventure.fun.objects.Bullet;
import com.adventure.fun.objects.Ground;
import com.adventure.fun.objects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Dennis on 29.10.2015.
 */
public class WorldRender {

    public Player player;
    public World world;
    public Ground ground;


    public WorldRender(){
        world = new World(new Vector2(0,-100), true);
        player = new Player(1,1,1,1,world);
        ground = new Ground(0,0,100,1,world);
        world.setContactListener(new MyContactListener());
    }


    public void renderWorld(SpriteBatch batch){
        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.render(batch);
        ground.render(batch);

    }

    public void updateWorld(float deltaTime){
        world.step(Gdx.graphics.getDeltaTime(), 60, 20);
        player.update(deltaTime);

    }
}
