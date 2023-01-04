package connectfour;
/**
 * @author Daaniyah Siddiqui
 *The class is used to maintain
 *the state of the board, creating
 *string representation of the board,
 * and checking win conditions
 */
public class Board {
    private int[][] state;
    private final int spacesTall = 6;
    private final int spacesWide = 7;

    public Board() {
        this.state = new int[spacesTall][spacesWide];
        resetBoardState();
    }
    /**
     *the method is responsible for
     * setting the board with correct
     * new lines and commas, checks if
     * board format is incorrect, returns
     * how many cells are filled
     * @param boardState - state of the board
     * @return usedCells - number of cells filled with a symbol
     */

    public int setBoardFromStateStringAndReturnUsedCells(String boardState) throws Exception {
        int usedCells = 0;
        String[] stateRows = boardState.split("\n");

        if (stateRows.length != spacesTall) {
            throw new Exception("Invalid number of rows. Displaying default board: \n");
        }

        for (int i=0; i<stateRows.length;i++){
            String[] stateColumn = stateRows[i].split(",");

            if (stateColumn.length != spacesWide) {
                throw new Exception("Invalid number of columns. Displaying default board: \n");
            }

            for (int j=0; j< stateColumn.length;j++){
                try {
                    state[i][j] = Integer.parseInt(stateColumn[j]);
                    if (state[i][j] == 1 || state[i][j] == 2) {
                        usedCells++;
                    } else if (state[i][j] != 0) {
                        throw new Exception("Invalid cell values. Displaying default board: \n");
                    }
                } catch(Exception error) {
                    resetBoardState();
                    throw error;
                }
            }
        }

        return usedCells;
    }
private void resetBoardState(){
    for (int i = 0; i < spacesTall; i++) {
        for (int j = 0; j < spacesWide; j++) {
            state[i][j] = 0;
        }
    }
}
    /**
     *the method returns
     * the total number
     * of cells in board
     * @return total of columns * rows
     */
    public int getNumberOfCells() {
        return this.spacesTall * this.spacesWide;
    }

    /**
     *the method sets the state of
     * the board with column input,
     * will throw exception
     * if column is filled
     * @param colPosition - position of column
     * @param currentPlayer - current player turn
     */
    public void setState(int colPosition, int currentPlayer) throws Exception {

        boolean didSet = false;
        for (int rowPosition = spacesTall - 1; rowPosition >= 0 && !didSet; rowPosition--) {
            if (state[rowPosition][colPosition] == 0) {
                state[rowPosition][colPosition] = currentPlayer;

                didSet = true;
            }
        }
        if (!didSet) {
            throw new Exception("Column is completely filled!");
        }

    }

    private boolean hasGameWonHorizontal(int playerNumber) {
        for (int column = 0; column < spacesWide - 3; column++) {
            for (int row = 0; row < spacesTall; row++) {
                if (state[row][column] == playerNumber
                        && state[row][column + 1] == playerNumber
                        && state[row][column + 2] == playerNumber
                        && state[row][column + 3] == playerNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasGameWonVertical(int playerNumber) {
        for (int column = 0; column < spacesWide; column++) {
            for (int row = 0; row < spacesTall - 3; row++) {
                if (state[row][column] == playerNumber
                        && state[row + 1][column] == playerNumber
                        && state[row + 2][column] == playerNumber
                        && state[row + 3][column] == playerNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasGameWonPositiveDiagonal(int playerNumber) {
        for (int column = 3; column < spacesWide; column++) {
            for (int row = 0; row < spacesTall - 3; row++) {
                if (state[row][column] == playerNumber
                        && state[row + 1][column - 1] == playerNumber
                        && state[row + 2][column - 2] == playerNumber
                        && state[row + 3][column - 3] == playerNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasGameWonNegativeDiagonal(int playerNumber) {
        for (int column = 3; column < spacesWide; column++) {
            for (int row = 3; row < spacesTall; row++) {
                if (state[row][column] == playerNumber
                        && state[row - 1][column - 1] == playerNumber
                        && state[row - 2][column - 2] == playerNumber
                        && state[row - 3][column - 3] == playerNumber) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     *the method will declare
     * true if either winning
     * case has been met, false
     * if not
     * @param playerNumber - player number on board
     * @return will return either winning case
     */
    public boolean hasGameWon(int playerNumber) {
        return hasGameWonHorizontal(playerNumber)
                || hasGameWonVertical(playerNumber)
                || hasGameWonPositiveDiagonal(playerNumber)
                || hasGameWonNegativeDiagonal(playerNumber);
    }
    /**
     *the method creates the
     * string representation
     * of the board
     * @return builder.toString() - string representation of board
     */

    public String getStringRepresentation() {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < spacesTall; row++) {
            for (int column = 0; column < spacesWide; column++) {
                builder.append(state[row][column]);
                if (column != spacesWide - 1) {
                    builder.append(',');
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }


}

