package com.adventure.fun.objects;

import com.adventure.fun.audio.AudioController;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;


/**
 * Created by Dennis on 27.10.2015.
 */
public abstract class LivingObject {


    //Attribute
    protected Vector2 speed;
    protected Vector2 maxSpeed;
    protected int lives;
    protected int score;
    private boolean isJumping = true;

    //Grafikattribute
    protected Sprite sprite;
    protected TextureRegion region;

    //Physikattribute
    protected Body body;
    protected BodyDef bodyDef;
    protected PolygonShape shape;
    protected FixtureDef fixtureDef;

    protected boolean removeFlag;
    protected boolean isDestroyed;

    protected float sound_reload;


    //Animation
    TextureRegion currentFrame;
    Animation walkAnimation;
    TextureRegion[] walkFrames;
    Float stateTime;

    public void render(){
        //ANIMATION LAUFEN
        if (removeFlag == true && isDestroyed == false) {
            destroy();
        }else{
            currentFrame = walkAnimation.getKeyFrame(stateTime,true);

        }

    }

    public void destroy(){
        this.body.getWorld().destroyBody(this.body);
        isDestroyed = true;
    }


    public void update(float deltaTime){
        checkIfLoose();
        sound_reload += deltaTime;
    }

    public void createAnimation(Texture texture,int lengthX,int lengthY){
        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth()/lengthX, texture.getHeight()/lengthY);

        walkFrames = new TextureRegion[lengthX*lengthY];
        int index = 0;
        for (int i = 0; i < lengthY; i++) {
            for (int j = 0; j < lengthX; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation(0.15f, walkFrames);
        stateTime = 0f;
    }

    public void checkIfLoose() {
        if (body.getPosition().y < 0) {
            AudioController.sound_die.play();
            body.setLinearVelocity(0, 0);
            body.setTransform(1, 5, 0);
            lives -= 1;
            score -= 100;
        }else if (lives <= 0){
            this.getBody().getWorld().destroyBody(this.body);
        }
    }

    public void move(boolean direction,float deltaTime){
        //LEFT == FALSE | RIGHT == TRUE
        if (currentFrame.getRegionX() == 32 && sound_reload >= 0.25f){
            AudioController.sound_step_02.play(0.2f);
            sound_reload = 0;
        }else if(currentFrame.getRegionX() == 96 && sound_reload >= 0.25f){
            AudioController.sound_step_01.play(0.2f);
            sound_reload = 0;
        }


        if (direction == false){
            if (this.getCurrentFrame().isFlipX() == false){
                for(int i = 0; i < this.getWalkFrames().length;i++){
                    this.getWalkFrames()[i].flip(true,false);
                }
            }
            if (this.getBody().getLinearVelocity().x >= -this.getMaxSpeed().x) {
                this.getBody().setLinearVelocity(this.getBody().getLinearVelocity().x -= this.getSpeed().x * deltaTime, this.getBody().getLinearVelocity().y);
            }
        }
        else if (direction == true){
            if (this.getCurrentFrame().isFlipX() == true){
                for(int i = 0; i < this.getWalkFrames().length;i++){
                    this.getWalkFrames()[i].flip(true,false);
                }
            }
            if (this.getBody().getLinearVelocity().x <= this.getMaxSpeed().x) {
                this.getBody().setLinearVelocity(this.getBody().getLinearVelocity().x += this.getSpeed().x * deltaTime, this.getBody().getLinearVelocity().y);
            }
        }



}

    public void playerJump(){
        if (this.getBody().getLinearVelocity().y <= 0.1f && this.getBody().getLinearVelocity().y >= -0.1f ){
            this.setIsJumping(false);
        }
        if (this.getIsJumping() == false){
            this.setIsJumping(true);
            AudioController.sound_jump.play(0.1f);
            this.getBody().setLinearVelocity(this.getBody().getLinearVelocity().x,10);
        }
    }













    public boolean getIsJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
    }

    public PolygonShape getShape() {
        return shape;
    }

    public void setShape(PolygonShape shape) {
        this.shape = shape;
    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    public void setFixtureDef(FixtureDef fixtureDef) {
        this.fixtureDef = fixtureDef;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setWalkFrames(TextureRegion[] walkFrames) {
        this.walkFrames = walkFrames;
    }

    public TextureRegion[] getWalkFrames() {
        return walkFrames;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }

    public Vector2 getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Vector2 maxSpeed) {
        this.maxSpeed = maxSpeed;
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

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }

    public Animation getWalkAnimation() {
        return walkAnimation;
    }

    public void setWalkAnimation(Animation walkAnimation) {
        this.walkAnimation = walkAnimation;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public boolean isRemoveFlag() {
        return removeFlag;
    }

    public void setRemoveFlag(boolean removeFlag) {
        this.removeFlag = removeFlag;
    }
}
