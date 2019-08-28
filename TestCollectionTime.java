 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**Tests different collections to find the most efficient method
 * of filling a collection (uses timings)
 * Utilises part of Saber's code for the constructor and load dictionary 
 * methods
 * @author Bridget
 * @version April 2019
 */
public class TestCollectionTime {

    /**The list of collections that will be tested
     * to find out which is the fasted
     * includes List, Stack, Queue, Deque and Set
     */
    private List<Word> option1 = new ArrayList<>(); 
    private Stack<Word> option2 = new Stack<>();
    private Queue<Word> option3 = new LinkedList<>();
    private Deque<Word> option4 = new LinkedList<>();
    private Set<Word> option5 = new HashSet<>();
    
    /**The string that sets the choice of collection that will be tested
     */
    private static String collectionChoice;
    
    /**The main method for the TestCollectionTime class
	 * @param args
	 */
	public static void main(String[] args) {
		//finds the time in order to test filling time for each collection
    	Date time = new Date(); 
    	
    	//testing for collection type List
    	TestCollectionTime.setcollectionChoice("List");
    	long timeBefore = System.currentTimeMillis();
    	TestCollectionTime testList = new TestCollectionTime(true);
    	long timeAfter = System.currentTimeMillis();
    	System.out.println("Time for " + 
    	    	TestCollectionTime.getcollectionChoice() + " collection:" 
    	    			+ (timeAfter - timeBefore));
    	
    	//testing for collection type Stack
    	TestCollectionTime.setcollectionChoice("Stack");
    	timeBefore = System.currentTimeMillis();
    	TestCollectionTime testStack = new TestCollectionTime(true);
    	timeAfter = System.currentTimeMillis();
    	System.out.println("Time for " + 
    	    	TestCollectionTime.getcollectionChoice() + " collection:" 
    	    			+ (timeAfter - timeBefore));
    	
    	//testing for collection type Queue
    	TestCollectionTime.setcollectionChoice("Queue");
    	timeBefore = System.currentTimeMillis();
    	TestCollectionTime testQueue= new TestCollectionTime(true);
    	timeAfter = System.currentTimeMillis();
    	System.out.println("Time for " + 
    	    	TestCollectionTime.getcollectionChoice() + " collection:" 
    	    			+ (timeAfter - timeBefore));
    	
    	//testing for collection type Deque
    	TestCollectionTime.setcollectionChoice("Deque");
    	timeBefore = System.currentTimeMillis();
    	TestCollectionTime testDeque= new TestCollectionTime(true);
    	timeAfter = System.currentTimeMillis();
    	System.out.println("Time for " + 
    	    	TestCollectionTime.getcollectionChoice() + " collection:" 
    	    			+ (timeAfter - timeBefore));
    	
    	//testing for collection type Set
    	TestCollectionTime.setcollectionChoice("Set");
    	timeBefore = System.currentTimeMillis();
    	TestCollectionTime testSet= new TestCollectionTime(true);
    	timeAfter = System.currentTimeMillis();
    	System.out.println("Time for " + 
    	    	TestCollectionTime.getcollectionChoice() + " collection:" 
    	    			+ (timeAfter - timeBefore));
    	
    }
    
    /**
     * construct a new phoneWord object
     *
     * @param isWordsFileProvided yes, if we need to read words 
     * from words.txt, otherwise no
     */
    public TestCollectionTime(boolean isWordsFileProvided) {
        if (isWordsFileProvided) {
            loadDict("words.txt", this);
        }
    }

    /**
     * read and process all the words in the words file and add them to the
     * PhoneWords object
     *
     * @param name of the file being indexed
     * @param pw PhoneWords object to add words to
     */
    private static void loadDict(String name, TestCollectionTime pw) {
        Scanner dictIn;                      // words file scanner
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
        } catch (IOException e) {
            System.err.println("Error reading words file: " + e);
            System.exit(100);
        }
    }
    
    /**Sets the type of collection chosen
     * @param choice - a string of the name of the chosen collection
     */
    private static void setcollectionChoice(String choice) {
    	collectionChoice = choice;
    }

    /**Finds the type of collection chosen
     * @return Returns the collectionChoice string variable
     */
    private static String getcollectionChoice() {
    	return collectionChoice;
    }
    
    /**Depending on the collection choice, the dictionary is 
     * added to a different type of list
     * @param word - a string of the collection choice
     */
    @SuppressWarnings("unchecked") //this allows the HashSet to run
	public void addWord(String word){
    	Word temp = new Word(word);
    	
    	if(collectionChoice == "List") {
        	if (!isKnown(temp, collectionChoice)){
        		option1.add(temp);
        	}
    	}
    	else if(collectionChoice == "Stack") {
    		if (!isKnown(temp, collectionChoice)){
        		option2.add(temp);
    		}
    	}
    	else if(collectionChoice == "Queue") {
    		if (!isKnown(temp, collectionChoice)){
        		option3.add(temp);
    		}
    	}
    	else if(collectionChoice == "Deque") {
    		if (!isKnown(temp, collectionChoice)){
        		option4.add(temp);
    		}
    	}
    	else if(collectionChoice == "Set") {
    		if (!isKnown(temp, collectionChoice)){
        		option5.add(temp);
    		}
    	}
    }
    
    /**Confirms if a word is known already in the chosen collection
     * @param word - the Word object that may be added
     * @param collectionChoice - type of collection being tested
     * @return Returns whether or not the word is known (true or false)
     */
    public boolean isKnown(Word word, String collectionChoice){
    	boolean check = false;
    	if (option1.contains(word) && collectionChoice == "List"){
    		check = true;
    	}
    	else if(option2.contains(word) && collectionChoice == "Stack") {
    		check = true;
    	}
    	else if(option3.contains(word) && collectionChoice == "Queue") {
    		check = true;
    	}
    	else if(option4.contains(word) && collectionChoice == "Deque") {
    		check = true;
    	}
    	else if(option5.contains(word) && collectionChoice == "Set") {
    		check = true;
    	}
    	return check;
    }
   
}
