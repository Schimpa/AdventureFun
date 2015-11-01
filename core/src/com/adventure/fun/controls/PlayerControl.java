package com.adventure.fun.controls;

import com.adventure.fun.objects.Bullet;
import com.adventure.fun.objects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Dennis on 31.10.2015.
 */
public class PlayerControl implements InputProcessor {
    Player player;

    public PlayerControl(Player player){
        this.player = player;

    }



    public void controls(){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (player.region.isFlipX() == true){
                player.region.flip(true,false);
                player.bullet.region.flip(true,false);
            }
            if (player.body.getLinearVelocity().x <= player.SPEED_X_MAX) {
                player.body.setLinearVelocity(player.body.getLinearVelocity().x += player.ACCELERATION_X, player.body.getLinearVelocity().y);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (player.region.isFlipX() == false){
                player.region.flip(true,false);
                player.bullet.region.flip(true,false);
            }
            if (player.body.getLinearVelocity().x >= -player.SPEED_X_MAX) {
                player.body.setLinearVelocity(player.body.getLinearVelocity().x -= player.ACCELERATION_X, player.body.getLinearVelocity().y);
            }
        }
    }

    public void bulletShot(){
        for(int i = 0;i < player.bullets.size;i++){
            if ((player.bullets.get(i).body.getPosition().x - player.body.getPosition().x) >= 50 || (player.bullets.get(i).body.getPosition().x - player.body.getPosition().x) <= -50 ){
                player.bullets.get(i).body.setTransform(0, 0, 0);
                player.bullets.get(i).body.setLinearVelocity(0, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            for(int j = 0;j < player.bullets.size;j++){
                if (player.bullets.get(j).body.getLinearVelocity().x == 0){
                    if (player.region.isFlipX() == false){
                        player.bullets.get(j).body.setTransform(player.body.getPosition().x + player.bullet.sprite.getWidth()/2, player.body.getPosition().y, 0);
                        player.bullets.get(j).body.setLinearVelocity(20, 0);
                    } else if (player.region.isFlipX() == true) {
                        player.bullets.get(j).body.setTransform(player.body.getPosition().x - player.bullet.sprite.getWidth()/2,player.body.getPosition().y,0);
                        player.bullets.get(j).body.setLinearVelocity(-20, 0);
                    }
                    break;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if (player.bullets.get(0).body.getLinearVelocity().x == 0 &&
                    player.bullets.get(1).body.getLinearVelocity().x == 0 &&
                    player.bullets.get(2).body.getLinearVelocity().x == 0){

                if (player.region.isFlipX() == false){
                    player.bullets.get(0).body.setTransform(player.body.getPosition().x + player.bullet.sprite.getWidth()/2, player.body.getPosition().y, 0);
                    player.bullets.get(0).body.setLinearVelocity(20, 0);
                    player.bullets.get(1).body.setTransform(player.body.getPosition().x + player.bullet.sprite.getWidth()/2, player.body.getPosition().y, 0);
                    player.bullets.get(1).body.setLinearVelocity(20, 20);
                    player.bullets.get(2).body.setTransform(player.body.getPosition().x + player.bullet.sprite.getWidth()/2, player.body.getPosition().y, 0);
                    player.bullets.get(2).body.setLinearVelocity(20, -20);
                } else if (player.region.isFlipX() == true) {
                    player.bullets.get(0).body.setTransform(player.body.getPosition().x - player.bullet.sprite.getWidth()/2,player.body.getPosition().y,0);
                    player.bullets.get(0).body.setLinearVelocity(-20, 0);
                    player.bullets.get(1).body.setTransform(player.body.getPosition().x - player.bullet.sprite.getWidth()/2,player.body.getPosition().y,0);
                    player.bullets.get(1).body.setLinearVelocity(-20, 20);
                    player.bullets.get(2).body.setTransform(player.body.getPosition().x - player.bullet.sprite.getWidth()/2,player.body.getPosition().y,0);
                    player.bullets.get(2).body.setLinearVelocity(-20, -20);
                }
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (player.body.getLinearVelocity().y <= 0.1f){
            player.isJumping = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.isJumping == false) {
            player.isJumping = true;
            player.body.applyForceToCenter(0, 2000f, true);
        }
        bulletShot();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
