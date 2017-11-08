package Entity.Scenes;

import Entity.Enemy.FlowerWolf;
import Entity.Enemy.NormalWolf;
import Entity.HUD.HP;
import Entity.Player.Player;
import Entity.Player.PlayerShootUI;
import Entity.Player.ViewPort;
import Tilemap.Background;
import Tilemap.Map;
import bases.GameObject;
import bases.scenes.Scene;

public class GamePlayScene implements Scene {
    Player player;
    Background background;
    public ViewPort viewPort;
    HP heart ;
    NormalWolf normalWolf;
    FlowerWolf flowerWolf;

    PlayerShootUI playerShootUI;
    @Override
    public void deinit() {

    }

    @Override
    public void init() {
        addBackGround();
        addPlatform();
        addPlayer();
        addEnemy();
        addHP();

        viewPort = new ViewPort();
        viewPort.getFollowOffset().set(-400 , -500);

    }

    private void addBackGround() {
        background = new Background("assets/images/Map1/background1.png");
        GameObject.add(background);
    }

    private void addPlatform() {
        Map map = Map.load("assets/Map/mapTest.json");
        map.generate();
    }

    private void addHP() {
        heart = new HP();
        GameObject.add(heart);
    }

    private void addEnemy() {
        for (int i = 0; i < 3; i++) {
            normalWolf = new NormalWolf();
            normalWolf.position.set(500 + i * 50, 50);
            GameObject.add(normalWolf);
        }
        // flowerwolf
        flowerWolf = new FlowerWolf();
        flowerWolf.position.set(2848, 368);
        GameObject.add(flowerWolf);
    }

    private void addPlayer() {
        player = new Player();
        player.getPosition().set(400, 50);
        playerShootUI = new PlayerShootUI();
        playerShootUI.position.set(40, 550);
        GameObject.add(player);
        GameObject.add(playerShootUI);
    }

    public void run () {
        viewPort.follow(player);
        background.getVelocity().set(player.getVelocity());
    }


}
