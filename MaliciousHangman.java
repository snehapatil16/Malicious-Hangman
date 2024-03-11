package patilProject04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class MaliciousHangman {
	
	private int letters = 0;
	private int guesses = 0;
	private String output;
	private String finalAnswer;
	//ArrayList where words from the dictionary will go that are the same length
	private ArrayList<String> dict; 
	//where guessed letters will go to be printed out
	private ArrayList<Character> guessed = new ArrayList<>();
	
	public MaliciousHangman(int letters, int guesses, ArrayList<String> words) {
		
		this.letters = letters;
		this.guesses = guesses;
		dict = new ArrayList<>();
		
		//Print out dashes for letters remaining to guess
		char[] outputDashes = new char[letters];
		for (int i = 0; i < letters; i++) {
			outputDashes[i] = '_';
	    }
		
		output = new String(outputDashes);
		
		//adding all words in dictionary that are the same length to the list
        dict.addAll(words.stream().filter(word -> word.length() == letters).collect(Collectors.toList()));
	}
	
	public boolean play(char guess) {
		
		String temp = output;
		HashMap<String, ArrayList<String>> words = new HashMap<>();
		boolean guessRight = false;
		
		if (gameOver()) {
            
			System.out.println("Game Over");
            return playerWon();
        }
		 
		//create keys for the hash map
	    for (String word : dict) {
			 
	    	char[] key = new char[letters];
	        for (int index = 0; index < letters; index++) {
	            if (word.charAt(index) == guess) {
	                   
	            	//making the key the guessed letter
	            	key[index] = guess;
	           
	            } else {
	                    
	                key[index] = output.charAt(index);
	            }
	        }
	        
	        String keyString = new String(key);
	        addWord(keyString, word, words);
	    }
       
	    for (String keyString : words.keySet()) {
            if (!words.keySet().contains(temp)) {
                temp = keyString;
            }
        }
       
        if (words.keySet().contains(temp)) {

            // shallow copy of the max value in word choices
            dict = new ArrayList<>(words.get(temp));
            guessRight = !temp.equals(output);

            if (!guessRight) {

            	output = temp;
            	finalAnswer = words.get(output).get(0);
            	guessed.add(guess);
            	return guessRight;
            }
        }
        
        return guessRight;
	}
	
	private void addWord(String key, String word, HashMap<String, ArrayList<String>> wordMap) {
	        
		if (wordMap.get(key) == null) {
	            
			wordMap.put(key, new ArrayList<>());
		}
		 
		//adding the word (key) to the Map
		wordMap.get(key).add(word);
	} 
	
	public String showCurrent() {
	     
		// to show current board
		char[] formattedOutput = output.toCharArray();
		return Arrays.toString(formattedOutput);
	}
	
	public int getGuesses() {
		
		return guesses;
	}
	
	public void decrementGuess() {
		
		if (guesses > 0) {
			
			this.guesses--;
			System.out.println("Guesses remaining: " + guesses);
		}
	}
	
	public boolean gameOver() {
        
		return guesses <= 0 || !output.contains("_") || dict.isEmpty();
    }
	 
	 public int dictSize() {
	        
		 return dict.size();
	 }
	 
	 public String printGuessed() {
	        
		 return guessed.toString();
	 }
	 
	 public boolean playerWon() {
	       
		 if (!gameOver()) {
	           
			 return false;
		
		 } else {
	          
			 gameOver();
			 return !output.contains("_");
		 }
	 }
	 
	 public String getFinalAnswer() {
	        
		 if (gameOver()) {
	            
			 return finalAnswer;
		
		 } else {
	           
			 return null;
		 }
	 }
}

//char guess = Character.toLowerCase(guessA);
//String temp = output;
//HashMap<String, ArrayList<String>> words = new HashMap<>();
//boolean guessRight = false;
//
//if (gameOver()) {
//    
//	System.out.println("Game Over");
//    return playerWon();
//}
// 
////create keys for the hash map
//for (String word : dict) {
//	 
//	char[] key = new char[letters];
//    for (int index = 0; index < letters; index++) {
//        if (word.charAt(index) == guess) {
//               
//        	key[index] = guess;
//        } else {
//                
//            key[index] = output.charAt(index);
//        }
//    }
//    String keyString = new String(key);
//    addWord(keyString, word, words);
//}
// 
////return true -> have guessed correctly
////true when we're playing regular hang man
////check if that letter in the word we randomly picked
//// if yes -> update String and return true
//// every other case, return false
//if (guesses == 1) {
//    
//	// if word is present in HashMap
//    if (words.keySet().contains(output)) {
//
//        dict = new ArrayList<>(words.get(output));
//        decrementGuess();
//        finalAnswer = words.get(output).get(0);
//        guessed.add(guess);
//        return guessRight;
//    }
//}
//
//for (String keyString : words.keySet()) {
//    if (!words.keySet().contains(temp)) {
//        temp = keyString;
//    }
//}
//
////go through all word families to find the one with the most possible words
////if (words.get(keyString).size() > words.get(temp).size()) {
////    
////	temp = keyString;
////}
//
//if (words.keySet().contains(temp)) {
//
//    // shallow copy of the max value in word choices
//    dict = new ArrayList<>(words.get(temp));
//    guessRight = !temp.equals(output);
//
//    if (!guessRight) {
//
//    	output = temp;
//    	finalAnswer = words.get(output).get(0);
//    	guessed.add(guess);
//    	return guessRight;
//    }
//}
//
//return guessRight;


//return true -> have guessed correctly
//true when we're playing regular hang man
//check if that letter in the word we randomly picked
// if yes -> update String and return true
// every other case, return false
