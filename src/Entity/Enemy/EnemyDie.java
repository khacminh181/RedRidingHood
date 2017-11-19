package Entity.Enemy;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

public class EnemyDie extends GameObject {

    public Animation animation;

    public EnemyDie (){
        this.renderer = animation;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (animation.ended) {
            this.isActive = false;
        }
    }

    @Override
    public void reset() {
        super.reset();
        animation.ended = false;
        animation.currentImageIndex = 0;
    }
}
