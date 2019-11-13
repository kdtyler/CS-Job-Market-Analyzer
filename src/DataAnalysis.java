import java.io.*;
import java.util.*;


public class DataAnalysis {
	private ArrayList<Job> jobs = new ArrayList<Job>();
	// Create array Lists of all the unique job titles, locations, and companies hiring
	private ArrayList<String> jobTitles = new ArrayList<String>();
	private ArrayList<String> jobLocations = new ArrayList<String>();
	private ArrayList<String> jobCompanies = new ArrayList<String>();
   
	//Purpose of this class is to parse out the job information from the text file and create a an array of job objects
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
	
	//Returns the top N job locations where jobs are located 
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
	
	//Returns the count of all the jobs whose description contains the identified keyword 
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
	
	//Parses out the first salary in the salary band and returns the average salary for an input of job arrays.
	//Will only compute salary if there is a salary listed 
	public double averageStartingSalary(ArrayList<Job> jobsArray) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		for (Job j : jobsArray) {
			String salary = j.getSalary();
			//compute salary only if data does not say none
			if (!salary.contentEquals("None")) {
				// if there is a salary band then parse out the first salary
				if (salary.contains("-")) {
					String[] salaryBand = salary.split("-");
					//remove all the non digit characters from the String of salaries
					salaryBand[0] = salaryBand[0].replaceAll("[^\\d.]", "");
					int begSalary = Integer.parseInt(salaryBand[0]);
					//Add salary to an arrayList
					values.add(begSalary);
				}
				//Same as above but for single salary value listed ie ("200,000 a year")
				else {
					salary = salary.replaceAll("[^\\d.]", "");
					int begSalary = Integer.parseInt(salary);
					System.out.println(begSalary);
					values.add(begSalary); 
				}
			}
		}
		double sum = 0;
		//sum all the values and return the average
		for (int i : values) {
			sum += i; 
		}
		return sum / (double) values.size(); 
	}
	
	public static void main(String[] args) {
		DataAnalysis da = new DataAnalysis();
		/*List<String> test = da.topNLocations(11);
		for (String s : test) {
			System.out.println(s);
		}
		int n = da.jobDescriptionContainsKeyWord("java"); 
		System.out.println(n);
		*/
		System.out.println(da.averageStartingSalary(da.jobs));
		
	}

	//Generate Getters for the ArrayLists in the constructer
	public ArrayList<Job> getJobs() {
		return jobs;
	}

	public ArrayList<String> getJobTitles() {
		return jobTitles;
	}

	public ArrayList<String> getJobLocations() {
		return jobLocations;
	}

	public ArrayList<String> getJobCompanies() {
		return jobCompanies;
	}
}
