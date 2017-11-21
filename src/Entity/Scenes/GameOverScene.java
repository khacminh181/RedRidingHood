package Entity.Scenes;

import Entity.Player.ViewPort;
import Tilemap.Background;
import bases.GameObject;
import bases.inputs.InputManager;
import bases.scenes.Scene;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

import static bases.scenes.SceneManager.changeScene;

public class GameOverScene implements Scene {

    @Override
    public void deinit() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        Clip clip1 = AudioUtils.loadSound("assets/SFX/wolf5.wav");
        AudioUtils.play(clip1);
        Background background = new Background("assets/images/Map1/GameOverScene.png");
        background.position.set(0,0);
        GameObject.add(background);
    }

    @Override
    public void run() {
        if (InputManager.instance.enterPressed) {
            changeScene(new TutScene());
        }

    }

    @Override
    public ViewPort getViewPort() {
        return null;
    }
}
