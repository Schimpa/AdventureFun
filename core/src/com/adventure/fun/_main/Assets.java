package com.adventure.fun._main;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Assets {

    private AssetManager assetManager;

    private Sound sound_shoot_laser_01;
    private Sound sound_shoot_laser_02;
    private Sound sound_shoot_laser_03;
    private Sound sound_shoot_laserbeam_01;
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

    private Sound sound_player_hit_01;
    private Sound sound_player_hit_02;
    private Sound sound_player_hit_03;

    private Sound sound_click_in;
    private Sound sound_click_out;

    private TextureAtlas atlas;

    //PLAYER
    private TextureRegion player_move;

    //ENEMIES
    private TextureRegion alien_zombie;
    private TextureRegion alien_soldier;
    private TextureRegion alien_kugus;

    //BULLETS
    private TextureRegion bullet_blitzkugel;
    private TextureRegion bullet_laser;

    //ETC
    private TextureRegion point;
    private TextureRegion whiteColor;

    //BACKGROUND
    private Texture background;
    private Texture background_02;
    private Texture backgroundMenu;
    private Texture backgroundMars;



    public Assets(){
        assetManager = new AssetManager();
        load();
    }

    public void load() {

        assetManager.load("background/galaxy.png",Texture.class);
        assetManager.load("background/mars.jpg",Texture.class);
        assetManager.load("background/mars_02.jpg",Texture.class);
        assetManager.load("background/background_02.png",Texture.class);
        assetManager.load("texture_data/images.pack.atlas",TextureAtlas.class);

        assetManager.load("audio/shoot_laser_01.ogg",Sound.class);
        assetManager.load("audio/shoot_laser_02.ogg",Sound.class);
        assetManager.load("audio/shoot_laser_03.ogg",Sound.class);
        assetManager.load("audio/shoot_laserbeam_01.ogg",Sound.class);

        assetManager.load("audio/step_stone_01.ogg",Sound.class);
        assetManager.load("audio/step_stone_02.ogg",Sound.class);

        assetManager.load("audio/click_in.ogg",Sound.class);
        assetManager.load("audio/click_out.ogg",Sound.class);

        assetManager.load("audio/jump.ogg", Sound.class);
        assetManager.load("audio/papierstau.ogg",Sound.class);

        assetManager.load("audio/alien_zombie.ogg",Sound.class);
        assetManager.load("audio/alien_zombie_dead.ogg", Sound.class);

        assetManager.load("audio/alien_soldier_01.ogg",Sound.class);
        assetManager.load("audio/alien_soldier_02.ogg",Sound.class);
        assetManager.load("audio/alien_soldier_hit_01.ogg",Sound.class);
        assetManager.load("audio/alien_soldier_hit_02.ogg",Sound.class);
        assetManager.load("audio/alien_soldier_dead.ogg",Sound.class);
        assetManager.load("audio/player_hit_01.ogg",Sound.class);
        assetManager.load("audio/player_hit_02.ogg",Sound.class);
        assetManager.load("audio/player_hit_03.ogg",Sound.class);


    }

    public void done() {
        sound_shoot_laser_01 = assetManager.get("audio/shoot_laser_01.ogg", Sound.class);
        sound_shoot_laser_02 = assetManager.get("audio/shoot_laser_02.ogg", Sound.class);
        sound_shoot_laser_03 = assetManager.get("audio/shoot_laser_03.ogg", Sound.class);
        sound_shoot_laserbeam_01 = assetManager.get("audio/shoot_laserbeam_01.ogg", Sound.class);

        sound_step_01 = assetManager.get("audio/step_stone_01.ogg", Sound.class);
        sound_step_02 = assetManager.get("audio/step_stone_02.ogg", Sound.class);

        sound_click_in = assetManager.get("audio/click_in.ogg",Sound.class);
        sound_click_out = assetManager.get("audio/click_out.ogg",Sound.class);

        sound_jump = assetManager.get("audio/jump.ogg", Sound.class);
        sound_die = assetManager.get("audio/papierstau.ogg", Sound.class);

        sound_alien_zombie = assetManager.get("audio/alien_zombie.ogg", Sound.class);
        sound_alien_zombie_dead = assetManager.get("audio/alien_zombie_dead.ogg", Sound.class);

        sound_alien_soldier_01 = assetManager.get("audio/alien_soldier_01.ogg", Sound.class);
        sound_alien_soldier_02 = assetManager.get("audio/alien_soldier_02.ogg", Sound.class);
        sound_alien_soldier_hit_01 = assetManager.get("audio/alien_soldier_hit_01.ogg", Sound.class);
        sound_alien_soldier_hit_02 = assetManager.get("audio/alien_soldier_hit_02.ogg", Sound.class);
        sound_alien_soldier_dead = assetManager.get("audio/alien_soldier_dead.ogg", Sound.class);

        sound_player_hit_01 = assetManager.get("audio/player_hit_01.ogg", Sound.class);
        sound_player_hit_02 = assetManager.get("audio/player_hit_02.ogg", Sound.class);
        sound_player_hit_03 = assetManager.get("audio/player_hit_02.ogg", Sound.class);

        atlas = assetManager.get("texture_data/images.pack.atlas",TextureAtlas.class);

        //BACKGROUND
        background = assetManager.get("background/galaxy.png",Texture.class);
        backgroundMenu = assetManager.get("background/mars.jpg",Texture.class);
        backgroundMars = assetManager.get("background/mars_02.jpg",Texture.class);
        background_02 = assetManager.get("background/background_02.png",Texture.class);

        point = atlas.findRegion("point");
        whiteColor = atlas.findRegion("whiteColor");

        player_move = atlas.findRegion("player_move");
        bullet_laser = atlas.findRegion("bullet_laser");
        alien_zombie = atlas.findRegion("alien_zombie");
        alien_soldier = atlas.findRegion("alien_soldier");
        alien_kugus = atlas.findRegion("alien_kugus");
        bullet_blitzkugel = atlas.findRegion("bullet_blitzkugel");

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

    public Sound getSound_shoot_laser_01() {
        return sound_shoot_laser_01;
    }

    public void setSound_shoot_laser_01(Sound sound_shoot_laser_01) {
        this.sound_shoot_laser_01 = sound_shoot_laser_01;
    }

    public Sound getSound_shoot_laser_02() {
        return sound_shoot_laser_02;
    }

    public void setSound_shoot_laser_02(Sound sound_shoot_laser_02) {
        this.sound_shoot_laser_02 = sound_shoot_laser_02;
    }

    public Sound getSound_shoot_laser_03() {
        return sound_shoot_laser_03;
    }

    public void setSound_shoot_laser_03(Sound sound_shoot_laser_03) {
        this.sound_shoot_laser_03 = sound_shoot_laser_03;
    }

    public Sound getSound_shoot_laserbeam_01() {
        return sound_shoot_laserbeam_01;
    }

    public void setSound_shoot_laserbeam_01(Sound sound_shoot_laserbeam_01) {
        this.sound_shoot_laserbeam_01 = sound_shoot_laserbeam_01;
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

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void setAtlas(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    public TextureRegion getPlayer_move() {
        return player_move;
    }

    public void setPlayer_move(TextureRegion player_move) {
        this.player_move = player_move;
    }

    public TextureRegion getAlien_zombie() {
        return alien_zombie;
    }

    public void setAlien_move(TextureRegion alien_move) {
        this.alien_zombie = alien_zombie;
    }

    public TextureRegion getBullet_Laser() {
        return bullet_laser;
    }

    public void setBullet_Laser(TextureRegion bullet) {
        this.bullet_laser = bullet;
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

    public TextureRegion getAlien_soldier() {
        return alien_soldier;
    }

    public void setAlien_soldier(TextureRegion alien_soldier) {
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

    public Sound getSound_click_in() {
        return sound_click_in;
    }

    public void setSound_click_in(Sound sound_click_in) {
        this.sound_click_in = sound_click_in;
    }

    public Sound getSound_click_out() {
        return sound_click_out;
    }

    public void setSound_click_out(Sound sound_click_out) {
        this.sound_click_out = sound_click_out;
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

    public Sound getSound_player_hit_01() {
        return sound_player_hit_01;
    }

    public void setSound_player_hit_01(Sound sound_player_hit_01) {
        this.sound_player_hit_01 = sound_player_hit_01;
    }

    public Sound getSound_player_hit_02() {
        return sound_player_hit_02;
    }

    public void setSound_player_hit_02(Sound sound_player_hit_02) {
        this.sound_player_hit_02 = sound_player_hit_02;
    }

    public Sound getSound_player_hit_03() {
        return sound_player_hit_03;
    }

    public void setSound_player_hit_03(Sound sound_player_hit_03) {
        this.sound_player_hit_03 = sound_player_hit_03;
    }

    public TextureRegion getBullet_blitzkugel() {
        return bullet_blitzkugel;
    }

    public void setBullet_blitzkugel(TextureRegion bullet_blitzkugel) {
        this.bullet_blitzkugel = bullet_blitzkugel;
    }

    public void setAlien_zombie(TextureRegion alien_zombie) {
        this.alien_zombie = alien_zombie;
    }

    public TextureRegion getWhiteColor() {
        return whiteColor;
    }

    public void setWhiteColor(TextureRegion whiteColor) {
        this.whiteColor = whiteColor;
    }

    public TextureRegion getAlien_kugus() {
        return alien_kugus;
    }

    public void setAlien_kugus(TextureRegion alien_kugus) {
        this.alien_kugus = alien_kugus;
    }

    public TextureRegion getBullet_laser() {
        return bullet_laser;
    }

    public void setBullet_laser(TextureRegion bullet_laser) {
        this.bullet_laser = bullet_laser;
    }

    public Texture getBackground_02() {
        return background_02;
    }

    public void setBackground_02(Texture background_02) {
        this.background_02 = background_02;
    }


}