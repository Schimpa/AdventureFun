package com.adventure.fun.items;

import com.adventure.fun._main.WorldLoader;

/**
 * Created by Dennis on 28.02.2016.
 */
public class Item_Weapon_Red extends Item {


    private int score;

    public Item_Weapon_Red(WorldLoader worldLoader, int score){
        super(worldLoader);
        this.score = score;
        name = "Item_Weapon_Red_";
    }


    @Override
    public void checkDestruction(){
        super.checkDestruction();
        for(int i = 0;i < this.items.size;i++){
            if (this.items.get(i).getUserData().toString().equals(name + i + "_Destroyed") && isDestroyed.get(i) == false){
                worldLoader.getPlayer().setScore(worldLoader.getPlayer().getScore() + this.score);
                worldLoader.getPlayer().getBullet().createGreenBullet();
                this.isDestroyed.set(i,true);
            }
        }
    }
}

