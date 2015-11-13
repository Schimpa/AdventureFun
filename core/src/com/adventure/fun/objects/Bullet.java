package com.adventure.fun.objects;

import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Dennis on 30.10.2015.
 */
public class Bullet extends Object {

    private int speedX;
    private boolean removeFlag = false;

    public Bullet(float x,float y,float sizeX,float sizeY,World world){
        init(x,y,sizeX,sizeY,world);

    }

    public void init(float x,float y,float sizeX,float sizeY,World world){

        speedX = 30;

        sprite = new Sprite();
        sprite.setPosition(x, y);
        sprite.setSize(sizeX, sizeY);

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX(), sprite.getY());

        shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        fixtureDef.restitution = 0;
        fixtureDef.density = 0;


        body = world.createBody(bodyDef);
        body.setUserData("Bullet");
        body.createFixture(fixtureDef);
        MassData massData = new MassData();
        massData.mass = 0;
        body.setMassData(massData);

        region = Textures.bullet_02;
        shape.dispose();

    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public boolean getRemoveFlag() {
        return removeFlag;
    }

    public void setRemoveFlag(boolean removeFlag) {
        this.removeFlag = removeFlag;
    }

    public void checkBulletCollision(Player player){
        if (player.getBullet().getRemoveFlag() == true){
            player.getBullet().getBody().setTransform(-1000,-1000,0);
            player.getBullet().setRemoveFlag(false);
        }
    }
}
