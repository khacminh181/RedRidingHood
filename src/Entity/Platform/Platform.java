package Entity.Platform;

import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class Platform extends GameObject implements PhysicsBody{
    public static final int NORMALTILE = 0;
    public static final int BLOCKTILE = 1;
    public static final int HORNTILE = 2;
    public static final int DOORTILE = 3;


    public int isType;

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

    public BoxCollider boxCollider;

    public Platform() {
        super();
        //this.renderer = new ImageRenderer("assets/images/yellow_square.jpg");
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
            case 4:
                platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/Map1/door.png"));
                platform.isType = DOORTILE;
                break;
            case 3:
                platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/Map1/Horn.png"));
               // platform.children.add(platform.boxCollider);
                platform.isType = HORNTILE;
                break;
            case 2:
                platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/Map1/grass.png"));
                //platform.children.add(platform.boxCollider);
                platform.isType = NORMALTILE;
                break;
            case 1:
                platform.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/Map1/dirt.png"));
                //platform.children.add(platform.boxCollider);
                platform.isType = BLOCKTILE;
                break;
        }

        return platform;
    }

}
