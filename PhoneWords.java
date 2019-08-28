
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
/**
 * Given a collection of words, you can convert any word to its equivalent 
 * phone number, or phone number into a list of possible valid words. 
 * It accepts either reading words from words.txt and/or adding new words
 *
 * @author Saber Elsayed
 * @version Jan 2019
 * @see Word
 * 
 * Edited and completed by:
 * @author Bridget Free
 * @version April 2019
 */
public class PhoneWords {

    /** if yes, sort the phone number, before finding its 
     * equivalent word(s)*/
    private boolean numSorted;
    /** if yes, sort the phone number and remove redundant digits, 
     * before finding its equivalent word(s) */
    private boolean numUniqueSorted;
    /** Initializes the array dictList to store an array of the words 
     * from the dictionary */
    private List<Word> dictList = new ArrayList<Word>();
    /** the array numWords that will store an array of words found that
     * match what the user is searching for*/
	ArrayList<Object> numWords = new ArrayList<Object>();
    
    /**the main method for this class - used for testing purposes
     * @param args
     * @throws InputException 
     */
    public static void main(String[] args) throws InputException { 	
    	//Put testing code here
    	
    	PhoneWords pw = new PhoneWords(true);	
    	pw.setNumSorted(true);
    	pw.setNumUniqueSorted(true);
    	System.out.println(pw.listWords("(22) 23"));

    }

    /**construct a new phoneWord object
     *
     * @param isWordsFileProvided yes, if we need to read words 
     * from words.txt, otherwise no
     * @throws InputException 
     */
    public PhoneWords(boolean isWordsFileProvided) throws InputException {

        numSorted = false;
        numUniqueSorted=false;
        if (isWordsFileProvided) {
            loadDict("words.txt", this);
        }
    }

    /**read and process all the words in the words file and add them to the
     * PhoneWords object
     *
     * @param name of the file being indexed
     * @param pw PhoneWords object to add words to
     * @throws InputException  - if an illegal case is entered
     */
    private static void loadDict(String name, PhoneWords pw) 
    		throws InputException {
        Scanner dictIn;   // words file scanner
        // open named words file, then read & add words to pw
        try {
            dictIn = new Scanner(new File(name));
            while (dictIn.hasNextLine()) {
                String word = dictIn.nextLine();
                // add next word from words file, catching any errors
                try {
                    pw.addWord(word);
                } catch (IllegalArgumentException iae) {
                    System.out.println("addWord failed: "+iae.getMessage());
                }
            }
        } // handle file I/O exceptions
        catch (FileNotFoundException fnfe) {
            System.err.println("Error opening words file: " + fnfe);
            System.exit(100);
        } catch (@SuppressWarnings("hiding") IOException e) {
            System.err.println("Error reading words file: " + e);
            System.exit(100);
        }
    }
    
    /**adds a word to the dictList collection 
     * if the word does not already exist
     * @param input - the word that may be added
     * @throws InputException - if an illegal string is entered
     */
    public void addWord(String input) throws InputException {
    	Word temp = new Word(input);
    	try {
    		if(!isKnown(temp)) {
    			dictList.add(temp);
    			//for testing purposes only
    			//System.out.println("word added successfully"); 
    		}
    	}catch (Exception e) {
    		throw new InputException();
    	}
    }
    
    /**determines if a word is already in the dictList or not
     * @param word - the word to be searched for
     * @return returns true or false depending on if the word is known
     */
    public boolean isKnown(Word word) {
    	for (int i = 0; i < dictList.size(); i ++) { 		
    		if (word.getWord().toLowerCase()
    				.equals(dictList.get(i).getWord()))
    			return true;
    	}
    	return false;
    }
    
    /**the size of the dictList collection
     * @return returns the size of the collection as an integer
     */
    public int getNumWords() {  
    	return dictList.size();
    }
    
    /**Returns whether or not the provided number has valid usage of 
     * brackets, and + (for the area code) or has random symbols
     * 
     * @param num - Phone number to validate
     * @return Returns the validity of the provided phone number
     * 
     */
    public static boolean isValidNumber(String num) {
    	num = num.toLowerCase();
    	
    	for (int i = 0; i < num.length(); i++) {
    		int code = (int) num.charAt(i);
    		if (!((code>=48 && code<=57) || (code>=97 && code <=122) || 
    				(code >= 40 && code <= 41) || (code == 43))){
    			return false;
    		}
    	}

    	if(num.contains("(") || num.contains(")")) {
    		return (countOccurrence(num, '(') == 1 
    				&& countOccurrence(num, ')') == 1) &&
    		(num.charAt(0) == '(' && num.charAt(3) == ')'); }
    	
    	if(num.contains("+"))
    		return num.charAt(0) == '+' && countOccurrence(num, '+') == 1;
    	 	    
    	return true;
    }
    
    /**Counts the Occurrence of a specific char within a given string
     * 
     * @param str input
     * @param c char to count
     * @return Returns an int between 0 and string.length()
     * 
     */
    private static int countOccurrence(String str, char c) {
    	return str.length() - 
    			str.replace(Character.toString(c), "").length();
    }
    
    /**Creates a new ArrayList of words that match the number provided
     * @param num - the string that is used to find any matches
     * @return Returns a list of words that match the string
     */
    public ArrayList<Object> listWords(String num) throws InputException {
    	num = num.replace(" ", "").toLowerCase();
    	if (numSorted && numUniqueSorted) {
    		
    		if(!isValidNumber(num))
    			throw new InputException();
    		
        	if(num.charAt(0)=='+') {
        		num=num.replace("+", "00");
        	}
        	if (num.charAt(0)=='('&&num.charAt(3)==')') {
        		num=num.replace("(","");
        		num=num.replace(")", "");
        	}
        	
    		for(int i = 0; i < getNumWords(); i++)
    			if(num.equals(dictList.get(i).getWordToNumber())) {
    				numWords.add(dictList.get(i));}
    	}else {
    		System.out.println("Your input is not sorted.");
    	}

    	return numWords;
    }
    
    /**determines the number of words matching the input string
     * @param string - the word that is being tested for matches
     * @return return the number of matches as an integer
     * @throws InputException - if an illegal case is entered
     */
    public int numWords(String string) throws InputException {
    	int count = 0;
    	try {
    		for (Word idx: dictList){
    			if (idx.getWordToNumber().equals(string)){
    				count++;
    			}
    		}
    	}catch (Exception e){
    		throw new InputException();
    	}
    	return count;
    }
        
    /**sets the boolean for whether or not a number is sorted
     * @param num - the boolean that changes numSorted
     * @return returns true or false dependent from numSorted
     */
    public boolean setNumSorted(boolean sorted) {
    	return numSorted = sorted;
    }
    
    /**finds the value of the boolean numSorted
     * @return returns either true or false dependent on numSorted
     */
    public boolean getNumSorted() {
    	return numSorted;
    }
    
    /**sets the boolean for whether or not there are redundant numbers
     * @param uniqueSorted - the boolean that changes numUniqueSorted
     * @return returns true or false from numUniqueSorted
     */
    public boolean setNumUniqueSorted(boolean uniqueSorted) { 	
    	return numUniqueSorted = uniqueSorted;
    }
    
    /**finds the value of the boolean numUniqueSorted
     * @return returns either true or false dependent on numUniqueSorted
     */
    public boolean getNumUniqueSorted() {
    	return numUniqueSorted;
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