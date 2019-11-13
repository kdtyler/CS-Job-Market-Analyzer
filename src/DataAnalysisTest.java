import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class DataAnalysisTest {
	DataAnalysis da = new DataAnalysis(); 

	@Test
	public void testTopNLocations() {
		List<String> test = da.topNLocations(2);
		assertEquals(2, test.size());
	}
	
	@Test
	public void testTopNLocationsWithLessResultsThanAskedFor() {
		List<String> test = da.topNLocations(100);
		assertEquals(da.getJobLocations().size(), test.size());
	}

	@Test
	public void testJobDescriptionContainsKeyWord() {
		int count = da.jobDescriptionContainsKeyWord("java");
		assertEquals(81, count);
	}

	@Test
	public void testAverageStartingSalary() {
		double salary = da.averageStartingSalary(da.getJobs());
		assertEquals(94714.9285714286, salary, .5); 
	}

}
