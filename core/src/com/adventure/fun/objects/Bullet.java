package com.adventure.fun.objects;

import com.adventure.fun._main.MainWindow;
import com.adventure.fun.effects.ObjectAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import box2dLight.PointLight;

/**
 * Created by Dennis on 30.10.2015.
 */
public class Bullet extends LivingObject {

    private int speedX;
    private float timeFromShoot;
    private boolean bulletShoot;

    private boolean isAnimation;

    private Array<Bullet> shootBullets;

    private ObjectAnimation shootAnimation;

    private float reloadTime;


    public Bullet(MainWindow game,float x,float y,float sizeX,float sizeY,World world,TextureRegion region,boolean isAnimation){
        super(game);
        init(x, y, sizeX, sizeY, world, region,isAnimation);
    }

    public void init(float x,float y,float sizeX,float sizeY,World world,TextureRegion region,boolean isAnimation) {
        super.init(x, y, sizeX, sizeY, world);
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef);
        speedX = 100;
        reloadTime = 0.6f;

        this.region = region;
        this.isAnimation = isAnimation;

        timeFromShoot = 0;

        shootBullets = new Array<Bullet>();

        bulletShoot = false;

        MassData massData = new MassData();
        massData.mass = 0;
        body.setMassData(massData);

        currentFrame = new TextureRegion();

        if (isAnimation == true){
            shootAnimation = new ObjectAnimation(region, 4, 1, 0, 3, 0.05f);
            shootAnimation.setIsActive(true);
        }


        shape.dispose();
    }

    public boolean shootBullet(LivingObject object){
        if (timeFromShoot >= reloadTime && bulletShoot == true){
            bulletShoot = false;
            PointLight light = new PointLight(
                    game.getMenuScreen().getGameScreen().getWorldLoader().getRayHandler(), 5, null, 2, 0f, 0f);

            light.attachToBody(this.getBody());
            light.setColor(1f, 0, 0, 0.5f);

            if ((this.getBody().getPosition().x - object.getBody().getPosition().x) >= 10
                    || (this.getBody().getPosition().x - object.getBody().getPosition().x) <= -10 ){
                this.getBody().setTransform(-1000, -1000, 0);
                this.getBody().setLinearVelocity(0, 0);
                timeFromShoot = 0;
            }

            if (this.getBody().getLinearVelocity().x == 0){
                game.getAssets().getSound_shoot_laser_03().play(0.2f);
                if (object.getCurrentFrame().isFlipX() == false){
                    this.getBody().setTransform(object.getBody().getPosition().x + object.getSprite().getWidth() / 1.5f, object.getBody().getPosition().y, 0);
                    this.getBody().setLinearVelocity(this.getSpeedX(), 0);
                } else if (object.getCurrentFrame().isFlipX() == true) {
                    this.getBody().setTransform(object.getBody().getPosition().x - object.getSprite().getWidth() / 1.5f, object.getBody().getPosition().y, 0);
                    this.getBody().setLinearVelocity(-this.getSpeedX(), 0);
                }
            }
        }
        return true;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
        checkBulletCollision();
        this.getBody().setLinearVelocity(this.getBody().getLinearVelocity().x, -0.1f);
        timeFromShoot += deltaTime;

    }

    public void render(SpriteBatch batch){
        if (this.isAnimation == true){
            if (shootAnimation.isActive() == true){
                currentFrame = shootAnimation.getAnimation().getKeyFrame(stateTime, true);
            }
            batch.draw(currentFrame,body.getPosition().x - sprite.getWidth() / 2,
                    body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());
        }else{
            batch.draw(region,body.getPosition().x - sprite.getWidth() / 2,
                    body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());
        }

    }

    public void checkBulletCollision(){
        if (this.removeFlag == true){
            this.body.setTransform(-1000,-1000,0);
            this.removeFlag = false;
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

    public float getTimeFromShoot() {
        return timeFromShoot;
    }

    public void setTimeFromShoot(float timeFromShoot) {
        this.timeFromShoot = timeFromShoot;
    }

    public boolean isBulletShoot() {
        return bulletShoot;
    }

    public void setBulletShoot(boolean bulletShoot) {
        this.bulletShoot = bulletShoot;
    }

    public Array<Bullet> getShootBullets() {
        return shootBullets;
    }

    public void setShootBullets(Array<Bullet> shootBullets) {
        this.shootBullets = shootBullets;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
    }

    public ObjectAnimation getShootAnimation() {
        return shootAnimation;
    }

    public void setShootAnimation(ObjectAnimation shootAnimation) {
        this.shootAnimation = shootAnimation;
    }


}
