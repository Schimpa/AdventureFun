package com.adventure.fun.objects;

import com.adventure.fun.controls.Controls;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends LivingObject {



    private Controls controls;

    private Bullet bullet;

    private float jumpStartPosition;

    private boolean isMovingRight;
    private boolean isMovingLeft;

    private float damageCoolDownTime;

    public Player(float x,float y,float sizeX,float sizeY,World world) {
        init(x,y,sizeX,sizeY,world);

    }

    public void init(float x,float y,float sizeX,float sizeY,World world) {
        //ATTRIBUTE
        speed = new Vector2(15f,15f);
        damageCoolDownTime = 0;
        score = 500;
        lives = 3;
        jumpStartPosition = 0;
        maxSpeed = new Vector2(5,5);
        sound_reload = 0.25f;

        isMovingRight = false;
        isMovingLeft = false;

        //SPRITE
        sprite = new Sprite();
        sprite.setPosition(x, y);
        sprite.setSize(sizeX, sizeY);

        //PHYSIK KÖRPER DEFINITION
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX(), sprite.getY());

        //PHYSIK KÖRPER HÜLLE
        shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

        //PHYSIK KÖRPER EIGENSCHAFTEN
        fixtureDef = new FixtureDef();
        fixtureDef.density = 0;
        fixtureDef.shape = shape;

        //GESCHOSS
        bullet = new Bullet(-100,-100,0.8f,0.2f,world,Textures.bullet,"Bullet_Player");

        //JETZTIGE ANIMATION
        currentFrame = new TextureRegion();

        createAnimation(Textures.player_move,4,1);

        body = world.createBody(bodyDef);
        body.setUserData("Player");
        body.createFixture(fixtureDef);

        shape.dispose();
    }



    public void render(SpriteBatch batch) {
        super.render();

        batch.draw(currentFrame, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());

        //BULLET
        batch.draw(bullet.region, bullet.body.getPosition().x - bullet.sprite.getWidth() / 2,
                bullet.body.getPosition().y - bullet.sprite.getHeight() / 2, bullet.sprite.getWidth(), bullet.sprite.getHeight());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.damageCoolDownTime += deltaTime;
        if (isMovingRight == true){
            this.move(true, Gdx.graphics.getDeltaTime());
        }
        if (isMovingLeft == true){
            this.move(false, Gdx.graphics.getDeltaTime());
        }
        bullet.update();
        bullet.getBody().setLinearVelocity(bullet.getBody().getLinearVelocity().x, -0.1f);
        checkIfLoose();
    }


    public TextureRegion[] getWalkFrames() {
        return walkFrames;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getJumpStartPosition() {
        return jumpStartPosition;
    }

    public void setJumpStartPosition(float jumpStartPosition) {
        this.jumpStartPosition = jumpStartPosition;
    }

    public Vector2 getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Vector2 maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Controls getControls() {
        return controls;
    }

    public void setControls(Controls controls) {
        this.controls = controls;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public boolean isMovingRight() {
        return isMovingRight;
    }

    public void setIsMovingRight(boolean isMovingRight) {
        this.isMovingRight = isMovingRight;
    }

    public boolean isMovingLeft() {
        return isMovingLeft;
    }

    public void setIsMovingLeft(boolean isMovingLeft) {
        this.isMovingLeft = isMovingLeft;
    }

    public float getDamageCoolDownTime() {
        return damageCoolDownTime;
    }

    public void setDamageCoolDownTime(float damageCoolDownTime) {
        this.damageCoolDownTime = damageCoolDownTime;
    }
}