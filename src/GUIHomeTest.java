import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GUIHomeTest {
	GUIHome gui = new GUIHome();
	
	@Test
	public void TextfieldCheck1() {
		String stringToCheck = "Software Engineer";
		String checkedString = gui.TextfieldCheck(stringToCheck); 
		assertEquals("Software Engineer", checkedString);
	}
	
	@Test
	public void TextfieldCheck2() {
		String stringToCheck = "";
		String checkedString = gui.TextfieldCheck(stringToCheck); 
		assertEquals("None", checkedString);
	}
	
	@Test
	public void ExperienceLevelCheck1() {
		String stringToCheck = "mid-level";
		String checkedString = gui.ExperienceLevelCheck(stringToCheck); 
		assertEquals("mid-level", checkedString);
	}
	
	@Test
	public void ExperienceLevelCheck2() {
		String stringToCheck = "Default (all)";
		String checkedString = gui.ExperienceLevelCheck(stringToCheck); 
		assertEquals("None", checkedString);
	}
	
	@Test
	public void distanceConverterCheck1() {
		String stringToCheck = "<10 miles";
		String checkedString = gui.distanceConverter(stringToCheck); 
		assertEquals("10", checkedString);
	}
	
	@Test
	public void distanceConverterCheck2() {
		String stringToCheck = "Default (<25 miles)";
		String checkedString = gui.distanceConverter(stringToCheck); 
		assertEquals("25", checkedString);
	}

}
