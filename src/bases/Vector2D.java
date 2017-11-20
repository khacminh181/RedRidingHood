package bases;

import static java.lang.Math.sqrt;

public class Vector2D {
    public float x;
    public float y;

    public static final Vector2D DOWN = new Vector2D(0, 1);

    public Vector2D() {
        this (0,0);
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void print() {
        System.out.println ("x = "+ this.x + " y = "+ this.y);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D vector2D) {
        set(vector2D.x, vector2D.y);
    }

    public Vector2D clone() {
        return new Vector2D(this.x, this.y);
    }

    public void addUp(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void addUp(Vector2D vector2D) {
        this.addUp(vector2D.x, vector2D.y);
    }


    public Vector2D add(float x, float y) {
        return new Vector2D(this.x + x, this.y + y);
    }

    public Vector2D add(Vector2D vector2D) {
        return add(vector2D.x, vector2D.y);
    }

    public void subtractBy(float x, float y) {
        this.x -= x;
        this.y -= y;
    }

    public void subtractBy(Vector2D vector2D) {
        this.subtractBy(vector2D.x, vector2D.y);
    }

    public Vector2D subtract(float x, float y) {
        return new Vector2D(this.x - x, this.y - y);
    }

    public Vector2D subtract(Vector2D vector2D) {
        return subtract(vector2D.x, vector2D.y);
    }

    public Vector2D multiply (float factor) {
        return new Vector2D(this.x * factor, this.y * factor);
    }

    public float length() {
        return (float)sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2D normalize() {
        float length = length();
        return new Vector2D(this.x / length, this.y / length);
    }

    public Vector2D rotate(float angle) {
        double rad = Math.toRadians(angle);
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);
        return new Vector2D(cos * x - sin * y, sin * x + cos * y);
    }
}
