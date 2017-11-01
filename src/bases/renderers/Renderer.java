package bases.renderers;

import bases.Vector2D;

import java.awt.*;

public interface Renderer {
    void render(Graphics g, Vector2D position);
}
