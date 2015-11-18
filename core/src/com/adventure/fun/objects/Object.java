package com.adventure.fun.objects;

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
public abstract class Object {


    protected Vector2 speedMax;              // MAX SPEED (X,Y)
    protected Sprite sprite;
    protected TextureRegion region;

    protected Body body;
    protected BodyDef bodyDef;
    protected PolygonShape shape;
    protected FixtureDef fixtureDef;



    //Animation
    TextureRegion currentFrame;
    Animation walkAnimation;
    TextureRegion[] walkFrames;
    float stateTime;


    public void render(){
        //ANIMATION LAUFEN
        currentFrame = walkAnimation.getKeyFrame(stateTime,true);
    }


    public void update(float deltaTime){};


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













































    public Vector2 getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(Vector2 speedMax) {
        this.speedMax = speedMax;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
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
}
