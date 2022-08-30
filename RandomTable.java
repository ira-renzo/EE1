public class RandomTable {
    private String[][] table;
    private int x_size;
    private int y_size;

    public RandomTable(Dimensions dimensions) {
        reset(dimensions);
    }

    public String search(String keyword) {
        StringBuilder rtn_val = new StringBuilder();
        int total = 0;
        for (int i = 0; i < y_size; i++) {
            for (int j = 0; j < x_size; j++) {
                int occurrence = 0;
                int cur_index = 0;
                while ((cur_index = table[i][j].indexOf(keyword, cur_index)) != -1) {
                    occurrence++;
                    cur_index++;
                }
                if (occurrence > 0) {
                    rtn_val.append(String.format("Found \"%s\" On (y: %d, x: %d) With %d Instance/s\n", keyword, i, j, occurrence));
                }
                total += occurrence;
            }
        }
        if (total == 0) return "No Occurrence";
        return rtn_val.toString().trim();
    }

    public void edit(Coordinates coordinates, String replacement) {
        table[coordinates.getY()][coordinates.getX()] = replacement;
    }

    public String print() {
        StringBuilder rtn_val = new StringBuilder();
        for (int i = 0; i < y_size; i++) {
            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < x_size; j++) {
                tmp.append(String.format("%-15s", table[i][j]));
            }
            rtn_val.append(tmp).append("\n");
        }
        return rtn_val.toString().trim();
    }

    public void reset(Dimensions dimensions) {
        this.x_size = dimensions.getSizeOfX();
        this.y_size = dimensions.getSizeOfY();
        table = new String[this.y_size][this.x_size];
        for (int i = 0; i < this.y_size; i++) {
            for (int j = 0; j < this.x_size; j++) {
                int length = (int) (Math.random() * 5) + 3;
                table[i][j] = randomString(length);
            }
        }
    }

    private String randomString(int length) {
        StringBuilder rtn_val = new StringBuilder();
        for (int i = 0; i < length; i++) {
            rtn_val.append((char) ((int) (Math.random() * 93) + 33));
        }
        return rtn_val.toString();
    }

    public int getSizeOfX() {
        return x_size;
    }

    public int getSizeOfY() {
        return y_size;
    }
}