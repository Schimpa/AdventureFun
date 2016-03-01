package com.adventure.fun._main;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Assets {

    private AssetManager assetManager;

    private Sound sound_shoot_laser_01;
    private Sound sound_shoot_laser_02;
    private Sound sound_shoot_laser_03;
    private Sound sound_shoot_laser_04;
    private Sound sound_shoot_laserbeam_01;

    private Sound sound_shoot_alien_01;


    private Sound sound_step_01;
    private Sound sound_step_02;
    private Sound sound_jump;

    //ALIEN FINGUS
    private Sound sound_alien_fingus;
    private Sound sound_alien_fingus_hit_01;
    private Sound sound_alien_fingus_dead;

    //ALIEN TAKEL
    private Sound sound_alien_takel_01;
    private Sound sound_alien_takel_02;
    private Sound sound_alien_takel_hit_01;
    private Sound sound_alien_takel_hit_02;
    private Sound sound_alien_takel_dead;

    //ALIEN KUGUS
    private Sound sound_alien_kugus_01;
    private Sound sound_alien_kugus_02;

    private Sound sound_player_hit_01;
    private Sound sound_player_hit_02;
    private Sound sound_player_hit_03;

    private Sound sound_click_in;
    private Sound sound_click_out;

    private TextureAtlas atlas;

    //PLAYER
    private TextureRegion player_move;

    //ENEMIES
    private TextureRegion alien_fingus;
    private TextureRegion alien_takel;
    private TextureRegion alien_kugus;
    private TextureRegion alien_bigmama;

    //BULLETS
    private TextureRegion bullet_blitzkugel;

    private TextureRegion bullet_blitz;
    private TextureRegion bullet_bigmama;

    private TextureRegion bullet_green;
    private TextureRegion bullet_red;
    private TextureRegion bullet_yellow;
    private TextureRegion bullet_blue;

    //ITEMS
    private TextureRegion item_Score_100;
    private TextureRegion item_Score_200;
    private TextureRegion item_Score_500;
    private TextureRegion item_Score_1000;
    private TextureRegion item_Health;

    private TextureRegion item_weapon_green;
    private TextureRegion item_weapon_blue;
    private TextureRegion item_weapon_red;
    private TextureRegion item_weapon_yellow;


    //ICONS
    private TextureRegion icon_Health;


    //ETC...
    private TextureRegion whiteColor;

    //BACKGROUND
    private Texture background_01;
    private Texture background_02;
    private Texture background_03;
    private Texture background_05;
    private Texture backgroundMenu_01;
    private Texture backgroundMenu_02;

    //MIDDLEGROUND
    private Texture middleground_01;
    private Texture middleground_05;

    public Assets(){
        assetManager = new AssetManager();
        load();
    }

    public void load() {

        assetManager.load("background/background_01.png",Texture.class);
        assetManager.load("background/background_02.png",Texture.class);
        assetManager.load("background/background_03.png",Texture.class);
        assetManager.load("background/background_05.png",Texture.class);
        assetManager.load("background/backgroundMenu_01.jpg",Texture.class);
        assetManager.load("background/backgroundMenu_02.jpg",Texture.class);


        assetManager.load("middleground/middleground_01.png",Texture.class);
        assetManager.load("middleground/middleground_05.png",Texture.class);

        assetManager.load("texture_data/images.pack.atlas",TextureAtlas.class);

        assetManager.load("audio/shoot_laser_01.ogg",Sound.class);
        assetManager.load("audio/shoot_laser_02.ogg",Sound.class);
        assetManager.load("audio/shoot_laser_03.ogg",Sound.class);
        assetManager.load("audio/shoot_laser_04.ogg",Sound.class);
        assetManager.load("audio/shoot_alien_01.ogg",Sound.class);
        assetManager.load("audio/shoot_laserbeam_01.ogg",Sound.class);

        assetManager.load("audio/step_stone_01.ogg",Sound.class);
        assetManager.load("audio/step_stone_02.ogg",Sound.class);

        assetManager.load("audio/click_in.ogg",Sound.class);
        assetManager.load("audio/click_out.ogg",Sound.class);

        assetManager.load("audio/jump.ogg", Sound.class);

        assetManager.load("audio/alien_fingus.ogg",Sound.class);
        assetManager.load("audio/alien_fingus_dead.ogg", Sound.class);
        assetManager.load("audio/alien_fingus_hit_01.ogg", Sound.class);

        assetManager.load("audio/alien_takel_01.ogg",Sound.class);
        assetManager.load("audio/alien_takel_02.ogg",Sound.class);
        assetManager.load("audio/alien_takel_hit_01.ogg",Sound.class);
        assetManager.load("audio/alien_takel_hit_02.ogg",Sound.class);
        assetManager.load("audio/alien_takel_dead.ogg",Sound.class);

        assetManager.load("audio/alien_kugus_01.ogg",Sound.class);
        assetManager.load("audio/alien_kugus_02.ogg",Sound.class);


        assetManager.load("audio/player_hit_01.ogg",Sound.class);
        assetManager.load("audio/player_hit_02.ogg",Sound.class);
        assetManager.load("audio/player_hit_03.ogg",Sound.class);


    }

    public void done() {
        sound_shoot_laser_01 = assetManager.get("audio/shoot_laser_01.ogg", Sound.class);
        sound_shoot_laser_02 = assetManager.get("audio/shoot_laser_02.ogg", Sound.class);
        sound_shoot_laser_03 = assetManager.get("audio/shoot_laser_03.ogg", Sound.class);
        sound_shoot_laser_04 = assetManager.get("audio/shoot_laser_04.ogg", Sound.class);
        sound_shoot_alien_01 = assetManager.get("audio/shoot_alien_01.ogg", Sound.class);
        sound_shoot_laserbeam_01 = assetManager.get("audio/shoot_laserbeam_01.ogg", Sound.class);

        sound_step_01 = assetManager.get("audio/step_stone_01.ogg", Sound.class);
        sound_step_02 = assetManager.get("audio/step_stone_02.ogg", Sound.class);

        sound_click_in = assetManager.get("audio/click_in.ogg",Sound.class);
        sound_click_out = assetManager.get("audio/click_out.ogg",Sound.class);

        sound_jump = assetManager.get("audio/jump.ogg", Sound.class);

        sound_alien_fingus = assetManager.get("audio/alien_fingus.ogg", Sound.class);
        sound_alien_fingus_dead = assetManager.get("audio/alien_fingus_dead.ogg", Sound.class);
        sound_alien_fingus_hit_01 = assetManager.get("audio/alien_fingus_hit_01.ogg", Sound.class);

        sound_alien_takel_01 = assetManager.get("audio/alien_takel_01.ogg", Sound.class);
        sound_alien_takel_02 = assetManager.get("audio/alien_takel_02.ogg", Sound.class);
        sound_alien_takel_hit_01 = assetManager.get("audio/alien_takel_hit_01.ogg", Sound.class);
        sound_alien_takel_hit_02 = assetManager.get("audio/alien_takel_hit_02.ogg", Sound.class);
        sound_alien_takel_dead = assetManager.get("audio/alien_takel_dead.ogg", Sound.class);

        sound_alien_kugus_01 = assetManager.get("audio/alien_kugus_01.ogg",Sound.class);
        sound_alien_kugus_02 = assetManager.get("audio/alien_kugus_02.ogg",Sound.class);

        sound_player_hit_01 = assetManager.get("audio/player_hit_01.ogg", Sound.class);
        sound_player_hit_02 = assetManager.get("audio/player_hit_02.ogg", Sound.class);
        sound_player_hit_03 = assetManager.get("audio/player_hit_02.ogg", Sound.class);

        atlas = assetManager.get("texture_data/images.pack.atlas",TextureAtlas.class);

        //BACKGROUND
        background_01 = assetManager.get("background/background_01.png",Texture.class);
        background_02 = assetManager.get("background/background_02.png",Texture.class);
        background_03 = assetManager.get("background/background_03.png", Texture.class);
        background_05 = assetManager.get("background/background_05.png", Texture.class);
        backgroundMenu_01= assetManager.get("background/backgroundMenu_01.jpg", Texture.class);
        backgroundMenu_02= assetManager.get("background/backgroundMenu_02.jpg", Texture.class);

        //MIDDLEGROUND
        middleground_01 = assetManager.get("middleground/middleground_01.png",Texture.class);
        middleground_05 = assetManager.get("middleground/middleground_05.png", Texture.class);

        //ITEMS
        item_Score_100 = atlas.findRegion("score_one");
        item_Score_200 = atlas.findRegion("score_two");
        item_Score_500 = atlas.findRegion("score_three");
        item_Score_1000 = atlas.findRegion("score_four");
        item_Health = atlas.findRegion("battery");

        item_weapon_green = atlas.findRegion("weapon_green");
        item_weapon_red = atlas.findRegion("weapon_red");
        item_weapon_blue = atlas.findRegion("weapon_blue");
        item_weapon_yellow = atlas.findRegion("weapon_yellow");

        //ICONS
        icon_Health = atlas.findRegion("icon_Health");

        whiteColor = atlas.findRegion("whiteColor");

        player_move = atlas.findRegion("player_move");
        alien_fingus = atlas.findRegion("alien_fingus");
        alien_takel = atlas.findRegion("alien_takel");
        alien_kugus = atlas.findRegion("alien_kugus");
        alien_bigmama = atlas.findRegion("alien_bigmama");

        bullet_blitzkugel = atlas.findRegion("bullet_blitzkugel");
        bullet_blitz = atlas.findRegion("bullet_blitz");
        bullet_bigmama = atlas.findRegion("bullet_bigmama");

        bullet_green = atlas.findRegion("bullet_green");
        bullet_red = atlas.findRegion("bullet_red");
        bullet_yellow = atlas.findRegion("bullet_yellow");
        bullet_blue = atlas.findRegion("bullet_blue");

    }

    public void dispose() {
        assetManager.dispose();
    }


    public TextureRegion getAlien_bigmama() {
        return alien_bigmama;
    }


    public Texture getMiddleground_01() {
        return middleground_01;
    }

    public void setMiddleground_01(Texture middleground_01) {
        this.middleground_01 = middleground_01;
    }

    public void setAlien_bigmama(TextureRegion alien_bigmama) {
        this.alien_bigmama = alien_bigmama;
    }

    public Sound getSound_alien_fingus() {
        return sound_alien_fingus;
    }

    public void setSound_alien_fingus(Sound sound_alien_fingus) {
        this.sound_alien_fingus = sound_alien_fingus;
    }

    public Sound getSound_alien_fingus_dead() {
        return sound_alien_fingus_dead;
    }

    public void setSound_alien_fingus_dead(Sound sound_alien_fingus_dead) {
        this.sound_alien_fingus_dead = sound_alien_fingus_dead;
    }

    public Sound getSound_shoot_laser_04() {
        return sound_shoot_laser_04;
    }

    public void setSound_shoot_laser_04(Sound sound_shoot_laser_04) {
        this.sound_shoot_laser_04 = sound_shoot_laser_04;
    }

    public Sound getSound_shoot_alien_01() {
        return sound_shoot_alien_01;
    }

    public void setSound_shoot_alien_01(Sound sound_shoot_alien_01) {
        this.sound_shoot_alien_01 = sound_shoot_alien_01;
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

    public TextureRegion getItem_weapon_green() {
        return item_weapon_green;
    }

    public void setItem_weapon_green(TextureRegion item_weapon_green) {
        this.item_weapon_green = item_weapon_green;
    }

    public TextureRegion getBullet_blitz() {
        return bullet_blitz;
    }

    public void setBullet_blitz(TextureRegion bullet_blitz) {
        this.bullet_blitz = bullet_blitz;
    }

    public Sound getSound_jump() {
        return sound_jump;
    }

    public void setSound_jump(Sound sound_jump) {
        this.sound_jump = sound_jump;
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

    public TextureRegion getAlien_fingus() {
        return alien_fingus;
    }

    public void setAlien_move(TextureRegion alien_move) {
        this.alien_fingus = alien_fingus;
    }

    public TextureRegion getBullet_Red() {
        return bullet_red;
    }

    public void setBullet_Red(TextureRegion bullet) {
        this.bullet_red = bullet;
    }

    public Texture getBackground_01() {
        return background_01;
    }

    public void setBackground_01(Texture background_01) {
        this.background_01 = background_01;
    }

    public TextureRegion getAlien_takel() {
        return alien_takel;
    }

    public void setAlien_takel(TextureRegion alien_takel) {
        this.alien_takel = alien_takel;
    }

    public Sound getSound_alien_takel_01() {
        return sound_alien_takel_01;
    }

    public void setSound_alien_takel_01(Sound sound_alien_takel_01) {
        this.sound_alien_takel_01 = sound_alien_takel_01;
    }

    public Sound getSound_alien_takel_02() {
        return sound_alien_takel_02;
    }

    public void setSound_alien_takel_02(Sound sound_alien_takel_02) {
        this.sound_alien_takel_02 = sound_alien_takel_02;
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

    public Sound getSound_alien_takel_hit_01() {
        return sound_alien_takel_hit_01;
    }

    public void setSound_alien_takel_hit_01(Sound sound_alien_takel_hit_01) {
        this.sound_alien_takel_hit_01 = sound_alien_takel_hit_01;
    }

    public Sound getSound_alien_takel_hit_02() {
        return sound_alien_takel_hit_02;
    }

    public void setSound_alien_takel_hit_02(Sound sound_alien_takel_hit_02) {
        this.sound_alien_takel_hit_02 = sound_alien_takel_hit_02;
    }

    public Sound getSound_alien_takel_dead() {
        return sound_alien_takel_dead;
    }

    public void setSound_alien_takel_dead(Sound sound_alien_takel_dead) {
        this.sound_alien_takel_dead = sound_alien_takel_dead;
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

    public void setAlien_fingus(TextureRegion alien_fingus) {
        this.alien_fingus = alien_fingus;
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

    public Texture getBackground_02() {
        return background_02;
    }

    public void setBackground_02(Texture background_02) {
        this.background_02 = background_02;
    }


    public TextureRegion getItem_Score_100() {
        return item_Score_100;
    }

    public void setItem_Score_100(TextureRegion item_Score_100) {
        this.item_Score_100 = item_Score_100;
    }

    public TextureRegion getItem_Score_200() {
        return item_Score_200;
    }

    public void setItem_Score_200(TextureRegion item_Score_200) {
        this.item_Score_200 = item_Score_200;
    }

    public TextureRegion getItem_Score_500() {
        return item_Score_500;
    }

    public void setItem_Score_500(TextureRegion item_Score_500) {
        this.item_Score_500 = item_Score_500;
    }

    public TextureRegion getItem_Score_1000() {
        return item_Score_1000;
    }

    public void setItem_Score_1000(TextureRegion item_Score_1000) {
        this.item_Score_1000 = item_Score_1000;
    }

    public TextureRegion getItem_Health() {
        return item_Health;
    }

    public void setItem_Health(TextureRegion item_Health) {
        this.item_Health = item_Health;
    }

    public TextureRegion getIcon_Health() {
        return icon_Health;
    }

    public void setIcon_Health(TextureRegion icon_Health) {
        this.icon_Health = icon_Health;
    }

    public TextureRegion getBullet_bigmama() {
        return bullet_bigmama;
    }

    public void setBullet_bigmama(TextureRegion bullet_bigmama) {
        this.bullet_bigmama = bullet_bigmama;
    }

    public Texture getBackground_03() {
        return background_03;
    }

    public void setBackground_03(Texture background_03) {
        this.background_03 = background_03;
    }

    public Texture getBackgroundMenu_01() {
        return backgroundMenu_01;
    }

    public void setBackgroundMenu_01(Texture backgroundMenu_01) {
        this.backgroundMenu_01 = backgroundMenu_01;
    }

    public Texture getBackgroundMenu_02() {
        return backgroundMenu_02;
    }

    public void setBackgroundMenu_02(Texture backgroundMenu_02) {
        this.backgroundMenu_02 = backgroundMenu_02;
    }

    public Sound getSound_alien_kugus_01() {
        return sound_alien_kugus_01;
    }

    public void setSound_alien_kugus_01(Sound sound_alien_kugus_01) {
        this.sound_alien_kugus_01 = sound_alien_kugus_01;
    }

    public Sound getSound_alien_kugus_02() {
        return sound_alien_kugus_02;
    }

    public void setSound_alien_kugus_02(Sound sound_alien_kugus_02) {
        this.sound_alien_kugus_02 = sound_alien_kugus_02;
    }

    public Sound getSound_alien_fingus_hit_01() {
        return sound_alien_fingus_hit_01;
    }

    public void setSound_alien_fingus_hit_01(Sound sound_alien_fingus_hit_01) {
        this.sound_alien_fingus_hit_01 = sound_alien_fingus_hit_01;
    }

    public Texture getBackground_05() {
        return background_05;
    }

    public void setBackground_05(Texture background_05) {
        this.background_05 = background_05;
    }

    public Texture getMiddleground_05() {
        return middleground_05;
    }

    public void setMiddleground_05(Texture middleground_05) {
        this.middleground_05 = middleground_05;
    }

    public TextureRegion getBullet_green() {
        return bullet_green;
    }

    public void setBullet_green(TextureRegion bullet_green) {
        this.bullet_green = bullet_green;
    }

    public TextureRegion getBullet_yellow() {
        return bullet_yellow;
    }

    public void setBullet_yellow(TextureRegion bullet_yellow) {
        this.bullet_yellow = bullet_yellow;
    }

    public TextureRegion getBullet_blue() {
        return bullet_blue;
    }

    public void setBullet_blue(TextureRegion bullet_blue) {
        this.bullet_blue = bullet_blue;
    }

    public TextureRegion getItem_weapon_blue() {
        return item_weapon_blue;
    }

    public void setItem_weapon_blue(TextureRegion item_weapon_blue) {
        this.item_weapon_blue = item_weapon_blue;
    }

    public TextureRegion getItem_weapon_red() {
        return item_weapon_red;
    }

    public void setItem_weapon_red(TextureRegion item_weapon_red) {
        this.item_weapon_red = item_weapon_red;
    }

    public TextureRegion getItem_weapon_yellow() {
        return item_weapon_yellow;
    }

    public void setItem_weapon_yellow(TextureRegion item_weapon_yellow) {
        this.item_weapon_yellow = item_weapon_yellow;
    }
}