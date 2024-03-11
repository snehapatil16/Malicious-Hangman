## Malicious-Hangman

### Algorithm:

1. Ask user about the number of letters and the number of guesses they would like to use for the game.
2. Display dashes for all unknown letters (letters the player still has to guess).
3. Player makes a guess.
4. Computer checks to see if removing all words with guessed letter will eliminate all words, and here computer has two cases:
    - Case 1: Removing all words containing guessed letter will NOT eliminate all words, then the computer eliminates all words containing the guessed letter.  And informs the player that they have guessed incorrectly.
    - Case 2: Removing all words with guessed letter will eliminate all remaining words, then computer chooses a word at random and displays the letter on the correct dash. After that, the game continues like in normal hangman.
5. Repeat steps 2 – 4 until:
    - Either player’s guesses exceed the number of allowed guesses, then the computer informs the player that they have lost the game.
    - Or player guesses all letters correctly, and they win the game.  Note that this can only happen after computer encounters case 2 in step 4.
