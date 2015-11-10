package com.adventure.fun;

import com.adventure.fun.objects.Ground;
import com.adventure.fun.objects.Player;
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
public class WorldRender {

    private Player player;
    private World world;
    private Array<Ground> grounds;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    public WorldRender(){
        grounds = new Array<Ground>();
        world = new World(new Vector2(0,-30), true);
        player = new Player(1,5,0.8f,0.8f,world);

        map = new TmxMapLoader().load("android/assets/maps/map01.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/32f);

        createMap();

        world.setContactListener(new CollisionListener(this));
    }


    public void renderObjects(SpriteBatch batch){
        player.render(batch);
    }

    public void renderMap(){
        renderer.render();
    }

    public void updateWorld(float deltaTime){
        world.step(deltaTime, 60, 20);
        player.update(deltaTime);

    }

    public void createMap(){
        for(MapObject object: map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            BodyDef bdef = new BodyDef();
            bdef.position.set(rect.getX() / 32 + rect.getWidth() / 2 / 32, rect.getY() / 32 + rect.getHeight() / 2 / 32);

            Body body = world.createBody(bdef);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(rect.getWidth()/ 2 / 32,rect.getHeight() / 2 / 32);
            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            body.createFixture(fdef);
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

    public Array<Ground> getGrounds() {
        return grounds;
    }

    public void setGrounds(Array<Ground> grounds) {
        this.grounds = grounds;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }
}
