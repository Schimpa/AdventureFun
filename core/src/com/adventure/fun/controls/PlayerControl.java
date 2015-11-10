package com.adventure.fun.controls;

import com.adventure.fun.audio.AudioController;
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

    public void movementControls(){
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

        if ((player.getBullet().getBody().getPosition().x - player.getBody().getPosition().x) >= 50
                || (player.getBullet().getBody().getPosition().x - player.getBody().getPosition().x) <= -50 ){
            player.getBullet().getBody().setTransform(-100, -100, 0);
            player.getBullet().getBody().setLinearVelocity(0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            AudioController.sound_shoot.play(0.1f);
            if (player.getBullet().getBody().getLinearVelocity().x == 0){
                if (player.getRegion().isFlipX() == false){
                    player.getBullet().getBody().setTransform(player.getBody().getPosition().x + player.getBullet().getSprite().getWidth() / 2, player.getBody().getPosition().y, 0);
                    player.getBullet().getBody().setLinearVelocity(player.getBullet().getSpeedX(), 0);
                } else if (player.getRegion().isFlipX() == true) {
                    player.getBullet().getBody().setTransform(player.getBody().getPosition().x - player.getBullet().getSprite().getWidth() / 2, player.getBody().getPosition().y, 0);
                    player.getBullet().getBody().setLinearVelocity(-player.getBullet().getSpeedX(), 0);
                }
                }

        }

    }

    @Override
    public boolean keyDown(int keycode) {
        if (player.getBody().getLinearVelocity().y <= 0.5f){
            player.setIsJumping(false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.getIsJumping() == false) {
            player.setIsJumping(true);
            AudioController.sound_jump.play(0.1f);
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
