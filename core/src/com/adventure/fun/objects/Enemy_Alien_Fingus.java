package com.adventure.fun.objects;

import com.adventure.fun._main.MainWindow;
import com.adventure.fun.effects.ObjectAnimation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

/**
 * Created by Dennis on 28.10.2015.
 */
public class Enemy_Alien_Fingus extends LivingObject {

    private Player player;
    private Bullet bullet;

    //Für zufällige Ereignisse
    Random rand = new Random();
    private int randomInt;

    private int reactionDistance;    //Der Abstand, bei den Gegner den Spieler anfangen zu bemerken

    private ObjectAnimation walkAnimation;

    public Enemy_Alien_Fingus(MainWindow game, float x, float y, float sizeX, float sizeY, World world, Player player){
        super(game);
        init(x,y,sizeX,sizeY,world);
        this.player = player;

    }

    public void init(float x,float y,float sizeX,float sizeY,World world){
        super.init(x,y,sizeX,sizeY,world);
        body.createFixture(fixtureDef);
        reactionDistance = 10;
        speed = new Vector2(12f,12f);
        maxSpeed = new Vector2(5,5);
        removeFlag = false;
        sound_reload = 0;

        stateTime = 0.0f;

        lives = 2;
        body.setUserData("Enemy_Alien_Fingus");

        currentFrame = new TextureRegion();

        walkAnimation = new ObjectAnimation(game.getAssets().getAlien_fingus(),3,1,0,2,0.15f);
        walkAnimation.setIsActive(true);

        shape.dispose();
    }

    @Override
    public void dispose(){
        particles.playEffect(this.getBody().getPosition().x,this.getBody().getPosition().y,particles.getExplosion_dead());
        this.body.setTransform(-1000,-1000f,0);
        super.dispose();
    }


    public void logic(float deltaTime){
        if (player.getBody().getPosition().x - this.getBody().getPosition().x > -reactionDistance &&
                player.getBody().getPosition().x - this.getBody().getPosition().x < reactionDistance&&
                player.getBody().getPosition().y - this.getBody().getPosition().y > -5 &&
                player.getBody().getPosition().y - this.getBody().getPosition().y < 5 ){
            randomInt = rand.nextInt(300)+1;
            if (randomInt == 48){
                game.getAssets().getSound_alien_zombie().play(0.2f);
            }

            this.stateTime += deltaTime;
            //LEFT OR RIGHT
            if (player.getBody().getPosition().x - this.getBody().getPosition().x < 0){
                this.move(false,deltaTime);
            }

            if (player.getBody().getPosition().x - this.getBody().getPosition().x > 0){
                this.move(true,deltaTime);
            }
        }
    }

    public void update(float deltaTime){
        logic(deltaTime);
    }

    public void render(SpriteBatch batch){
        if (removeFlag != true){
            if (walkAnimation.isActive() == true){
                currentFrame = walkAnimation.getAnimation().getKeyFrame(stateTime, true);
            }
            batch.draw(currentFrame, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth() ,sprite.getHeight());
        }
        super.render(batch);
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}
