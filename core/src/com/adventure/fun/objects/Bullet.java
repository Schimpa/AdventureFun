package com.adventure.fun.objects;

import com.adventure.fun._main.MainWindow;
import com.adventure.fun.effects.ObjectAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.sun.org.apache.xerces.internal.impl.dv.xs.AnySimpleDV;

import box2dLight.PointLight;

/**
 * Created by Dennis on 30.10.2015.
 */
public class Bullet extends LivingObject {

    public float speedX;

    //ZEIT, DIE NACH DEM SCHUSS VERGANGEN IST
    private float timeFromShoot;

    //OB DAS GESCHOSS GESCHOSSEN WIRD
    private boolean bulletShoot;

    //IST DIE TEXTUR EINE ANIMATION ?
    private boolean isAnimation;

    private TextureRegion region;

    //SOUND DES GESCHOSSESE
    private Sound bulletSound;
    private float soundVolume;

    //SCHIEßANIMATION
    private ObjectAnimation shootAnimation;

    //ZEIT NACHDEM EIN NEUES GESCHOSS GESCHOSSEN WERDEN KANN
    private float reloadTime;

    //SCHADEN DES GESCHOSSES
    private int bulletDamage;

    //DAS LICHT DES GESCHOSSESE
    private PointLight bulletLight;

    //ANZAHL DER GESCHOSSE DIE ÜBRIG BLEIBEN
    private int bullets;



    public Bullet(MainWindow game,float x,float y,float sizeX,float sizeY,World world,TextureRegion region,boolean isAnimation){
        super(game);
        soundVolume = 1f;
        initNormalBullet(x, y, sizeX, sizeY, world, region,isAnimation);


    }

    public void initNormalBullet(float x,float y,float sizeX,float sizeY,World world,TextureRegion region,boolean isAnimation) {
        super.init(x, y, sizeX, sizeY, world);
        fixtureDef.isSensor = true;
        fixtureDef.filter.groupIndex = (short)-3;
        body.createFixture(fixtureDef);

        createRedBullet();
        timeFromShoot = 0f;

        this.region = region;
        this.isAnimation = isAnimation;

        bulletShoot = false;

        MassData massData = new MassData();
        massData.mass = 0;
        body.setMassData(massData);

        currentFrame = new TextureRegion();

        if (region.equals(game.getAssets().getBullet_bigmama()) && isAnimation == true){
            createBulletBigmama();
        }else if (isAnimation == true){
            shootAnimation = new ObjectAnimation(region, 4, 1, 0, 3, 0.05f);
            shootAnimation.setIsActive(true);
        }

        shape.dispose();
    }


    public void createGreenBullet() {
        this.region = game.getAssets().getBullet_green();
        this.setBulletSound(game.getAssets().getSound_shoot_alien_01());
        this.setSpeed(new Vector2(12f,12f));
        this.setMaxSpeed(new Vector2(12f,12f));
        this.reloadTime = 1.5f;
        this.bulletDamage = 2;
        this.bullets = 10;
    }

    public void createBlueBullet(){
        this.region = game.getAssets().getBullet_blue();
        this.setBulletSound(game.getAssets().getSound_shoot_laser_04());
        this.setSpeed(new Vector2(5f,5f));
        this.setMaxSpeed(new Vector2(5f,5f));
        this.reloadTime = 1f;
        this.bulletDamage = 1;
        this.bullets = 10;
    }

    public void createYellowBullet(){
        this.region = game.getAssets().getBullet_yellow();
        this.setBulletSound(game.getAssets().getSound_shoot_laser_04());
        this.setSpeed(new Vector2(20f,20f));
        this.setMaxSpeed(new Vector2(20f,20f));
        this.reloadTime = 3f;
        this.bulletDamage = 3;
        this.bullets = 10;
    }

    public void createRedBullet(){
        this.region = game.getAssets().getBullet_Red();
        this.setBulletSound(game.getAssets().getSound_shoot_laser_02());
        this.setSpeed(new Vector2(25f,25f));
        this.setMaxSpeed(new Vector2(25f,25f));
        this.reloadTime = 0.4f;
        this.bulletDamage = 1;
    }


    public void createBulletBigmama(){
        setBulletSound(game.getAssets().getSound_shoot_alien_01());
        shootAnimation = new ObjectAnimation(region, 2, 1, 0, 1, 0.05f);
        shootAnimation.setIsActive(true);
    }

    @Override
    public void postInit(){
        super.postInit();
        bulletLight = new PointLight(game.getMenuScreen().getGameScreen().getWorldLoader().getRayHandler(), 5, null, 2, 0f, 0f);
        bulletLight.attachToBody(this.getBody());
        bulletLight.setColor(1f, 0, 0, 0.5f);
    }

    public boolean shootBullet(LivingObject object){
        if (timeFromShoot >= reloadTime && bulletShoot == true){
            bulletShoot = false;
            if (object instanceof Player ){
                game.getMenuScreen().getGameScreen().getCamera().setBarPercent(game.getMenuScreen().getGameScreen().getCamera().getBarPercent() - 20);
                if (game.getMenuScreen().getGameScreen().getCamera().getBarPercent() <= 0){
                    game.getMenuScreen().getGameScreen().getCamera().setBarPercent(0);
                }
            }
            /*
            PointLight light = new PointLight(
                    game.getMenuScreen().getGameScreen().getWorldLoader().getRayHandler(), 5, null, 2, 0f, 0f);

            light.attachToBody(this.getBody());
            light.setColor(1f, 0, 0, 0.5f);*
            */

            if ((this.getBody().getPosition().x - object.getBody().getPosition().x) >= 10
                    || (this.getBody().getPosition().x - object.getBody().getPosition().x) <= -10 ){
                this.getBody().setTransform(-1000, -1000, 0);
                this.getBody().setLinearVelocity(0, 0);
                timeFromShoot = 0;
            }
            if (this.getBody().getLinearVelocity().x == 0){
                bulletSound.play(soundVolume);
                if (object.getCurrentFrame().isFlipX() == false){
                    this.getBody().setTransform(object.getBody().getPosition().x + object.getSprite().getWidth() / 1.5f, object.getBody().getPosition().y, 0);
                    this.getBody().setLinearVelocity(this.getSpeed().x, 0);
                } else if (object.getCurrentFrame().isFlipX() == true) {
                    this.getBody().setTransform(object.getBody().getPosition().x - object.getSprite().getWidth() / 1.5f, object.getBody().getPosition().y, 0);
                    this.getBody().setLinearVelocity(-this.getSpeed().x, 0);
                }
            }
        }
        return true;
    }

    public void shootBulletBigmama(LivingObject object){
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
                bulletSound.play(soundVolume);
                if (object.getCurrentFrame().isFlipX() == false){
                    this.getBody().setTransform(object.getBody().getPosition().x + object.getSprite().getWidth() / 1.5f, object.getBody().getPosition().y - (object.getSprite().getHeight()/12), 0);
                    this.getBody().setLinearVelocity(this.getSpeedX(), 0);
                } else if (object.getCurrentFrame().isFlipX() == true) {
                    this.getBody().setTransform(object.getBody().getPosition().x - object.getSprite().getWidth() / 1.5f, object.getBody().getPosition().y - (object.getSprite().getHeight()/12), 0);
                    this.getBody().setLinearVelocity(-this.getSpeedX(), 0);
                }
            }
        }
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        stateTime += deltaTime;
        checkBulletCollision();
        this.getBody().setLinearVelocity(this.getBody().getLinearVelocity().x, 0.2f);
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

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
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

    public boolean isAnimation() {
        return isAnimation;
    }

    public void setAnimation(boolean animation) {
        isAnimation = animation;
    }

    public Sound getBulletSound() {
        return bulletSound;
    }

    public void setBulletSound(Sound bulletSound) {
        this.bulletSound = bulletSound;
    }

    public PointLight getBulletLight() {
        return bulletLight;
    }

    public void setBulletLight(PointLight bulletLight) {
        this.bulletLight = bulletLight;
    }

    public void setIsAnimation(boolean isAnimation) {
        this.isAnimation = isAnimation;
    }

    @Override
    public TextureRegion getRegion() {
        return region;
    }

    @Override
    public void setRegion(TextureRegion region) {
        this.region = region;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }
}
