package com.adventure.fun.objects;

import com.adventure.fun._main.MainWindow;
import com.adventure.fun.effects.ObjectAnimation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Enemy_Alien_Kefos extends LivingObject {

    private Player player;
    private Bullet bullet;

    private int reactionDistance;    //Der Abstand, bei den Gegner den Spieler anfangen zu bemerken

    //DIE ZEIT AB WANN DER KEFOS WIEDER SPRINGEN KANN
    private float jumpTimer;

    private ObjectAnimation walkAnimation;

    public Enemy_Alien_Kefos(MainWindow game, float x, float y, float sizeX, float sizeY, World world, Player player){
        super(game);
        init(x,y,sizeX,sizeY,world);
        this.player = player;
    }

    public void init(float x,float y,float sizeX,float sizeY,World world){
        super.init(x,y,sizeX,sizeY,world);
        fixtureDef.filter.groupIndex = (short)-1;
        body.createFixture(fixtureDef);
        reactionDistance = 18;
        speed = new Vector2(15f,15f);
        maxSpeed = new Vector2(4,4);
        removeFlag = false;
        sound_reload = 0;

        stateTime = 0.0f;

        jumpTimer = 0;

        lives = 3;

        body.setUserData("Enemy_Alien_Kefos");

        bullet = new Bullet(game,-100,-100,0.8f,0.8f,world,game.getAssets().getBullet_blitzkugel(),true);
        bullet.setBulletSound(game.getAssets().getSound_shoot_laser_03());
        bullet.setSpeed(new Vector2(5,5));
        bullet.setReloadTime(5.0f);

        currentFrame = new TextureRegion();

        walkAnimation = new ObjectAnimation(game.getAssets().getAlien_kefos(),4,1,0,3,0.07f);
        walkAnimation.setIsActive(true);

        particles.activateParticle(particles.getExplosion_blitzkugel());
        particles.activateParticle(particles.getExplosion_dead());

        setIsJumping(true);

        shape.dispose();
    }

    public void logic(float deltaTime) {
        if (player.getBody().getPosition().x - this.getBody().getPosition().x > -reactionDistance &&
                player.getBody().getPosition().x - this.getBody().getPosition().x < reactionDistance &&
                player.getBody().getPosition().y - this.getBody().getPosition().y > -5 &&
                player.getBody().getPosition().y - this.getBody().getPosition().y < 5) {

            this.stateTime += deltaTime;
            //LEFT OR RIGHT
            if (player.getBody().getPosition().x - this.getBody().getPosition().x < 0) {
                this.move(false, deltaTime);
            }

            if (player.getBody().getPosition().x - this.getBody().getPosition().x > 0) {
                this.move(true, deltaTime);
            }
            if (jumpTimer > 2) {
                this.jump();
                jumpTimer = 0;
            }
            bullet.setBulletShoot(true);
            randomInt = rand.nextInt(150) + 1;
            if (randomInt == 50) {
                game.getAssets().getSound_alien_fingus().play(1f);
            }

        }
    }


    public void update(float deltaTime){
        if (isDestroyed == false){
            if (walkAnimation.isActive() == true){
                currentFrame = walkAnimation.getAnimation().getKeyFrame(stateTime, true);
            }
            logic(deltaTime);
            bullet.shootBullet(this);
            jumpTimer += deltaTime;
        }
        bullet.update(deltaTime);


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
        particles.playEffect(this.getBody().getPosition().x,this.getBody().getPosition().y,particles.getExplosion_dead());
        game.getAssets().getSound_alien_fingus_dead().play(1f);
        this.body.setTransform(-1000,-1000f,0);
        player.setScore(player.getScore() + 300);
        super.dispose();
    }

    public void render(SpriteBatch batch){
        super.render(batch);
        if (removeFlag != true){
            batch.draw(currentFrame, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth() ,sprite.getHeight());
        }
        bullet.render(batch);
    }

    public Bullet getBullet(){
        return this.bullet;
    }

    public void setBullet(Bullet bullet){
        this.bullet = bullet;
    }
}
