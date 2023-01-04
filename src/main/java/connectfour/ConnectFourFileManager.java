package connectfour;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 * @author Daaniyah Siddiqui
 *The class is used to save the
 * state of game board to file
 * and load state of game board
 * from previous saved file
 */

public class ConnectFourFileManager {
    //method to save board state to file entered in assets folder
    public void saveBoardStateToFile(String boardState, String fileName) throws IOException {
        Path filePath = FileSystems.getDefault().getPath("./assets", fileName);
        Files.writeString(filePath, boardState);
    }
    //method to load board state from file entered from assets folder
    public String loadBoardStateFromFile(String fileName) throws Exception {
        try {
            Path filePath = FileSystems.getDefault().getPath("./assets", fileName);
            return Files.readString(filePath);
        } catch (Exception error) {
            //throws exception if file is not found
            throw new Exception("File not found. Displaying default board: \n");
        }
    }
}
