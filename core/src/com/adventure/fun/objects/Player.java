package com.adventure.fun.objects;

import com.adventure.fun.audio.AudioController;
import com.adventure.fun.controls.Controls;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Object {

    private boolean isJumping = true;

    private Controls controls;

    private Bullet bullet;


    private Vector2 speed;
    private Vector2 maxSpeed;
    private float jumpStartPosition;

    private int lives;
    private int score;





    public void init(float x,float y,float sizeX,float sizeY,World world) {
        //ATTRIBUTE
        speed = new Vector2(0.3f,0.3f);
        score = 500;
        lives = 3;
        jumpStartPosition = 0;
        maxSpeed = new Vector2(5,5);


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
        bullet = new Bullet(-100,-100,0.8f,0.2f,world,Textures.bullet_02);

        //JETZTIGE ANIMATION
        currentFrame = new TextureRegion();

        createMoveAnimation();

        body = world.createBody(bodyDef);
        body.setUserData("Player");
        body.createFixture(fixtureDef).setUserData(this);

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
        bullet.getBody().setLinearVelocity(bullet.getBody().getLinearVelocity().x, -0.1f);
        checkIfLoose();
    }


    public void checkIfLoose() {
        if (body.getPosition().y < 0) {
            AudioController.sound_die.play();
            body.setLinearVelocity(0, 0);
            body.setTransform(1, 5, 0);
            lives -= 1;
            score -= 100;
        }
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

    public Player(float x,float y,float sizeX,float sizeY,World world) {
        init(x,y,sizeX,sizeY,world);

    }

    public Vector2 getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Vector2 maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public boolean getIsJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
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
}