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
            if (player.getRegion().isFlipX() == true){
                player.getRegion().flip(true, false);
            }

            if (player.getBody().getLinearVelocity().x <= player.getMaxSpeed().x) {
                player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x += player.getSpeed().x, player.getBody().getLinearVelocity().y);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (player.getRegion().isFlipX() == false){
                player.getRegion().flip(true, false);
            }
            if (player.getBody().getLinearVelocity().x >= -player.getMaxSpeed().x) {
                player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x -= player.getSpeed().x, player.getBody().getLinearVelocity().y);
            }
        }
    }

    public void bulletShot(){
        for(int i = 0;i < player.getBullets().size;i++){
            if ((player.getBullets().get(i).getBody().getPosition().x - player.getBody().getPosition().x) >= 50
                    || (player.getBullets().get(i).getBody().getPosition().x - player.getBody().getPosition().x) <= -50 ){
                player.getBullets().get(i).getBody().setTransform(0, 0, 0);
                player.getBullets().get(i).getBody().setLinearVelocity(0, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            for(int j = 0;j < player.getBullets().size;j++){
                if (player.getBullets().get(j).getBody().getLinearVelocity().x == 0){
                    if (player.getRegion().isFlipX() == false){
                        player.getBullets().get(j).getBody().setTransform(player.getBody().getPosition().x + player.getBullets().get(j).getSprite().getWidth() / 2, player.getBody().getPosition().y, 0);
                        player.getBullets().get(j).getBody().setLinearVelocity(player.getBullets().get(j).getSpeedX(), 0);
                    } else if (player.getRegion().isFlipX() == true) {
                        player.getBullets().get(j).getBody().setTransform(player.getBody().getPosition().x - player.getBullets().get(j).getSprite().getWidth() / 2, player.getBody().getPosition().y, 0);
                        player.getBullets().get(j).getBody().setLinearVelocity(-player.getBullets().get(j).getSpeedX(), 0);
                    }
                    break;
                }
            }
        }
        /*
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if (player.getBullets().get(0).body.getLinearVelocity().x == 0 &&
                    player.getBullets().get(1).body.getLinearVelocity().x == 0 &&
                    player.getBullets().get(2).body.getLinearVelocity().x == 0){

                if (player.region.isFlipX() == false){
                    player.getBullets().get(0).body.setTransform(player.getBody().getPosition().x + player.bullet.sprite.getWidth()/2, player.getBody().getPosition().y, 0);
                    player.getBullets().get(0).body.setLinearVelocity(20, 0);
                    player.getBullets().get(1).body.setTransform(player.getBody().getPosition().x + player.bullet.sprite.getWidth()/2, player.getBody().getPosition().y, 0);
                    player.getBullets().get(1).body.setLinearVelocity(20, 20);
                    player.getBullets().get(2).body.setTransform(player.getBody().getPosition().x + player.bullet.sprite.getWidth()/2, player.getBody().getPosition().y, 0);
                    player.getBullets().get(2).body.setLinearVelocity(20, -20);
                } else if (player.region.isFlipX() == true) {
                    player.getBullets().get(0).body.setTransform(player.getBody().getPosition().x - player.bullet.sprite.getWidth()/2,player.getBody().getPosition().y,0);
                    player.getBullets().get(0).body.setLinearVelocity(-20, 0);
                    player.getBullets().get(1).body.setTransform(player.getBody().getPosition().x - player.bullet.sprite.getWidth()/2,player.getBody().getPosition().y,0);
                    player.getBullets().get(1).body.setLinearVelocity(-20, 20);
                    player.getBullets().get(2).body.setTransform(player.getBody().getPosition().x - player.bullet.sprite.getWidth()/2,player.getBody().getPosition().y,0);
                    player.getBullets().get(2).body.setLinearVelocity(-20, -20);
                }
            }
        }*/
    }

    @Override
    public boolean keyDown(int keycode) {
        if (player.getBody().getLinearVelocity().y <= 0.5f){
            player.setIsJumping(false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.getIsJumping() == false) {
            player.setIsJumping(true);
            player.getBody().applyForceToCenter(0, 1500f, true);
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
