package dev.efnilite.gui.util.vector;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * A 2D vector.
 */
public class Vector2D implements Serializable {

    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Converts this Vector2D instance to a default Bukkit one
     *
     * @return this vector but usable in Bukkit applications
     */
    @NotNull
    public Vector toBukkitVector() {
        return new Vector(x, y, 0);
    }

    /**
     * Gets a Vector2D from a Bukkit vector
     *
     * @param   vector
     *          The Bukkit vector to be converted
     *
     * @return a new Vector2D
     */
    public static Vector2D fromBukkit(Vector vector) {
        return new Vector2D(vector.getX(), vector.getY());
    }

    /**
     * Adds another vector to this one by adding all coordinates one by one.
     *
     * @param   other
     *          The other vector.
     *
     * @return the instance of this class with updated coordinates
     */
    @NotNull
    public Vector2D add(@NotNull Vector2D other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    /**
     * Multiplies all coordinates by the same modifier.
     *
     * @param   modifier
     *          The modifier which all coordinates will be multiplied by.
     *
     * @return the instance of this class with updated coordinates
     */
    @NotNull
    public Vector2D multiply(double modifier) {
        this.x *= modifier;
        this.y *= modifier;
        return this;
    }

    /**
     * Subtracts another vector from this one by subtracting all coordinates one by one.
     *
     * @param   other
     *          The other vector.
     *
     * @return the instance of this class with updated coordinates
     */
    @NotNull
    public Vector2D subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    /**
     * Gets the distance to another vector.
     *
     * @param   other
     *          The other vector.
     *
     * @return the distance between the two vectors. Always positive.
     */
    public double distanceTo(@NotNull Vector2D other) {
        double x2 = Math.pow(other.x - x, 2);
        double y2 = Math.pow(other.y - y, 2);
        return Math.sqrt(Math.abs(x2 + y2));
    }

    /**
     * Rotates this vector a specific amount of degrees.
     * Clockwise is negative and counterclockwise is positive.
     *
     * @param   deg
     *          The angle in degrees.
     */
    public void rotate(int deg) {
        double rad =  Math.toRadians(deg * -1);
        double rotatedX = (Math.cos(rad) * x) - (Math.sin(rad) * y); // uses rotation matrix
        double rotatedY = (Math.sin(rad) * x) + (Math.cos(rad) * y);

        this.x = (int) Math.round(rotatedX);
        this.y = (int) Math.round(rotatedY);
    }

    /**
     * Gets the length of this vector
     *
     * @return the length of this vector
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Normalizes this vector instance.
     * All values will be divided by the length.
     *
     * @return the instance of this vector, but normalized
     */
    public Vector2D normalize() {
        double length = length();
        this.x /= length;
        this.y /= length;
        return this;
    }

    /**
     * Gets the current angle in degrees between this vector and the provided vector instance.
     *
     * @param   other
     *          The other vector
     *
     * @return the angle in degrees to the other vector
     */
    public double angle(Vector2D other) {
        if (this == other) {
            return 0;
        }

        double dotProduct = x * other.x + y * other.y;
        double divideBy = length() * other.length();

        return Math.toDegrees(Math.acos(dotProduct / divideBy));
    }

    @NotNull
    public Vector2D setX(int x) {
        this.x = x;
        return this;
    }

    @NotNull
    public Vector2D setY(int y) {
        this.y = y;
        return this;
    }

    @Override
    public Vector2D clone() {
        return new Vector2D(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}