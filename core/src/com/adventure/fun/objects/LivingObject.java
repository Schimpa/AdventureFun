package com.adventure.fun.objects;

import com.adventure.fun.audio.AudioController;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by Dennis on 27.10.2015.
 */
public abstract class LivingObject {


    //Attribute
    protected Vector2 speed;
    protected Vector2 maxSpeed;
    protected int lives;
    protected int score;

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

    //Animation
    TextureRegion currentFrame;
    Animation walkAnimation;
    TextureRegion[] walkFrames;
    float stateTime;

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

    }

    public void createMoveAnimation(){
        TextureRegion[][] tmp = TextureRegion.split(Textures.player_move,
                Textures.player_move.getWidth()/4, Textures.player_move.getHeight());

        walkFrames = new TextureRegion[4];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
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
        }
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
