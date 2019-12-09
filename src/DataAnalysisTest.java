import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class DataAnalysisTest {
	DataAnalysis da = new DataAnalysis("software_engineer_boston.txt");

	@Test
	public void testCleanLocation() {
		String location = "Boston, MA (Center City 08054)";
		String cleaned = da.cleanLocation(location); 
		assertEquals("Boston, MA", cleaned);
	}

	@Test
	//method should always return at least 1 result
	public void testNumberOfJobs() {
		String test = da.numberOfJobs();
		assertTrue(test.contains("1. "));
	}
	
	@Test
	//will never have 6 results.
	public void testNumberOfJobs2() {
		String test = da.numberOfJobs();
		assertTrue(!test.contains("6. "));
	}


	@Test
	//make sure the that the results can not be more than the number of companies hiring if number
	//number higher than total companies is choosen
	public void testTopNCompaniesHiring() {
		String test = da.topNCompaniesHiring(1000);
		int limit = da.getJobCompanies().size();
		StringBuilder s = new StringBuilder();
		s.append(Integer.toString(limit));
		s.append(". ");
		String s2 = s.toString();
		assertTrue(test.contains(s2));
		
		//test one over the limit does not exist
		int limit2 = limit + 1;
		StringBuilder sb2 = new StringBuilder();
		sb2.append(Integer.toString(limit2));
		sb2.append(".");
		String s3 = sb2.toString();
		assertTrue(!test.contains(s3)); 
	}
	
	@Test
	//make sure the that the results are limited to 2 if an limit is input into the method
	public void testTopNCompaniesHiring2() {
		String test = da.topNCompaniesHiring(2);
		int limit = 2;
		StringBuilder s = new StringBuilder();
		s.append(Integer.toString(limit));
		s.append(". ");
		String s2 = s.toString();
		assertTrue(test.contains(s2)); 
		assertTrue(!test.contains("3."));
	}
	
	@Test
	//Test whether the results contain only 5 results if no limit is entered into the method
	public void testTopNCompaniesHiring3() {
		String test = da.topNCompaniesHiring();
		//contains the limit
		assertTrue(test.contains("5."));
		//should not contain 1 over the limit
		assertTrue(!test.contains("6."));
		
	}
	
	@Test
	//Test whether the results contain less than 5 results if no limit is entered into the method
	// and the results only contains less than 5 companies
	public void testTopNCompaniesHiring4() {
		ArrayList<Job> jobsTest = new ArrayList<>(); 
		jobsTest.add(da.getJobs().get(1));
		jobsTest.add(da.getJobs().get(2));
		jobsTest.add(da.getJobs().get(3));
		da.setJobs(jobsTest);
		
		//Find the unique jobs in the 1st 3 jobs
		ArrayList<String> companies = new ArrayList<>(); 
		for (Job j : jobsTest) {
			String comp = j.getCompany();
			if (!companies.contains(comp)) {
				companies.add(comp);
			}
		}
		// get the unique listing of companies in the sample
		int n = companies.size(); 
		StringBuilder s = new StringBuilder();
		s.append(Integer.toString(n)); 
		s.append(". "); 
		// limit above max to test it does not exist
		int n2 = n+1; 
		StringBuilder s2 = new StringBuilder(); 
		s2.append(Integer.toString(n2));
		s2.append(". "); 
		
		String max = s.toString();
		String overMax = s2.toString();
				
		// run the method on the sample size 
		String test = da.topNCompaniesHiring();
		//contains
		assertTrue(test.contains(max));
		//does not contain
		assertTrue(!test.contains(overMax));
	}

	@Test
	public void testSalaryInformation() {
		String test = da.salaryInformation();
		assertTrue(test.contains("JOB"));
		assertTrue(test.contains("LOCATION"));
		assertTrue(test.contains("COMPANY"));
		assertTrue(test.contains("SALARY"));
	}

}
