package com.adventure.fun.physics;

import com.adventure.fun.WorldLoader;
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
        // contactBody01.setLinearVelocity(0,10);

        Gdx.app.debug("Collision", "Collision between " + contactBody01.getUserData().toString() + " and " + contactBody02.getUserData().toString());

        if (contactBody01 == worldLoader.getPlayer().getBullet().getBody() && contactBody02 != worldLoader.getPlayer().getBody()) {
            worldLoader.getParticles().playEffect(contactBody01.getPosition().x,contactBody01.getPosition().y,worldLoader.getParticles().getExplosion01());
            worldLoader.getPlayer().getBullet().setRemoveFlag(true);
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
