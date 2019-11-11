
public class DataCleaner {
	public DataCleaner() {
		
	}; 
	
	public String cleanLocation (String text) {
		int n = text.indexOf(",");
		String cleanString = text.substring(0, n+4);
		return cleanString; 
	}

	
	public static void main(String[] args) {
		DataCleaner dc = new DataCleaner(); 
		String s = dc.cleanLocation("Boston, MA center city");
		System.out.println(s);
	}
}
