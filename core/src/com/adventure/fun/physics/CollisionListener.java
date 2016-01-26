package com.adventure.fun.physics;

import com.adventure.fun.screens.GameScreen;
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

    private GameScreen gameScreen;

    public CollisionListener(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    @Override
    public void beginContact(Contact contact) {
        Body contactBody01 = contact.getFixtureA().getBody();
        Body contactBody02 = contact.getFixtureB().getBody();

        /*
        gameScreen.getWorldLoader().getEnemies_alien_soldier().get(0).getParticles().playEffect(gameScreen.getWorldLoader().getPlayer().getBody().getPosition().x,
                gameScreen.getWorldLoader().getPlayer().getBody().getPosition().y,
                gameScreen.getWorldLoader().getEnemies_alien_soldier().get(0).getParticles().getExplosion_blitzkugel());
        */

        //Gdx.app.debug("Collision", "Collision between " + contactBody01.getUserData().toString() + " and " + contactBody02.getUserData().toString());

        //PLAYER SOLL AUFHÖREN ZU SPRINGEN, WENN ER ETWAS BERÜHRT
        if (contactBody01.getUserData().toString().equals("Player") || contactBody02.getUserData().toString().equals("Player")){
            this.gameScreen.getWorldLoader().getPlayer().getJumpAnimation().setIsActive(false);
            this.gameScreen.getWorldLoader().getPlayer().getWalkAnimation().setIsActive(true);
        }


        //ANZAHL DER GESCHOSSE VON ALIEN SOLDIER
        for(int i = 0;i <= gameScreen.getWorldLoader().getEnemies_alien_soldier().size;i++){
            //COLLISION BULLET-ENEMY-SOLDIER --- PLAYER-BULLET
            if (contactBody02.getUserData().toString().equals("Bullet_Player") && !contactBody01.getUserData().toString().contains("Bullet_Enemy_Soldier")) {
                gameScreen.getWorldLoader().getPlayer().getParticles().playEffect(contactBody02.getPosition().x, contactBody01.getPosition().y,
                        gameScreen.getWorldLoader().getPlayer().getParticles().getExplosion_bullet_01());
                gameScreen.getWorldLoader().getPlayer().getBullet().setRemoveFlag(true);
            }else if (contactBody01.getUserData().toString().equals("Bullet_Player") && !contactBody02.getUserData().toString().contains("Bullet_Enemy_Soldier")) {
                gameScreen.getWorldLoader().getPlayer().getParticles().playEffect(contactBody01.getPosition().x, contactBody02.getPosition().y,
                        gameScreen.getWorldLoader().getPlayer().getParticles().getExplosion_bullet_01());
                gameScreen.getWorldLoader().getPlayer().getBullet().setRemoveFlag(true);
            }
            //COLLISION BULLET-ENEMY-SOLDIER --- PLAYER
            if (contactBody01.getUserData().toString().equals("Bullet_Enemy_Soldier_"+i) && contactBody02.getUserData().toString().equals("Player") ||
                    contactBody02.getUserData().toString().equals("Bullet_Enemy_Soldier_"+i) && contactBody01.getUserData().toString().equals("Player")) {
                gameScreen.getWorldLoader().getPlayer().looseLife(1);
                if (contactBody02.getUserData().toString().equals("Player")){
                    gameScreen.getWorldLoader().getEnemies_alien_soldier().get(i).getParticles().playEffect(contactBody01.getPosition().x, contactBody01.getPosition().y,
                            gameScreen.getWorldLoader().getEnemies_alien_soldier().get(i).getParticles().getExplosion_blitzkugel());
                }else if (contactBody01.getUserData().toString().equals("Player")){
                    gameScreen.getWorldLoader().getEnemies_alien_soldier().get(i).getParticles().playEffect(contactBody02.getPosition().x, contactBody02.getPosition().y,
                            gameScreen.getWorldLoader().getEnemies_alien_soldier().get(i).getParticles().getExplosion_blitzkugel());
                }
                gameScreen.getWorldLoader().getEnemies_alien_soldier().get(i).getBullet().setRemoveFlag(true);
            }
        }


        //COLLISION PLAYER --- ENEMY
        for(int i = 0; i < gameScreen.getWorldLoader().getEnemies_alien_zombie().size + gameScreen.getWorldLoader().getEnemies_alien_soldier().size; i++){
            if (contactBody01.getUserData().toString().equals("Player") && contactBody02.getUserData().toString().equals("Enemy_"+i) ||
                    contactBody02.getUserData().toString().equals("Player") && contactBody01.getUserData().toString().equals("Enemy_"+i)){
                if (gameScreen.getWorldLoader().getPlayer().getDamageCoolDownTime() >= 1){
                    gameScreen.getWorldLoader().getPlayer().looseLife(1);

                    if (contactBody01.getUserData().toString().equals("Player")){

                        if (contactBody02.getLinearVelocity().x > 0){
                            this.gameScreen.getWorldLoader().getPlayer().getBody().setLinearVelocity(
                                    this.gameScreen.getWorldLoader().getPlayer().getBody().getLinearVelocity().x + 6,this.gameScreen.getWorldLoader().getPlayer().getBody().getLinearVelocity().y + 6);
                        }else{
                            this.gameScreen.getWorldLoader().getPlayer().getBody().setLinearVelocity(
                                    -this.gameScreen.getWorldLoader().getPlayer().getBody().getLinearVelocity().x + -6, this.gameScreen.getWorldLoader().getPlayer().getBody().getLinearVelocity().y + 6);
                        }
                    }
                    if (contactBody02.getUserData().toString().equals("Player")){

                        if (contactBody01.getLinearVelocity().x > 0){
                            this.gameScreen.getWorldLoader().getPlayer().getBody().setLinearVelocity(
                                    this.gameScreen.getWorldLoader().getPlayer().getBody().getLinearVelocity().x + 6,this.gameScreen.getWorldLoader().getPlayer().getBody().getLinearVelocity().y + 6);
                        }else{
                            this.gameScreen.getWorldLoader().getPlayer().getBody().setLinearVelocity(
                                    -this.gameScreen.getWorldLoader().getPlayer().getBody().getLinearVelocity().x + -6,this.gameScreen.getWorldLoader().getPlayer().getBody().getLinearVelocity().y + 6);
                        }
                    }
                    gameScreen.getWorldLoader().getPlayer().setDamageCoolDownTime(0);
                }
            }
        }


        //COLLISION PLAYER --- ITEM SCORE 100
        for(int i = 0;i < gameScreen.getWorldLoader().getScoreItem_100().getItems().size;i++){
            if (contactBody01.getUserData().toString().equals("Item_Point_"+i) || contactBody02.getUserData().toString().equals("Item_Point_"+i) &&
                    contactBody01.getUserData().toString().equals("Player") || contactBody02.getUserData().toString().equals("Player") ) {
                    Body body = gameScreen.getWorldLoader().getScoreItem_100().getItems().get(i);
                    Gdx.app.debug("Item:", body.getUserData().toString());
                    if (body.getUserData().toString().equals("Item_Point_"+i)){
                        body.setUserData("Item_Point_" + i + "_Destroy");
                    }
            }
        }

        //COLLISION ENEMY_ZOMBIE --- PLAYER BULLET
        for(int i = 0; i < gameScreen.getWorldLoader().getEnemies_alien_zombie().size; i++){
            if (contactBody01.getUserData().toString().equals("Enemy_"+i) && contactBody02.getUserData().toString().equals("Bullet_Player") ||
                    contactBody02.getUserData().toString().equals("Enemy_"+i) && contactBody01.getUserData().toString().equals("Bullet_Player"))
            {
                gameScreen.getWorldLoader().getEnemies_alien_zombie().get(i).setLives(gameScreen.getWorldLoader().getEnemies_alien_zombie().get(i).getLives() - 1);
                if (contactBody01.getUserData().toString().equals("Bullet_Player")){
                    gameScreen.getWorldLoader().getPlayer().getParticles().playEffect(contactBody01.getPosition().x,
                            contactBody01.getPosition().y, gameScreen.getWorldLoader().getPlayer().getParticles().getExplosion_bullet_01());
                }else if (contactBody02.getUserData().toString().equals("Bullet_Player")){
                    gameScreen.getWorldLoader().getPlayer().getParticles().playEffect(contactBody02.getPosition().x,
                            contactBody02.getPosition().y, gameScreen.getWorldLoader().getPlayer().getParticles().getExplosion_bullet_01());
                }


                if (gameScreen.getWorldLoader().getEnemies_alien_zombie().get(i).getLives() <= 0){
                    gameScreen.getWorldLoader().getEnemies_alien_zombie().get(i).setRemoveFlag(true);
                }
            }
        }

        //COLLISION ENEMY_SOLDIER --- PLAYER BULLET
        for(int i = 0; i < gameScreen.getWorldLoader().getEnemies_alien_soldier().size; i++){
            if (contactBody01.getUserData().toString().equals("Enemy_"+ (i + gameScreen.getWorldLoader().getEnemies_alien_zombie().size)) && contactBody02.getUserData().toString().equals("Bullet_Player") ||
                    contactBody02.getUserData().toString().equals("Enemy_"+(i + gameScreen.getWorldLoader().getEnemies_alien_zombie().size)) && contactBody01.getUserData().toString().equals("Bullet_Player")){

                gameScreen.getWorldLoader().getEnemies_alien_soldier().get(i).setLives(gameScreen.getWorldLoader().getEnemies_alien_soldier().get(i).getLives() - 1);

                if (contactBody01.getUserData().toString().equals("Bullet_Player")){
                    gameScreen.getWorldLoader().getPlayer().getParticles().playEffect(contactBody01.getPosition().x, contactBody01.getPosition().y,
                            gameScreen.getWorldLoader().getPlayer().getParticles().getExplosion_bullet_01());
                }else if (contactBody02.getUserData().toString().equals("Bullet_Player")){
                    gameScreen.getWorldLoader().getPlayer().getParticles().playEffect(contactBody01.getPosition().x, contactBody01.getPosition().y,
                            gameScreen.getWorldLoader().getPlayer().getParticles().getExplosion_bullet_01());
                }
                 if (gameScreen.getWorldLoader().getEnemies_alien_soldier().get(i).getLives() <= 0){
                    gameScreen.getWorldLoader().getEnemies_alien_soldier().get(i).setRemoveFlag(true);
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
