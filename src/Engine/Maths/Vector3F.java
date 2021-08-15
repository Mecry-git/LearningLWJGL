package Engine.Maths;

public class Vector3F {
    public float x, y, z;

    public Vector3F() {
        this(0.0f, 0.0f, 0.0f);
    }
    public Vector3F(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Vector3F vector3F) {
        x += vector3F.x;
        y += vector3F.y;
        z += vector3F.z;
    }
}
