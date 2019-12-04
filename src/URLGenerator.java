/**
 * URL Generator Class
 * This class generate a valid URL for job posting search on indeed.com given 
 * search criteria (job title, location, estimated salary, etc.) users are interested in
 * Users must specify job title, location (city and state); Others are optional
 * @author Bo Jiang
 *
 */
public class URLGenerator {
	private String jobTitle;
	private String estimatedSalary;
	private String city;
	private String state;
	private String distance;
	private String jobType;
	private String experienceLevel;
	private String sortBy;
	private String URL;
	
	public URLGenerator(String jobTitle, String estimatedSalary, String city, String state, String distance, 
			String jobType, String experienceLevel, String sortBy) {
		this.jobTitle = jobTitle;
		this.estimatedSalary = estimatedSalary;
		this.city = city;
		this.state = state;
		this.distance = distance;
		this.jobType = jobType;
		this.experienceLevel = experienceLevel;
		this.sortBy = sortBy;
		this.setURL(this.generateURL());
	}
	
	public void setURL(String url) {
		this.URL = url;
	}
	
	public String getURL() {
		return this.URL;
	}
	
	public String generateURL() {
		String url = "https://www.indeed.com/jobs?";
		String[] jts = this.jobTitle.trim().split("\\s+");
		url += ("q=" + String.join("+", jts));
		if(!this.estimatedSalary.equals("None")) {
			url += ("%24" + this.estimatedSalary.substring(0, 3) + "%2C" + this.estimatedSalary.substring(3, 6));
		}
		
		String[] cities = this.city.trim().split("\\s+");
		url += ("&l=" + String.join("+", cities) + "%2C+" + this.state.trim());
		
		if(!this.distance.equals("None")) {
			url += ("&radius=" + this.distance);
		}
		
		if(this.jobType.equals("full-time") || this.jobType.equals("internship") || jobType.equals("contract") ||
				this.jobType.equals("part-time") || this.jobType.equals("temporary") || this.jobType.equals("commission")) {
			url += ("&jt=" + this.jobType.replace("-", ""));
		}
		
		if(this.experienceLevel.equals("senior level") || this.experienceLevel.equals("mid level") || 
				this.experienceLevel.equals("entry level")) {
			String[] exp = this.experienceLevel.trim().split("\\s+");
			url += ("&explvl=" + String.join("_", exp));
		}
		
		if(this.sortBy.equals("date")) {
			url += "&sort=date";
		}
		
		url += "&start=0";
		return url;
	}
	
	/*
	public static void main(String[] args) {
		URLGenerator g = new URLGenerator("machine learning engineer", "None", "San Francisco", "CA", "None", "None", "None", "None");
		System.out.println(g.getURL());
	}
	*/
	
}
