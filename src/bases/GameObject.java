package bases;

import Entity.Player.Player;
import Entity.Player.ViewPort;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Renderer;
import bases.scenes.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class
GameObject {
    public Vector2D position;
    public Vector2D screenPosition;

    public boolean isActive;

    public Renderer renderer;


    protected Vector<GameObject> children;

    public ArrayList<bases.action.Action> actions;
    public ArrayList<bases.action.Action> newActions;

    static Vector<GameObject> gameObjects = new Vector<>();
    static Vector<GameObject> newGameObjects = new Vector<>();

    public static void add (GameObject gameObject) {
        newGameObjects.add(gameObject);
        if (gameObject instanceof PhysicsBody) {
            Physics.add((PhysicsBody) gameObject);
        }
    }

    public GameObject() {
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.children = new Vector<>();
        this.actions = new ArrayList<>();
        this.newActions = new ArrayList<>();
        isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public Vector2D getPosition() {
        return position;
    }


    public static <T extends GameObject>T recycle(Class<T> cls) {
        for (GameObject gameObject : gameObjects) {
            if (!(gameObject.getClass().equals(cls))) continue;
            if (!gameObject.isActive) {
                gameObject.reset();
                return (T) gameObject;
            }
        }

        try {
            T newGameObject = cls.newInstance(); // = new
            add(newGameObject);
            return newGameObject;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void runAll() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive) {
                gameObject.run(new Vector2D(0, 0));
                gameObject.runActions();
            }
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public void run(Vector2D parentPosition) {
        this.screenPosition = parentPosition.add(position);
        for (GameObject child : children) {
            child.run(this.screenPosition);
        }

    }
    public void runActions() {
        this.actions.removeIf(action -> action.run(this));
        this.actions.addAll(newActions);
        newActions.clear();
    }

    public void addAction(bases.action.Action action) {
        newActions.add(action);
    }


    public static void renderAll(Graphics2D g2d, ViewPort viewPort) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive)
                gameObject.render(g2d, viewPort);
        }

    }

    public void render(Graphics2D g2d, ViewPort viewPort) {
        if (renderer != null) {
            renderer.render(g2d, viewPort.translate(this.screenPosition));
        }
        for (GameObject child: children) {
            if (child.isActive)
                child.render(g2d, viewPort);
        }
    }

    public void reset() {
        isActive = true;
    }


    public static void clearAll() {
        gameObjects.clear();
        newGameObjects.clear();
        Physics.clearAll();
        SceneManager.mediaPlayer.dispose();
    }
}
