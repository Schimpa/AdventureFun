package com.adventure.fun.objects;

import com.adventure.fun.audio.AudioController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Dennis on 30.10.2015.
 */
public class Bullet extends LivingObject {

    private int speedX;
    private float reload;

    public Bullet(float x,float y,float sizeX,float sizeY,World world,TextureRegion region,String name){
        init(x,y,sizeX,sizeY,world,region,name);

    }

    public void init(float x,float y,float sizeX,float sizeY,World world,TextureRegion region,String name){

        speedX = 30;
        reload = 5.0f;

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
        fixtureDef.isSensor = true;


        body = world.createBody(bodyDef);
        body.setUserData(name);
        body.createFixture(fixtureDef);
        MassData massData = new MassData();
        massData.mass = 0;
        body.setMassData(massData);

        this.region = region;

        shape.dispose();
    }

    public void shootBullet(LivingObject object){
        if (reload >= 1){
            if ((this.getBody().getPosition().x - object.getBody().getPosition().x) >= 50
                    || (this.getBody().getPosition().x - object.getBody().getPosition().x) <= -50 ){
                this.getBody().setTransform(-1000, -1000, 0);
                this.getBody().setLinearVelocity(0, 0);
                reload = 0;
            }
            if (this.getBody().getLinearVelocity().x == 0){
                AudioController.sound_shoot_02.play(0.5f);
                if (object.getCurrentFrame().isFlipX() == false){
                    this.getBody().setTransform(object.getBody().getPosition().x + this.getSprite().getWidth() / 1.5f, object.getBody().getPosition().y, 0);
                    this.getBody().setLinearVelocity(this.getSpeedX(), 0);
                } else if (object.getCurrentFrame().isFlipX() == true) {
                    this.getBody().setTransform(object.getBody().getPosition().x - this.getSprite().getWidth() / 1.5f, object.getBody().getPosition().y, 0);
                    this.getBody().setLinearVelocity(-this.getSpeedX(), 0);
                }
            }
        }
    }

    public void update() {
        checkBulletCollision();
        reload += Gdx.graphics.getDeltaTime();
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

    public void checkBulletCollision(){
        if (this.removeFlag == true){
            this.body.setTransform(-1000,-1000,0);
            this.removeFlag = false;
        }
    }
}
