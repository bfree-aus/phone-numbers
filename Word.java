import java.util.*;

/**Given a string, this class converts a word into its telephone equivalent.
 * 
 * @author Bridget
 * @version March 2019
 */
public class Word {

	/**initializes the variable word */
	private String word;
	/** initializes the variable wordToNumber */
	private String wordToNumber;
	/** initializes the hash map used to store the telephone keys to 
	 * convert words to numbers */
	private HashMap<Character, Integer> valueMap;
		
	/**the main method for this class - used for testing purposes
	 * @param args
	 */
	public static void main(String[] args) {
		Word w = new Word("PIZZA2GO");
		System.out.println(w.getWordToNumber()  + "=" + "74992246");	
	}

	/**construct a new Word object
	 * sets the variables of word, wordToNumber and creates the hashmap
	 * @param word - sets the variable of the word to be transformed
	 */
	public Word(String word){
		createMap();
		this.word = word;
		try {
			this.setWordToNumber(word);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	/**creates the HashMap used to store the letters 
	 * that relate to each telephone key
	 */
	public void createMap () {
		this.valueMap = new HashMap<Character, Integer>();
		
		addTelephoneKeys("abc", 2);
		addTelephoneKeys("def", 3);
		addTelephoneKeys("ghi", 4);
		addTelephoneKeys("jkl", 5);
		addTelephoneKeys("mno", 6);
		addTelephoneKeys("pqrs", 7);
		addTelephoneKeys("tuv", 8);
		addTelephoneKeys("wxyz", 9);
		
	}
	
	/**matches the letters with the right keys in the value map (hashmap)
	 * @param characters - string that relates letters to that telephone key
	 * @param value - the value of that telephone key
	 */
	public void addTelephoneKeys(String characters, int value) {
		for(int i = 0; i < characters.length(); i++)
			this.valueMap.put(characters.charAt(i), value);
	}

	/**sets the variable word
	 * @param userWord - the new value that will be set as the variable word
	 * @return - returns the new set value of the variable word
	 */
	public String setWord(String userWord) {
		return word = userWord;
	}

	/**gets the varaible word
	 * @return word variable
	 */
	public String getWord() {
		return word;
	}
	
	/**checks that an input is only made up of only alphanumeric digits 
	 * @param word - the String word that will be checked
	 * @return returns true/false depending on if the word is alphanumeric
	 */
	private boolean checkInput(String word) {
		word = word.toLowerCase();
		int check = 0;
		
		for (char c: word.toCharArray()) {
			if(!Character.isLetterOrDigit(c)) {
				check += 1;
			}
		}
		if(check > 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**changes a character into its number equivalent (or leaves it alone)
	 * @param letter - the character that will be changed
	 * @return returns a numerical equivalent of the character letter
	 */
	public char getDigit(char letter) {	
		if(Character.isDigit(letter))
			return letter;
		else
			return Integer.toString
					(valueMap.get(Character.toLowerCase(letter))).charAt(0);
	}

	/**changes the word to a string of numbers (after checking the input)
	 * @param word - the String word that will be changed into a number
	 * @return returns wordToNumber
	 * @throws Exception handles exception if user enters illegal case
	 */
	public String setWordToNumber(String word) throws Exception {		
		String changeword = word.replace(" ", "");
		wordToNumber = "";
		Integer letter;
		
		if(!checkInput(word)) {
			throw new InputException();
		}
		else {
			/*for (int i = 0; i < word.length(); i++) {
				letter = getDigit(word.charAt(i));
				if (letter == null) {
					letter = word.charAt(i) - '0';
				}
			}*/
			
			for (Character c : changeword.toCharArray())
				wordToNumber += getDigit(c);
		}
		
		return wordToNumber;
	}

	/**gets the wordToNumber variable
	 * @return returns wordToNumber
	 */
	public String getWordToNumber() {
		return wordToNumber;
	}
	
	/**returns the two main variables (word and wordToNumber) in a string
	 */
	public String toString() {
		return "Word [word=" + word + ", wordToNumber=" + wordToNumber + "]";
	}
	
	/**checks the equality of the word class based on whether or not 
	 * the attributes (namely word and wordToNumber) are the same.
	 */
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof Word))
			return false;
		
		Word w = (Word) o;
		
		return (w.getWord().equals(this.getWord())) && 
				(w.getWordToNumber().equals(this.getWordToNumber()));
	}

	/**class that creates an case to handle input exceptions throughout
	 * the word class so we can test easily for any user errors in input
	 * e.g. non-alphanumeric inputs
	 * @author Bridget
	 */
	private class InputException extends Exception{
		private static final long serialVersionUID = 1L;		
		//Constructor
		public InputException() {
			super();
		}		
		//Warning that the input is invalid
		public String getMessage() {
			return "Invalid input";
		}
	}
}
