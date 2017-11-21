package Entity.Scenes;

import Entity.Boss.Boss;
import Entity.Boss.GhostSpawner;
import Entity.Boss.WolfSpawner;
import Entity.Enemy.NormalWolf;
import Entity.HUD.HP;
import Entity.Player.Player;
import Entity.Player.PlayerShootUI;
import Entity.Player.ViewPort;
import Tilemap.Background;
import Tilemap.Map;
import bases.GameObject;
import bases.ParticleEffect;
import bases.Vector2D;
import bases.action.Action;
import bases.action.ActionRepeatForever;
import bases.inputs.InputManager;
import bases.scenes.Scene;
import bases.scenes.SceneManager;
import javafx.util.Duration;
import tklibs.AudioUtils;
import tklibs.Utils;

public class BossScene implements Scene {
    Player player;
    Background background;
    public ViewPort viewPort;
    HP heart ;
    NormalWolf normalWolf;
    Boss boss;
    HP bossHP;
    WolfSpawner wolfSpawner;
    GhostSpawner ghostSpawner;

    PlayerShootUI playerShootUI;

    @Override
    public void deinit() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        AudioUtils.initialize();
        SceneManager.mediaPlayer = AudioUtils.playMedia("assets/Musics/Bats In The Belfry.mp3");
        SceneManager.mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                SceneManager.mediaPlayer.seek(Duration.ZERO);
            }
        });
        SceneManager.mediaPlayer.play();

        addBackGround();
        addPlatform();
        addPlayer();
        addEnemy();
        addBoss();
        addHP();
        viewPort = new ViewPort();
        viewPort.getFollowOffset().set(-400 , -500);
    }

    private void addBoss() {
        boss = new Boss();
        boss.position.set(17 * 32 + 112 + 8 +4 + 2 , 16*32 - 224 + 8 + 8+80 );
        GameObject.add(boss);

    }

    private void addHP() {
        heart = new HP();
        GameObject.add(heart);

    }

    private void addEnemy() {
//        normalWolf = GameObject.recycle(NormalWolf.class);
//        normalWolf.position.set(50, 100);
        wolfSpawner = new WolfSpawner();
        GameObject.add(wolfSpawner);
        ghostSpawner = new GhostSpawner();
        GameObject.add(ghostSpawner);

    }

    private void addPlayer() {
        player = new Player();
        player.getPosition().set(200, 100);
        playerShootUI = new PlayerShootUI();
        playerShootUI.position.set(40, 550);
        GameObject.add(player);
        GameObject.add(playerShootUI);
    }

    private void addPlatform() {
        Map map = Map.load("assets/Map/BossScene.json");
        map.generate();
    }

    private void addBackGround() {
        background = new Background("assets/images/Map1/background1.png");
        GameObject.add(background);
    }

    @Override
    public void run() {
        //viewPort.follow(player);
        //background.getVelocity().set(player.getVelocity());
        heart.hP = player.HP;
        wolfSpawner.bossHP = boss.hP;
        ghostSpawner.bossHP = boss.hP;

        System.out.println(boss.isShooting);
        if (InputManager.instance.cheatPressed) {
            SceneManager.changeScene(new GameWinScene());
        }

        if (player.checkDoor) {
            if (InputManager.instance.upPressed) {
                if (boss.hP <= 0) {
                    SceneManager.changeScene(new GameWinScene());
                }
            }
        }
    }

    @Override
    public ViewPort getViewPort() {
        return this.viewPort;
    }
}
