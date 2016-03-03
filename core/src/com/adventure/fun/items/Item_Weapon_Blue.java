package com.adventure.fun.items;

import com.adventure.fun._main.WorldLoader;

/**
 * Created by Dennis on 28.02.2016.
 */
public class Item_Weapon_Blue extends Item {



    public Item_Weapon_Blue(WorldLoader worldLoader){
        super(worldLoader);
        name = "Item_Weapon_Blue_";
    }


    @Override
    public void checkDestruction(){
        super.checkDestruction();
        for(int i = 0;i < this.items.size;i++){
            if (this.items.get(i).getUserData().toString().equals(name + i + "_Destroyed") && isDestroyed.get(i) == false){
                worldLoader.getPlayer().getBullet().createBlueBullet();
                this.isDestroyed.set(i,true);
            }
        }
    }
}

