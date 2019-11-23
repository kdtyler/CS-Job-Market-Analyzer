import java.io.*;
import java.util.*;

public class DataAnalysis2 {
	private ArrayList<Job> jobs = new ArrayList<Job>();
	private ArrayList<Job> filteredJobs = new ArrayList<Job>();
	private String jobTitle; 
	private String location;
	// Create array Lists of all the unique job titles, locations, and companies hiring
	private ArrayList<String> jobTitles = new ArrayList<String>();
	private ArrayList<String> jobLocations = new ArrayList<String>();
	private ArrayList<String> jobCompanies = new ArrayList<String>();
	
	public DataAnalysis2(String jobTitle, String location){
		this.jobTitle = jobTitle.toLowerCase();
		this.location = location; 
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
			
			for (Job j : jobs) {
				String job1 = j.getJobTitle().toLowerCase();
				String job2 = jobTitle.toLowerCase();
				String loc1 =  j.getLocation().toLowerCase().replace(" ", "");
				String loc2 = location.toLowerCase().replace(" ", ""); 
				if (job1.contains(job2) & loc1.contains(loc2)){
					filteredJobs.add(j); 
				}
			}
			
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}

	//Helper method to clean location information
		private String cleanLocation (String text) {
			int n = text.indexOf(",");
			String cleanString = text.substring(0, n+4);
			return cleanString; 
		}
		// returns number of jobs with title in a location using city,State format 
		
		public String numberOfJobs() {
			int numFiltered = filteredJobs.size();
			int totalJobs = jobs.size();
			return "Your search contains "+numFiltered+" jobs, compared to "+totalJobs+" jobs everywhere. "; 
		}
		
		//Returns the count of all the jobs whose description contains the identified keyword 
		public String jobDescriptionContainsKeyWord(String keyword) {
			int keywordCounter =0; 
			for (Job j : filteredJobs) {
				String description = j.getJobDescription().toLowerCase();
				if (description.contains(keyword.toLowerCase())) {
					keywordCounter++;
				}
			}
			return "Of the "+filteredJobs.size()+" jobs "+keywordCounter+" contain the keyword entered."; 
		}
		
		public String averageStartingSalary() {
			ArrayList<Integer> localStartingValues = new ArrayList<Integer>();
			ArrayList<Integer> allStartingValues = new ArrayList<Integer>();
			ArrayList<Job> similarJobs = new ArrayList<Job>();
			
			//Add similar jobs to the job array
			for (Job j : jobs) {
				if (j.getJobTitle().toLowerCase().contains(jobTitle)) {
					similarJobs.add(j);
				}
			}
			// Add starting salaries for filtered jobs
			for (Job j : filteredJobs) {
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
						localStartingValues.add(begSalary);
					}
					//Same as above but for single salary value listed ie ("200,000 a year")
					else {
						salary = salary.replaceAll("[^\\d.]", "");
						int begSalary = Integer.parseInt(salary);
						System.out.println(begSalary);
						localStartingValues.add(begSalary); 
					}
				}
			}
			
			for (Job j : similarJobs) {
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
						allStartingValues.add(begSalary);
					}
					//Same as above but for single salary value listed ie ("200,000 a year")
					else {
						salary = salary.replaceAll("[^\\d.]", "");
						int begSalary = Integer.parseInt(salary);
						System.out.println(begSalary);
						allStartingValues.add(begSalary); 
					}
				}
			}
			
			double localSum = 0;
			double allSum = 0;
			
			//sum all the values and return the average
			for (int i : localStartingValues) {
				localSum += i; 
			}
			for (int i : allStartingValues) {
				allSum += i; 
			}
			
			double localStartingSalary = localSum / (double) localStartingValues.size(); 
			double allStartingSalary = allSum	 / (double) allStartingValues.size();
			
			return "The average starting salary for a "+jobTitle+" in "+location+" is "+localStartingSalary+" compared to the average of "+allStartingSalary+" in all locations.";
		}
		
		
		public static void main(String[] args) {
			DataAnalysis2 d2 = new DataAnalysis2("software engineer", "Boston,MA"); 
			String i = d2.averageStartingSalary();
			System.out.println(i);	
			
		}


}
