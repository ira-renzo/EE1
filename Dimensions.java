public class Dimensions {
    private int x_size;
    private int y_size;

    public int getSizeOfX() {
        return x_size;
    }

    public int setSizeOfX(int x_size) {
        if (x_size <= 0) return -1;
        this.x_size = x_size;
        return 1;
    }

    public int getSizeOfY() {
        return y_size;
    }

    public int setSizeOfY(int y_size) {
        if (y_size <= 0) return -1;
        this.y_size = y_size;
        return 1;
    }
}