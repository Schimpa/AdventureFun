package com.adventure.fun.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Dennis on 27.10.2015.
 */
public class Textures {
    private static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("texture_data/images.pack.atlas"));

    //PLAYER
    public static Texture player_move = new Texture(Gdx.files.internal("texture_data/player_move.png"));
    public static Texture alien_move = new Texture(Gdx.files.internal("texture_data/alien_move.png"));

    //BULLET
    public static TextureRegion bullet = atlas.findRegion("bullet");

    //PICKUPS
    public static TextureRegion point = atlas.findRegion("point");

    //BACKGROUND
    public static Texture background = new Texture(Gdx.files.internal("background/asdf.png"));
}
