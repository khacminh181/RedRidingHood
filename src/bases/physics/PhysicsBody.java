package bases.physics;

import bases.Vector2D;

public interface PhysicsBody {

    BoxCollider getBoxCollider();
    boolean isActive();
    Vector2D getVelocity();
}
