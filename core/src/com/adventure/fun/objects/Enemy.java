package com.adventure.fun.objects;

import com.adventure.fun.texture.Textures;
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
public class Enemy extends Object {

    private Vector2 speed;
    private Vector2 maxSpeed;
    private Player player;

    public Enemy(float x, float y, float sizeX, float sizeY, World world,Player player){
        init(x,y,sizeX,sizeY,world);
        this.player = player;

    }

    public void init(float x,float y,float sizeX,float sizeY,World world){
        speed = new Vector2(0.3f,0.3f);
        maxSpeed = new Vector2(1,1);

        sprite = new Sprite();
        sprite.setPosition(x, y);
        sprite.setSize(sizeX, sizeY);

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

        region = new TextureRegion(Textures.player);


        shape.dispose();
    }

    public void logic(){
        if (player.getBody().getPosition().x - this.getBody().getPosition().x < 0){
            if (this.body.getLinearVelocity().x <= this.maxSpeed.x && this.body.getLinearVelocity().x >= -this.maxSpeed.x){
                this.body.setLinearVelocity(this.body.getLinearVelocity().x - this.speed.x,this.body.getLinearVelocity().y);
            }
        }
        if (player.getBody().getPosition().x - this.getBody().getPosition().x > 0){
            if (this.body.getLinearVelocity().x <= this.maxSpeed.x && this.body.getLinearVelocity().x >= -this.maxSpeed.x){
                this.body.setLinearVelocity(this.body.getLinearVelocity().x + this.speed.x,this.body.getLinearVelocity().y);
            }
        }

    }


    public void update(float deltaTime){
        logic();

    }



    public void render(SpriteBatch batch){
        batch.draw(region, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());
    }
}
