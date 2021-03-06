package com.adventure.fun.controls;

import com.adventure.fun._main.WorldLoader;
import com.adventure.fun.audio.AudioController;
import com.adventure.fun.objects.Bullet;
import com.adventure.fun.screens.GameScreen;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Dennis on 31.10.2015.
 */
public class Controls implements InputProcessor {

    WorldLoader worldLoader;
    public Controls(WorldLoader worldLoader){
        this.worldLoader = worldLoader;
    }

    public void movementControls(){
        if (this.worldLoader.getPlayer().getBody().getLinearVelocity().x > 0 || this.worldLoader.getPlayer().getBody().getLinearVelocity().x < 0 ){
            this.worldLoader.getPlayer().setStateTime(this.worldLoader.getPlayer().getStateTime() + Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.worldLoader.getPlayer().move(true, Gdx.graphics.getDeltaTime());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.worldLoader.getPlayer().move(false, Gdx.graphics.getDeltaTime());

        }

    }

    public void bulletShot(){
        if (Gdx.input.isKeyPressed(Input.Keys.A) && worldLoader.getGame().getMenuScreen().getGameScreen().getCamera().getBarPercent() > 20 ){
            this.worldLoader.getPlayer().getBullet().setBulletShoot(true);
        }
    }


    public void update(){
        this.movementControls();
    }

    @Override
    public boolean keyDown(int keycode) {
        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE))  && worldLoader.getPlayer().getBody().getLinearVelocity().y <= 0.1f &&
                worldLoader.getPlayer().getBody().getLinearVelocity().y >= -0.1f) {
            worldLoader.getPlayer().setIsJumping(true);
            worldLoader.getPlayer().setJumpTimer(0);
            worldLoader.getGame().getAssets().getSound_jump().play(1f);
        }

        bulletShot();

        System.out.println("pressed");
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE){
            worldLoader.getPlayer().setIsJumping(false);
        }
        System.out.println("released");
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
