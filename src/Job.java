/**
 * Job class
 * @author Bo Jiang
 *
 */
public class Job {
	/**
	 * Job class stores job title, location, company salary and job description
	 */
	private String jobTitle;
	private String location;
	private String company;
	private String salary;
	private String jobDescription;

	public Job() {
		this.jobTitle = "None";
		this.location = "None";
		this.company = "None";
		this.salary = "None";
		this.jobDescription = "None";
	}
	
	public Job(String jobTitle, String location, String company, String salary, String jobDescription) {
		this.jobTitle = jobTitle;
		this.location = location;
		this.company = company;
		this.salary = salary;
		this.jobDescription = jobDescription;
	}
	
	public String getJobTitle() {
		return this.jobTitle;
	}

	public String getLocation() {
		return this.location;
	}

	public String getCompany() {
		return this.company;
	}

	public String getSalary() {
		return this.salary;
	}

	public String getJobDescription() {
		return this.jobDescription;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	/**
	 * Return string representation of a job. 
	 * Each piece of information is separated by "|"
	 * @return a string has job title, location, company, salary and job description of a job posting separated by "|"
	 */
	public String convertToString() {
		return this.getJobTitle() + "|" + this.getLocation() + "|" + this.getCompany() + "|" + this.getSalary() + "|" + this.getJobDescription() + "|" + "\n";
	}
}
