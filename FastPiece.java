public class FastPiece extends Piece {

    public FastPiece(String name, String colour, int x, int y) {
        super(name, colour, x, y);
    }

    public void move(String direction, int n) {
        int[] pos = getPosition();
        int x = pos[0];
        int y = pos[1];

        if (direction.equalsIgnoreCase("left")) {
            if (x - n >= 0) {
                setPosition(x - n, y);
            } else {
                return;
            }
        } else if (direction.equalsIgnoreCase("right")) {
            if (x + n <= 7) {
                setPosition(x + n, y);
            } else {
                return;
            }
        }
    }

    @Override
    public String toString() {
        return getName() + getColour() + "F";
    }

}
