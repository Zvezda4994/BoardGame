public class Board {

    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
    }

    // Adds a piece to the board if valid
    public void addPiece(Piece piece, int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7 || board[x][y] != null) {
            System.out.println("Error: The entered position is invalid");
        } else {
            board[x][y] = piece;
        }
    }

    // Move the given piece as specified
    public void movePiece(int x, int y, String direction, int n) {
        Piece piece = getPieceAt(x, y);

        if (piece == null) { //checks if piece exists at given position
            System.out.println("Error: No piece exists at the given position.");
            return;
        }

        boolean isFlexible = (piece instanceof SlowFlexible) || (piece instanceof FastFlexible); //checks if piece is a flexible piece

        // Check if the direction is valid for the piece type
        if (!isFlexible && (direction.equalsIgnoreCase("up") || direction.equalsIgnoreCase("down"))) {
            System.out.println("Error: This piece cannot move in this direction.");
            return;
        }

        //new coordinates of the piece
        int newX = x;
        int newY = y;

        // Uses the correct move method depending on piece type
        if (piece instanceof FastPiece) {
            ((FastPiece) piece).move(direction, n);
        } else if (piece instanceof SlowPiece) {
            ((SlowPiece) piece).move(direction);
        }

        // Gets the new position after moving the piece
        int[] newPosition = piece.getPosition();
        newX = newPosition[0];
        newY = newPosition[1];

        // Check if the new position is within the board and not occupied
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
            piece.setPosition(x, y); // Revert to original position if move is invalid
            System.out.println("Error: Move would take the piece off the board.");
            return;
        }

        // Check if the target position is already occupied
        if (board[newX][newY] != null) {
            piece.setPosition(x, y); // Revert to original position if move is invalid
            System.out.println("Error: Target position (" + newX + ", " + newY + ") is already occupied.");
            return;
        }

        // If valid move, update the board
        board[x][y] = null; // Remove piece from original location
        board[newX][newY] = piece; // Place piece at new location
    }

    public Piece getPieceAt(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return null; 
        }
        return board[x][y]; 
    }

    public void displayBoard() {
        int standardWidth = 15; //width for each cell to ensure alignment

        // nested for loop to iterate through the board
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (board[x][y] == null) {
                    System.out.print("-"); // Print "-" to denote empty cell followed by empty spaces to pad the cell length
                    for (int k = 0; k < standardWidth - 1; k++) {
                        System.out.print(" ");
                    }
                } else {
                    //retrieve current piece and convert it to its string representation
                    Piece piece = board[x][y];
                    String pieceString = piece.toString();

                    //Shorten length of the pieceString if it's longer than the standard cell width
                    if (pieceString.length() > standardWidth) {
                        pieceString = pieceString.substring(0, standardWidth);
                    }

                    System.out.print(pieceString); 

                    // Add spaces based on the length of the piece representation
                    for (int k = 0; k < standardWidth - pieceString.length(); k++) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println(); // move to next line after printing columns of current row
        }
    }
}
