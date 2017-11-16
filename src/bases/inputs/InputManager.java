package bases.inputs;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class InputManager {
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public static boolean xPressed; // bullet
    public boolean zPressed; // glide
    public boolean cPressed; //jump
    public boolean spacePressed; // fireball
    public boolean enterPressed;
    public static boolean shootPressed;


    public static final InputManager instance = new InputManager();

    public InputManager() {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_RIGHT:
                rightPressed = true;
                break;
            case VK_LEFT:
                leftPressed = true;
                break;
            case VK_UP:
                upPressed = true;
                break;
            case VK_DOWN:
                downPressed = true;
                break;
            case VK_X:
                shootPressed = true;
                break;
            case VK_Z:
                zPressed = true;
                break;
            case VK_C:
                cPressed = true;
                break;
            case VK_SPACE:
                spacePressed = true;
                break;
            case VK_ENTER:
                enterPressed = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_RIGHT:
                rightPressed = false;
                break;
            case VK_LEFT:
                leftPressed = false;
                break;
            case VK_UP:
                upPressed = false;
                break;
            case VK_DOWN:
                downPressed = false;
                break;
            case VK_X:
                xPressed = true;
                shootPressed = false;
                break;
            case VK_Z:
                zPressed = false;
                break;
            case VK_C:
                cPressed = false;
                break;
            case VK_SPACE:
                spacePressed = false;
                break;
            case VK_ENTER:
                enterPressed = false;
                break;
        }
    }
}
