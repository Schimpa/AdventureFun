package com.adventure.fun.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Dennis on 11.11.2015.
 */
public class Particles {

    private ParticleEffect explosion01;

    public Particles(){
        explosion01 = new ParticleEffect();
        explosion01.load(Gdx.files.internal("particles/explosion01.p"),
                Gdx.files.internal("particles"));
        explosion01.scaleEffect(0.01f);
        explosion01.setDuration(1);
    }

    public void playEffect(float x,float y,ParticleEffect effect){
        System.out.println("EFFEKTE");
        if (effect.isComplete()){
            effect.reset();
        }
        effect.setPosition(x,y);
        effect.start();


    }
    public void dispose(){
        explosion01.dispose();
    }

    public void render(SpriteBatch batch,float deltaTime){
        explosion01.draw(batch,deltaTime);
    }


    public ParticleEffect getExplosion01() {
        return explosion01;
    }

    public void setExplosion01(ParticleEffect explosion01) {
        this.explosion01 = explosion01;
    }
}
