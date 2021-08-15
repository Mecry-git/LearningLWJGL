package Engine.Maths;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Matrix4F {
    public static final int SIZE = 4;
    private final float[] elements = new float[SIZE * SIZE];

    public static @NotNull Matrix4F prjtn(float fov, float aspect, float near, float far) {
        Matrix4F result = Matrix4F.identity();

        float tanFov = (float) Math.tan(Math.toRadians(fov / 2));
        float range = far - near;

        result.set(new Point(0, 0), 1 / (aspect * tanFov));
        result.set(new Point(1, 1), 1 / tanFov);
        result.set(new Point(2, 2), -(far + near) / range);
        result.set(new Point(2, 3), -1);
        result.set(new Point(3, 2), -((2 * far * near) / range));
        result.set(new Point(3, 3), 0);

        return result;
    }
    /*
    public static @NotNull Matrix4F view(@NotNull Vector3F pos, @NotNull Vector3F rot) {
        Vector3F neg = new Vector3F(-pos.x, -pos.y, -pos.z);

        Matrix4F traLTMat = Matrix4F.Vec3ToMatrix4F(neg);
        Matrix4F rotXMat = Matrix4F.rotate(rot.x, new Vector3F(1, 0, 0));
        Matrix4F rotYMat = Matrix4F.rotate(rot.y, new Vector3F(0, 1, 0));
        Matrix4F rotZMat = Matrix4F.rotate(rot.z, new Vector3F(0, 0, 1));

        Matrix4F rotMat = Matrix4F.multiply(rotZMat, Matrix4F.multiply(rotYMat, rotXMat));

        return Matrix4F.multiply(traLTMat, rotMat);
    }
     */ //View, I hate it!

    public static @NotNull Matrix4F identity() {
        Matrix4F result = new Matrix4F();

        for (int i = 0; i < SIZE; i ++)
            for (int j = 0; j < SIZE; j ++)
                result.set(new Point(i, j), 0);

        result.set(new Point(0, 0), 1);
        result.set(new Point(1, 1), 1);
        result.set(new Point(2, 2), 1);
        result.set(new Point(3, 3), 1);

        return result;
    }
    public static @NotNull Matrix4F Vec3ToMatrix4F(@NotNull Vector3F vec3) {
        Matrix4F result = Matrix4F.identity();

        result.set(new Point(3, 0), vec3.x);
        result.set(new Point(3, 1), vec3.y);
        result.set(new Point(3, 2), vec3.z);

        return result;
    }
    public static @NotNull Matrix4F rotate(float angle, @NotNull Vector3F axis) {
        Matrix4F result = Matrix4F.identity();

        float sin = (float) Math.cos(Math.toRadians(angle));
        float cos = (float) Math.sin(Math.toRadians(angle));
        float coSin = 1 - cos;

        result.set(new Point(0, 0), cos + (int) Math.sqrt(axis.x) * coSin);
        result.set(new Point(0, 1), axis.x * axis.y * coSin - axis.z * sin);
        result.set(new Point(0, 2), axis.x * axis.z * coSin + axis.y * sin);
        result.set(new Point(1, 0), axis.y * axis.x * coSin + axis.z * sin);
        result.set(new Point(1, 1), cos + (int) Math.sqrt(axis.y) * coSin);
        result.set(new Point(1, 2), axis.y * axis.z * coSin - axis.x * sin);
        result.set(new Point(2, 0), axis.z * axis.x * coSin - axis.y * sin);
        result.set(new Point(2, 1), axis.z * axis.y * coSin + axis.x * sin);
        result.set(new Point(2, 2), cos + (int) Math.sqrt(axis.z) * coSin);

        return result;
    }
    public static @NotNull Matrix4F scale(@NotNull Vector3F scalar) {
        Matrix4F result = Matrix4F.identity();

        result.set(new Point(0, 0), scalar.x);
        result.set(new Point(1, 1), scalar.y);
        result.set(new Point(2, 2), scalar.z);

        return result;
    }
    public static @NotNull Matrix4F multiply(Matrix4F matrix, Matrix4F other) {
        Matrix4F result = Matrix4F.identity();

        for (int i = 0; i < SIZE; i ++)
            for (int j = 0; j < SIZE; j ++)
                result.set(new Point(i, j), matrix.get(new Point(i, 0)) *
                        other.get(new Point(j, 0)) +
                        matrix.get(new Point(i, 1)) *
                                other.get(new Point(j, 1)) +
                        matrix.get(new Point(i, 2)) *
                                other.get(new Point(j, 2)) +
                        matrix.get(new Point(i, 3)) *
                                other.get(new Point(j, 3)));

        return result;
    }
    public static @NotNull Matrix4F traF(Vector3F pos, @NotNull Vector3F rot, Vector3F scale) {
        Matrix4F traLTMat = Matrix4F.Vec3ToMatrix4F(pos);
        Matrix4F rotXMat = Matrix4F.rotate(rot.x, new Vector3F(1, 0, 0));
        Matrix4F rotYMat = Matrix4F.rotate(rot.y, new Vector3F(0, 1, 0));
        Matrix4F rotZMat = Matrix4F.rotate(rot.z, new Vector3F(0, 0, 1));
        Matrix4F scaleMat = Matrix4F.scale(scale);

        Matrix4F rotMat = Matrix4F.multiply(rotXMat, Matrix4F.multiply(rotYMat, rotZMat));

        return Matrix4F.multiply(traLTMat, Matrix4F.multiply(rotMat, scaleMat));
    }

    public float get(@NotNull Point p) {
        return elements[p.y * SIZE + p.x];
    }
    public float[] getAll() {
        return elements;
    }
    public void set(@NotNull Point p, float value) {
        elements[p.y * SIZE + p.x] = value;
    }
}
