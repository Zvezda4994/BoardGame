public class Piece {

    private String name;
    private String colour;
    private int[] position;

    public Piece(String name, String colour, int x, int y) {
        this.name = name;
        this.colour = colour;
        this.position = new int[] {x, y};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position[0] = x;
        this.position[1] = y;
    }

    public String toString() {
        return name + " " + colour;
    }
}