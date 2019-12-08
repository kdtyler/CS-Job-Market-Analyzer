import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class URLGeneratorTest {

	@Test
	void testGenerateURL() {
		// test search with job title, estimated salary, location, distance, job type, experience level and sort results by job posting date
		URLGenerator g1 = new URLGenerator("data scientist", "150000", "New York", "NY", "50", "full-time", "entry level", "date");
		assertEquals(g1.getURL(), 
				"https://www.indeed.com/jobs?q=data+scientist%24150%2C000&l=New+York%2C+NY&radius=50&jt=fulltime&explvl=entry_level&sort=date");

		// test search with job title, estimated salary, location, distance, job type, experience level and sort results by relevance
		URLGenerator g2 = new URLGenerator("software engineer", "140000", "San Francisco", "CA", "5", "full-time", "entry level", "None");
		assertEquals(g2.getURL(), 
				"https://www.indeed.com/jobs?q=software+engineer%24140%2C000&l=San+Francisco%2C+CA&radius=5&jt=fulltime&explvl=entry_level");

		// test search with job title and location
		URLGenerator g3 = new URLGenerator("machine learning engineer", "None", "San Francisco", "CA", "None", "None", "None", "None");
		assertEquals(g3.getURL(), 
				"https://www.indeed.com/jobs?q=machine+learning+engineer&l=San+Francisco%2C+CA");

		// test search with job title, location, job type
		URLGenerator g4 = new URLGenerator("machine learning engineer", "None", "San Francisco", "CA", "None", "internship", "None", "None");
		assertEquals(g4.getURL(), 
				"https://www.indeed.com/jobs?q=machine+learning+engineer&l=San+Francisco%2C+CA&jt=internship");

		// test search with job title, location and experience level
		URLGenerator g5 = new URLGenerator("machine learning engineer", "None", "San Francisco", "CA", "None", "None", "mid level", "None");
		assertEquals(g5.getURL(), 
				"https://www.indeed.com/jobs?q=machine+learning+engineer&l=San+Francisco%2C+CA&explvl=mid_level");
	}

}
