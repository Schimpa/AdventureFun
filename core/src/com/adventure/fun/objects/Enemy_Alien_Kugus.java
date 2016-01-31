package com.adventure.fun.objects;

import com.adventure.fun._main.MainWindow;
import com.adventure.fun.effects.ObjectAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

/**
 * Created by Dennis on 28.10.2015.
 */
public class Enemy_Alien_Kugus extends LivingObject {

    private Player player;
    private int reactionDistance;    //Der Abstand, bei den Gegner den Spieler anfangen zu bemerken

    private ObjectAnimation walkAnimation;
    private ObjectAnimation weaponAnimation;

    private TextureRegion currentWeaponFrame;

    private float x,y,sizeX,sizeY;

    private int bodyNumber;


    public Enemy_Alien_Kugus(MainWindow game, float x, float y, float sizeX, float sizeY, World world, Player player){
        super(game);
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        init(x,y,sizeX,sizeY,world);
        this.player = player;

    }

    //DIRECTION -> FALSE == LINKS | DIRECTION -> TRUE = RECHTS
    public void createWeapon(boolean direction){
        float lastPositionX = this.getBody().getPosition().x;
        float lastPositionY = this.getBody().getPosition().y;
        this.body.getWorld().destroyBody(this.getBody());
        super.init(lastPositionX,lastPositionY,sizeX,sizeY,game.getMenuScreen().getGameScreen().getWorldLoader().getWorld());
        super.body.createFixture(super.fixtureDef);
        this.createWeaponBody(direction);
        body.setUserData("Enemy_Alien_Kugus_"+bodyNumber);
    }

    public void createWeaponBody(boolean direction){
        shape = new PolygonShape();
        if (direction == false){
            shape.setAsBox((sprite.getWidth() / 2) * 1.6f,(sprite.getHeight() / 2f) * 0.2f, new Vector2(-1,0),0 );
        }else if (direction == true){
            shape.setAsBox((sprite.getWidth() / 2) * 1.6f,(sprite.getHeight() / 2f) * 0.2f, new Vector2(1,0),0 );
        }
        //PHYSIK KÖRPER EIGENSCHAFTEN
        fixtureDef = new FixtureDef();
        fixtureDef.density = 0;
        fixtureDef.isSensor = true;
        fixtureDef.friction = 1;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
    }


    public void init(float x,float y,float sizeX,float sizeY,World world){
        super.init(x,y,sizeX,sizeY,world);
        super.body.createFixture(super.fixtureDef);
        createWeaponBody(false);


        reactionDistance = 10;
        speed = new Vector2(12f,12f);
        maxSpeed = new Vector2(6,6);
        removeFlag = false;
        sound_reload = 0;

        stateTime = 0.0f;

        lives = 4;

        body.setUserData("Enemy_Alien_Kugus");

        currentFrame = new TextureRegion();
        currentWeaponFrame = new TextureRegion();

        walkAnimation = new ObjectAnimation(game.getAssets().getAlien_kugus(),4,1,0,3,0.15f);
        walkAnimation.setIsActive(true);

        weaponAnimation = new ObjectAnimation(game.getAssets().getBullet_blitzkugel(),4,1,0,3,0.15f);
        weaponAnimation.setIsActive(false);

        particles.activateParticle(particles.getExplosion_blitzkugel());
        particles.activateParticle(particles.getExplosion_dead());
        particles.activateParticle(particles.getFunken_01());

        shape.dispose();
    }

    public void logic(float deltaTime){
        if (player.getBody().getPosition().x - this.getBody().getPosition().x > -reactionDistance &&
                player.getBody().getPosition().x - this.getBody().getPosition().x < reactionDistance &&
                player.getBody().getPosition().y - this.getBody().getPosition().y > -5 &&
                player.getBody().getPosition().y - this.getBody().getPosition().y < 5  ){
            weaponAnimation.setIsActive(true);

            if (player.getBody().getPosition().x - this.getBody().getPosition().x < 2 &&
                    player.getBody().getPosition().x - this.getBody().getPosition().x > -2 ){
            }else{
                this.stateTime += deltaTime;
                //LEFT OR RIGHT
                if (player.getBody().getPosition().x - this.getBody().getPosition().x < 0){
                    this.move(false,deltaTime);
                }

                if (player.getBody().getPosition().x - this.getBody().getPosition().x > 0){
                    this.move(true,deltaTime);
                }
            }
            randomInt = rand.nextInt(300)+1;
            if (randomInt == 50){
                game.getAssets().getSound_alien_soldier_01().play(0.7f);
            }else if (randomInt == 74){
                game.getAssets().getSound_alien_soldier_02().play(0.7f);
            }
        }else{
            weaponAnimation.setIsActive(false);
        }
    }

    public void update(float deltaTime){
        if (walkAnimation.isActive() == true){
            currentFrame = walkAnimation.getAnimation().getKeyFrame(stateTime, true);
        }
        currentWeaponFrame = weaponAnimation.getAnimation().getKeyFrame(stateTime, true);
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
                createWeapon(false);
            }
        }
        else if (direction == true){
            if (this.getCurrentFrame().isFlipX() == true){
                for(int i = 0; i < this.walkAnimation.getFrames().length;i++){
                    this.walkAnimation.getFrames()[i].flip(true,false);
                }
                createWeapon(true);
            }
        }
    }

    @Override
    public void dispose(){
        particles.playEffect(this.getBody().getPosition().x,this.getBody().getPosition().y,particles.getExplosion_dead());
        super.dispose();
    }

    public void render(SpriteBatch batch){
        super.render(batch);
        if (removeFlag != true){
            batch.draw(currentFrame, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth() ,sprite.getHeight());
            if (weaponAnimation.isActive() == true){
                if (this.getCurrentFrame().isFlipX() == true){
                    batch.draw(currentWeaponFrame, body.getPosition().x - sprite.getWidth() * 1.25f, body.getPosition().y -sprite.getHeight()/10,(sprite.getWidth() / 2.2f) * 1.8f,(sprite.getHeight() / 2f) * 0.4f);
                }else if (this.getCurrentFrame().isFlipX() == false){
                    batch.draw(currentWeaponFrame, body.getPosition().x + sprite.getWidth() * 1.25f, body.getPosition().y -sprite.getHeight()/10,(sprite.getWidth() / 2.2f) * 1.8f,(sprite.getHeight() / 2f) * 0.4f);
                }

            }
        }
    }

    public int getBodyNumber() {
        return bodyNumber;
    }

    public void setBodyNumber(int bodyNumber) {
        this.bodyNumber = bodyNumber;
    }
}
