package com.adventure.fun.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Dennis on 11.11.2015.
 */
public class Particles {

    private ParticleEffect explosion_bullet_01;
    private ParticleEffect explosion_blitzkugel;
    private ParticleEffect explosion_dead;
    private ParticleEffect funken_01;

    public Particles(){
        explosion_bullet_01 = new ParticleEffect();
        explosion_dead = new ParticleEffect();
        explosion_blitzkugel = new ParticleEffect();
        funken_01 = new ParticleEffect();
    }

    public ParticleEffect activateParticle(ParticleEffect particle){
        if (particle == explosion_bullet_01){
            explosion_bullet_01.load(Gdx.files.internal("particles/explosion_bullet_01.p"),
                    Gdx.files.internal("particles"));
            explosion_bullet_01.scaleEffect(0.01f);
            explosion_bullet_01.setDuration(1);
        }else if (particle == explosion_dead){
            explosion_dead.load(Gdx.files.internal("particles/explosion_dead.p"),
                    Gdx.files.internal("particles"));
            explosion_dead.scaleEffect(0.005f);
            explosion_dead.setDuration(1);
        }else if (particle == explosion_blitzkugel){
            explosion_blitzkugel.load(Gdx.files.internal("particles/explosion_blitzkugel.p"),
                    Gdx.files.internal("particles"));
            explosion_blitzkugel.scaleEffect(0.005f);
            explosion_blitzkugel.setDuration(1);
        }else if (particle == funken_01){
            funken_01.load(Gdx.files.internal("particles/funken_01.p"),
                    Gdx.files.internal("particles"));
            funken_01.scaleEffect(0.01f);
            funken_01.setDuration(1);
        }
        return particle;
    }

    public void playEffect(float x,float y,ParticleEffect effect){
        effect.reset();
        effect.setPosition(x,y);
        effect.start();
    }

     /* //
    public void drawIfActivated(ParticleEffect particleEffect,SpriteBatch batch,float deltaTime){
        if (particleEffect.i){

        }
    }
     */ //

    public boolean completeAll() {
        boolean complete = false;
        while (complete != true) {
            if (explosion_bullet_01.isComplete()) {
                complete = true;
            }if (explosion_dead.isComplete()){
                complete = true;
            }
        }
        return true;
    }


    public void dispose(){
        explosion_bullet_01.dispose();
        explosion_dead.dispose();
        explosion_blitzkugel.dispose();
        funken_01.dispose();
    }

    public void render(SpriteBatch batch,float deltaTime){
        explosion_bullet_01.draw(batch,deltaTime);
        explosion_dead.draw(batch,deltaTime);
        explosion_blitzkugel.draw(batch,deltaTime);
        funken_01.draw(batch,deltaTime);
    }














    public ParticleEffect getExplosion_bullet_01() {
        return explosion_bullet_01;
    }

    public void setExplosion_bullet_01(ParticleEffect explosion_bullet_01) {
        this.explosion_bullet_01 = explosion_bullet_01;
    }

    public ParticleEffect getExplosion_dead() {
        return explosion_dead;
    }

    public void setExplosion_dead(ParticleEffect explosion_dead) {
        this.explosion_dead = explosion_dead;
    }

    public ParticleEffect getExplosion_blitzkugel() {
        return explosion_blitzkugel;
    }

    public void setExplosion_blitzkugel(ParticleEffect explosion_blitzkugel) {
        this.explosion_blitzkugel = explosion_blitzkugel;
    }

    public ParticleEffect getFunken_01() {
        return funken_01;
    }

    public void setFunken_01(ParticleEffect funken_01) {
        this.funken_01 = funken_01;
    }
}
