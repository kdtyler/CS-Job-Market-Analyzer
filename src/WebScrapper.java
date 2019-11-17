import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.NoSuchElementException;
// import java.lang.NullPointerException;
import java.util.ArrayList;

/**
 * WebScrapper class
 * @author Bo Jiang
 *
 */
public class WebScrapper {
	private String url;

	public WebScrapper(String url) {
		this.url = url;
	}

	public String getURL() {
		return this.url;
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
		}

		driver.close();
		return jobs;
	}
}
