package top.zoyn.particlelib.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * 向量工具类
 *
 * @author Zoyn
 */
public class VectorUtils {

    /**
     * 只通过数字本身相减得到向量, 减少额外克隆的损耗
     *
     * @param start 起点
     * @param end   终点
     * @return {@link Vector}
     */
    public static Vector createVector(Location start, Location end) {
        return new Vector(end.getX() - start.getX(), end.getY() - start.getY(), end.getZ() - start.getZ());
    }

    public static Vector getLeftDirection(Location location) {
        return rotateAroundAxisY(location.getDirection().clone(), 90);
    }

    public static Vector getRightDirection(Location location) {
        return rotateAroundAxisY(location.getDirection().clone(), -90);
    }

    /**
     * 得到一个单位为 1 的向上的向量
     *
     * @return {@link Vector}
     */
    public static Vector getUpVector() {
        return getUpVector(1);
    }

    public static Vector getUpVector(double multiply) {
        return new Vector(0, 1, 0).multiply(multiply);
    }

    public static Vector rotateAroundAxisX(Vector v, double angle) {
        angle = Math.toRadians(angle);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double y = v.getY() * cos - v.getZ() * sin;
        double z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    public static Vector rotateAroundAxisY(Vector v, double angle) {
        angle = -angle;
        angle = Math.toRadians(angle);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static Vector rotateAroundAxisZ(Vector v, double angle) {
        angle = Math.toRadians(angle);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos - v.getY() * sin;
        double y = v.getX() * sin + v.getY() * cos;
        return v.setX(x).setY(y);
    }

    /**
     * This handles non-unit vectors, with yaw and pitch instead of X,Y,Z angles.
     * <p>
     * Thanks to SexyToad!
     * <p>
     * 将一个非单位向量使用yaw和pitch来代替X, Y, Z的角旋转方式
     *
     * @param v            向量
     * @param yawDegrees   yaw的角度
     * @param pitchDegrees pitch的角度
     * @return {@link Vector}
     */
    public static Vector rotateVector(Vector v, float yawDegrees, float pitchDegrees) {
        double yaw = Math.toRadians(-1 * (yawDegrees + 90));
        double pitch = Math.toRadians(-pitchDegrees);

        double cosYaw = Math.cos(yaw);
        double cosPitch = Math.cos(pitch);
        double sinYaw = Math.sin(yaw);
        double sinPitch = Math.sin(pitch);

        double initialX, initialY, initialZ;
        double x, y, z;

        // Z_Axis rotation (Pitch)
        initialX = v.getX();
        initialY = v.getY();
        x = initialX * cosPitch - initialY * sinPitch;
        y = initialX * sinPitch + initialY * cosPitch;

        // Y_Axis rotation (Yaw)
        initialZ = v.getZ();
        initialX = x;
        z = initialZ * cosYaw - initialX * sinYaw;
        x = initialZ * sinYaw + initialX * cosYaw;

        return new Vector(x, y, z);
    }
}
