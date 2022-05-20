package Engine.Scenes.Objects.Blocks;

import Engine.Graphics.Vertex;
import Engine.Maths.Vector3F;

public class Image {
    public Vector3F pos;
    public Vertex[] vertices;
    public int[] indices;
    public String pic;

    public Image(Vector3F pos, String pic, int loc) {
        this.pos = pos;
        switch (loc) {
            case 0 -> {
                vertices = new Vertex().toFloatArray(new float[]{
                        pos.x, pos.y - 0.5f, pos.z - 0.5f, pos.x, pos.y + 0.5f, pos.z - 0.5f,
                        pos.x, pos.y + 0.5f, pos.z + 0.5f, pos.x, pos.y - 0.5f, pos.z + 0.5f,
                }, new float[]{
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                }, new float[]{
                        0, 0, 0, 1, 1, 1, 1, 0
                });
                indices = new int[]{
                        0, 1, 2, 0, 2, 3
                };
            }
            case 1 -> {
                vertices = new Vertex().toFloatArray(new float[]{
                        pos.x + 0.5f, pos.y, pos.z + 0.5f, pos.x + 0.5f, pos.y, pos.z - 0.5f,
                        pos.x - 0.5f, pos.y, pos.z - 0.5f, pos.x - 0.5f, pos.y, pos.z + 0.5f
                }, new float[]{
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                }, new float[]{
                        0, 0, 0, 1, 1, 1, 1, 0
                });
                indices = new int[]{
                        0, 1, 2, 0, 2, 3
                };
            }
            case 2 -> {
                vertices = new Vertex().toFloatArray(new float[]{
                        pos.x - 0.5f, pos.y + 0.5f, pos.z, pos.x + 0.5f, pos.y + 0.5f, pos.z,
                        pos.x + 0.5f, pos.y - 0.5f, pos.z, pos.x - 0.5f, pos.y - 0.5f, pos.z,
                }, new float[]{
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                }, new float[]{
                        0, 0, 0, 1, 1, 1, 1, 0
                });
                indices = new int[]{
                        0, 1, 2, 0, 2, 3
                };
            }
            case 3 -> {
                vertices = new Vertex().toFloatArray(new float[]{
                        pos.x - 0.5f, pos.y + 0.5f, pos.z, pos.x + 0.5f, pos.y + 0.5f, pos.z,
                        pos.x + 0.5f, pos.y - 0.5f, pos.z, pos.x - 0.5f, pos.y - 0.5f, pos.z,
                }, new float[]{
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                }, new float[]{
                        0, 0, 0, 1, 1, 1, 1, 0
                });
                indices = new int[]{
                        0, 3, 2, 0, 2, 1
                };
            }
            case 4 -> {
                vertices = new Vertex().toFloatArray(new float[]{
                        pos.x + 0.5f, pos.y, pos.z + 0.5f, pos.x + 0.5f, pos.y, pos.z - 0.5f,
                        pos.x - 0.5f, pos.y, pos.z - 0.5f, pos.x - 0.5f, pos.y, pos.z + 0.5f
                }, new float[]{
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                }, new float[]{
                        0, 0, 0, 1, 1, 1, 1, 0
                });
                indices = new int[]{
                        0, 3, 2, 0, 2, 1
                };
            }
            case 5 -> {
                vertices = new Vertex().toFloatArray(new float[]{
                        pos.x, pos.y - 0.5f, pos.z - 0.5f, pos.x, pos.y + 0.5f, pos.z - 0.5f,
                        pos.x, pos.y + 0.5f, pos.z + 0.5f, pos.x, pos.y - 0.5f, pos.z + 0.5f,
                }, new float[]{
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                }, new float[]{
                        0, 0, 0, 1, 1, 1, 1, 0
                });
                indices = new int[]{
                        0, 3, 2, 0, 2, 1
                };
            }
        }
        this.pic = pic;
    }
}
