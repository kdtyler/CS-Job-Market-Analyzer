import java.io.*;
import java.util.*;

public class DataAnalysis {
	ArrayList<Job> jobs = new ArrayList<Job>();

	public DataAnalysis(){
		String inputFile = "software_engineer_boston.txt";
		File f = new File(inputFile);
		try {
			Scanner s = new Scanner(f);
			
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String[] lineData = line.split("\\|");
				Job job = new Job (lineData[0],lineData[1],lineData[2],lineData[3],lineData[4]); 
				jobs.add(job); 
				
				
			}
		} catch (FileNotFoundException e) {
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
		// return the top N from the sorted TreeMap
		List<String> finalResults = results.subList(0, n);
		return finalResults; 
	}
	
	public static void main(String[] args) {
		DataAnalysis da = new DataAnalysis();
		List<String> test = da.topNLocations(3);
		for (String s : test) {
			System.out.println(s);
		}
		
		
	}
}
