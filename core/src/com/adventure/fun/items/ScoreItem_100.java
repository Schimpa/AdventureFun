package com.adventure.fun.items;

import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Dennis on 22.11.2015.
 */
public class ScoreItem_100 extends com.adventure.fun.controls.Item {

    public ScoreItem_100(){

    }

    public Sprite createSpriteForBody(Rectangle rect){
        Sprite sprite = new Sprite(Textures.point);
        sprite.setPosition(rect.getX() / 32, rect.getY() / 32);
        sprite.setSize(1,1);

        return sprite;
    }
}
