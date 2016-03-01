package com.adventure.fun.items;

import com.adventure.fun._main.WorldLoader;

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