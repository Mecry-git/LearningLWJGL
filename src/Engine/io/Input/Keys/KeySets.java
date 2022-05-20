package Engine.io.Input.Keys;

public class KeySets {
    private final int []keys;

    public KeySets(int []keys) {
        this.keys = keys;
    }

    public int getKey(int index) {
        return keys[index];
    }
    public void setKey(int index, int key) {
        this.keys[index] = key;
    }
}
