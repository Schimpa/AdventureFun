package com.adventure.fun.objects;

import com.adventure.fun._main.MainWindow;
import com.adventure.fun.effects.Particles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;


/**
 * Created by Dennis on 27.10.2015.
 */
public abstract class LivingObject  {

    protected MainWindow game;

    //Attribute
    protected Vector2 speed;
    protected Vector2 maxSpeed;
    protected int lives;
    protected int score;
    private boolean isJumping;

    //Grafikattribute
    protected Sprite sprite;
    protected TextureRegion region;

    //Physikattribute
    protected Body body;
    protected BodyDef bodyDef;
    protected PolygonShape shape;
    protected FixtureDef fixtureDef;

    //Für zufällige Ereignisse
    protected Random rand = new Random();
    protected int randomInt;

    //OB DAS OBJEKT ENTFERNT WERDEN SOLL
    protected boolean removeFlag;

    //OB DAS OBJEKT SCHON ZERSTÖRT IST
    protected boolean isDestroyed;

    //WURDE DAS POSTINIT SCHON DURCHGEFÜHRT
    protected boolean postInitFlag;

    protected float sound_reload;

    protected float jumpTimer;

    //JETZTIGES FRAME DER ANIMATIONEN
    protected TextureRegion currentFrame;

    //ABLAUFZEIT FÜR DIE ANIMATIONEN
    protected float stateTime;

    //PARTIKEL EINES OBJEKTES
    protected Particles particles;

    /*
        COLLISION GROUPS
        -1 = PLAYER
        -2 = ENEMIES
        -3 = BULLET & ITEMS

     */



    public LivingObject(MainWindow game){
        this.game = game;
    }

    public void init(float x,float y,float sizeX,float sizeY,World world){
        //SPRITE
        sprite = new Sprite();
        sprite.setPosition(x, y);
        sprite.setSize(sizeX, sizeY);

        particles = new Particles();

        postInitFlag = false;

        stateTime = 0.0f;

        //PHYSIK KÖRPER DEFINITION
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX(), sprite.getY());

        //PHYSIK KÖRPER HÜLLE
        shape = new PolygonShape();
        shape.setAsBox((sprite.getWidth() / 2) * 0.8f,sprite.getHeight() / 2 );

        //PHYSIK KÖRPER EIGENSCHAFTEN
        fixtureDef = new FixtureDef();
        fixtureDef.density = 0;
        fixtureDef.isSensor = false;
        fixtureDef.friction = 1;
        fixtureDef.shape = shape;


        body = world.createBody(bodyDef);


    }

    //ZWEITE INITIALISIERUNG DAMIT ALLE REFERNZEN AUCH VERFÜGBAR SIND
    public void postInit(){
        postInitFlag = true;
    }

    public void looseLife(int amount){
        this.lives = this.lives - amount;
        if (this.lives <= 0){
            this.removeFlag = true;
        }

    }

    public void render(SpriteBatch batch){
        //ANIMATION LAUFEN
        if (removeFlag == true && isDestroyed == false) {
            this.dispose();
        }
    }

    public void dispose(){
        this.body.getWorld().destroyBody(this.body);
        isDestroyed = true;
    }


    public void update(float deltaTime){
        if (postInitFlag == false){
            postInit();
        }
        jumpTimer += deltaTime;
        sound_reload += deltaTime;
    }


    public void move(boolean direction,float deltaTime){
        //LEFT == FALSE | RIGHT == TRUE
        if (direction == false){
            if (this.getBody().getLinearVelocity().x >= -this.getMaxSpeed().x) {
                this.getBody().setLinearVelocity(this.getBody().getLinearVelocity().x -= this.getSpeed().x * deltaTime, this.getBody().getLinearVelocity().y);
            }
        }
        else if (direction == true){
            if (this.getBody().getLinearVelocity().x <= this.getMaxSpeed().x) {
                this.getBody().setLinearVelocity(this.getBody().getLinearVelocity().x += this.getSpeed().x * deltaTime, this.getBody().getLinearVelocity().y);
            }
        }
    }

    public void jump(){
        if (jumpTimer <= 0.5 && getIsJumping() == true){
            this.getBody().setLinearVelocity(this.getBody().getLinearVelocity().x, 10);
            System.out.println(jumpTimer);
        }else if (this.getBody().getLinearVelocity().y <= 0.1f){
            setIsJumping(false);
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

    public Particles getParticles() {
        return particles;
    }

    public void setParticles(Particles particles) {
        this.particles = particles;
    }

    public MainWindow getGame() {
        return game;
    }

    public void setGame(MainWindow game) {
        this.game = game;
    }
}
