package com.adventure.fun.objects;

import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Dennis on 28.10.2015.
 */
public class Ground extends Object {

    public Ground(float x,float y,float sizeX,float sizeY,World world){
        init(x,y,sizeX,sizeY,world);

    }

    public void init(float x,float y,float sizeX,float sizeY,World world){
        sprite = new Sprite(Textures.block_blue);
        sprite.setPosition(x, y);
        sprite.setSize(sizeX, sizeY);

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(sprite.getX(), sprite.getY());

        body = world.createBody(bodyDef);

        shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);


        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void render(SpriteBatch batch){
        sprite.setPosition(body.getPosition().x, body.getPosition().y);

        batch.draw(sprite, sprite.getX() - sprite.getWidth()/2 , sprite.getY() - sprite.getHeight()/2,sprite.getWidth(),sprite.getHeight());
    }
}
