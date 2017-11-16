package Entity.Scenes;

import Entity.Player.ViewPort;
import Tilemap.Background;
import bases.GameObject;
import bases.scenes.Scene;

public class GameWinScene implements Scene {
    @Override
    public void deinit() {

    }

    @Override
    public void init() {
        Background background = new Background("assets/images/Map1/GameWinScene.png");
        background.position.set(0,0);
        GameObject.add(background);
    }

    @Override
    public void run() {

    }

    @Override
    public ViewPort getViewPort() {
        return null;
    }
}
