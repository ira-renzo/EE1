public class Coordinates {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int setX(int x, int indexLimit) {
        if (x >= indexLimit || x < 0) return -1;
        this.x = x;
        return 1;
    }

    public int getY() {
        return y;
    }

    public int setY(int y, int indexLimit) {
        if (y >= indexLimit || y < 0) return -1;
        this.y = y;
        return 1;
    }
}