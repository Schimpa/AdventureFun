package com.adventure.fun.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by Dennis on 27.10.2015.
 */
public abstract class Object {


    public Vector2 speedMax;              // MAX SPEED (X,Y)
    public Sprite sprite;
    public TextureRegion region;

    public Body body;
    public BodyDef bodyDef;
    public PolygonShape shape;
    public FixtureDef fixtureDef;


    public void move(float x, float y, float deltaTime){
        sprite.setPosition(body.getPosition().x + x * deltaTime,body.getPosition().y+y * deltaTime);
    }


    public void update(float deltaTime){};


}
