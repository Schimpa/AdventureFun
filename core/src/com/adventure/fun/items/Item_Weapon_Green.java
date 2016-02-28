package com.adventure.fun.items;

import com.adventure.fun._main.WorldLoader;

/**
 * Created by Dennis on 28.02.2016.
 */
public class Item_Weapon_Green extends Item {


    public Item_Weapon_Green(WorldLoader worldLoader){
        super(worldLoader);
        name = "Item_Weapon_Green_";
    }


    @Override
    public void checkDestruction(){
        super.checkDestruction();
        for(int i = 0;i < this.items.size;i++){
            if (this.items.get(i).getUserData().toString().equals(name + i + "_Destroyed") && isDestroyed.get(i) == false){
                //if (worldLoader.getPlayer().getRegion().equals(worldLoader.getGame().getAssets().getBullet_green()) == false){
                    worldLoader.getPlayer().getBullet().createGreenBullet(-100,-100,0.4f,0.4f,worldLoader.getWorld(),worldLoader.getGame().getAssets().getBullet_green(),false);
                    worldLoader.getPlayer().setScore(worldLoader.getPlayer().getScore() + 250);
                    this.isDestroyed.set(i,true);
                //}
            }
        }
    }


}
