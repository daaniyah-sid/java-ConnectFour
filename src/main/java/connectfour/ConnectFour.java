package connectfour;
/**
 * @author Daaniyah Siddiqui
 *The class is used to manage turns,
 * call Board methods to update state,
 * detects win conditions, passes
 * messages to textUI
 */
public class ConnectFour {

    private Board board;
    private TextUI textUI;
    private ConnectFourFileManager fileManager;
    private int numberOfTurns = 0;
    private int currentPlayer = 1;
    private int alternativePlayer = 2;

    //declare constructors
    public ConnectFour() {
        this.board = new Board();
        this.textUI = new TextUI();
        this.fileManager = new ConnectFourFileManager();
    }
    public static void main(String[] args) {
        new ConnectFour().play();
    }
    public void play() {
        textUI.printWelcomeMessage();
        loadGame();
        takeTurns();
    }
    //player positions are swapped using temp variable
    private void swapPlayers() {
        int temp = currentPlayer;
        currentPlayer = alternativePlayer;
        alternativePlayer = temp;
    }
    //method to load data from previously saved file
    private void loadGame() {
        if(!textUI.getUserChoiceForLoadingGame().equals("Y")) {
            return;
        }

        //declares file name from input as string
        String fileName = textUI.getFileName();

        try {
            //sets state of board from loaded file
            String boardState = fileManager.loadBoardStateFromFile(fileName);
            textUI.printSuccessfulFileLoad();
            //sets number of turns by counting used cells in loaded file
            numberOfTurns = board.setBoardFromStateStringAndReturnUsedCells(boardState);
            //computes which players turn to continue from
            if (numberOfTurns % 2 != 0){
                swapPlayers();
            }
            //prints error message if file is not found
        }catch(Exception error){
            textUI.printErrorMessage(error.getMessage());
        }
    }
    //method to save board state to a file
    private void saveGame() {
        if (!textUI.getUserChoiceForSavingGame().equals("Y")){
            return;
        }

        //declares file name entered by user as a string
        String filePath = textUI.getFileName();
        try {
            //saves the board state to file by passing the string representation of the board
            fileManager.saveBoardStateToFile(board.getStringRepresentation(), filePath);
            textUI.printSuccessfulFileSave();
            //prints error message if unable to save
        }catch(Exception error){
            textUI.printErrorMessage(error.getMessage());
        }
    }
    //sets board with column input from player
    private void takeCurrentPlayerTurn() {
        while (true) {
            int column = textUI.getColumns();
            try {
                //subtracts from column array position to match input
                board.setState(column - 1, currentPlayer);
                break;
            } catch (Exception exception) {
                textUI.printIndexErrorMessage();
            }
        }
    }

    private void takeTurns() {
        //prints the board to display
        textUI.printBoard(board.getStringRepresentation());
        //loops till total number of turns is less than total cells of board
        while (numberOfTurns < board.getNumberOfCells()) {
            textUI.printCurrentPlayer(currentPlayer);
            takeCurrentPlayerTurn();
            //increments total number of turns
            numberOfTurns++;
            textUI.printBoard(board.getStringRepresentation());
            //if win condition is met, a win is declared
            if (board.hasGameWon(currentPlayer)) {
                textUI.printWinnerMessage(currentPlayer);
                break;
            //if number of turns equals the total number of cells, a tie is declared
            } else if (numberOfTurns == board.getNumberOfCells()) {
                textUI.printTieMessage();
            } else {
                saveGame();
                swapPlayers();
            }
        }
    }
}
