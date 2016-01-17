package com.adventure.fun.objects;

import com.adventure.fun._main.MainWindow;
import com.adventure.fun.controls.Controls;
import com.adventure.fun.effects.ObjectAnimation;
import com.adventure.fun.screens.GameScreen;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class Player extends LivingObject {
    private Controls controls;

    private Bullet bullet;

    private boolean isMovingRight;
    private boolean isMovingLeft;

    private ObjectAnimation walkAnimation;
    private ObjectAnimation jumpAnimation;

    private Vector2 respawnPoint;

    private float damageCoolDownTime;

    public Player(MainWindow game,float x,float y,float sizeX,float sizeY,World world) {
        super(game);
        init(x, y, sizeX, sizeY, world);

    }

    public void init(float x,float y,float sizeX,float sizeY,World world) {
        super.init(x,y,sizeX,sizeY,world);
        body.createFixture(fixtureDef);
        //ATTRIBUTE
        speed = new Vector2(25f,25f);
        damageCoolDownTime = 0;
        score = 500;
        lives = 3;
        maxSpeed = new Vector2(8,8);
        sound_reload = 0.25f;

        respawnPoint = new Vector2(x,y);
        stateTime = 0.0f;

        isMovingRight = false;
        isMovingLeft = false;

        //GESCHOSS
        bullet = new Bullet(game,-100,-100,0.4f,0.4f,world,game.getAssets().getBullet_Laser());
        bullet.getBody().setUserData("Bullet_Player");
        bullet.setSpeedX(30);

        //JETZTIGE ANIMATION
        currentFrame = new TextureRegion();

        walkAnimation = new ObjectAnimation(game.getAssets().getPlayer_move(),6,1,0,3,0.15f);
        walkAnimation.setIsActive(true);

        jumpAnimation = new ObjectAnimation(game.getAssets().getPlayer_move(),6,1,4,5,0.15f);


        body.setUserData("Player");

        shape.dispose();
    }




    public void render(SpriteBatch batch) {
        super.render();
        if (walkAnimation.isActive() == true){
            currentFrame = walkAnimation.getAnimation().getKeyFrame(stateTime, true);
        }
        else if (jumpAnimation.isActive() == true){
            currentFrame = jumpAnimation.getAnimation().getKeyFrame(stateTime, true);
        }
        try{
            batch.draw(currentFrame, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());
        }catch (NullPointerException e) {
            System.out.println("error: " + e);
        }
        //BULLET
        bullet.render(batch);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        checkIfLoose();
        this.damageCoolDownTime += deltaTime;
        if (isMovingRight == true){
            this.move(true, deltaTime);
        }
        if (isMovingLeft == true){
            this.move(false, deltaTime);
        }

        bullet.shootBullet(this);

        bullet.update(deltaTime);
        checkIfLoose();
        jump();
    }

    public void looseLife(int amount){

        this.lives = this.lives - amount;
        randomInt = rand.nextInt(3)+1;

        switch(randomInt){
            case 1:
                this.game.getAssets().getSound_player_hit_01().play();
                break;
            case 2:
                this.game.getAssets().getSound_player_hit_02().play();
                break;
            case 3:
                this.game.getAssets().getSound_player_hit_03().play();
                break;
        }
    }

    public void move(boolean direction,float deltaTime){
        super.move(direction, deltaTime);
        //LEFT == FALSE | RIGHT == TRUE
        if (walkAnimation.getFrames()[0].equals(currentFrame) && sound_reload >= 0.25f){
            game.getAssets().getSound_step_02().play(0.2f);
            sound_reload = 0;
        }else if(walkAnimation.getFrames()[2].equals(currentFrame)&& sound_reload >= 0.25f){
            game.getAssets().getSound_step_01().play(0.2f);
            sound_reload = 0;
        }
        if (direction == false){
            if (this.getCurrentFrame().isFlipX() == false){
                for(int i = 0; i < this.walkAnimation.getFrames().length;i++){
                    this.walkAnimation.getFrames()[i].flip(true,false);
                }
                for(int i = 0; i < this.jumpAnimation.getFrames().length;i++){
                    this.jumpAnimation.getFrames()[i].flip(true,false);
                }
            }
        }
        else if (direction == true){
            if (this.getCurrentFrame().isFlipX() == true){
                for(int i = 0; i < this.walkAnimation.getFrames().length;i++){
                    this.walkAnimation.getFrames()[i].flip(true,false);
                }
                for(int i = 0; i < this.jumpAnimation.getFrames().length;i++){
                    this.jumpAnimation.getFrames()[i].flip(true,false);
                }
            }
        }
    }

    public void checkIfLoose() {
        if (body.getPosition().y < 0) {
            game.getAssets().getSound_die().play();
            body.setLinearVelocity(0, 0);
            body.setTransform(respawnPoint.x, respawnPoint.y, 0);
            lives -= 1;
            score -= 100;
        }else if (lives <= 0){
            Gdx.app.exit();
        }
    }

    @Override
    public void jump(){
        super.jump();
        if (getIsJumping() == true){
            jumpAnimation.setIsActive(true);
            walkAnimation.setIsActive(false);
        }
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

    public float getJumpTimer() {
        return jumpTimer;
    }

    public void setJumpTimer(float jumpTimer) {
        this.jumpTimer = jumpTimer;
    }

    public ObjectAnimation getWalkAnimation() {
        return walkAnimation;
    }

    public void setWalkAnimation(ObjectAnimation walkAnimation) {
        this.walkAnimation = walkAnimation;
    }

    public ObjectAnimation getJumpAnimation() {
        return jumpAnimation;
    }

    public void setJumpAnimation(ObjectAnimation jumpAnimation) {
        this.jumpAnimation = jumpAnimation;
    }
}