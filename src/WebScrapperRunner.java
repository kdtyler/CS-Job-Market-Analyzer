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
		int cnt = 1;
		for(String url: this.URLs) {
			WebScrapper scrapper = new WebScrapper(url);
			ArrayList<Job> jobs = scrapper.ScrapeURL();
			DataWriter dw = new DataWriter(this.outputFileName);
			dw.WriteDataToFile(jobs);
			System.out.println("Page " + cnt + " is scrapped !");
			cnt += 1;
		}
	}

	/*
	 * Search for software engineer jobs in Boston, MA, extract all job posting data from the first 5 pages
	 */
	public static void main(String[] args) {

		//File input = new File("C:\\Users\\jiang\\Desktop\\Upenn\\MCIT591\\Project\\CSJobMarketAnalyzer\\job_posting1.txt");
		File input = new File(".\\input\\job_posting1.txt");
		try {
			Scanner s = new Scanner(input);
			String url = s.nextLine().replace("\n", "");
			int num = Integer.parseInt(s.nextLine());
			String outputFile = s.nextLine();
			s.close();

			WebScrapperRunner sr = new WebScrapperRunner(url, num, outputFile);
			sr.Run();
			System.out.println("Done !");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found !");
			e.printStackTrace();
		}

	}
}
