package com.adventure.fun.items;

import com.adventure.fun._main.WorldLoader;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Dennis on 22.11.2015.
 */
public class Item_Health extends Item {

    private int health;

    public Item_Health(WorldLoader worldLoader, int health){
        super(worldLoader);
        this.health = health;
        name = "Item_Health_"+ health + "_";
    }


    @Override
    public void checkDestruction(){
        super.checkDestruction();
        for(int i = 0;i < this.items.size;i++){
            if (this.items.get(i).getUserData().toString().equals(name + i + "_Destroyed") && isDestroyed.get(i) == false){
                worldLoader.getPlayer().setLives(worldLoader.getPlayer().getLives() + this.health);
                this.isDestroyed.set(i,true);
            }
        }
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}