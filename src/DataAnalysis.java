import java.io.*;
import java.util.*;

public class DataAnalysis {
	private ArrayList<Job> jobs = new ArrayList<Job>();
	private ArrayList<String> jobTitles = new ArrayList<String>();
	private ArrayList<String> jobLocations = new ArrayList<String>();
	private ArrayList<String> jobCompanies = new ArrayList<String>();

	public DataAnalysis(){
		String inputFile = "software_engineer_boston.txt";
		File f = new File(inputFile);
		try {
			Scanner s = new Scanner(f);
			
			while (s.hasNextLine()) {
				String line = s.nextLine();
				// split job information based on delimter
				String[] lineData = line.split("\\|");
				// clean the location using the helper method
				lineData[1] = cleanLocation(lineData[1]);
				//create a new job and it to the jobs array list
				Job job = new Job (lineData[0],lineData[1],lineData[2],lineData[3],lineData[4]);
		
				jobs.add(job); 
				
				// create arrays of all the unique job titles, location, and companies
				if (!jobTitles.contains(lineData[0])) {jobTitles.add(lineData[0]);}
				if (!jobLocations.contains(lineData[1])) {jobLocations.add(lineData[1]);}
				if (!jobCompanies.contains(lineData[2])) { jobCompanies.add(lineData[2]);}
				
			}	
			
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public List<String> topNLocations(int n) {
		//create a hashmap of locatins and amount of times they appear in the job listings 
		HashMap<String, Integer> locationCount = new HashMap<String, Integer>();
		
		//increase the count for each location
		for (Job j : jobs) {
			if (locationCount.containsKey(j.getLocation())){
				int value = locationCount.get(j.getLocation());
				locationCount.put(j.getLocation(), value +1);
			}
			else { 
				locationCount.put(j.getLocation(),0); 
			}
		}
		//use a TreeMap to sort by values
		TreeMap<String, Integer> sorted = new TreeMap<String, Integer>(); 
		sorted.putAll(locationCount); 
		ArrayList<String> results = new ArrayList<String>();
		for (String key : sorted.keySet()) {
			results.add(key); 
		}
		// return the top N from the sorted TreeMap if list is greater then N only
		if (results.size() > n) {
			List<String> finalResults = results.subList(0, n);
			return finalResults; 
		}
		else { return results;} 
	}
	
	public int jobDescriptionContainsKeyWord(String keyword) {
		int keywordCounter =0; 
		for (Job j : jobs) {
			String description = j.getJobDescription().toLowerCase();
			if (description.contains(keyword)) {
				keywordCounter++;
			}
		}
		return keywordCounter; 
	}
	
	//Helper method to clean location information
	private String cleanLocation (String text) {
		int n = text.indexOf(",");
		String cleanString = text.substring(0, n+4);
		return cleanString; 
	}
	
	public static void main(String[] args) {
		DataAnalysis da = new DataAnalysis();
		List<String> test = da.topNLocations(11);
		for (String s : test) {
			System.out.println(s);
		}
		int n = da.jobDescriptionContainsKeyWord("java"); 
		System.out.println(n);
		
		
	}
}
