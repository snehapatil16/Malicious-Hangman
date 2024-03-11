package patilProject04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		
		ArrayList<String> words = new ArrayList<String>();
		char guessLetter;
		
		try(Scanner fileReader = new Scanner(new File("dictionary.txt"))){
           
			while(fileReader.hasNextLine()){
                
            	words.add(fileReader.nextLine());
            }
		
		} catch (FileNotFoundException e) {
			
			System.out.println("An error occurred.");
		    System.exit(0);
		}
		
		int letters = 0;
		int guess = 0;
		boolean play = false;
		String ifPlay = "";
		
		while (!play) {
		
			//Prompt the user for a number of letters and a number of guesses for the game
			Scanner scanner = new Scanner(System.in);
			System.out.println("How many letters in the word?");
			letters = scanner.nextInt();
			if (letters <= 0) {
			
				System.out.println("The wword should have at least one letter.");
		
			}  else {
			
				System.out.println("How many guesses?");
				guess = scanner.nextInt();
				if (guess <= 0) {
				
					System.out.println("You need to have atleast one guess.");
				}
			}
		
			MaliciousHangman game = new MaliciousHangman(letters, guess, words);
		
			System.out.println(game.showCurrent());
		
			while (!game.gameOver()) {
			
				System.out.println("Enter a letter: ");
				guessLetter = scanner.next().charAt(0);
				
				//only accepting letters that they haven’t guessed yet
//				if (game.printGuessed().charAt(0).contains(guessLetter)) {
//					
//					System.out.println("Please enter a letter you haven't entered yet.");
//					guessLetter = scanner.next().charAt(0);
//				}
//			
				//passes the guessed letter into play method returning whether guessed letter is correct or not
				if (game.play(guessLetter)) {
				
					System.out.println("Guessed letter is correct!");
				} else {
					System.out.println("Guessed letter was wrong.");
				}
			
				System.out.println(game.showCurrent());
				game.decrementGuess();
				System.out.println("Letters already guessed: " + game.printGuessed());
			
				//if game is won
				if (game.playerWon()){
	            
					System.out.println("You won!");
					System.out.println("Final answer is: ");
					System.out.println(game.getFinalAnswer());
	        
					//if there are no guesses remaining and game isn't won
				} else if (game.gameOver()){
	           
					System.out.println("You lost.");
					System.out.println("Final answer is: ");
					System.out.println(game.getFinalAnswer());
				}
			}
			
			//ask the user if they want to play again and start a new game
			Scanner playScanner = new Scanner(System.in);
			System.out.println("Do you want to play again?");
			ifPlay += playScanner.nextLine();
			if (ifPlay.equals("Yes") || ifPlay.equals("yes")) {
				
				System.out.println("Playing again!");
			
			} else {
				
				System.out.println("Good game!");
				play = true;
				scanner.close();
				playScanner.close();
			}
		} //while loop to continue playing
	}
}
