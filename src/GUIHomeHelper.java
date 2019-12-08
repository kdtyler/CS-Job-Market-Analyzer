
/**
 * @author Kevin Tyler
 * This class provides helper functions that mainly help convert user input into the correct strings to be passed
 * to the URLGenerator.java class
 */
public class GUIHomeHelper {
	
	/**
	    * This method checks if the user did not input anything in the passed in text from a text field,
	    * and returns "None" if they have not, or the string as it is if they have.
	    * @param stringToCheck
	    * @return stringToCheck or "None"
	    */
	   String TextfieldCheck(String stringToCheck) {
		   
		   if(stringToCheck.equals("")) {
			   return "None" ;
		   }
		   
		   return stringToCheck;
	   }
	   
	   /**
	    * This method checks if the experience level is set to default, and returns "None" if it is (for error-checking
	    * purposes in the WebScrapper.java class). 
	    * @param stringToCheck
	    * @return stringToCheck or "None"
	    */
	   String ExperienceLevelCheck(String stringToCheck) {
		   
		   if(stringToCheck.equals("Default (all)")) {
			   return "None" ;
		   }
		   
		   return stringToCheck;
	   }
	   
	   /**
	    * This method converts the distance string from the user to just the number (i.e. "<10 miles" becomes "10")
	    * @param distance
	    * @return distanceNumber
	    */
	   String distanceConverter(String distance) {
		   
		   String distanceNumber = "None" ;
		  
		   if (distance.contains("10")) {
			   distanceNumber = "10";
		   } else if (distance.contains("25")) {
			   distanceNumber = "25";
		   } else if (distance.contains("50")) {
			   distanceNumber = "50";
		   } else if (distance.contains("100")) {
			   distanceNumber = "100";
		   }
		   
		   return distanceNumber;
	   }

}
