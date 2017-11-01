package scenes;

public class SceneManager {
    private static Scene currentScene;
    private static Scene nextScene;


    public static void changeScene(Scene newScene) {

        // Remmeber scene transition
        if (nextScene == null) {
            nextScene = newScene;
        }
    }

    public static void changeSceneIfNeeded() {
        if (nextScene != null) {
            // Change scene REQUESTED

//            if (currentScene != null)
//                currentScene.destroy();

            nextScene.init();

            currentScene = nextScene;

            // Clear request
            nextScene = null;
        }
    }
}
