package com.adventure.fun.items;

import com.adventure.fun._main.WorldLoader;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Dennis on 22.11.2015.
 */
public class ScoreItem_100 extends Item {

    private int score;

    public ScoreItem_100(WorldLoader worldLoader){
        super(worldLoader);
        score = 100;
        name = "Item_Point_";
    }

    public Sprite createSpriteForBody(Rectangle rect){
        Sprite sprite = new Sprite(worldLoader.getGame().getAssets().getPoint());
        sprite.setPosition(rect.getX() / 32, rect.getY() / 32);
        sprite.setSize(0.5f,0.5f);

        return sprite;
    }
    @Override
    public void checkDestruction(){
        super.checkDestruction();
        for(int i = 0;i < this.items.size;i++){
            if (this.items.get(i).getUserData().toString().equals(name + i + "_Destroyed") && isDestroyed.get(i) == false){
                worldLoader.getPlayer().setScore(worldLoader.getPlayer().getScore() + 100);
                this.isDestroyed.set(i,true);
            }
        }
    }

}
