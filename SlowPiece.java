public class SlowPiece extends Piece{
   
    public SlowPiece(String name, String color, int x, int y) {
        super(name, color, x , y);
    }

    public void move(String direction) {
        int[] pos = getPosition();
        int x = pos[0];
        int y = pos[1];

        if (direction.equalsIgnoreCase("left")) {
            if (x > 0) {
                setPosition(x-1, y);
            } else {
                return;
            }
        } else if (direction.equalsIgnoreCase("right")) {
            if (x < 7) {
                setPosition(x+1, y);
            } else {
                return;
            }
        }
    }

    @Override
    public String toString() {
        return getName() + getColour() + "S"; 
    }
    
}


