package bases;

import Entity.Platform.Platform;
import Entity.Player.ViewPort;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;

import java.awt.*;
import java.util.Random;

public class ParticleEffect extends GameObject implements PhysicsBody{
    private BoxCollider boxCollider;
    public Vector2D velocity;
    private final float GRAVITY = 0.5f;

    FrameCounter frameCounter;

    public ParticleEffect() {
        velocity = new Vector2D();
        generateAngle();
        frameCounter = new FrameCounter(600);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        updatePhysics();
        deactiveParticleIfNeed();
    }

    private void updatePhysics() {
        velocity.y += GRAVITY;
        updateHorizontalPhysics();
        updateVerticalPhysics();
    }

    private void updateVerticalPhysics() {
        Vector2D checkPosition = screenPosition.add(0, velocity.y);
        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);
        if (platform != null) {
            while(Physics.collideWith(screenPosition.add(0, Math.signum(velocity.y)), boxCollider.getWidth(), boxCollider.getHeight(), Platform.class) == null) {
                position.addUp(0, Math.signum(velocity.y));
                screenPosition.addUp(0, Math.signum(velocity.y));
            }
            velocity.x = 0;
            velocity.y = 0;
        }

        this.position.y += velocity.y;
        this.screenPosition.y += velocity.y;
    }

    private void updateHorizontalPhysics() {
        Vector2D checkPosition = screenPosition.add(velocity.x, 0);
        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);

//        if (platform != null) {
//            float dx = Math.signum(velocity.x);
//            while(Physics.collideWith(screenPosition.add(dx, 0), boxCollider.getWidth(), boxCollider.getHeight(), Platform.class) == null) {
//                position.addUp(dx, 0);
//                screenPosition.addUp(dx, 0);
//            }
//            velocity.x = 0;
//        }

        this.position.x += velocity.x;
        this.screenPosition.x += velocity.x;
    }

    public void generateAngle(){
        Random rdm = new Random();
        velocity.x =  (rdm.nextInt(5) );
        velocity.y =  (rdm.nextInt(10) * -1);
        int ind = rdm.nextInt(3);
        if (ind == 1) {
            this.renderer = new ImageRenderer("assets/images/Particle Effect/blood_4.png");
            this.boxCollider = new BoxCollider(4, 4);

        }
        else if (ind == 2) {
            this.renderer = new ImageRenderer("assets/images/Particle Effect/blood_7.png");
            this.boxCollider = new BoxCollider(7, 7);

        }
        else {
            this.renderer = new ImageRenderer("assets/images/Particle Effect/blood_2.png");
            this.boxCollider = new BoxCollider(2, 2);
        }

    }
    @Override
    public void render(Graphics2D g2d, ViewPort viewPort) {
        super.render(g2d, viewPort);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return null;
    }

    @Override
    public Vector2D getVelocity() {
        return new Vector2D();
    }

    public void deactiveParticleIfNeed() {
        if (frameCounter.run()) {
            this.isActive = false;
            frameCounter.reset();
        }
    }
}
