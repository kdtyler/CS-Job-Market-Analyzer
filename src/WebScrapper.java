import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;
// import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WebScrapper class
 * @author Bo Jiang
 *
 */
public class WebScrapper {
	private String url;
	private int totalNumOfJobs;
	private int numOfJobsOnCurrentPage;

	public WebScrapper(String url) {
		this.url = url;
		this.totalNumOfJobs = 0;
		this.numOfJobsOnCurrentPage = 0;
	}

	public String getURL() {
		return this.url;
	}

	// Check how many job postings are returned by a search
	public void checkNumOfJobs() {
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(this.url);
		//String original_window = driver.getWindowHandle();
		try {
			// checks how many job postings are returned by search
			String text = driver.findElement(By.id("searchCountPages")).getText();
			text = text.replace("Page 1", "").replace("jobs", "").trim();
			// System.out.println(text);
			Pattern p = Pattern.compile("\\d{0,3},*\\d{1,3}");
			Matcher m = p.matcher(text);
			if(m.find()) {
				String num = m.group(0).replace(",", "");
				this.totalNumOfJobs = Integer.parseInt(num);
				// System.out.println(this.totalNumOfJobs);
			}
			
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			// No job posting meets search criteria 
			System.out.println("No job posting matches search criteria");
		}
		driver.close();
	}

	public int getTotalNumOfJobs() {
		return this.totalNumOfJobs;
	}
	
	/**
	 * Scrape job posting data from a URL
	 * @return An array list of Jobs
	 */
	public ArrayList<Job> ScrapeURL(){
		ArrayList<Job> jobs = new ArrayList<Job>();
		// Connect a driver to a program
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// Go to url
		driver.get(this.url);
		String original_window = driver.getWindowHandle();

		/*
		 * For each of job posting, extract job title, location, company, salary and job description.
		 * If information is not available, "None" value is used as default
		 */
		for(WebElement position: driver.findElements(By.className("result"))) {
			String jobTitle, location, company, salary, jobDescription;
			try {
				jobTitle = position.findElement(By.className("jobtitle")).getText();			
			}
			catch(org.openqa.selenium.NoSuchElementException e) {
				jobTitle = "None";
			}

			try {
				location = position.findElement(By.className("location")).getText();	
			}
			catch(org.openqa.selenium.NoSuchElementException e) {
				location = "None";
			}

			try {
				company = position.findElement(By.className("company")).getText();			
			}
			catch(org.openqa.selenium.NoSuchElementException e) {
				company = "None";
			}

			try {
				salary = position.findElement(By.className("salary")).getText();			
			}
			catch(org.openqa.selenium.NoSuchElementException e) {
				salary = "None";
			}

			/*
			 * Click on each job posting and extract job description
			 */
			position.click();
			String new_window = null;
			for(String win: driver.getWindowHandles()) {
				if(!win.equals(original_window)) {
					new_window = win;
				}
			}

			try {
				driver.switchTo().window(new_window);
				jobDescription = driver.findElement(By.className("jobsearch-JobComponent-description")).getText().replace("\n", " ");
				driver.close();
				driver.switchTo().window(original_window);

			}
			catch(java.lang.NullPointerException e) {
				jobDescription = "None";
			}
			Job job = new Job(jobTitle, location, company, salary, jobDescription);
			jobs.add(job);
			// System.out.println(job.convertToString());
		}

		driver.close();
		this.setNumOfJobsOnCurrentPage(jobs);
		return jobs;
	}
	
	public void setNumOfJobsOnCurrentPage(ArrayList<Job> jobs) {
		this.numOfJobsOnCurrentPage = jobs.size();
	}
	
	
	public int getNumOfJobsOnCurrentPage() {
		return this.numOfJobsOnCurrentPage;
	}

	/*
	public static void main(String[] args) {
		WebScrapper scrapper = new WebScrapper("https://www.indeed.com/jobs?q=machine+learning+engineer%24100%2C000&l=Tempe%2C+AZ");
		scrapper.checkNumOfJobs();
	}
	*/
}
