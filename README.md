# CIT591 Final Project: CS Job Market Analyzer
Instruction on web scrapping:
1. Download ChromeDriver - WebDriver for Chrome: https://sites.google.com/a/chromium.org/chromedriver/
   
   Make sure the version of ChromeDriver is the same as the version of your Chrome browser.
   
2. Download Selenium for Java: https://www.seleniumhq.org/download/

3. Add external JARs to the project
  
   Right click on the project, click Build Path, click Configure Build Path and then click Add External JARs.
   
   Select all JARs from step 2 and then click Apply and Close.
  
4. Configure WebScrapper.java
   
   In line 39 and 74, set the second argument of System.setProperty() function to the path for the .exe file from step 1.
   
5. Run WebScrapperRunner.java in Eclipse

   The program reads in parameters from input file you configured in step 5. Then it searches for job postings. For each job posting, the program extracts job title, location, company, salary and job description. Finally, it saves the output data to the output file you specified in step 5. 


Instructions on GUI

Download JavaFX using the instructions in the link provided: https://www.tutorialkart.com/javafx/install-javafx-in-eclipse-ide/
One thing not in the instructions is that you most likely need to directly add the Neon page to make it a list to choose from, and you can do so by using the Add button and typing in "Neon" and the webpage directly. A common error is to not have spaces around the "-" symbol when typing "Neon - websiteURL". 

Running the program:

The program should be ran by pressing Run on the GUIHome.java class.

Possible Errors from github:

Our team found that for only certain teammembers, the github version when pulling would mess up the indendation of the GUIHome.java file. While this likely won't happen, if the GUI seems to not work, the best choice for a quick fix is cntr-I on the GUIHome.java file. We apologize for any inconvenience.

