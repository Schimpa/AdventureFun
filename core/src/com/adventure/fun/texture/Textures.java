package com.adventure.fun.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Dennis on 27.10.2015.
 */
public abstract class Textures {
    private static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("android/assets/textures/player/images.pack.atlas"));

    //GROUND


    //PLAYER
    public static TextureRegion player = atlas.findRegion("player_astro");
    public static Texture player_move = new Texture(Gdx.files.internal("android/assets/textures/player/player_move.png"));

    //BULLET
    public static TextureRegion bullet_02 = atlas.findRegion("bullet02");

    //BACKGROUND
    public static Texture background = new Texture(Gdx.files.internal("android/assets/background/asdf.png"));



}
