package com.adventure.fun.objects;

import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Dennis on 30.10.2015.
 */
public class Bullet extends Object {

    public Bullet(float x,float y,float sizeX,float sizeY,World world){
        init(x,y,sizeX,sizeY,world);

    }

    public void init(float x,float y,float sizeX,float sizeY,World world){
        sprite = new Sprite();
        sprite.setPosition(x, y);
        sprite.setSize(sizeX, sizeY);

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(sprite.getX(), sprite.getY());

        shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;


        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        region = Textures.bullet;
        shape.dispose();
    }
}
