import org.junit.Test;
import junit.framework.TestCase;

public class WordTest extends TestCase {
	@Test public void testUpperCaseStringToNumber() {
		Word testWord = new Word("PIZZAGO");
		assertEquals("7499246", testWord.getWordToNumber());
	}
	
	@Test public void testLowerCaseStringtoNumber() {
		Word testWord = new Word("pizzago");
		assertEquals("7499246", testWord.getWordToNumber());
	}
	
	@Test public void testMixedCaseStringtoNumber() {
		Word testWord = new Word("PizZagO");
		assertEquals("7499246", testWord.getWordToNumber());
	}
	
	@Test public void testNumberStringtoNumber() {
		Word testWord = new Word("1234567890");
		assertEquals("1234567890", testWord.getWordToNumber());
	}
	
	@Test public void testMixedNumberandStringtoNumber() {
		Word testWord = new Word("PizZA2gO");
		assertEquals("74992246", testWord.getWordToNumber());
	}
	
	@Test public void testIllegalCase() {
		Word testWord = new Word("(PizZA2go!");
		//should fail > does fail
		assertEquals("Invalid Input", testWord.getWordToNumber());
	}
}
