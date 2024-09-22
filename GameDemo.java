import java.util.Scanner;

public class GameDemo {
    
    private Board board;
    private Scanner scanner;

    public GameDemo() {
        board = new Board();
        scanner = new Scanner(System.in);
    }

    //start the game
    public void start() {
        System.out.println("Enter a command (type help for details):");

        while (true) {
            System.out.println("Enter a command (type help for details): ");
            String command = scanner.nextLine().toLowerCase();

            String[] parts = command.split(" ");

            //switch case to handle commands from the user
            switch (parts[0]) {
                case "create":
                    handleCreateCommmand(parts);
                    break;
                case "move":
                    handleMoveCommand(parts);
                    break;
                case "print":
                    board.displayBoard();
                    break;
                case "help":
                    printHelp();
                    break;
                case "exit":
                    System.out.println("Done.");
                    return;
                default:
                System.out.println("Invalid command. Type 'help' for a list of commands");
            }
        }
    }

    //handles create function
    private void handleCreateCommmand(String[] parts) {
        if (parts.length < 3) { //checks if there are atleast three arguments
            System.out.println("Error: invalid command");
            return;
        }

        //converts the second and third arguments to integers for x and y coordinates
        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);

        //checks if coordinates are valid
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Error: invalid coordinates. The position is outside the board");
            return;
        }

        //follow up requirements for piece attributes
        System.out.println("Input a name for the new piece: ");
        String name = scanner.nextLine();
        System.out.println("Input a colour for the new piece: ");
        String colour = scanner.nextLine();

        boolean isFast = false;
        boolean isFlexible = false;

         // Check for optional parameters'fast' and 'flexible' for piece
         for (int i = 3; i < parts.length; i++) {
            if (parts[i].equals("fast")) {
                isFast = true;
            } else if (parts[i].equals("flexible")) {
                isFlexible = true;
            }
        }

        //checks what type of piece to create 
        Piece piece;
        if (isFast && isFlexible) {
            piece = new FastFlexible(name, colour, x, y);
        } else if (isFast) {
            piece = new FastPiece(name, colour, x, y);
        } else if (isFlexible) {
            piece = new SlowFlexible(name, colour, x, y);
        } else {
            piece = new SlowPiece(name, colour, x, y);
        }

        board.addPiece(piece, x, y);
    }

    private void handleMoveCommand(String[] parts) {
        if (parts.length < 4) { //checks if there are atleast 4 arguments
            System.out.println("Error: Invalid command. Usage: move x y direction [spaces]");
            return;
        }
    
        //converts second and third arguments to integers for x and y coordinates
        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        String direction = parts[3]; 
        int spaces = 1; // Default to 1 space for slow pieces
    
        if (parts.length == 5) { //if fifth argument present, assign to spaces
            spaces = Integer.parseInt(parts[4]);
        }
    
        // Call the movePiece method from Board class directly with updated parameters
        board.movePiece(x, y, direction, spaces);
    }
    
    
        //handles print command
        private void printHelp() {
            System.out.println("Possible commands are as follows:");
            System.out.println("create location [fast][flexible]: Creates a new piece. The location has to be in the format 'x y' without commas.");
            System.out.println("move location direction [spaces]: Moves a piece. The location has to be in the format 'x y' without commas.");
            System.out.println("print: Displays the board.");
            System.out.println("help: Displays help.");
            System.out.println("exit: Exits the program.");
        }

        //Initialise the game by calling start function
        public static void main(String[] args) {
            GameDemo game = new GameDemo();
            game.start();
        }
}

