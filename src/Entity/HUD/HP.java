package Entity.HUD;

import bases.GameObject;
import bases.renderers.ImageRenderer;

public class HP extends GameObject{
    public HP() {
        this.renderer = new ImageRenderer("assets/images/HUD/HP.png");
    }
}
