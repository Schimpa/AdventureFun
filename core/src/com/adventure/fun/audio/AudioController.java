package com.adventure.fun.audio;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Dennis on 31.10.2015.
 */
public class AudioController {

    public AssetManager assetManager;
    private Sound sound_shoot_01;
    private Sound sound_shoot_02;

    private Sound sound_step_01;
    private Sound sound_step_02;

    private Sound sound_jump;

    private Sound sound_die;

    private Music music_ambient;

    public AudioController(AssetManager assetManager){
        this.assetManager = assetManager;
    }

    public void init(){
            sound_shoot_01 = assetManager.get("audio/shoot_01.ogg", Sound.class);
        if (assetManager.isLoaded("audio/shoot_02.ogg")){
            sound_shoot_02 = assetManager.get("audio/shoot_02.ogg", Sound.class);
        }
        if (assetManager.isLoaded("audio/step_stone_01.ogg")){
            sound_step_01 = assetManager.get("audio/step_stone_01.ogg", Sound.class);
        }
        if (assetManager.isLoaded("audio/step_stone_02.ogg")){
            sound_step_02 = assetManager.get("audio/step_stone_02.ogg", Sound.class);
        }
        if (assetManager.isLoaded("audio/jump.ogg")){
            sound_jump = assetManager.get("audio/jump.ogg", Sound.class);
        }
        if (assetManager.isLoaded("audio/papierstau.ogg")){
            sound_die = assetManager.get("audio/papierstau.ogg", Sound.class);
        }
        if (assetManager.isLoaded("audio/Ambient2.mp3")){
            music_ambient = assetManager.get("audio/Ambient2.mp3",Music.class);
        }

    }


    public Sound getSound_shoot_01() {
        return sound_shoot_01;
    }

    public void setSound_shoot_01(Sound sound_shoot_01) {
        this.sound_shoot_01 = sound_shoot_01;
    }

    public Sound getSound_shoot_02() {
        return sound_shoot_02;
    }

    public void setSound_shoot_02(Sound sound_shoot_02) {
        this.sound_shoot_02 = sound_shoot_02;
    }

    public Sound getSound_step_01() {
        return sound_step_01;
    }

    public void setSound_step_01(Sound sound_step_01) {
        this.sound_step_01 = sound_step_01;
    }

    public Sound getSound_step_02() {
        return sound_step_02;
    }

    public void setSound_step_02(Sound sound_step_02) {
        this.sound_step_02 = sound_step_02;
    }

    public Sound getSound_jump() {
        return sound_jump;
    }

    public void setSound_jump(Sound sound_jump) {
        this.sound_jump = sound_jump;
    }

    public Sound getSound_die() {
        return sound_die;
    }

    public void setSound_die(Sound sound_die) {
        this.sound_die = sound_die;
    }

    public Music getMusic_ambient() {
        return music_ambient;
    }

    public void setMusic_ambient(Music music_ambient) {
        this.music_ambient = music_ambient;
    }
}
