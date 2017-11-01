package Entity.Enemy;

import Entity.Platform.Platform;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;

public class NormalWolf extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private final float GRAVITY = 0.4f;
    private Vector2D velocity;
    int hP = 5;

    public NormalWolf() {
        super();
        this.renderer = new ImageRenderer("assets/images/Enemies/wolf.png");
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(32, 32);
        this.children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        updatePosition();

    }

    private void updatePosition() {
        velocity.y += GRAVITY;
        move();
        updateVerticalPhysics();
        updateHorizontalPhysics();

    }

    private void move() {
    }

    private void updateVerticalPhysics() {
        Vector2D checkPosition = screenPosition.add(0, velocity.y);
        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);
        if (platform != null) {
            while(Physics.collideWith(screenPosition.add(0, Math.signum(velocity.y)), boxCollider.getWidth(), boxCollider.getHeight(), Platform.class) == null) {
                position.addUp(0, Math.signum(velocity.y));
                screenPosition.addUp(0, Math.signum(velocity.y));
            }
            velocity.y = 0;
        }

        this.position.y += velocity.y;
        this.screenPosition.y += velocity.y;
    }

    private void updateHorizontalPhysics() {
        Vector2D checkPosition = screenPosition.add(velocity.x, 0);
        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);

        if (platform != null) {
            float dx = Math.signum(velocity.x);
            while(Physics.collideWith(screenPosition.add(dx, 0), boxCollider.getWidth(), boxCollider.getHeight(), Platform.class) == null) {
                position.addUp(dx, 0);
                screenPosition.addUp(dx, 0);
            }
            velocity.x = 0;
        }

        this.position.x += velocity.x;
        this.screenPosition.x += velocity.x;
    }

    public void getHit() {
        hP--;
        if (hP <= 0) {
            isActive = false;
        }
    }
}
