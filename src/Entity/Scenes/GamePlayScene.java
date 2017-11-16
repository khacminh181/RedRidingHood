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
import bases.inputs.InputManager;
import bases.scenes.Scene;
import bases.scenes.SceneManager;
import tklibs.AudioUtils;

public class GamePlayScene implements Scene {
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
        SceneManager.mediaPlayer = AudioUtils.playMedia("assets/Musics/level1-1.mp3");
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
        //NormalWolf
        for (int i = 0; i < 2; i++) {
            normalWolf = GameObject.recycle(NormalWolf.class);
            normalWolf.position.set(30*32 + i * 50, 8*32 - 8);
        }

        NormalWolf normalWolf1 = GameObject.recycle(NormalWolf.class);
        normalWolf1.position.set(151 * 32, 17*32 - 8);

        for (int i = 0; i < 2; i++) {
            normalWolf = GameObject.recycle(NormalWolf.class);
            normalWolf.position.set(169*32 + i * 50, 7*32 - 8);
        }

        for (int i = 0; i < 2; i++) {
            normalWolf = GameObject.recycle(NormalWolf.class);
            normalWolf.position.set(186*32 + i * 50, 8*32 - 8);
        }

        for (int i = 0; i < 2; i++) {
            normalWolf = GameObject.recycle(NormalWolf.class);
            normalWolf.position.set(230*32 + i * 50, 13*32 - 8);
        }

        for (int i = 0; i < 2; i++) {
            normalWolf = GameObject.recycle(NormalWolf.class);
            normalWolf.position.set(229*32 + i * 50, 3*32 - 8);
        }

        NormalWolf normalWolf2 = GameObject.recycle(NormalWolf.class);
        normalWolf2.position.set(188 * 32, 17*32 - 8);



        // flowerwolf
        FlowerWolf flowerWolf0 = GameObject.recycle(FlowerWolf.class);
        flowerWolf0.position.set(2848, 368);
        flowerWolf0.facingRight = true;

        FlowerWolf flowerWolf1 = GameObject.recycle(FlowerWolf.class);
        flowerWolf1.position.set(132*32, 18*32 - 16);
        flowerWolf1.facingRight = false;

        FlowerWolf flowerWolf2 = GameObject.recycle(FlowerWolf.class);
        flowerWolf2.position.set(132*32, 14*32 - 16);
        flowerWolf2.facingRight = false;

        FlowerWolf flowerWolf3 = GameObject.recycle(FlowerWolf.class);
        flowerWolf3.position.set(149*32, 16*32 - 16);
        flowerWolf3.facingRight = true;

        FlowerWolf flowerWolf4 = GameObject.recycle(FlowerWolf.class);
        flowerWolf4.position.set(149*32, 11*32 - 16);
        flowerWolf4.facingRight = true;

        FlowerWolf flowerWolf5 = GameObject.recycle(FlowerWolf.class);
        flowerWolf5.position.set(149*32, 8*32 - 16);
        flowerWolf5.facingRight = true;

        FlowerWolf flowerWolf6 = GameObject.recycle(FlowerWolf.class);
        flowerWolf6.position.set(149*32, 5*32 - 16);
        flowerWolf6.facingRight = true;

        FlowerWolf flowerWolf7 = GameObject.recycle(FlowerWolf.class);
        flowerWolf7.position.set(174*32, 7*32 - 16);
        flowerWolf7.facingRight = true;

        FlowerWolf flowerWolf8 = GameObject.recycle(FlowerWolf.class);
        flowerWolf8.position.set(229*32, 18*32 - 16);
        flowerWolf8.facingRight = false;

        FlowerWolf flowerWolf9 = GameObject.recycle(FlowerWolf.class);
        flowerWolf9.position.set(306*32, 10*32 - 16);
        flowerWolf9.facingRight = true;

//        FlowerWolf flowerWolf10 = GameObject.recycle(FlowerWolf.class);
//        flowerWolf10.position.set(260*32, 7*32 - 16);
//        flowerWolf10.facingRight = true;

        FlowerWolf flowerWolf11 = GameObject.recycle(FlowerWolf.class);
        flowerWolf11.position.set(261*32, 8*32 - 16);
        flowerWolf11.facingRight = false;

        FlowerWolf flowerWolf12 = GameObject.recycle(FlowerWolf.class);
        flowerWolf12.position.set(240*32, 13*32 - 16);
        flowerWolf12.facingRight = true;

        FlowerWolf flowerWolf13 = GameObject.recycle(FlowerWolf.class);
        flowerWolf13.position.set(204*32, 7*32 - 16);
        flowerWolf13.facingRight = false;
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
        heart.hP = player.HP;
        if (InputManager.instance.spacePressed) {
            SceneManager.changeScene(new GameWinScene());
        }
        if (player.checkDoor) {
            if (InputManager.instance.upPressed) {
                SceneManager.changeScene(new GameWinScene());
            }
        }
    }

    @Override
    public ViewPort getViewPort() {
        return this.viewPort;
    }


}
