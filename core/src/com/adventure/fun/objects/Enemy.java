package com.adventure.fun.objects;

import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Dennis on 28.10.2015.
 */
public class Enemy extends LivingObject {

    private Player player;
    private Bullet bullet;

    public Enemy(float x, float y, float sizeX, float sizeY, World world,Player player){
        init(x,y,sizeX,sizeY,world);
        this.player = player;

    }

    public void init(float x,float y,float sizeX,float sizeY,World world){
        speed = new Vector2(12f,12f);
        maxSpeed = new Vector2(2,2);
        removeFlag = false;
        sound_reload = 0;
        sprite = new Sprite();

        lives = 2;

        bullet = new Bullet(-100,-100,0.8f,0.2f,world,Textures.bullet,"Bullet_Enemy");
        this.bullet.setSpeedX(10);


        sprite.setPosition(x, y);
        sprite.setSize(sizeX, sizeY);

        currentFrame = new TextureRegion(Textures.point);

        //PHYSIK KÖRPER DEFINITION
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX(), sprite.getY());

        //PHYSIK KÖRPER HÜLLE
        shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

        fixtureDef = new FixtureDef();
        fixtureDef.density = 0;
        fixtureDef.shape = shape;


        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData("Enemy");

        createAnimation(Textures.alien_move,3,1);

        shape.dispose();
    }

    public void logic(float deltaTime){
        if (player.getBody().getPosition().x - this.getBody().getPosition().x > -10 &&
                player.getBody().getPosition().x - this.getBody().getPosition().x < 10&&
                player.getBody().getPosition().y - this.getBody().getPosition().y > -5 &&
                player.getBody().getPosition().y - this.getBody().getPosition().y < 5 ){

            this.stateTime += deltaTime;
            //LEFT OR RIGHT
            if (player.getBody().getPosition().x - this.getBody().getPosition().x < 0){
                this.move(false,deltaTime);
            }

            if (player.getBody().getPosition().x - this.getBody().getPosition().x > 0){
                this.move(true,deltaTime);
            }
            this.bullet.shootBullet(this);
        }
    }


    public void update(float deltaTime){
        if (removeFlag != true){
            bullet.update();
            bullet.getBody().setLinearVelocity(bullet.getBody().getLinearVelocity().x, -0.1f);
            logic(deltaTime);
        }
    }

    public void render(SpriteBatch batch){
        super.render();
        if (removeFlag != true){
            batch.draw(currentFrame, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());
            batch.draw(bullet.region, bullet.body.getPosition().x - bullet.sprite.getWidth() / 2,
                    bullet.body.getPosition().y - bullet.sprite.getHeight() / 2, bullet.sprite.getWidth(), bullet.sprite.getHeight());

        }
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}
