import javafx.application.Application; 
import javafx.collections.FXCollections; 
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 

import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.CheckBox; 
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView; 
import javafx.scene.control.RadioButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;  
import javafx.scene.control.ToggleButton;
import javafx.stage.Modality;
import javafx.stage.Stage; 

/**
 * @author Kevin Tyler
 * This class sets up the user input GUI and the output GUI. In the input GUI, the user chooses parameters for the jobs
 * they are interested in. After the webpages are scraped, and the data analysis is run, the output GUI then shows 
 * statistics based on their search parameters.
 */
public class GUIHome extends Application { 
   
	/**
	 * The start method contains the creation of both the input GUI and output GUI
	 **/
   @Override 
   public void start(Stage stage) {    
	   
	  // Application Title - enlarged
	  Text title = new Text("Job Market Analyzer");
	  title.setStyle("-fx-font: normal bold 25px 'serif' ");
	   
	  // Label for Job Title
	  Text jobTitleLabel = new Text("Job Title");
	  jobTitleLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
	   
	  // Textfield for Job Title
	  TextField jobTitleText = new TextField();
	  
	  // Label for estimated salary 
      Text estimatedSalaryLabel = new Text("Estimated Salary"); 
      estimatedSalaryLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
      
      // Textfield for estimated salary
      TextField estimatedSalaryText = new TextField();
	   	   
      // Label for City
      Text cityOfInterestLabel = new Text("City"); 
      cityOfInterestLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
      
      // Textfield for city
      TextField cityOfInterestText = new TextField(); 
      
      // Label for state
      Text stateOfInterestLabel = new Text("State");
      stateOfInterestLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
         
      // Combo box for state (allows scrolling)
      ComboBox<String> stateComboBox = new ComboBox<String>(); 
      stateComboBox.getItems().addAll
         ("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", 
        		 "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", 
        		 "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"); 
      stateComboBox.setValue("AL") ;
      
      // Label for type of job (full-time, internship, etc.)
      Text typeLabel = new Text("Type"); 
      typeLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
      
      // Toggle group of radio buttons for type of job      
      ToggleGroup jobType = new ToggleGroup(); 
      RadioButton fullTimeRadio = new RadioButton("full-time"); 
      fullTimeRadio.setToggleGroup(jobType); 
      RadioButton internshipRadio = new RadioButton("internship"); 
      internshipRadio.setToggleGroup(jobType); 
      RadioButton contractRadio = new RadioButton("contract"); 
      contractRadio.setToggleGroup(jobType); 
      RadioButton partTimeRadio = new RadioButton("part-time"); 
      partTimeRadio.setToggleGroup(jobType); 
      RadioButton temporaryRadio = new RadioButton("temporary"); 
      temporaryRadio.setToggleGroup(jobType); 
      RadioButton commissionRadio = new RadioButton("commission"); 
      commissionRadio.setToggleGroup(jobType); 
      
      // Sets default to full-time
      fullTimeRadio.setSelected(true);

      // Label for experience required
      Text experienceLabel = new Text("Experience"); 
      experienceLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
      
      // Choice box for experience required
      ChoiceBox<String> educationChoiceBox = new ChoiceBox<String>(); 
      educationChoiceBox.getItems().addAll("Default (all)", "entry level", "mid level", "senior level"); 
      educationChoiceBox.setValue("Default (all)") ;
      
      // Label for distance
	  Text distanceLabel = new Text("Distance");
	  distanceLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      
      // Choice box for distance
      ChoiceBox<String> distanceChoiceBox = new ChoiceBox<String>(); 
      distanceChoiceBox.getItems().addAll("Default (<25 miles)", "<10 miles", "<50 miles", "<100 miles");
      distanceChoiceBox.setValue("Default (<25 miles)") ;
       
      // Search Button created
      Button buttonRegister = new Button("Search");  
      buttonRegister.setStyle("-fx-font: normal bold 15px 'serif' ");
      
      // Occurs when user presses the "Search" button
      buttonRegister.setOnAction(e -> {
    	  
    	  // Creating GUIHelper object to help parse the correct strings
    	  GUIHomeHelper guiHH = new GUIHomeHelper() ;
    	  
    	  // Pulling out all user input and saving in variables to send to DataAnalysis.java class
    	  String userJobTitle = guiHH.TextfieldCheck(jobTitleText.getText());
    	  String state = (String) stateComboBox.getValue();
    	  String city = guiHH.TextfieldCheck(cityOfInterestText.getText());
    	  String estimatedSalary = guiHH.TextfieldCheck(estimatedSalaryText.getText());
    	  RadioButton selectedRadioButton = (RadioButton) jobType.getSelectedToggle();
    	  String userInputToggleJobType = selectedRadioButton.getText();
    	  String experienceChoice = guiHH.ExperienceLevelCheck((String) educationChoiceBox.getValue());
    	  String distanceChoice = (String) distanceChoiceBox.getValue();
    	  String distanceChoiceNumber = guiHH.distanceConverter(distanceChoice);
    	  
    	  // Print to console to check user inputs. (Actual output user sees is in separate window
    	  System.out.println("Job Title: " + userJobTitle);
    	  System.out.println("State: " + state);
    	  System.out.println("City: " + city);
    	  System.out.println("Estimated Salary: " + estimatedSalary);
    	  System.out.println("Type: " + userInputToggleJobType);
    	  System.out.println("Experience: " + experienceChoice);
    	  System.out.println("Distance: " + distanceChoiceNumber);
    	  
    	  // Generates a valid URL to be used in WebScrapperRunner.java
    	  URLGenerator g = new URLGenerator(userJobTitle, estimatedSalary, city, state, distanceChoiceNumber, userInputToggleJobType, experienceChoice, "None");
    	  WebScrapperRunner wbr = new WebScrapperRunner(g.getURL(), "output.txt");
    	  
    	  String noJobsString = "";
    	  String salaryInformationString = "";
    	  String topNCompaniesHiringString = "";
    	  String numberOfJobsString = "";
    	  
    	  int numOfJobs = wbr.getTotalNumOfJobs();
    	  if(numOfJobs == 0) {
    		  System.out.println("No job posting matches search !");
    		  noJobsString = "No job postings returned for search parameters." ;
    	  } else {
    		  wbr.Run(); // Creates and fills in output.txt file
    		  
    		  // DataAnalysis object created, uses output.txt file generated above
        	  DataAnalysis da = new DataAnalysis();
        	  
        	  // Formated strings for output to user generated 
        	  salaryInformationString = da.salaryInformation();
        	  topNCompaniesHiringString = da.topNCompaniesHiring(10);
        	  numberOfJobsString = da.numberOfJobs();
    	  }
    	  
    	  // Results header for output window
    	  Text results = new Text("Results");
    	  results.setStyle("-fx-font: normal bold 25px 'serif' ");
    	  
    	  // GridPane class basis for output window
    	  GridPane secondaryLayout = new GridPane();
    	  secondaryLayout.setMinSize(800,  500);
    	  
    	  // Setting the padding    
          secondaryLayout.setPadding(new Insets(10, 10, 10, 10));  
          
          // Setting the vertical and horizontal gaps between the columns 
          secondaryLayout.setVgap(5); 
          secondaryLayout.setHgap(5); 
    	  
    	  // Creating second scene (output window)
    	  Scene secondScene = new Scene(secondaryLayout);
    	  
    	  // Creating output stage
    	  Stage outputStage = new Stage();
    	  outputStage.setTitle("Output");
    	  outputStage.setScene(secondScene);
    	  
    	  // output stage owner set to stage (input window)
    	  outputStage.initOwner(stage);
    	  
    	  // Creating text for output into window
    	  Text noJobsStringText = new Text(noJobsString);
    	  Text salaryInformationStringText = new Text(salaryInformationString);
    	  Text topNCompaniesHiringStringText = new Text(topNCompaniesHiringString);
    	  Text numberOfJobsStringText = new Text(numberOfJobsString);
    	  
    	  // Adding output text to output window
    	  secondaryLayout.add(results, 0, 0);
    	  secondaryLayout.add(noJobsStringText, 0, 1);
    	  secondaryLayout.add(salaryInformationStringText, 0, 2);
    	  secondaryLayout.add(topNCompaniesHiringStringText, 0, 3);
    	  secondaryLayout.add(numberOfJobsStringText, 0, 4);
    	  
    	  // Changing output window background color to LIGHTBLUE to match input window
    	  secondaryLayout.setStyle("-fx-background-color: LIGHTBLUE;");    
    	  
    	  // Show output window
    	  outputStage.show();  
      });
      
      
      // Creating a Grid Pane 
      GridPane gridPane = new GridPane();    
      
      // Custom column constraints to make window neater
      ColumnConstraints column1 = new ColumnConstraints(120) ;
      gridPane.getColumnConstraints().add(column1);
      
      ColumnConstraints column2 = new ColumnConstraints(150) ;
      gridPane.getColumnConstraints().add(column2);
      
      ColumnConstraints column3 = new ColumnConstraints(150) ;
      gridPane.getColumnConstraints().add(column3);

      // Setting the padding    
      gridPane.setPadding(new Insets(10, 10, 10, 10));  
      
      // Setting the vertical and horizontal gaps between the columns 
      gridPane.setVgap(5); 
      gridPane.setHgap(5);       
      
      // Setting the Grid alignment 
      gridPane.setAlignment(Pos.CENTER); 
       
      // Arranging all the nodes in the grid (node, column #, row #)
      gridPane.add(title,  1, 0);
      
      gridPane.add(jobTitleLabel,  0,  1);
      gridPane.add(jobTitleText,  1,  1);
      
      gridPane.add(estimatedSalaryLabel, 0, 2);
      gridPane.add(estimatedSalaryText, 1, 2);;
      
      gridPane.add(cityOfInterestLabel, 0, 3); 
      gridPane.add(cityOfInterestText, 1, 3); 
      
      gridPane.add(stateOfInterestLabel, 0, 4); 
      gridPane.add(stateComboBox, 1, 4); 
      
      gridPane.add(typeLabel, 0, 5); 
      gridPane.add(fullTimeRadio, 1, 5); 
      gridPane.add(internshipRadio, 2, 5);       
      gridPane.add(contractRadio,  3,  5);
      gridPane.add(partTimeRadio,  1,  6);
      gridPane.add(temporaryRadio,  2,  6);
      gridPane.add(commissionRadio,  3,  6);     
       
      gridPane.add(experienceLabel, 0, 7); 
      gridPane.add(educationChoiceBox, 1, 7);    
      
      gridPane.add(distanceLabel, 0, 8); 
      gridPane.add(distanceChoiceBox, 1, 8);
       
      gridPane.add(buttonRegister, 3, 9);      
       
      // Setting the back ground color 
      gridPane.setStyle("-fx-background-color: LIGHTBLUE;");       
       
      // Creating a scene object 
      Scene scene = new Scene(gridPane); 
      
      // Setting title to the Stage 
      stage.setTitle("Job Market Analyzer"); 
         
      // Adding scene to the stage 
      stage.setScene(scene);  
      
      // Displaying the window with its contents
      stage.show();  
   }
   
   /**
    * The main method launches the GUI with launch(args) for JavaFX applications
    * @param args
    */
   public static void main(String args[]){ 
      launch(args); 
   } 
}