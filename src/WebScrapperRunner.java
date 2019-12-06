import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * WebScrapperRunner class
 * @author Bo Jiang
 *
 */
public class WebScrapperRunner {
	private String baseURL;
	private String outputFileName;
	private int totalNumOfJobs;
	private int currNumOfJobs;
	private int currNumOfPages;


	public WebScrapperRunner(String baseURL, String outputFileName) {
		this.baseURL = baseURL;
		this.outputFileName = outputFileName;
		this.totalNumOfJobs = 0;
		this.currNumOfJobs = 0;
		this.currNumOfPages = 0;
	}


	public String getBaseURL() {
		return this.baseURL;
	}

	public String getOutputFileName() {
		return this.outputFileName;
	}

	public int getTotalNumOfJobs() {
		return this.totalNumOfJobs;
	}

	public int getCurrNumOfJobs() {
		return this.currNumOfJobs;
	}

	public int getCurrNumOfPages() {
		return this.currNumOfPages;
	}

	public void setTotalNumOfJobs(int n) {
		this.totalNumOfJobs = n;
	}

	public void addToCurrNumOfPages(int n) {
		this.currNumOfPages += n;
	}

	public void addToCurrNumOfJobs(int n) {
		this.currNumOfJobs += n;
	}
	/*
	 * Extract job posting data for each of the URLs, save data into a .txt file
	 */
	public void Run() {
		WebScrapper firstPage = new WebScrapper(this.baseURL);
		firstPage.checkNumOfJobs();
		this.setTotalNumOfJobs(firstPage.getTotalNumOfJobs());
		System.out.println("Total number of job postings is " + firstPage.getTotalNumOfJobs());

		while(this.getCurrNumOfJobs() < this.getTotalNumOfJobs() && this.getCurrNumOfPages() < 100) {
			String url = this.getBaseURL() + "&start=" + Integer.toString(this.getCurrNumOfPages() * 10);			
			WebScrapper scrapper = new WebScrapper(url);
			ArrayList<Job> jobs = scrapper.ScrapeURL();
			DataWriter dw = new DataWriter(this.getOutputFileName());
			dw.WriteDataToFile(jobs);
			this.addToCurrNumOfJobs(jobs.size());
			this.addToCurrNumOfPages(1);
			System.out.println("Page " + this.getCurrNumOfPages() + " is scrapped !");
			System.out.println("There are " + this.getCurrNumOfJobs() + " number of jobs !");
		}
		System.out.println("Done !");
	}

	/*
	 * Search for software engineer jobs in Boston, MA, extract all job posting data from the first 5 pages
	 */

	/*
	public static void main(String[] args) {
		int a = Integer.parseInt("123a");
		 WebScrapperRunner sr = new WebScrapperRunner("https://www.indeed.com/jobs?q=machine+learning+engineer%24100%2C000&l=Tempe%2C+AZ", 
				".\\output\\machine_learning_engineer_Tempe.txt");
		 sr.Run();
	}
	*/

}
