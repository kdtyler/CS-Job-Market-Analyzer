import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * DataWriter class
 * Writes job posting information to file
 * @author Bo Jiang
 *
 */
public class DataWriter {
	private String fileName;
	
	public DataWriter(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Writes job posting information to file
	 * @param jobs: An array list of Jobs
	 */
	public void WriteDataToFile(ArrayList <Job> jobs) {
		try {
			FileWriter fw = new FileWriter(this.fileName, true);
			for(Job job: jobs) {
				fw.write(job.convertToString());
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
