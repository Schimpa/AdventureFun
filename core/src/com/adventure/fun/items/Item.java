package com.adventure.fun.items;

import com.adventure.fun._main.WorldLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Dennis on 22.11.2015.
 */
public abstract class Item{

    protected Array<Body> items;
    protected Array<Sprite> items_texture;
    protected Array<Boolean> isDestroyed;

    protected WorldLoader worldLoader;

    protected String name;

    public Item(WorldLoader worldLoader){
        items = new Array<Body>();
        items_texture = new Array<Sprite>();
        name = "DEFAULT";
        this.worldLoader = worldLoader;
        isDestroyed = new Array<Boolean>();
        for(int i = 0;i < 10;i++){
            isDestroyed.add(false);
        }

    }

    public void render(SpriteBatch batch){
        for(Sprite sprite: items_texture){
            if (sprite.getColor().toString().equals(": 00000000") == false){
                sprite.draw(batch);
            }
        }
    }

    public void checkDestruction(){
        for(int i = 0;i < this.items.size;i++){
            if (this.items.get(i).getUserData().toString().equals(name + i + "_Destroy")){
                this.items.get(i).getWorld().destroyBody(this.items.get(i));
                this.items_texture.get(i).setColor(0,0,0,0);
                this.items.get(i).setUserData(name + i + "_Destroyed");
                Gdx.app.debug(this.items.get(i).getUserData().toString(),"destroyed");
            }
        }
    }





































    public Array<Body> getItems() {
        return items;
    }

    public void setItems(Array<Body> items) {
        this.items = items;
    }

    public Array<Sprite> getItems_texture() {
        return items_texture;
    }

    public void setItems_texture(Array<Sprite> items_texture) {
        this.items_texture = items_texture;
    }
}
