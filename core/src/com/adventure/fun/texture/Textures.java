package com.adventure.fun.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Dennis on 27.10.2015.
 */
public abstract class Textures {
    private static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("android/assets/textures/images.pack.atlas"));

    //GROUND
    public static TextureRegion block_blue = atlas.findRegion("block_blue");
    public static TextureRegion block_red = atlas.findRegion("block_red");
    public static TextureRegion block_green = atlas.findRegion("block_green");
    public static TextureRegion block_yellow= atlas.findRegion("block_yellow");


    //PLAYER
    public static TextureRegion player = atlas.findRegion("player");

    //BULLET
    public static TextureRegion bullet = atlas.findRegion("bullet");
    public static TextureRegion bullet_02 = atlas.findRegion("bullet02");

    //BACKGROUND
    public static Texture background = new Texture(Gdx.files.internal("android/assets/background/asdf.png"));

}
