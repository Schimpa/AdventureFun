package com.adventure.fun.objects;

import com.adventure.fun.audio.AudioController;
import com.adventure.fun.texture.Textures;
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
public class Bullet extends Object {

    private int speedX;
    private boolean removeFlag = false;

    public Bullet(float x,float y,float sizeX,float sizeY,World world,TextureRegion region){
        init(x,y,sizeX,sizeY,world,region);

    }

    public void init(float x,float y,float sizeX,float sizeY,World world,TextureRegion region){

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

        this.region = region;

        shape.dispose();

    }

    public void shootBullet(Object object){
        if ((this.getBody().getPosition().x - object.getBody().getPosition().x) >= 50
                || (this.getBody().getPosition().x - object.getBody().getPosition().x) <= -50 ){
            this.getBody().setTransform(-1000, -1000, 0);
            this.getBody().setLinearVelocity(0, 0);
        }
        if (this.getBody().getLinearVelocity().x == 0){
            AudioController.sound_shoot.play(0.1f);
            if (object.getCurrentFrame().isFlipX() == false){
                this.getBody().setTransform(object.getBody().getPosition().x + this.getSprite().getWidth() / 2, object.getBody().getPosition().y, 0);
                this.getBody().setLinearVelocity(this.getSpeedX(), 0);
            } else if (object.getCurrentFrame().isFlipX() == true) {
                this.getBody().setTransform(object.getBody().getPosition().x - this.getSprite().getWidth() / 2, object.getBody().getPosition().y, 0);
                this.getBody().setLinearVelocity(-this.getSpeedX(), 0);
            }
        }
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
