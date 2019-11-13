import java.util.ArrayList;

/**
 * WebScrapperRunner class
 * @author Bo Jiang
 *
 */
public class WebScrapperRunner {
	private String baseURL;
	private int num;
	private String outputFileName;
	private String[] URLs;
	
	public WebScrapperRunner(String baseURL, int num, String outputFileName) {
		this.baseURL = baseURL;
		this.num = num;
		this.outputFileName = outputFileName;
		this.URLs = new String[this.num / 10];
		for(int i = 0; i < this.URLs.length; i++) {
			this.URLs[i] = this.baseURL + "&start=" + Integer.toString(i * 10);
		}
	}
	
	/*
	 * Extract job posting data for each of the URLs, save data into a .txt file
	 */
	public void Run() {
		for(String url: this.URLs) {
			WebScrapper scrapper = new WebScrapper(url);
			ArrayList<Job> jobs = scrapper.ScrapeURL();
			DataWriter dw = new DataWriter(this.outputFileName);
			dw.WriteDataToFile(jobs);
		}
	}
	
	/*
	 * Search for software engineer jobs in Boston, MA, extract all job posting data from the first 5 pages
	 */
	public static void main(String[] args) {
		WebScrapperRunner sr = new WebScrapperRunner("https://www.indeed.com/jobs?q=software+engineer&l=Boston%2C+MA", 50, "software_engineer_boston.txt");
		sr.Run();
		System.out.println("Done !");
	}
}
