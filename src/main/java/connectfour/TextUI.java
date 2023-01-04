package connectfour;

import java.util.Scanner;
/**
 * @author Daaniyah Siddiqui
 *The class is entirely responsible
 * for interacting with user, prints
 * messages and asks for user input
 */

public class TextUI {
    //prints the player of current turn
    public void printCurrentPlayer(int currentPlayer) {
        System.out.println("Player Turn: " + currentPlayer);

    }
    //prompts user to input column position
    public int getPosition() {
        System.out.println("Enter a number between 0 and 8: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    //prints corresponding error message to exception
    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
    //prints welcome message when game begins
    public void printWelcomeMessage(){
        System.out.println("Welcome to TicTacToe!");
    }
    //prints congrats message for winning player
    public void printWinnerMessage(int playerNumber) {
        System.out.println("Congrats! You have won Player" + playerNumber+".");
    }
    //prints error message for invalid column position input
    public void printIndexErrorMessage(){
        System.out.println("Invalid column position. "
            +
            "Please enter a position between 1 and 6.");}
    //prints if game has ended in a tie
    public void printTieMessage() {
        System.out.println("Game has ended in a tie!");
    }
    //prompts user to enter file name
    public String getFileName(){
        System.out.println("Please enter file name: ");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    //asks if user wants load previously saved game
    public String getUserChoiceForLoadingGame(){
        System.out.println("Would you like to load a previously saved game?(Y or N): ");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    //message printed when file has been loaded successfully
    public void printSuccessfulFileLoad(){
        System.out.println("Game has been successfully loaded!");
    }
    //asks if user wants to save running game
    public String getUserChoiceForSavingGame(){
        System.out.println("Would you like to save the game?(Y or N): ");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    //message printed when file has been successfully saved
    public void printSuccessfulFileSave(){
        System.out.println("Game has been successfully saved!");
    }
    //prints the board display
    public void printBoard(String boardStringRepresentation) {
        System.out.println(boardStringRepresentation);
    }

}