package Main;

import Entity.Player.PlayerShootUI;
import Entity.Scenes.TutScene;
import bases.GameObject;
import bases.inputs.InputManager;
import bases.scenes.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import static bases.scenes.SceneManager.changeSceneIfNeeded;
import static bases.scenes.SceneManager.currentScene;

public class GameWindow extends JFrame {
    private long lastTimeUpdate = -1;

    private BufferedImage backBufferImage;
    private Graphics2D backBufferGraphics2D;

    InputManager inputManager = InputManager.instance;
    PlayerShootUI playerShootUI;

    public final int GAMEPLAY_WIDTH = 800;
    public final int GAMEPLAY_HEIGHT = 600;

    public GameWindow() {
        setupWindow();
        setupBackBuffer();
        setupInputs();
        this.setVisible(true);
        addPlayer();
        SceneManager.nextScene = new TutScene();
    }

    private void addPlayer() {
        playerShootUI = new PlayerShootUI();
        playerShootUI.position.set(40, 550);
        GameObject.add(playerShootUI);
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

        GameObject.renderAll(backBufferGraphics2D,currentScene.getViewPort());

        repaint();
    }

    private void run() {
        GameObject.runAll();
        changeSceneIfNeeded();
        currentScene.run();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backBufferImage, 0, 0, null);
    }
}
