import java.io.*;
import java.util.*;

public class DataAnalysis {
	// Array list of jobs, unique locations and companies and locations
	private ArrayList<Job> jobs = new ArrayList<Job>();
	private ArrayList<String> jobTitles = new ArrayList<String>();
	private ArrayList<String> jobLocations = new ArrayList<String>();
	private ArrayList<String> jobCompanies = new ArrayList<String>();
	

	public DataAnalysis () {

		// read in the text file created from the webscrapping 
		String inputFile = ".\\output\\output.txt";
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
	//helper method to clean locations
	/**
	 * @author amannischal
	 * @param text - takes in a location text like Boston, MA (08040) Downtown
	 * @return returns a cleaned version of test like Boston, MA based on location of ',' if it 
	 * does not contain a comma then it return the whole text unedited
	 */
	public String cleanLocation (String text) {
		int n = text.indexOf(",");
		//takes location up to the state abbreviation
		//If it does not contain the State and only country then return the full string un-edited
		if (n == -1) {
			return text;
		}
		else {
			String cleanString = text.substring(0, n+4);
			return cleanString; 
		} 
	}

	//shows number of jobs by area
	/**
	 * @author amannischal
	 * @return reads the jobs array made from constructor and returns a String statements of the total jobs
	 * and a numbered list of the most popular areas and how many jobs are in each area
	 * 
	 */
	public String numberOfJobs() {
		HashMap<String, Integer> locationCount = new HashMap<String, Integer>();
		//place job into hash map if it does not exist and count matches
		for (Job j : jobs) {
			if (locationCount.containsKey(j.getLocation())){
				int value = locationCount.get(j.getLocation());
				locationCount.put(j.getLocation(), value +1);
			}
			else { 
				locationCount.put(j.getLocation(),1); 
			}
		}
		// create a new sorted hash map
		LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>(); 
		locationCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
		
		//Create an array list of results that show locations and number of jobs
		ArrayList<String> results = new ArrayList<>(); 
		for (String key : sorted.keySet()) {
			String s = key + " -- " + sorted.get(key)+" jobs"; 
			results.add(s);
		}
		StringBuilder outputData = new StringBuilder("\n"); 
	
		String header = "Your searched criteria contains a total of " + jobs.size()+ " jobs. The most popular areas in the location include:";
		outputData.append(header + "\n");
		
		
		//Print out results up to 5 locations & all if less than or equal to 5
		if (results.size() > 5) {
			for (int i = 0; i < 5; i++) {
				String line = (i+1) +". "+ results.get(i)+"\n";
				outputData.append(line);
			}
		}
		else {
			for (int i = 0; i < results.size(); i++) {
				String line = (i+1)+". "+results.get(i)+"\n";
				outputData.append(line);
			}
		}
		String out = outputData.toString();
		System.out.print(out);
		return out; 
	}
	
	//Return list of all companies who are have job openings otherwise top 5
	/**
	 * @author amannischal
	 * @param n - integer value of the limit of how many companies you want returned from the method. 
	 * @return A String of statements which tells you how many companies are hiring and also a numbered list of the top
	 * companies with job openings. The numbered list is based on the limit specified in the parameter. If the results are less
	 * than the parameter value then all of the results are returned.
	 */
	public String topNCompaniesHiring(int n) {
		HashMap<String, Integer> companyCount = new HashMap<>(); 
		//add each company to hash map and count job openings
		for (Job j : jobs) {
			if (companyCount.containsKey(j.getCompany())) {
				int value = companyCount.get(j.getCompany());
				companyCount.put(j.getCompany(), value + 1); 
			}
			else {
				companyCount.put(j.getCompany(), 1);
			}
		}
		//create a new sorted hash map in descending order by values
		LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>(); 
		companyCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
		//Create the print statements and save them to an array
		ArrayList<String> results = new ArrayList<>(); 
		for (String key : sorted.keySet()) {
			String s = key + " -- " + sorted.get(key)+" job openings"; 
			results.add(s);
		}
		String header = "Your search criteria contains "+ jobCompanies.size() + " companies looking to hire for this position in the area. The most job openings are by the following companies:\n";
		StringBuilder outputData = new StringBuilder("\n");
		outputData.append(header);
		
		//Print out the statements based on user input
		if (results.size() > n) {
			for (int i = 0; i < n; i++) {
				String line = (i+1) + ". "+ results.get(i) + "\n"; 
				outputData.append(line);
			}
		}
		else {
			for (int i =0; i<results.size(); i++) {
				String line = (i+1) + ". " + results.get(i) + "\n";
				outputData.append(line);
			}
		}
		String out = outputData.toString();
		System.out.print(out);
		return out;
	}
	
	//same method if no int value of results is provided
	/**
	 * @author amannischal
	 * @return This method is overloaded to handle if no parameter value is entered. It will return the total companies hiring
	 * and automatically limit the results of the numbered list to top 5, and all the results if the total companies is less than 5
	 */
	public String topNCompaniesHiring() {
		HashMap<String, Integer> companyCount = new HashMap<>(); 
		//add each company to hash map and count job openings
		for (Job j : jobs) {
			if (companyCount.containsKey(j.getCompany())) {
				int value = companyCount.get(j.getCompany());
				companyCount.put(j.getCompany(), value + 1); 
			}
			else {
				companyCount.put(j.getCompany(), 1);
			}
		}
		//create a new sorted hash map in descending order by values
		LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>(); 
		companyCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
		//Create the print statements and save them to an array
		ArrayList<String> results = new ArrayList<>(); 
		for (String key : sorted.keySet()) {
			String s = key + " -- " + sorted.get(key)+" job openings"; 
			results.add(s);
		}
		String header = "Your search criteria contains "+ jobCompanies.size() + " companies looking to hire for this position in the area. The most job openings are by the following companies:\n";
		StringBuilder outputData = new StringBuilder("\n");
		outputData.append(header);
		
		//Print out the statements based on user input
		if (results.size() > 5) {
			for (int i = 0; i < 5; i++) {
				String line = (i+1) + ". "+ results.get(i) + "\n"; 
				outputData.append(line);
			}
		}
		else {
			for (int i =0; i<results.size(); i++) {
				String line = (i+1) + ". " + results.get(i) + "\n";
				outputData.append(line);
			}
		}
		String out = outputData.toString();
		System.out.print(out);
		return out;
		
	}
	
	// Calculate the average starting salary & highest paying job
	/**
	 * @author amannischal
	 * @return This method reads the jobs array created in the constructor and returns String statements of the average 
	 * starting salary and information on the highest paying job from the search results including job, location, salary, and hiring company
	 */
	public String salaryInformation() {
		ArrayList<Integer> allSalaries = new ArrayList<>();
		Job highestSalaryJob = null; 
		int highestSalary = 0; 
		
		for (Job j : jobs) {
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
					allSalaries.add(begSalary);
					if (begSalary > highestSalary) {
						highestSalary = begSalary;
						highestSalaryJob = j; 
					}
				}
				//Same as above but for single salary value listed ie ("200,000 a year")
				else {
					salary = salary.replaceAll("[^\\d.]", "");
					int begSalary = Integer.parseInt(salary);
					allSalaries.add(begSalary);
					if (begSalary > highestSalary) {
						highestSalary = begSalary;
						highestSalaryJob = j;
					}
				}
			}
		}
		//calculate the sum of the numbers to compute the average
		double sum = 0;
		for (int salary : allSalaries) {
			sum += salary; 
		}
		int averageSalary = (int) Math.round(sum /  (double) allSalaries.size());
		//Convert integers to number format with commas 
		String avgSalaryString =  String.format("%,d", averageSalary);
		String highestSalaryString = String.format("%,d", highestSalary);
		
		StringBuilder outputData = new StringBuilder("\n");
		
		String line1 = "The average Starting Salary for your searched criteria is $" + avgSalaryString +". \n";
		String line2 = "The highest paying job for your search criteria is the follwing:\n\n";
		String line3 = "JOB:\t\t\t" + highestSalaryJob.getJobTitle() + "\n"; 
		String line4 = "LOCATION:\t" + highestSalaryJob.getLocation() + "\n";
		String line5 = "COMPANY:\t" + highestSalaryJob.getCompany() + "\n";
		String line6 = "SALARY:\t\t$" + highestSalaryString + "\n"; 
		
		outputData.append(line1);
		outputData.append(line2);
		outputData.append(line3);
		outputData.append(line4);
		outputData.append(line5);
		outputData.append(line6);
		
		String out = outputData.toString();
		System.out.println(out);
		return out;
		
	}
	//getters for the unique job titles, companies, locations and array Jobs for all data	
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
	// generate setter for j unit testing only
	public void setJobs(ArrayList<Job> jobs) {
		this.jobs = jobs;
	}
	
	
	public static void main(String[] args) {
		DataAnalysis d3 = new DataAnalysis();
		d3.numberOfJobs();
		d3.topNCompaniesHiring();
		d3.salaryInformation();

	}
	
}
	
