package Entity.Platform;

import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class Platform extends GameObject implements PhysicsBody{

//    private BoxCollider boxCollider;
//
//    public Platform() {
//        super();
//        this.renderer = new ImageRenderer("assets/images/yellow_square.jpg");
//
//        this.boxCollider = new BoxCollider(32, 32);
//        this.children.add(boxCollider);
//    }
//
//    @Override
//    public BoxCollider getBoxCollider() {
//        return boxCollider;
//    }

    private BoxCollider boxCollider;

    public Platform() {
        super();
        this.renderer = new ImageRenderer("assets/images/yellow_square.jpg");
        this.boxCollider = new BoxCollider(32, 32);
        this.children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public static Platform create(int platformType) {
        Platform platform = new Platform();
        switch (platformType) {
//            case 3:
//                platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/sprite/platform0000.png"));
//                break;
            case 2:
                platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/Map1/grass.png"));
                break;
            case 1:
                platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/Map1/dirt.png"));
                break;
        }

        return platform;
    }
}
