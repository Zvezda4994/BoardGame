public class FastFlexible extends FastPiece {

    public FastFlexible(String name, String colour, int x, int y) {
        super(name, colour, x, y);
    }

    public void move(String direction, int n) {
        int[] pos = getPosition();
        int x = pos[0];
        int y = pos[1];

        switch (direction.toLowerCase()) {
            case "left":
                if (x - n >= 0) {
                    setPosition(x - n, y);
                }
                break;
            case "right":
                if (x + n <= 7) {
                    setPosition(x + n, y);
                }
                break;
            case "up":
                if (y - n >= 0) {
                    setPosition(x, y - n);
                }
                break;
            case "down":
                if (y + n <= 7) {
                    setPosition(x, y + n);
                }
                break;
        }
    }

    @Override
    public String toString() {
        return getName() + getColour() + "FF";
    }

}
