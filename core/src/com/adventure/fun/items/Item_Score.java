package com.adventure.fun.items;

import com.adventure.fun._main.WorldLoader;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Dennis on 22.11.2015.
 */
public class Item_Score extends Item {

    private int score;

    public Item_Score(WorldLoader worldLoader, int score){
        super(worldLoader);
        this.score = score;
        name = "Item_Score_"+score + "_";
    }

    public Sprite createSpriteForBody(Rectangle rect,TextureRegion region){
        Sprite sprite = new Sprite(region);
        sprite.setPosition(rect.getX() / 32, rect.getY() / 32);
        sprite.setSize(0.9f,0.9f);

        return sprite;
    }
    @Override
    public void checkDestruction(){
        super.checkDestruction();
        for(int i = 0;i < this.items.size;i++){
            if (this.items.get(i).getUserData().toString().equals(name + i + "_Destroyed") && isDestroyed.get(i) == false){
                worldLoader.getPlayer().setScore(worldLoader.getPlayer().getScore() + this.score);
                this.isDestroyed.set(i,true);
            }
        }
    }
}