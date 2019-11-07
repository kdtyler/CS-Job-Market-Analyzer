# CIT591 Final Project: CS Job Market Analyzer
Instruction on web scrapping:
1. Download ChromeDriver - WebDriver for Chrome: https://sites.google.com/a/chromium.org/chromedriver/. 
   
   Make sure you the version of ChromeDriver is the same as the version of your Chrome.
   
2. Download Selenium for Java: https://www.seleniumhq.org/download/.

3. Add external JARs to the project.
  
   Right click on the project, click Build Path, click Configure Build Path and then click Add External JARs.
   
   Select all JARs from step 2 and then click Apply and Close.
  
4. Configure WebScrapper.java
   
   In line 23, set the second argument of System.setProperty() function to the path for the .exe file from step 1.

5. Run WebScrapper.java.

   The program searches for all software engineer jobs in Boston area and scrapes the first 5 pages of the job posting. For each job posting, the program extracts job title, location, company, salary and job description. It saves the output data to .txt file. 
