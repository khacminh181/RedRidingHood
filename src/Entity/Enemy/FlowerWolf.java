package Entity.Enemy;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;

public class FlowerWolf extends GameObject implements PhysicsBody {
    BoxCollider boxCollider;
    int hP = 5;


    boolean spellDisabled = false;
    final int COOL_DOWN_TIME = 100;
    int coolDownCount;


    PlayerDamage playerDamage;
    public FlowerWolf() {
        super();
        this.renderer = new ImageRenderer("assets/images/Enemies/flowerwolf.png");
        boxCollider = new BoxCollider(32, 64);
        this.children.add(this.boxCollider);
        playerDamage = new PlayerDamage();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        shoot();
        playerDamage.run(this);
    }

    private void shoot() {
        if (spellDisabled) {
            coolDownCount++;
            if (coolDownCount >= COOL_DOWN_TIME) {
                spellDisabled = false;
                coolDownCount = 0;
            }
            return;
        }

        FlowerWolfBullet newFlowerWolfBullet = GameObject.recycle(FlowerWolfBullet.class);
        newFlowerWolfBullet.position.set(this.position.x, this.position.y - 16);
        spellDisabled = true;

    }

    public void getHit() {
        hP--;
        if (hP <= 0) {
            isActive = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
