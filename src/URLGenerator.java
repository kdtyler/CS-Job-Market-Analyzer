/**
 * URL Generator Class
 * Generate a valid URL for job posting search on indeed.com given 
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
		// base URL
		String url = "https://www.indeed.com/jobs?";
		
		// add job title
		String[] jts = this.jobTitle.trim().split("\\s+");
		url += ("q=" + String.join("+", jts));
		
		// add estimated salary
		if(!this.estimatedSalary.equals("None")) {
			try {
				int estimatedSalaryInt = Integer.parseInt(this.estimatedSalary);
				String thousands = Integer.toString(Math.min(estimatedSalaryInt / 1000, 999));
				String ones = Integer.toString(estimatedSalaryInt % 1000);
				if(ones.length() == 1) {
					ones = "00" + ones;
				}
				else if(ones.length() == 2) {
					ones = "0" + ones;
				}
				url += ("%24" + thousands + "%2C" + ones);
			}
			catch(java.lang.NumberFormatException e) {
				System.out.println("Invalid input for estimated salary !");
			}

		}
		
		// add location
		String[] cities = this.city.trim().split("\\s+");
		url += ("&l=" + String.join("+", cities) + "%2C+" + this.state.trim());

		// add distance
		if(!this.distance.equals("None")) {
			try {
				Integer.parseInt(this.distance);
				url += ("&radius=" + this.distance);
			}
			catch(java.lang.NumberFormatException e) {
				System.out.println("Invalid input for distance !");
			}
		}

		// add job type
		if(!this.jobType.equals("None")){
			if(!(this.jobType.equals("full-time") || this.jobType.equals("internship") || jobType.equals("contract") ||
					this.jobType.equals("part-time") || this.jobType.equals("temporary") || this.jobType.equals("commission"))) {
				System.out.println("Invalid input for job type !");
			}else {
				url += ("&jt=" + this.jobType.replace("-", ""));
			}	
		}

		// add experience level
		if(!this.experienceLevel.equals("None")) {
			if(!(this.experienceLevel.equals("senior level") || this.experienceLevel.equals("mid level") || 
					this.experienceLevel.equals("entry level"))) {
				System.out.println("Invalid input for experience level !");
			}
			else {
				String[] exp = this.experienceLevel.trim().split("\\s+");
				url += ("&explvl=" + String.join("_", exp));
			}
		}

		// add sort by
		if(!this.sortBy.equals("None")) {
			if(!(this.sortBy.equals("date") || this.sortBy.equals("relevance"))) {
				System.out.println("Invalid input for sort by !");
			}
			else {
				if(this.sortBy.equals("date")) {
					url += "&sort=date";
				}
			}
		}

		return url;
	}

}
