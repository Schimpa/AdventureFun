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
public class Enemy_Alien_Bigmama extends LivingObject {

    private Player player;
    private Bullet bullet;

    //Für zufällige Ereignisse
    Random rand = new Random();
    private int randomInt;

    private int reactionDistance;    //Der Abstand, bei den Gegner den Spieler anfangen zu bemerken

    private ObjectAnimation walkAnimation;

    public Enemy_Alien_Bigmama(MainWindow game, float x, float y, float sizeX, float sizeY, World world, Player player){
        super(game);
        init(x,y,sizeX,sizeY,world);
        this.player = player;

    }

    public void init(float x,float y,float sizeX,float sizeY,World world){
        super.init(x, y, sizeX, sizeY, world);
        shape.setAsBox((sprite.getWidth() / 2) * 0.5f, sprite.getHeight() / 2.1f);
        body.createFixture(fixtureDef);

        reactionDistance = 13;
        speed = new Vector2(9,9f);
        maxSpeed = new Vector2(4,4);
        removeFlag = false;
        sound_reload = 0;

        stateTime = 0.0f;

        lives = 10;
        body.setUserData("Enemy_Alien_Bigmama");

        currentFrame = new TextureRegion();

        bullet = new Bullet(game,-100,-100,1.3f,1.3f,world,game.getAssets().getBullet_bigmama(),true);
        bullet.getShootAnimation().setAnimationSpeed(0.5f);
        bullet.setSpeedX(6);
        bullet.setReloadTime(2f);

        walkAnimation = new ObjectAnimation(game.getAssets().getAlien_bigmama(),3,1,0,2,0.08f);
        walkAnimation.setIsActive(true);

        shape.dispose();
    }

    public void logic(float deltaTime){
        if (    player.getBody().getPosition().x - this.getBody().getPosition().x > -reactionDistance &&
                player.getBody().getPosition().x - this.getBody().getPosition().x < reactionDistance &&
                player.getBody().getPosition().y - this.getBody().getPosition().y > -5 &&
                player.getBody().getPosition().y - this.getBody().getPosition().y < 5  ) {

            if (player.getBody().getPosition().x - this.getBody().getPosition().x < 7 &&
                    player.getBody().getPosition().x - this.getBody().getPosition().x > -7) {
            } else {
                this.stateTime += deltaTime;
                //LEFT OR RIGHT
                if (player.getBody().getPosition().x - this.getBody().getPosition().x < 0) {
                    this.move(false, deltaTime);
                }

                if (player.getBody().getPosition().x - this.getBody().getPosition().x > 0) {
                    this.move(true, deltaTime);
                }
            }
            randomInt = rand.nextInt(300) + 1;
            if (randomInt == 50) {
                game.getAssets().getSound_alien_takel_01().play(1f);
            } else if (randomInt == 74) {
                game.getAssets().getSound_alien_takel_02().play(1f);
            }
            bullet.setBulletShoot(true);



        }else if (removeFlag == true) {
            bullet.setBulletShoot(true);

        }
    }

    public void update(float deltaTime){
        if (isDestroyed == false){
            if (walkAnimation.isActive() == true){
                currentFrame = walkAnimation.getAnimation().getKeyFrame(stateTime, true);
            }
            logic(deltaTime);
            bullet.shootBulletBigmama(this);
        }
        bullet.update(deltaTime);
    }

    @Override
    public void move(boolean direction,float deltaTime){
        super.move(direction, deltaTime);
        if (direction == false){
            if (this.getCurrentFrame().isFlipX() == false){
                for(int i = 0; i < this.walkAnimation.getFrames().length;i++){
                    this.walkAnimation.getFrames()[i].flip(true,false);
                }
            }
        }
        else if (direction == true){
            if (this.getCurrentFrame().isFlipX() == true){
                for(int i = 0; i < this.walkAnimation.getFrames().length;i++){
                    this.walkAnimation.getFrames()[i].flip(true,false);
                }
            }
        }
    }

    @Override
    public void dispose(){
        particles.playEffect(this.getBody().getPosition().x,this.getBody().getPosition().y,particles.getExplosion_dead());
        this.body.setTransform(-1000,-1000f,0);
        player.setScore(player.getScore() + 2000);
        super.dispose();
    }


    public void render(SpriteBatch batch){
        super.render(batch);
        if (removeFlag != true){
            batch.draw(currentFrame, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth() ,sprite.getHeight());
        }
        bullet.render(batch);

    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}
