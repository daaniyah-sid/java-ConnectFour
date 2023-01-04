package connectfour;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author Daaniyah Siddiqui
 *The class creates test cases for
 * public methods used in Board class
 * and implements them as junit tests
 */

public class BoardTest{
    private Board tester;

    @Before
    public void setup(){
        //set up for the test
        tester = new Board();

    }
    //tests if number of cells equals correct value of board cells
    @Test
    public void getNumberOfCellsEqualsCorrectCells(){
        int correctCells = 42;
        Assert.assertEquals(tester.getNumberOfCells(), correctCells);
        
    }
    //tests if number of cells does not equal incorrect value of cells
    @Test
    public void getNumberOfCellsDoesNotEqualInvalidCells(){
        int invalidCells = 30;
        Assert.assertNotEquals(tester.getNumberOfCells(),invalidCells);

    }
    //tests if set state method does not throw exception when valid column position is entered
    @Test
    public void setStateDoesNotThrowException(){
        assertDoesNotThrow(()->tester.setState(4,1));
    }
    //tests if set state method does throw exception when invalid column position is entered
    @Test
    public void setStateThrowsException(){
        assertThrows(Exception.class, ()->tester.setState(10,1));
    }
    //tests if game has won in vertical case for player 1 and if set false for player 2
    @Test
    public void hasGameWonEqualsTrueForVerticalCaseForPlayer1AndFalseForPlayer2() {
        assertDoesNotThrow(()->{
            tester.setState(1,1);
            tester.setState(1,1);
            tester.setState(1,1);
            tester.setState(1,1);
        });
        Assert.assertTrue(tester.hasGameWon(1));
        Assert.assertFalse(tester.hasGameWon(2));
    }
    //tests if game has won in horizontal case for player 1 and if set false for player 2
    @Test
    public void hasGameWonEqualsTrueForHorizontalCaseForPlayer1AndFalseForPlayer2() {
        assertDoesNotThrow(()->{
            tester.setState(1,1);
            tester.setState(2,1);
            tester.setState(3,1);
            tester.setState(4,1);
        });
        Assert.assertTrue(tester.hasGameWon(1));
        Assert.assertFalse(tester.hasGameWon(2));
    }
    //tests if game has won in negative diagonal case for player 1 and if set false for player 2
    @Test
    public void hasGameWonEqualsTrueForNegativeDiagonalForPlayer1AndFalseForPlayer2() {
        assertDoesNotThrow(() -> {
            tester.setState(1, 1);
            tester.setState(2, 2);
            tester.setState(2, 1);
            tester.setState(3, 2);
            tester.setState(3, 2);
            tester.setState(3, 1);
            tester.setState(4, 2);
            tester.setState(4, 2);
            tester.setState(4, 2);
            tester.setState(4, 1);
        });
        Assert.assertTrue(tester.hasGameWon(1));
        Assert.assertFalse(tester.hasGameWon(2));
    }
    //tests if game has won in positive diagonal case for player 1 and if set false for player
    @Test
    public void hasGameWonEqualsTrueForPositiveDiagonalCaseForPlayer1AndFalseForPlayer2() {
        assertDoesNotThrow(() -> {
            tester.setState(4, 2);
            tester.setState(4, 2);
            tester.setState(4, 2);
            tester.setState(4, 1);
            tester.setState(3, 2);
            tester.setState(3, 2);
            tester.setState(3, 1);
            tester.setState(2, 2);
            tester.setState(2, 1);
            tester.setState(1, 1);
        });
        Assert.assertTrue(tester.hasGameWon(1));
        Assert.assertFalse(tester.hasGameWon(2));
    }
    //tests if string representation equals the expected string passed
    @Test
    public void getStringRepresentationEqualsTrue(){
        String expectedString =
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n";
        assertTrue(tester.getStringRepresentation().equals(expectedString));
    }
    //tests if expected number of used cells are filled in string representation
    @Test
    public void setBoardFromStateStringAndReturnUsedCellsDoesNotThrowExceptionAndEqualsExpectedUsedCells(){
        int expectedUsedCells = 4;
        String boardStringRepresentation =
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,1,0,0,0,0,0\n"
                        +
                "0,1,2,0,2,0,0\n";
        assertDoesNotThrow(()->{
            assertEquals(tester.setBoardFromStateStringAndReturnUsedCells(boardStringRepresentation),expectedUsedCells);
        });
    }
    //tests if passed string representation throws expected error message when the board has invalid rows
    @Test
    public void setBoardFromStateStringAndReturnUsedCellsInvalidRows(){
        String expectedErrorMessage = "Invalid number of rows. Displaying default board: \n";
        String boardStringRepresentation =
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,1,0,0,0,0,0\n"
                        +
                "0,1,2,0,2,0,0\n";
        Exception error = assertThrows(Exception.class,
                ()->tester.setBoardFromStateStringAndReturnUsedCells(boardStringRepresentation));
        assertTrue(error.getMessage().equals(expectedErrorMessage));

    }
    //tests if passed string representation throws expected error message when the board has invalid number of columns
    @Test
    public void setBoardFromStateStringAndReturnUsedCellsInvalidRowsInvalidColumns(){
        String expectedErrorMessage = "Invalid number of columns. Displaying default board: \n";
        String boardStringRepresentation =
                "0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0\n"
                        +
                "0,1,0,0,0,0\n"
                        +
                "0,1,2,0,2,0\n";
        Exception error = assertThrows(Exception.class,
                ()->tester.setBoardFromStateStringAndReturnUsedCells(boardStringRepresentation));
        assertTrue(error.getMessage().equals(expectedErrorMessage));

    }
    //tests if passed string representation throws expected error message when the board has invalid cell values
    @Test
    public void setBoardFromStateStringAndReturnUsedCellsInvalidRowsInvalidIndex(){
        String expectedErrorMessage = "Invalid cell values. Displaying default board: \n";
        String boardStringRepresentation =
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,0,0,0,0,0,0\n"
                        +
                "0,1,3,0,0,0,0\n"
                        +
                "0,1,2,0,2,0,0\n";
        Exception error = assertThrows(Exception.class,
                ()->tester.setBoardFromStateStringAndReturnUsedCells(boardStringRepresentation));
        assertTrue(error.getMessage().equals(expectedErrorMessage));
    }
}
