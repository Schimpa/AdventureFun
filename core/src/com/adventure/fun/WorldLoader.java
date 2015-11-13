package com.adventure.fun;

import com.adventure.fun.effects.Particles;
import com.adventure.fun.objects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by Dennis on 29.10.2015.
 */
public class WorldLoader {

    //Spieler
    private Player player;

    //Welt
    private World world;

    //Karte
    private TiledMap map;

    //Renderer der Karte
    private OrthogonalTiledMapRenderer renderer;

    //Partikel
    private Particles particles;


    public WorldLoader(){
        world = new World(new Vector2(0,-20), true);
        player = new Player(20,5,0.8f,0.8f,world);

        particles = new Particles();

        map = new TmxMapLoader().load("android/assets/maps/map01.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/32f);

        createMap();
        world.setContactListener(new com.adventure.fun.physics.CollisionListener(this));
    }


    public void renderMap(SpriteBatch batch){
        renderer.render();

        batch.begin();
        player.render(batch);
        particles.render(batch, Gdx.graphics.getDeltaTime());
        batch.end();

    }

    public void updateWorld(float deltaTime){
        player.update(deltaTime);
        player.getBullet().checkBulletCollision(player);
        world.step(deltaTime, 60, 20);
    }

    //Lädt die Karte aus Tiled und erstellt Physik mit Box2d
    public void createMap(){
        int i = 0;
        for(MapObject object: map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
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
        i = 0;
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
}
