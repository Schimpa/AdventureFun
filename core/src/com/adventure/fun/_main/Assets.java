package com.adventure.fun._main;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Assets {

    private AssetManager assetManager;
    private Sound sound_shoot_01;
    private Sound sound_shoot_02;
    private Sound sound_step_01;
    private Sound sound_step_02;
    private Sound sound_jump;
    private Sound sound_die;

    //ALIEN ZOMBIE
    private Sound sound_alien_zombie;
    private Sound sound_alien_zombie_dead;

    //ALIEN SOLDIER
    private Sound sound_alien_soldier_01;
    private Sound sound_alien_soldier_02;
    private Sound sound_alien_soldier_hit_01;
    private Sound sound_alien_soldier_hit_02;
    private Sound sound_alien_soldier_dead;


    private TextureAtlas atlas;

    //PLAYER
    private Texture player_move;
    private Texture alien_move;
    private Texture alien_soldier;

    //BULLET
    private TextureRegion bullet;

    //PICKUPS
    private TextureRegion point;

    //BACKGROUND
    private Texture background;
    private Texture backgroundMenu;
    private Texture backgroundMars;



    private Music music_ambient;


    public Assets(){
        assetManager = new AssetManager();
        load();
    }

    public void load() {
        assetManager.load("texture_data/images.pack.atlas",TextureAtlas.class);
        assetManager.load("texture_data/player_move.png",Texture.class);
        assetManager.load("texture_data/alien_move.png",Texture.class);
        assetManager.load("texture_data/alien_soldier.png",Texture.class);

        assetManager.load("background/galaxy.png",Texture.class);
        assetManager.load("background/mars.jpg",Texture.class);
        assetManager.load("background/mars_02.jpg",Texture.class);

        assetManager.load("audio/shoot_01.ogg",Sound.class);
        assetManager.load("audio/shoot_02.ogg",Sound.class);
        assetManager.load("audio/step_stone_01.ogg",Sound.class);
        assetManager.load("audio/step_stone_02.ogg",Sound.class);
        assetManager.load("audio/jump.ogg", Sound.class);
        assetManager.load("audio/papierstau.ogg",Sound.class);
        assetManager.load("audio/Ambient2.mp3", Music.class);

        assetManager.load("audio/alien_zombie.ogg",Sound.class);
        assetManager.load("audio/alien_zombie_dead.ogg", Sound.class);

        assetManager.load("audio/alien_soldier_01.ogg",Sound.class);
        assetManager.load("audio/alien_soldier_02.ogg",Sound.class);
        assetManager.load("audio/alien_soldier_hit_01.ogg",Sound.class);
        assetManager.load("audio/alien_soldier_hit_02.ogg",Sound.class);
        assetManager.load("audio/alien_soldier_dead.ogg",Sound.class);


    }

    public void done() {
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

        sound_alien_zombie = assetManager.get("audio/alien_zombie.ogg", Sound.class);
        sound_alien_zombie_dead = assetManager.get("audio/alien_zombie_dead.ogg", Sound.class);

        sound_alien_soldier_01 = assetManager.get("audio/alien_soldier_01.ogg", Sound.class);
        sound_alien_soldier_02 = assetManager.get("audio/alien_soldier_02.ogg", Sound.class);
        sound_alien_soldier_hit_01 = assetManager.get("audio/alien_soldier_hit_01.ogg", Sound.class);
        sound_alien_soldier_hit_02 = assetManager.get("audio/alien_soldier_hit_02.ogg", Sound.class);
        sound_alien_soldier_dead = assetManager.get("audio/alien_soldier_dead.ogg", Sound.class);

        atlas = assetManager.get("texture_data/images.pack.atlas",TextureAtlas.class);

        //PLAYER
        player_move = assetManager.get("texture_data/player_move.png",Texture.class);
        alien_move = assetManager.get("texture_data/alien_move.png",Texture.class);
        alien_soldier = assetManager.get("texture_data/alien_soldier.png",Texture.class);

        //BACKGROUND
        background = assetManager.get("background/galaxy.png",Texture.class);
        backgroundMenu = assetManager.get("background/mars.jpg",Texture.class);
        backgroundMars = assetManager.get("background/mars_02.jpg",Texture.class);

        bullet = atlas.findRegion("bullet");
        point = atlas.findRegion("point");
    }

    public void dispose() {
        assetManager.dispose();
    }

    public Sound getSound_alien_zombie() {
        return sound_alien_zombie;
    }

    public void setSound_alien_zombie(Sound sound_alien_zombie) {
        this.sound_alien_zombie = sound_alien_zombie;
    }

    public Sound getSound_alien_zombie_dead() {
        return sound_alien_zombie_dead;
    }

    public void setSound_alien_zombie_dead(Sound sound_alien_zombie_dead) {
        this.sound_alien_zombie_dead = sound_alien_zombie_dead;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
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

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void setAtlas(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    public Texture getPlayer_move() {
        return player_move;
    }

    public void setPlayer_move(Texture player_move) {
        this.player_move = player_move;
    }

    public Texture getAlien_move() {
        return alien_move;
    }

    public void setAlien_move(Texture alien_move) {
        this.alien_move = alien_move;
    }

    public TextureRegion getBullet() {
        return bullet;
    }

    public void setBullet(TextureRegion bullet) {
        this.bullet = bullet;
    }

    public TextureRegion getPoint() {
        return point;
    }

    public void setPoint(TextureRegion point) {
        this.point = point;
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public Texture getBackgroundMenu() {
        return backgroundMenu;
    }

    public void setBackgroundMenu(Texture backgroundMenu) {
        this.backgroundMenu = backgroundMenu;
    }

    public Texture getAlien_soldier() {
        return alien_soldier;
    }

    public void setAlien_soldier(Texture alien_soldier) {
        this.alien_soldier = alien_soldier;
    }

    public Sound getSound_alien_soldier_01() {
        return sound_alien_soldier_01;
    }

    public void setSound_alien_soldier_01(Sound sound_alien_soldier_01) {
        this.sound_alien_soldier_01 = sound_alien_soldier_01;
    }

    public Sound getSound_alien_soldier_02() {
        return sound_alien_soldier_02;
    }

    public void setSound_alien_soldier_02(Sound sound_alien_soldier_02) {
        this.sound_alien_soldier_02 = sound_alien_soldier_02;
    }

    public Sound getSound_alien_soldier_hit_01() {
        return sound_alien_soldier_hit_01;
    }

    public void setSound_alien_soldier_hit_01(Sound sound_alien_soldier_hit_01) {
        this.sound_alien_soldier_hit_01 = sound_alien_soldier_hit_01;
    }

    public Sound getSound_alien_soldier_hit_02() {
        return sound_alien_soldier_hit_02;
    }

    public void setSound_alien_soldier_hit_02(Sound sound_alien_soldier_hit_02) {
        this.sound_alien_soldier_hit_02 = sound_alien_soldier_hit_02;
    }

    public Sound getSound_alien_soldier_dead() {
        return sound_alien_soldier_dead;
    }

    public void setSound_alien_soldier_dead(Sound sound_alien_soldier_dead) {
        this.sound_alien_soldier_dead = sound_alien_soldier_dead;
    }

    public Texture getBackgroundMars() {
        return backgroundMars;
    }

    public void setBackgroundMars(Texture backgroundMars) {
        this.backgroundMars = backgroundMars;
    }
}
