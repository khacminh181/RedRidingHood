package Main;

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
import tklibs.AudioUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class GameWindow extends JFrame {
    private long lastTimeUpdate = -1;
    private ViewPort viewPort;

    private BufferedImage backBufferImage;
    private Graphics2D backBufferGraphics2D;

    InputManager inputManager = InputManager.instance;

    Player player;
    PlayerShootUI playerShootUI;
    NormalWolf normalWolf;
    FlowerWolf flowerWolf;
    Background background;
    HP heart ;

    public final int GAMEPLAY_WIDTH = 800;
    public final int GAMEPLAY_HEIGHT = 600;




    public GameWindow() {
        AudioUtils.initialize();
        AudioUtils.playMedia("assets/Musics/level1-1.mp3");
        setupWindow();
        setupBackBuffer();
        setupInputs();
        this.setVisible(true);

        addBackGround();
        addPlatforms();
        addHUD();
        addEnemy();
        addPlayer();


    }

    private void addHUD() {
        heart = new HP();
        //heart.position.set(200, 200);
        GameObject.add(heart);
    }

    private void addEnemy() {
        //normalwolf
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

    private void addBackGround() {
        background = new Background("assets/images/Map1/background1.png");
        GameObject.add(background);

    }

    private void addPlayer() {
        player = new Player();
        player.getPosition().set(400, 50);
        GameObject.add(player);
        playerShootUI = new PlayerShootUI();
        playerShootUI.position.set(40, 550);
        GameObject.add(playerShootUI);
    }

    private void addPlatforms() {
        Map map = Map.load("assets/Map/mapTest.json");
        map.generate();
    }

    private void setupInputs() {

        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });

    }

    private void setupBackBuffer() {
        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();
    }

    private void setupWindow() {
        this.setSize(GAMEPLAY_WIDTH, GAMEPLAY_HEIGHT);
        this.setTitle("RED RIDING HOOD");
        this.setResizable(false);
        this.viewPort = new ViewPort();
        this.viewPort.getFollowOffset().set(-400 , -500);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void gameLoop() {
        while (true) {

            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.nanoTime();
            }

            long currentTime = System.nanoTime();

            if (currentTime - lastTimeUpdate > 17000000) {

                lastTimeUpdate = currentTime;

                run();
                render();
            }
        }
    }

    private void render() {

        GameObject.renderAll(backBufferGraphics2D, viewPort);

        repaint();
    }

    private void run() {
        GameObject.runAll();
        viewPort.follow(player);
        background.getVelocity().set(player.getVelocity());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backBufferImage, 0, 0, null);
    }
}
