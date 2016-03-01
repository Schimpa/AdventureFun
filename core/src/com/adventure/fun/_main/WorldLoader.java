package com.adventure.fun._main;

import com.adventure.fun.controls.Controls;
import com.adventure.fun.effects.Particles;
import com.adventure.fun.items.Item_Health;
import com.adventure.fun.items.Item_Score;
import com.adventure.fun.items.Item_Weapon_Green;
import com.adventure.fun.objects.Enemy_Alien_Bigmama;
import com.adventure.fun.objects.Enemy_Alien_Kugus;
import com.adventure.fun.objects.Enemy_Alien_Takel;
import com.adventure.fun.objects.Enemy_Alien_Fingus;
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

import box2dLight.RayHandler;


/**
 * Created by Dennis on 29.10.2015.
 */
public class WorldLoader {

    //Spieler
    private Player player;
    private Array<Enemy_Alien_Fingus> enemies_alien_zombie;
    private Array<Enemy_Alien_Takel> enemies_alien_takel;
    private Array<Enemy_Alien_Kugus> enemies_alien_kugus;
    private Array<Enemy_Alien_Bigmama> enemies_alien_bigmama;

    private MainWindow game;
    private GameScreen gameScreen;

    //Items
    private Item_Score items_Score_100;
    private Item_Score items_Score_200;
    private Item_Score items_Score_500;
    private Item_Score items_Score_1000;

    private Item_Weapon_Green items_Weapon_Green;

    private Item_Health items_Health_1;


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

    private int[] backgroundLayers,foregroundLayers;

    public void dispose(){
        world.dispose();
        for(Enemy_Alien_Fingus enemyAlienZombie : enemies_alien_zombie){
            enemyAlienZombie.dispose();
        }
        for(Enemy_Alien_Takel enemyAlienTakel : enemies_alien_takel){
            enemyAlienTakel.dispose();
        }
        for(Enemy_Alien_Kugus enemyAlienKugus : enemies_alien_kugus){
            enemyAlienKugus.dispose();
        }
        for(Enemy_Alien_Bigmama enemyAlienBigmama : enemies_alien_bigmama){
            enemyAlienBigmama.dispose();
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

        items_Score_100 = new Item_Score(this,100);
        items_Score_200 = new Item_Score(this,200);
        items_Score_500 = new Item_Score(this,500);
        items_Score_1000 = new Item_Score(this,1000);
        items_Weapon_Green = new Item_Weapon_Green(this);

        items_Health_1 = new Item_Health(this,1);

        enemies_alien_zombie = new Array<Enemy_Alien_Fingus>();
        enemies_alien_takel = new Array<Enemy_Alien_Takel>();
        enemies_alien_kugus = new Array<Enemy_Alien_Kugus>();
        enemies_alien_bigmama = new Array<Enemy_Alien_Bigmama>();

        controls = new Controls(this);

        world = new World(new Vector2(0,-15f), true);

        backgroundLayers = new int[]{0,1};
        foregroundLayers = new int[]{2,3};

        createLights();

        createMap();

        //game.getAssets().getMusic_ambient().play();
        //game.getAssets().getMusic_ambient().setVolume(0.2f);
        world.setContactListener(new CollisionListener(this.gameScreen));

    }

    public void createLights(){

        //Umgebungslicht
        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(true);

        rayHandler = new RayHandler(world);

        rayHandler.setAmbientLight(0.2f, 0.2f, 0.2f, 0.5f);
        rayHandler.setBlurNum(1);
    }

    public void renderMap(SpriteBatch batch){
        renderer.render(backgroundLayers);
        batch.begin();

        for (Enemy_Alien_Fingus enemyAlienZombie : enemies_alien_zombie){
            enemyAlienZombie.render(batch);
        }
        for (Enemy_Alien_Takel enemyAlienTakel : enemies_alien_takel){
            enemyAlienTakel.render(batch);
        }
        for(Enemy_Alien_Kugus enemyAlienKugus : enemies_alien_kugus){
            enemyAlienKugus.render(batch);
        }
        for(Enemy_Alien_Bigmama enemyAlienBigmama : enemies_alien_bigmama){
            enemyAlienBigmama.render(batch);
        }

        items_Score_100.render(batch);
        items_Score_200.render(batch);
        items_Score_500.render(batch);
        items_Score_1000.render(batch);
        items_Weapon_Green.render(batch);

        items_Health_1.render(batch);

        player.render(batch);


        renderParticles(batch, Gdx.graphics.getDeltaTime());
        batch.end();
        renderer.render(foregroundLayers);
        //renderer.render();


        if (gameScreen.isActivateLights() == true){
            rayHandler.setCombinedMatrix(gameScreen.getCamera().getPlayerCamera());
            rayHandler.render();
        }


    }

    public void renderParticles(SpriteBatch batch,float deltaTime){
        for (Enemy_Alien_Fingus enemyAlienZombie : enemies_alien_zombie){
            enemyAlienZombie.getParticles().render(batch,deltaTime);
        }
        for (Enemy_Alien_Takel enemyAlienTakel : enemies_alien_takel){
            enemyAlienTakel.getParticles().render(batch,deltaTime);
        }
        for(Enemy_Alien_Kugus enemyAlienKugus : enemies_alien_kugus){
            enemyAlienKugus.getParticles().render(batch,deltaTime);
        }
        for(Enemy_Alien_Bigmama enemyAlienBigmama : enemies_alien_bigmama){
            enemyAlienBigmama.getParticles().render(batch,deltaTime);
        }

        player.getParticles().render(batch,deltaTime);
    }

    public void updateWorld(float deltaTime){
        controls.update();
        player.update(deltaTime);

        for(Enemy_Alien_Fingus enemyAlienZombie : enemies_alien_zombie){
            enemyAlienZombie.update(deltaTime);
        }
        for(Enemy_Alien_Takel enemyAlienTakel : enemies_alien_takel){
            enemyAlienTakel.update(deltaTime);
        }
        for(Enemy_Alien_Kugus enemyAlienKugus : enemies_alien_kugus){
            enemyAlienKugus.update(deltaTime);
        }
        for(Enemy_Alien_Bigmama enemyAlienBigmama : enemies_alien_bigmama){
            enemyAlienBigmama.update(deltaTime);
        }

        items_Score_100.checkDestruction();
        items_Score_200.checkDestruction();
        items_Score_500.checkDestruction();
        items_Score_1000.checkDestruction();

        items_Health_1.checkDestruction();

        items_Weapon_Green.checkDestruction();

        rayHandler.update();
        world.step(deltaTime, 60, 20);
    }

    //Lädt die Karte aus Tiled und erstellt Physik mit Box2d
    public void createMap(){
        map = new TmxMapLoader().load(this.getGameScreen().getLevelName());
        renderer = new OrthogonalTiledMapRenderer(map, 1/32f);

        //PLAYER SPAWN

        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            player = new Player(game,rect.getX() / 32 + rect.getWidth() / 2 / 32,rect.getY() / 32 + rect.getHeight() / 2 / 32,1.6f,3.4f,world);
        }

        //COLLISION
        int i = 0;
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

        //ITEM - SCORE - 100
        i = 0;
        for(MapObject object: map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() / 32 + rect.getWidth() / 2 / 32, rect.getY() / 32 + rect.getHeight() / 2 / 32);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.01f,0.01f);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.isSensor = true;
            fdef.restitution = 0;
            fdef.filter.groupIndex = (short)-3;


            Body body = world.createBody(bdef);
            body.setUserData("Item_Score_100_"+i);
            body.createFixture(fdef);

            items_Score_100.getItems().add(body);
            items_Score_100.getItems_texture().add(items_Score_100.createSpriteForBody(rect, game.getAssets().getItem_Score_100()));

            i++;
        }

        //ITEM - SCORE - 200
        i = 0;
        for(MapObject object: map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() / 32 + rect.getWidth() / 2 / 32, rect.getY() / 32 + rect.getHeight() / 2 / 32);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.01f,0.01f);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.isSensor = true;
            fdef.restitution = 0;
            fdef.filter.groupIndex = (short)-3;


            Body body = world.createBody(bdef);
            body.setUserData("Item_Score_200_"+i);
            body.createFixture(fdef);

            items_Score_200.getItems().add(body);
            items_Score_200.getItems_texture().add(items_Score_200.createSpriteForBody(rect, game.getAssets().getItem_Score_200()));

            i++;
        }

        //ITEM - SCORE - 500
        i = 0;
        for(MapObject object: map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() / 32 + rect.getWidth() / 2 / 32, rect.getY() / 32 + rect.getHeight() / 2 / 32);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.01f,0.01f);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.isSensor = true;
            fdef.restitution = 0;
            fdef.filter.groupIndex = (short)-3;


            Body body = world.createBody(bdef);
            body.setUserData("Item_Score_500_"+i);
            body.createFixture(fdef);

            items_Score_500.getItems().add(body);
            items_Score_500.getItems_texture().add(items_Score_500.createSpriteForBody(rect, game.getAssets().getItem_Score_500()));

            i++;
        }

        //ITEM - SCORE - 1000
        i = 0;
        for(MapObject object: map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() / 32 + rect.getWidth() / 2 / 32, rect.getY() / 32 + rect.getHeight() / 2 / 32);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.01f,0.01f);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.isSensor = true;
            fdef.restitution = 0;
            fdef.filter.groupIndex = (short)-3;


            Body body = world.createBody(bdef);
            body.setUserData("Item_Score_1000_"+i);
            body.createFixture(fdef);

            items_Score_1000.getItems().add(body);
            items_Score_1000.getItems_texture().add(items_Score_1000.createSpriteForBody(rect, game.getAssets().getItem_Score_1000()));

            i++;
        }

        // ITEM - HEALTH - 1
        i = 0;
        for(MapObject object: map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() / 32 + rect.getWidth() / 2 / 32, rect.getY() / 32 + rect.getHeight() / 2 / 32);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.01f,0.01f);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.isSensor = true;
            fdef.restitution = 0;
            fdef.filter.groupIndex = (short)-3;


            Body body = world.createBody(bdef);
            body.setUserData("Item_Health_1_"+i);
            body.createFixture(fdef);

            items_Health_1.getItems().add(body);
            items_Health_1.getItems_texture().add(items_Health_1.createSpriteForBody(rect, game.getAssets().getItem_Health()));

            i++;
        }

        i=0;
        //ITEM WEAPON GREEN
        for(MapObject object: map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() / 32 + rect.getWidth() / 2 / 32, rect.getY() / 32 + rect.getHeight() / 2 / 32);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.01f,0.01f);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.isSensor = true;
            fdef.restitution = 0;
            fdef.filter.groupIndex = (short)-3;


            Body body = world.createBody(bdef);
            body.setUserData("Item_Weapon_Green_"+i);
            body.createFixture(fdef);

            items_Weapon_Green.getItems().add(body);
            items_Weapon_Green.getItems_texture().add(items_Weapon_Green.createSpriteForBody(rect, game.getAssets().getItem_weapon_green()));

            i++;
        }
        i = 0;
        /*
        for(MapObject object: map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() / 32 + rect.getWidth() / 2 / 32, rect.getY() / 32 + rect.getHeight() / 2 / 32);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.01f,0.01f);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.isSensor = true;
            fdef.restitution = 0;
            fdef.filter.groupIndex = (short)-3;


            Body body = world.createBody(bdef);
            body.setUserData("Item_Weapon_Green_"+i);
            body.createFixture(fdef);

            items_Weapon_Green.getItems().add(body);
            items_Weapon_Green.getItems_texture().add(items_Weapon_Green.createSpriteForBody(rect, game.getAssets().getItem_weapon_green()));

            i++;
        }
        */

        //ENEMY-ALIEN-FINGUS
        i = 0;
        for(MapObject object: map.getLayers().get(16).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            Enemy_Alien_Fingus newEnemyAlienZombie = new Enemy_Alien_Fingus(game,rect.getX() / 32 + rect.getWidth() / 2 / 32,rect.getY() / 32 + rect.getHeight() / 2 / 32,2f,3.4f,world,this.player);
            newEnemyAlienZombie.getBody().setUserData("Enemy_"+i);
            enemies_alien_zombie.add(newEnemyAlienZombie);
            i++;
        }

        //ENEMY-ALIEN-TAKEL
        int j = 0;
        for(MapObject object: map.getLayers().get(17).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            Enemy_Alien_Takel newEnemyAlienTakel = new Enemy_Alien_Takel(game,rect.getX() / 32 + rect.getWidth() / 2 / 32,rect.getY() / 32 + rect.getHeight() / 2 / 32,1.6f,3.4f,world,this.player);
            newEnemyAlienTakel.getBody().setUserData("Enemy_"+i);
            newEnemyAlienTakel.getBullet().getBody().setUserData("Bullet_Enemy_Takel_"+j);
            enemies_alien_takel.add(newEnemyAlienTakel);
            i++;
            j++;
        }

        i = 0;
        //ENEMY ALIEN KUGUS
        for(MapObject object: map.getLayers().get(18).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            Enemy_Alien_Kugus newEnemyAlienKugus = new Enemy_Alien_Kugus(game,rect.getX() / 32 + rect.getWidth() / 2 / 32,rect.getY() / 32 + rect.getHeight() / 2 / 32,2.4f,4.4f,world,this.player);
            newEnemyAlienKugus.getBody().setUserData("Enemy_Alien_Kugus_"+i);
            newEnemyAlienKugus.setBodyNumber(i);
            enemies_alien_kugus.add(newEnemyAlienKugus);
            i++;
        }

        //ENEMY ALIEN BIGMAMA
        i = 0;
        for(MapObject object: map.getLayers().get(19).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            Enemy_Alien_Bigmama newEnemyAlienBigmama = new Enemy_Alien_Bigmama(game,rect.getX() / 32 + rect.getWidth() / 2 / 32,rect.getY() / 32 + rect.getHeight() / 2 / 32,4f,8f,world,this.player);
            newEnemyAlienBigmama.getBody().setUserData("Enemy_Alien_Bigmama_" + i);
            newEnemyAlienBigmama.getBullet().getBody().setUserData("Bullet_Enemy_Bigmama_"+i);
            enemies_alien_bigmama.add(newEnemyAlienBigmama);
            i++;
        }
    }

    public Array<Enemy_Alien_Takel> getEnemies_alien_takel() {
        return enemies_alien_takel;
    }

    public void setEnemies_alien_takel(Array<Enemy_Alien_Takel> enemies_alien_takel) {
        this.enemies_alien_takel = enemies_alien_takel;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public void setRayHandler(RayHandler rayHandler) {
        this.rayHandler = rayHandler;
    }

    public Array<Enemy_Alien_Fingus> getEnemies_alien_zombie() {
        return enemies_alien_zombie;
    }

    public void setEnemies_alien_zombie(Array<Enemy_Alien_Fingus> enemies_alien_zombie) {
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

    public Item_Score getItems_Score_1000() {
        return items_Score_1000;
    }

    public void setItems_Score_1000(Item_Score items_Score_1000) {
        this.items_Score_1000 = items_Score_1000;
    }

    public Item_Score getItems_Score_200() {
        return items_Score_200;
    }

    public void setItems_Score_200(Item_Score items_Score_200) {
        this.items_Score_200 = items_Score_200;
    }

    public Item_Score getItems_Score_500() {
        return items_Score_500;
    }

    public void setItems_Score_500(Item_Score items_Score_500) {
        this.items_Score_500 = items_Score_500;
    }

    public Item_Score getItems_Score_100() {
        return items_Score_100;
    }

    public void setItems_Score_100(Item_Score items_Score_100) {
        this.items_Score_100 = items_Score_100;
    }

    public Array<Enemy_Alien_Bigmama> getEnemies_alien_bigmama() {
        return enemies_alien_bigmama;
    }

    public void setEnemies_alien_bigmama(Array<Enemy_Alien_Bigmama> enemies_alien_bigmama) {
        this.enemies_alien_bigmama = enemies_alien_bigmama;
    }

    public Item_Health getItems_Health_1() {
        return items_Health_1;
    }

    public void setItems_Health_1(Item_Health items_Health_1) {
        this.items_Health_1 = items_Health_1;
    }

    public int[] getBackgroundLayers() {
        return backgroundLayers;
    }

    public void setBackgroundLayers(int[] backgroundLayers) {
        this.backgroundLayers = backgroundLayers;
    }

    public int[] getForegroundLayers() {
        return foregroundLayers;
    }

    public void setForegroundLayers(int[] foregroundLayers) {
        this.foregroundLayers = foregroundLayers;
    }

    public Item_Weapon_Green getItems_Weapon_Green() {
        return items_Weapon_Green;
    }

    public void setItems_Weapon_Green(Item_Weapon_Green items_Weapon_Green) {
        this.items_Weapon_Green = items_Weapon_Green;
    }
}
