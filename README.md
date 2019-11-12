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


GUI Update (Kevin 11/11/19)

1. Download JavaFX using the instructions in the link provided: https://www.tutorialkart.com/javafx/install-javafx-in-eclipse-ide/
   One thing not in the instructions is that you most likely need to directly add the Neon page to make it a list to choose from, and you    can do so by using the Add button and typing in "Neon" and the webpage directly.
   
 2. JavaFX is a very user-friendly application, especially for simple things like what we need to do. For now I've created a simple GUI       that let's a user input areas of interest, and I played around with a few different options (pull down lists, fill-in bubbles, text 
    boxes). Pressing 'Search' won't do anything yet, so maybe two goals going forward would be 1. Identifying one very easy thing to show 
    the answer to or graph (i.e. we will pick the user's options for them to test the interface between GUI and your guys' code). and 2.
    Figure out what other types of options to provide (time-based, etc.). 