package bases.scenes;

import javafx.scene.media.MediaPlayer;

public class SceneManager {
    public static Scene currentScene;
    public static Scene nextScene;
    public static boolean isChangeScene;
    public static MediaPlayer mediaPlayer;


    public static void changeScene(Scene newScene) {
        if (newScene != null) {
            nextScene = newScene;
            isChangeScene = true;
        }
    }

    public static void changeSceneIfNeeded() {
        if (nextScene != null) {
            if (currentScene != null) {
                currentScene.deinit();
            }
            nextScene.init();

            currentScene = nextScene;
            nextScene = null;
            isChangeScene = false;
        }
    }
}
