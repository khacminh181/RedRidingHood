package Entity.Scenes;

import Entity.Enemy.NormalWolf;
import Entity.HUD.HP;
import Entity.Player.Player;
import Entity.Player.PlayerShootUI;
import Entity.Player.ViewPort;
import Tilemap.Background;
import Tilemap.Map;
import bases.GameObject;
import bases.inputs.InputManager;
import bases.scenes.Scene;
import bases.scenes.SceneManager;
import tklibs.AudioUtils;

public class TutScene implements Scene {
    Player player;
    Background background;
    public ViewPort viewPort;
    HP heart ;
    NormalWolf normalWolf;

    PlayerShootUI playerShootUI;

    @Override
    public void deinit() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        AudioUtils.initialize();
        SceneManager.mediaPlayer = AudioUtils.playMedia("assets/Musics/level1-2.MP3");
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
        Map map = Map.load("assets/Map/Level1Scene.json");
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
    }

    private void addPlayer() {
        player = new Player();
        player.getPosition().set(400, 50);
        playerShootUI = new PlayerShootUI();
        playerShootUI.position.set(40, 550);
        GameObject.add(player);
        GameObject.add(playerShootUI);
    }

    @Override
    public void run() {
        viewPort.follow(player);
        background.getVelocity().set(player.getVelocity());
        heart.hP = player.HP;

        if (InputManager.instance.spacePressed) {
            SceneManager.changeScene(new GamePlayScene());
        }

        if (player.checkDoor) {
            if (InputManager.instance.upPressed) {
                SceneManager.changeScene(new GamePlayScene());
            }
        }
    }

    @Override
    public ViewPort getViewPort() {
        return this.viewPort;
    }
}
