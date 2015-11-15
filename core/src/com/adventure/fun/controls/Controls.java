package com.adventure.fun.controls;

import com.adventure.fun.WorldLoader;
import com.adventure.fun.audio.AudioController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Dennis on 31.10.2015.
 */
public class Controls implements InputProcessor {

    WorldLoader render;
    public Controls(WorldLoader render){
        this.render = render;
    }


    public void movementControls(){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (render.getPlayer().getRegion().isFlipX() == true){
                render.getPlayer().getRegion().flip(true, false);
            }

            if (render.getPlayer().getBody().getLinearVelocity().x <= render.getPlayer().getMaxSpeed().x) {
                render.getPlayer().getBody().setLinearVelocity(render.getPlayer().getBody().getLinearVelocity().x += render.getPlayer().getSpeed().x, render.getPlayer().getBody().getLinearVelocity().y);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (render.getPlayer().getRegion().isFlipX() == false){
                render.getPlayer().getRegion().flip(true, false);
            }
            if (render.getPlayer().getBody().getLinearVelocity().x >= -render.getPlayer().getMaxSpeed().x) {
                render.getPlayer().getBody().setLinearVelocity(render.getPlayer().getBody().getLinearVelocity().x -= render.getPlayer().getSpeed().x, render.getPlayer().getBody().getLinearVelocity().y);
            }
        }
    }

    public void bulletShot(){

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            if (render.getPlayer().getBullet().getBody().getLinearVelocity().x == 0){
                AudioController.sound_shoot.play(0.1f);
                if (render.getPlayer().getRegion().isFlipX() == false){
                    render.getPlayer().getBullet().getBody().setTransform(render.getPlayer().getBody().getPosition().x + render.getPlayer().getBullet().getSprite().getWidth() / 2, render.getPlayer().getBody().getPosition().y, 0);
                    render.getPlayer().getBullet().getBody().setLinearVelocity(render.getPlayer().getBullet().getSpeedX(), 0);
                } else if (render.getPlayer().getRegion().isFlipX() == true) {
                    render.getPlayer().getBullet().getBody().setTransform(render.getPlayer().getBody().getPosition().x - render.getPlayer().getBullet().getSprite().getWidth() / 2, render.getPlayer().getBody().getPosition().y, 0);
                    render.getPlayer().getBullet().getBody().setLinearVelocity(-render.getPlayer().getBullet().getSpeedX(), 0);
                }
            }
            if ((render.getPlayer().getBullet().getBody().getPosition().x - render.getPlayer().getBody().getPosition().x) >= 50
                    || (render.getPlayer().getBullet().getBody().getPosition().x - render.getPlayer().getBody().getPosition().x) <= -50 ){
                render.getPlayer().getBullet().getBody().setTransform(-1000, -1000, 0);
                render.getPlayer().getBullet().getBody().setLinearVelocity(0, 0);
            }

        }

    }

    public void playerJump(){
        if (render.getPlayer().getBody().getLinearVelocity().y <= 0.1f && render.getPlayer().getBody().getLinearVelocity().y >= -0.1f ){
            render.getPlayer().setIsJumping(false);
        }
        if (render.getPlayer().getIsJumping() == false){
            render.getPlayer().setIsJumping(true);
            AudioController.sound_jump.play(0.1f);
            render.getPlayer().getBody().setLinearVelocity(render.getPlayer().getBody().getLinearVelocity().x,20);
        }
    }

    public void update(){
        this.movementControls();
    }

    @Override
    public boolean keyDown(int keycode) {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            playerJump();
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
