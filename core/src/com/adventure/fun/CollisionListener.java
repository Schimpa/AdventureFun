package com.adventure.fun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Dennis on 31.10.2015.
 */
public class CollisionListener implements ContactListener {

    private WorldRender worldRender;

    public CollisionListener(WorldRender worldRender){
        this.worldRender = worldRender;
    }

    @Override
    public void beginContact(Contact contact) {
        Body contactBody01 = contact.getFixtureA().getBody();
        Body contactBody02 = contact.getFixtureB().getBody();

        if (contactBody01 == worldRender.getPlayer().getBody()) {
            Gdx.app.debug("Collision","Collision between " + contact.getFixtureA().toString() + " and " + contact.getFixtureB().toString());
        }

        if (contactBody01 == worldRender.getPlayer().getBullet().getBody()){
            Gdx.app.debug("Collision","Collision between " + contact.getFixtureA().toString() + " and " + contact.getFixtureB().toString());
            //worldRender.getPlayer().getBullets().get(0).getBody().setTransform(-100,-100,0);
            contactBody01.setTransform(-100,-100,0);
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
