package com.adventure.fun._main;

import com.adventure.fun.audio.AudioController;
import com.adventure.fun.controls.Controls;
import com.adventure.fun.effects.Particles;
import com.adventure.fun.items.ScoreItem_100;
import com.adventure.fun.objects.Enemy;
import com.adventure.fun.objects.Player;
import com.adventure.fun.texture.Textures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


/**
 * Created by Dennis on 29.10.2015.
 */
public class WorldLoader {

    //Spieler
    private Player player;
    private Enemy enemy;
    private Array<Enemy> enemies;

    //Items
    private ScoreItem_100 scoreItem_100;

    //Steuerung
    Controls controls = new Controls(this);

    //Welt
    private World world;

    //Karte
    private TiledMap map;

    //Renderer der Karte
    private OrthogonalTiledMapRenderer renderer;

    //Partikel
    private Particles particles;



    public WorldLoader(){
        //Pickup Items
        scoreItem_100 = new ScoreItem_100(this);
        enemies = new Array<Enemy>();

        world = new World(new Vector2(0,-20), true);
        player = new Player(3,5,0.8f,1.7f,world);

        particles = new Particles();

        createMap();

        AudioController.music_ambient.play();
        AudioController.music_ambient.setVolume(0.2f);

        world.setContactListener(new com.adventure.fun.physics.CollisionListener(this));
    }


    public void renderMap(SpriteBatch batch){
        renderer.render();
        batch.begin();

        for(Enemy enemy: enemies){
            enemy.render(batch);
        }

        scoreItem_100.render(batch);


        player.render(batch);
        particles.render(batch, Gdx.graphics.getDeltaTime());
        batch.end();

    }

    public void updateWorld(float deltaTime){
        controls.movementControls();
        player.update(deltaTime);

        for(Enemy enemy: enemies){
            enemy.update(deltaTime);
        }

        scoreItem_100.checkDestruction();

        world.step(deltaTime, 60, 20);
    }



    //Lädt die Karte aus Tiled und erstellt Physik mit Box2d
    public void createMap(){
        map = new TmxMapLoader().load("maps/map01.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/32f);

        //GROUND
        int i = 0;
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            BodyDef bdef = new BodyDef();
            bdef.position.set(rect.getX() / 32 + rect.getWidth() / 2 / 32, rect.getY() / 32 + rect.getHeight() / 2 / 32);

            Body body = world.createBody(bdef);
            body.setUserData("Ground_"+i);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(rect.getWidth()/ 2 / 32,rect.getHeight() / 2 / 32);
            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            body.createFixture(fdef);
            i++;
        }
        //STAIRS
        /*
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            BodyDef bdef = new BodyDef();
            bdef.position.set(rect.getX() / 32 + rect.getWidth() /32 - rect.getWidth() / 32, rect.getY() / 32 + rect.getHeight() /32 - rect.getWidth() / 32);

            PolygonShape shape = new PolygonShape();

            Vector2[] polygons = new Vector2[3];
            polygons[0] = new Vector2(0,0);
            polygons[1] = new Vector2(1,0);
            polygons[2] = new Vector2(1,1);


            shape.set(polygons);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.density = 1;
            Body body = world.createBody(bdef);
            body.setUserData("Stair_Right_"+i);
            body.createFixture(fdef);
            i++;
        }
        */

        //ITEM - POINTS
        i = 0;
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() / 32 + rect.getWidth() / 2 / 32, rect.getY() / 32 + rect.getHeight() / 2 / 32);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(rect.getWidth()/ 2 / 32,rect.getHeight() / 2 / 32);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.isSensor = true;

            Body body = world.createBody(bdef);
            body.setUserData("Item_Point_"+i);
            body.createFixture(fdef);

            scoreItem_100.getItems().add(body);
            scoreItem_100.getItems_texture().add(scoreItem_100.createSpriteForBody(rect));

            i++;
        }
        i = 0;
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            Enemy newEnemy = new Enemy(rect.getX() / 32 + rect.getWidth() / 2 / 32,rect.getY() / 32 + rect.getHeight() / 2 / 32,0.8f,1.7f,world,this.player);
            newEnemy.getBody().setUserData("Enemy_"+i);
            enemies.add(newEnemy);
            i++;
        }
    }
















































    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(Array<Enemy> enemies) {
        this.enemies = enemies;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }


    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(OrthogonalTiledMapRenderer renderer) {
        this.renderer = renderer;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public Particles getParticles() {
        return particles;
    }

    public void setParticles(Particles particles) {
        this.particles = particles;
    }

    public Controls getControls() {
        return controls;
    }

    public ScoreItem_100 getScoreItem_100() {
        return scoreItem_100;
    }

    public void setScoreItem_100(ScoreItem_100 scoreItem_100) {
        this.scoreItem_100 = scoreItem_100;
    }
}
