package bases.scenes;

public class SceneManager {
    public static Scene currentScene;
    public static Scene nextScene;


    public static void changeScene(Scene newScene) {
        if (newScene != null)
        nextScene = newScene;
    }

    public static void changeSceneIfNeeded() {
        if (nextScene != null) {
            if (currentScene != null) {
                currentScene.deinit();
            }
            nextScene.init();

            currentScene = nextScene;
            nextScene = null;
        }
    }
}
