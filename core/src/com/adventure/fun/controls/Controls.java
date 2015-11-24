package com.adventure.fun.controls;

import com.adventure.fun._main.WorldLoader;
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
        if (render.getPlayer().getBody().getLinearVelocity().x > 0 ||render.getPlayer().getBody().getLinearVelocity().x < 0 ){
            render.getPlayer().setStateTime(render.getPlayer().getStateTime() + Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            render.getPlayer().move(true,Gdx.graphics.getDeltaTime());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            render.getPlayer().move(false,Gdx.graphics.getDeltaTime());
        }
    }

    public void bulletShot(){
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            render.getPlayer().getBullet().shootBullet(render.getPlayer());
        }

    }

    public void playerJump(){
        if (render.getPlayer().getBody().getLinearVelocity().y <= 0.1f && render.getPlayer().getBody().getLinearVelocity().y >= -0.1f ){
            render.getPlayer().setIsJumping(false);
        }
        if (render.getPlayer().getIsJumping() == false){
            render.getPlayer().setIsJumping(true);
            AudioController.sound_jump.play(0.1f);
            render.getPlayer().getBody().setLinearVelocity(render.getPlayer().getBody().getLinearVelocity().x,10);
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
