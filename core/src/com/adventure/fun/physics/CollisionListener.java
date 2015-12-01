package com.adventure.fun.physics;

import com.adventure.fun._main.WorldLoader;
import com.adventure.fun.objects.Bullet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;


/**
 * Created by Dennis on 31.10.2015.
 */
public class CollisionListener implements ContactListener {

    private WorldLoader worldLoader;

    public CollisionListener(WorldLoader worldLoader){
        this.worldLoader = worldLoader;
    }

    @Override
    public void beginContact(Contact contact) {
        Body contactBody01 = contact.getFixtureA().getBody();
        Body contactBody02 = contact.getFixtureB().getBody();

        Gdx.app.debug("Collision", "Collision between " + contactBody01.getUserData().toString() + " and " + contactBody02.getUserData().toString());



        for(int i = 0;i < worldLoader.getEnemies().size;i++) {
            if (contactBody01.getUserData().toString().equals("Bullet_Enemy_"+i) && !contactBody02.getUserData().toString().equals("Enemy_"+i) ) {
                worldLoader.getEnemies().get(i).getBullet().setRemoveFlag(true);
                if (contactBody02.getUserData().toString().equals("Player")){
                    worldLoader.getPlayer().setLives(worldLoader.getPlayer().getLives()-1);
                }
                worldLoader.getParticles().playEffect(contactBody01.getPosition().x, contactBody01.getPosition().y, worldLoader.getParticles().getExplosion01());
            }
            if (contactBody02.getUserData().toString().equals("Bullet_Enemy_"+i) && !contactBody01.getUserData().toString().equals("Enemy_"+i) ) {
                if (contactBody01.getUserData().toString().equals("Player")){
                    worldLoader.getPlayer().setLives(worldLoader.getPlayer().getLives()-1);
                }
                worldLoader.getParticles().playEffect(contactBody02.getPosition().x, contactBody02.getPosition().y, worldLoader.getParticles().getExplosion01());
                worldLoader.getEnemies().get(i).getBullet().setRemoveFlag(true);
            }
        }

        if (contactBody02.getUserData().toString().equals("Bullet_Player")) {
            worldLoader.getParticles().playEffect(contactBody02.getPosition().x, contactBody02.getPosition().y, worldLoader.getParticles().getExplosion01());
            worldLoader.getPlayer().getBullet().setRemoveFlag(true);
        }
        if (contactBody01.getUserData().toString().equals("Bullet_Player")) {
            worldLoader.getParticles().playEffect(contactBody01.getPosition().x, contactBody01.getPosition().y, worldLoader.getParticles().getExplosion01());
            worldLoader.getPlayer().getBullet().setRemoveFlag(true);
        }


        for(int i = 0;i < worldLoader.getScoreItem_100().getItems().size;i++){
            if (contactBody01.getUserData().toString().equals("Item_Point_"+i) || contactBody02.getUserData().toString().equals("Item_Point_"+i)) {
                    Body body = worldLoader.getScoreItem_100().getItems().get(i);
                    Gdx.app.debug("Item:", body.getUserData().toString());
                    if (body.getUserData().toString().equals("Item_Point_"+i)){
                        body.setUserData("Item_Point_" + i + "_Destroy");
                    }
            }
        }

        for(int i = 0;i < worldLoader.getEnemies().size;i++){
            if (contactBody01.getUserData().toString().equals("Enemy_"+i) && contactBody02.getUserData().toString().equals("Bullet_Player") ||
                    contactBody02.getUserData().toString().equals("Enemy_"+i) && contactBody01.getUserData().toString().equals("Bullet_Player")){
                worldLoader.getEnemies().get(i).setLives(worldLoader.getEnemies().get(i).getLives() - 1);
                if (worldLoader.getEnemies().get(i).getLives() <= 0){
                    worldLoader.getEnemies().get(i).setRemoveFlag(true);
                }
            }
        }

    }


    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
