package bases.scenes;

import Entity.Player.ViewPort;

public interface Scene {
    void deinit ();
    void init ();
    void run();
    ViewPort getViewPort();
}
