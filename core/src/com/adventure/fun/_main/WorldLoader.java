package com.adventure.fun._main;

import com.adventure.fun.controls.Controls;
import com.adventure.fun.effects.Particles;
import com.adventure.fun.items.ScoreItem_100;
import com.adventure.fun.objects.Enemy_Alien_Kugus;
import com.adventure.fun.objects.Enemy_Alien_Soldier;
import com.adventure.fun.objects.Enemy_Alien_Zombie;
import com.adventure.fun.objects.Player;
import com.adventure.fun.physics.CollisionListener;
import com.adventure.fun.screens.GameScreen;
import com.badlogic.gdx.Gdx;
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

import box2dLight.PointLight;
import box2dLight.RayHandler;


/**
 * Created by Dennis on 29.10.2015.
 */
public class WorldLoader {

    //Spieler
    private Player player;
    private Array<Enemy_Alien_Zombie> enemies_alien_zombie;
    private Array<Enemy_Alien_Soldier> enemies_alien_soldier;
    private Array<Enemy_Alien_Kugus> enemies_alien_kugus;

    private MainWindow game;
    private GameScreen gameScreen;

    //Items
    private ScoreItem_100 scoreItem_100;

    //Steuerung
    Controls controls;

    //Welt
    private World world;

    //Karte
    private TiledMap map;

    //Renderer der Karte
    private OrthogonalTiledMapRenderer renderer;

    //Partikel
    private Particles particles;

    private RayHandler rayHandler;

    public void dispose(){
        world.dispose();
        for(Enemy_Alien_Zombie enemyAlienZombie : enemies_alien_zombie){
            enemyAlienZombie.dispose();
        }
        for(Enemy_Alien_Soldier enemyAlienSoldier : enemies_alien_soldier){
            enemyAlienSoldier.dispose();
        }
        for(Enemy_Alien_Kugus enemyAlienKugus : enemies_alien_kugus){
            enemyAlienKugus.dispose();
        }
        player.dispose();
        map.dispose();
        renderer.dispose();
        particles.dispose();
    }

    public WorldLoader(MainWindow game, GameScreen gameScreen){
        //Pickup Items
        this.game = game;
        this.gameScreen = gameScreen;
        scoreItem_100 = new ScoreItem_100(this);
        enemies_alien_zombie = new Array<Enemy_Alien_Zombie>();
        enemies_alien_soldier = new Array<Enemy_Alien_Soldier>();
        enemies_alien_kugus = new Array<Enemy_Alien_Kugus>();
        controls = new Controls(this);

        world = new World(new Vector2(0,-15f), true);


        //particles = new Particles();

        createMap();

        createLights();

        //game.getAssets().getMusic_ambient().play();
        //game.getAssets().getMusic_ambient().setVolume(0.2f);
        world.setContactListener(new CollisionListener(this.gameScreen));

    }

    public void createLights(){

        //Umgebungslicht
        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(true);

        rayHandler = new RayHandler(world);

        rayHandler.setAmbientLight(0.0f, 0.0f, 0.0f, 0.5f);
        rayHandler.setBlurNum(1);

        //Spielerlicht
        PointLight light = new PointLight(rayHandler, 100, null, 20, 0f, 0f);

        light.attachToBody(this.getPlayer().getBody());
        light.setColor(1, 1, 1, 0.5f);
    }


    public void renderMap(SpriteBatch batch){
        renderer.render();
        batch.begin();

        for (Enemy_Alien_Zombie enemyAlienZombie : enemies_alien_zombie){
            enemyAlienZombie.render(batch);
        }
        for (Enemy_Alien_Soldier enemyAlienSoldier : enemies_alien_soldier){
            enemyAlienSoldier.render(batch);
        }
        for(Enemy_Alien_Kugus enemyAlienKugus : enemies_alien_kugus){
            enemyAlienKugus.render(batch);
        }

        scoreItem_100.render(batch);
        //particles.render(batch, Gdx.graphics.getDeltaTime());

        player.render(batch);

        renderParticles(batch,Gdx.graphics.getDeltaTime());
        batch.end();

        if (gameScreen.isActivateLights() == true){
            rayHandler.setCombinedMatrix(gameScreen.getCamera().getPlayerCamera());
            rayHandler.render();
        }


    }

    public void renderParticles(SpriteBatch batch,float deltaTime){
        for (Enemy_Alien_Zombie enemyAlienZombie : enemies_alien_zombie){
            enemyAlienZombie.getParticles().render(batch,deltaTime);
        }
        for (Enemy_Alien_Soldier enemyAlienSoldier : enemies_alien_soldier){
            enemyAlienSoldier.getParticles().render(batch,deltaTime);
        }
        for(Enemy_Alien_Kugus enemyAlienKugus : enemies_alien_kugus){
            enemyAlienKugus.getParticles().render(batch,deltaTime);
        }
        player.getParticles().render(batch,deltaTime);
    }

    public void updateWorld(float deltaTime){
        controls.update();
        player.update(deltaTime);

        for(Enemy_Alien_Zombie enemyAlienZombie : enemies_alien_zombie){
            enemyAlienZombie.update(deltaTime);
        }

        for(Enemy_Alien_Soldier enemyAlienSoldier : enemies_alien_soldier){
            enemyAlienSoldier.update(deltaTime);
        }
        for(Enemy_Alien_Kugus enemyAlienKugus : enemies_alien_kugus){
            enemyAlienKugus.update(deltaTime);
        }

        scoreItem_100.checkDestruction();
        rayHandler.update();
        world.step(deltaTime, 60, 20);
    }



    //Lädt die Karte aus Tiled und erstellt Physik mit Box2d
    public void createMap(){
        map = new TmxMapLoader().load(this.getGameScreen().getLevelName());
        renderer = new OrthogonalTiledMapRenderer(map, 1/32f);

        //PLAYER SPAWN
        int i = 0;
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            player = new Player(game,rect.getX() / 32 + rect.getWidth() / 2 / 32,rect.getY() / 32 + rect.getHeight() / 2 / 32,1.6f,3.4f,world);
            i++;
        }

        //GROUND
        i = 0;
        for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
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
        for(MapObject object: map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() / 32 + rect.getWidth() / 2 / 32, rect.getY() / 32 + rect.getHeight() / 2 / 32);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(rect.getWidth()/ 2 / 32,rect.getHeight() / 2 / 32);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.isSensor = true;
            fdef.restitution = 0;


            Body body = world.createBody(bdef);
            body.setUserData("Item_Point_"+i);
            body.createFixture(fdef);

            scoreItem_100.getItems().add(body);
            scoreItem_100.getItems_texture().add(scoreItem_100.createSpriteForBody(rect));

            i++;
        }

        //ENEMY-ALIEN-ZOMBIE
        i = 0;
        for(MapObject object: map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            Enemy_Alien_Zombie newEnemyAlienZombie = new Enemy_Alien_Zombie(game,rect.getX() / 32 + rect.getWidth() / 2 / 32,rect.getY() / 32 + rect.getHeight() / 2 / 32,2f,3.4f,world,this.player);
            newEnemyAlienZombie.getBody().setUserData("Enemy_"+i);
            enemies_alien_zombie.add(newEnemyAlienZombie);
            i++;
        }

        //ENEMY-ALIEN-SOLDIER
        int j = 0;
        for(MapObject object: map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            Enemy_Alien_Soldier newEnemyAlienSoldier = new Enemy_Alien_Soldier(game,rect.getX() / 32 + rect.getWidth() / 2 / 32,rect.getY() / 32 + rect.getHeight() / 2 / 32,1.6f,3.4f,world,this.player);
            newEnemyAlienSoldier.getBody().setUserData("Enemy_"+i);
            newEnemyAlienSoldier.getBullet().getBody().setUserData("Bullet_Enemy_Soldier_"+j);
            //newEnemyAlienSoldier.getBullet().getBody().setTransform(1000*i,1000*i,0);
            enemies_alien_soldier.add(newEnemyAlienSoldier);
            i++;
            j++;
        }
        j = 0;
        for(MapObject object: map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            Enemy_Alien_Kugus newEnemyAlienKugus = new Enemy_Alien_Kugus(game,rect.getX() / 32 + rect.getWidth() / 2 / 32,rect.getY() / 32 + rect.getHeight() / 2 / 32,2.4f,4.4f,world,this.player);
            newEnemyAlienKugus.getBody().setUserData("Enemy_"+i);
            newEnemyAlienKugus.getBullet().getBody().setUserData("Bullet_Enemy_Kugus_"+j);
            //newEnemyAlienKugus.getBullet().getBody().setTransform(1000*i,1000*i,0);
            enemies_alien_kugus.add(newEnemyAlienKugus);
            i++;
            j++;
        }
    }


    public Array<Enemy_Alien_Soldier> getEnemies_alien_soldier() {
        return enemies_alien_soldier;
    }

    public void setEnemies_alien_soldier(Array<Enemy_Alien_Soldier> enemies_alien_soldier) {
        this.enemies_alien_soldier = enemies_alien_soldier;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public void setRayHandler(RayHandler rayHandler) {
        this.rayHandler = rayHandler;
    }

    public Array<Enemy_Alien_Zombie> getEnemies_alien_zombie() {
        return enemies_alien_zombie;
    }

    public void setEnemies_alien_zombie(Array<Enemy_Alien_Zombie> enemies_alien_zombie) {
        this.enemies_alien_zombie = enemies_alien_zombie;
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

    public MainWindow getGame() {
        return game;
    }

    public void setGame(MainWindow game) {
        this.game = game;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void setControls(Controls controls) {
        this.controls = controls;
    }

    public Array<Enemy_Alien_Kugus> getEnemies_alien_kugus() {
        return enemies_alien_kugus;
    }

    public void setEnemies_alien_kugus(Array<Enemy_Alien_Kugus> enemies_alien_kugus) {
        this.enemies_alien_kugus = enemies_alien_kugus;
    }
}
