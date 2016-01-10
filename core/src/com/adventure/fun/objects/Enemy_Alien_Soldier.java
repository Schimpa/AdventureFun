package com.adventure.fun.objects;

import com.adventure.fun._main.MainWindow;
import com.adventure.fun.effects.ObjectAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

/**
 * Created by Dennis on 28.10.2015.
 */
public class Enemy_Alien_Soldier extends LivingObject {

    private Player player;
    private Bullet bullet;

    //Für zufällige Ereignisse
    Random rand = new Random();
    private int randomInt;

    private int reactionDistance;    //Der Abstand, bei den Gegner den Spieler anfangen zu bemerken

    private ObjectAnimation walkAnimation;

    public Enemy_Alien_Soldier(MainWindow game, float x, float y, float sizeX, float sizeY, World world, Player player){
        super(game);
        init(x,y,sizeX,sizeY,world);
        this.player = player;

    }

    public void init(float x,float y,float sizeX,float sizeY,World world){
        super.init(x,y,sizeX,sizeY,world);
        body.createFixture(fixtureDef);
        reactionDistance = 15;
        speed = new Vector2(12f,12f);
        maxSpeed = new Vector2(2,2);
        removeFlag = false;
        sound_reload = 0;

        stateTime = 0.0f;

        lives = 2;

        body.setUserData("Enemy_Alien_Soldier");

        bullet = new Bullet(game,-100,-100,0.2f,0.2f,world,game.getAssets().getBullet(),"Bullet_Enemy_Soldier");
        bullet.setSpeedX(10);
        bullet.setReloadTime(0.01f);


        currentFrame = new TextureRegion();

        walkAnimation = new ObjectAnimation(game.getAssets().getAlien_soldier(),5,1,0,4);
        walkAnimation.setIsActive(true);

        shape.dispose();
    }

    public void logic(float deltaTime){
        if (player.getBody().getPosition().x - this.getBody().getPosition().x > -reactionDistance &&
                player.getBody().getPosition().x - this.getBody().getPosition().x < reactionDistance&&
                player.getBody().getPosition().y - this.getBody().getPosition().y > -5 &&
                player.getBody().getPosition().y - this.getBody().getPosition().y < 5 ){
            randomInt = rand.nextInt(300)+1;
            if (randomInt == 48){
                game.getAssets().getSound_alien_soldier_01().play(0.7f);
            }else if (randomInt == 74){
                game.getAssets().getSound_alien_soldier_02().play(0.7f);
            }

            this.stateTime += deltaTime;
            //LEFT OR RIGHT
            if (player.getBody().getPosition().x - this.getBody().getPosition().x < 0){
                this.move(false,deltaTime);
            }

            if (player.getBody().getPosition().x - this.getBody().getPosition().x > 0){
                this.move(true,deltaTime);
            }
            bullet.setBulletShoot(true);
        }
    }

    public void update(float deltaTime){
        bullet.update(deltaTime);
        bullet.shootBullet(this);
        logic(deltaTime);
    }

    @Override
    public void move(boolean direction,float deltaTime){
        super.move(direction,deltaTime);
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
        game.getAssets().getSound_alien_soldier_dead().play(1);
        super.dispose();
    }


    public void render(SpriteBatch batch){
        super.render();
        if (removeFlag != true){
            if (walkAnimation.isActive() == true){
                currentFrame = walkAnimation.getAnimation().getKeyFrame(stateTime, true);
            }
            batch.draw(bullet.region, bullet.body.getPosition().x - bullet.sprite.getWidth() / 2,
                    bullet.body.getPosition().y - bullet.sprite.getHeight() / 2, bullet.sprite.getWidth(), bullet.sprite.getHeight());

            batch.draw(currentFrame, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth() ,sprite.getHeight());
        }
    }

    public Bullet getBullet(){
        return this.bullet;
    }

    public void setBullet(Bullet bullet){
        this.bullet = bullet;
    }
}
