import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * WebScrapperRunner class
 * @author Bo Jiang
 *
 */
public class WebScrapperRunner {
	private String baseURL;
	private int start;
	private int end;
	private int curr;
	private String outputFileName;

	public WebScrapperRunner(String baseURL, int start, int end, String outputFileName) {
		this.baseURL = baseURL;
		this.start = start;
		this.end = end;
		this.curr = this.start - 1;
		this.outputFileName = outputFileName;
	}

	public int getCurr() {
		return this.curr;
	}
	
	public String getBaseURL() {
		return this.baseURL;
	}
	
	public String getOutputFileName() {
		return this.outputFileName;
	}
	
	public void addToCurr() {
		this.curr += 1;
	}
	
	/*
	 * Extract job posting data for each of the URLs, save data into a .txt file
	 */
	public void Run() {
		while(this.getCurr() < this.end) {
			String url = this.getBaseURL() + "&start=" + Integer.toString(this.getCurr() * 10);			
			WebScrapper scrapper = new WebScrapper(url);
			ArrayList<Job> jobs = scrapper.ScrapeURL();
			DataWriter dw = new DataWriter(this.getOutputFileName());
			dw.WriteDataToFile(jobs);
			System.out.println("Page " + (this.getCurr() + 1) + " is scrapped !");
			this.addToCurr();
		}
	}

	/*
	 * Search for software engineer jobs in Boston, MA, extract all job posting data from the first 5 pages
	 */
	public static void main(String[] args) {

		//File input = new File("C:\\Users\\jiang\\Desktop\\Upenn\\MCIT591\\Project\\CSJobMarketAnalyzer\\job_posting1.txt");
		File input = new File(".\\input\\job_posting3.txt");
		try {
			Scanner s = new Scanner(input);
			String url = s.nextLine().replace("\n", "");
			int num1 = Integer.parseInt(s.nextLine());
			int num2 = Integer.parseInt(s.nextLine());
			String outputFile = s.nextLine();
			s.close();

			WebScrapperRunner sr = new WebScrapperRunner(url, num1, num2, outputFile);
			sr.Run();
			System.out.println("Done !");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found !");
			e.printStackTrace();
		}

	}
}
