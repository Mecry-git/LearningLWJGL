package Engine.Scenes.Objects.Blocks;

import Engine.Maths.Vector3F;
import org.jetbrains.annotations.NotNull;

public class Block {
    public Vector3F pos;
    public final Image[] images = new Image[6];
    public String[] pics = new String[6];
    public Block(Vector3F pos, @NotNull String type) {
        this.pos = pos;
        switch (type) {
            case "Grass_Block" -> {
                pics[0] = "/Textures/top.png";
                pics[1] = "/Textures/side.png";
                pics[2] = "/Textures/side.png";
                pics[3] = "/Textures/side.png";
                pics[4] = "/Textures/side.png";
                pics[5] = "/Textures/bottom.png";
            }
            case "Dirt_Block" -> {
                pics[0] = "/Textures/bottom.png";
                pics[1] = "/Textures/bottom.png";
                pics[2] = "/Textures/bottom.png";
                pics[3] = "/Textures/bottom.png";
                pics[4] = "/Textures/bottom.png";
                pics[5] = "/Textures/bottom.png";
            }
            //...
        }

        images[0] = new Image(new Vector3F(pos.x + 0.5f, pos.y, pos.z), pics[0], 0);
        images[1] = new Image(new Vector3F(pos.x, pos.y - 0.5f, pos.z), pics[1], 1);
        images[2] = new Image(new Vector3F(pos.x, pos.y, pos.z + 0.5f), pics[2], 2);
        images[3] = new Image(new Vector3F(pos.x, pos.y, pos.z - 0.5f), pics[3], 3);
        images[4] = new Image(new Vector3F(pos.x, pos.y + 0.5f, pos.z), pics[4], 4);
        images[5] = new Image(new Vector3F(pos.x - 0.5f, pos.y, pos.z), pics[5], 5);
    }
}
