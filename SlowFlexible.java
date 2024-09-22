public class SlowFlexible extends SlowPiece {
    
    public SlowFlexible(String name, String colour, int x, int y) {
        super(name, colour, x, y);
    }

    public void move(String direction) {
        int[] pos = getPosition();
        int x = pos[0];
        int y = pos[1];

        switch (direction.toLowerCase()) {
            case "left":
                if (x > 0) {
                    setPosition(x-1, y);
                }
                break;
            case "right":
                if (x < 7) {
                    setPosition(x+1, y);
                }
                break;
            case "up":
                if (y > 0) {
                    setPosition(x, y-1);
                }
                break;
            case "down":
                if (y < 7) {
                    setPosition(x, y+1);
                }
                break;
        }
    }

    @Override
    public String toString() {
        return getName() + getColour() + "SF"; // e.g., "CloneBlueSF"
    }

}
