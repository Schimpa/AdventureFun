package com.adventure.fun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by Dennis on 31.10.2015.
 */
public class MyContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Body body01 = contact.getFixtureA().getBody();
        Body body02 = contact.getFixtureB().getBody();
        Gdx.app.debug("A","Collision started");
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
