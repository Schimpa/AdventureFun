package com.adventure.fun.objects;

import com.adventure.fun.controls.PlayerControl;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


/**
 * Created by Dennis on 27.10.2015.
 */
public class Player extends Object {

    private boolean isJumping = false;

    private PlayerControl playerControl;

    private Array<Bullet> bullets;


    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    private Vector2 speed;
    private Vector2 maxSpeed;

    private int lives;


    public void init(float x,float y,float sizeX,float sizeY,World world) {
        speed = new Vector2(1,1);
        lives = 3;
        maxSpeed = new Vector2(10,10);
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

        bullets = new Array<Bullet>();
        for(int i = 0;i < 5;i++){
            Bullet bullet = new Bullet(0,0,1f,0.2f,world);
            bullets.add(bullet);
        }


        region = new TextureRegion(Textures.player);

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef).setUserData(this);

        shape.dispose();

        playerControl = new PlayerControl(this);
        Gdx.input.setInputProcessor(playerControl);
    }

    public void render(SpriteBatch batch) {
        //PLAYER
        batch.draw(region, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());

        //BULLET
        for(int i = 0;i < bullets.size;i++){
            batch.draw(bullets.get(i).region, bullets.get(i).body.getPosition().x - bullets.get(i).sprite.getWidth() / 2,
                    bullets.get(i).body.getPosition().y - bullets.get(i).sprite.getHeight() / 2, bullets.get(i).sprite.getWidth(), bullets.get(i).sprite.getHeight());
        }

    }


    @Override
    public void update(float deltaTime) {
        playerControl.controls();
        checkIfLoose();
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
    }


    public void checkIfLoose() {
        if (body.getPosition().y < 0) {
            body.setLinearVelocity(0, 0);
            body.setTransform(0, 2, 0);
            lives -= 1;
            if (lives == 0){
                try {
                    this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
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

    public PlayerControl getPlayerControl() {
        return playerControl;
    }

    public void setPlayerControl(PlayerControl playerControl) {
        this.playerControl = playerControl;
    }


    public Array<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Array<Bullet> bullets) {
        this.bullets = bullets;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }
}