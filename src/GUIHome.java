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
import javafx.scene.control.DatePicker; 
import javafx.scene.control.ListView; 
import javafx.scene.control.RadioButton; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;  
import javafx.scene.control.ToggleButton; 
import javafx.stage.Stage; 

/**
 * @author Kevin Tyler
 * This class sets up the home page GUI for the user to specify what data they are interested in. For now, it is simply a 
 * typical search field one might find on any job searching page, though once we have some data analysis we will include
 * options to search for that type of data as well.
 */
public class GUIHome extends Application { 
   @Override 
   public void start(Stage stage) {    
	   
	   //Label for Job Title
	   Text jobTitleLabel = new Text("Job Title");
	   
	   //Text field for Job Title
	   TextField jobTitleText = new TextField();
	   
	   
      //Label for City
      Text cityOfInterestLabel = new Text("City"); 
      
      //Label for State
      Text stateOfInterestLabel = new Text("State");
      
      //Text field for city
      TextField cityOfInterestText = new TextField(); 
      //String cityOfInterestTextExtracted = cityOfInterestText.getText() ;
      
      //Text field for state
      TextField stateOfInterestText = new TextField();
      
      //Label for estimated salary 
      Text estimatedSalaryLabel = new Text("Estimated Salary"); 
      
      //Text field for company
      TextField estimatedSalaryText = new TextField();
      
      //Label for type of job (internship vs. full time)
      Text typeLabel = new Text("Type"); 
      
      //Toggle group of radio buttons for type of job      
      ToggleGroup internshipFullTime = new ToggleGroup(); 
      RadioButton internshipRadio = new RadioButton("internship"); 
      internshipRadio.setToggleGroup(internshipFullTime); 
      RadioButton fullTimeRadio = new RadioButton("full-time"); 
      fullTimeRadio.setToggleGroup(internshipFullTime); 
      RadioButton contractRadio = new RadioButton("contract"); 
      contractRadio.setToggleGroup(internshipFullTime); 
      RadioButton partTimeRadio = new RadioButton("part-time"); 
      partTimeRadio.setToggleGroup(internshipFullTime); 
      RadioButton temporaryRadio = new RadioButton("temporary"); 
      temporaryRadio.setToggleGroup(internshipFullTime); 
      RadioButton commissionRadio = new RadioButton("commission"); 
      commissionRadio.setToggleGroup(internshipFullTime); 
       
      //Label for salary
      Text salaryLabel = new Text("Salary Range"); 
      
      final int ROW_HEIGHT = 24;
      //list View for salary options
      //ObservableList<String> salaryOptions = FXCollections.observableArrayList( 
         //"<50k", "50-60k", "60-70k", "70-80k", "80-90k", "90-100k", "100-110k", "110-120k", "120-130k", "130-140k", "140-150k", "150k+"); 
     // ListView<String> salaryOptionsListView = new ListView<String>(salaryOptions); 
      //salaryOptionsListView.setPrefHeight(salaryOptions.size() * ROW_HEIGHT + 2);
      
      //Label for education required
      Text educationLabel = new Text("Experience"); 
      
    //Label for Job Title
	   Text distanceLabel = new Text("Distance");
      
      //Choice box for education required
      ChoiceBox educationChoiceBox = new ChoiceBox(); 
      educationChoiceBox.getItems().addAll
         ("all", "entry level", "mid level", "senior level"); 
      
    //Choice box for education required
      ChoiceBox distanceChoiceBox = new ChoiceBox(); 
      distanceChoiceBox.getItems().addAll
         ("All", "<10 miles", "<50 miles", "<100 miles"); 
       
      //Search Button
      Button buttonRegister = new Button("Search");  
      //root.getChildren().add(buttonRegister) ;
      buttonRegister.setOnAction(e -> {
    	  
    	  String userJobTitle = TextfieldCheck(jobTitleText.getText());
    	  String state = TextfieldCheck(stateOfInterestText.getText());
    	  String city = TextfieldCheck(cityOfInterestText.getText());
    	  String estimatedSalary = TextfieldCheck(estimatedSalaryText.getText());
    	  RadioButton selectedRadioButton = (RadioButton) internshipFullTime.getSelectedToggle();
    	  String userInputToggleJobType = selectedRadioButton.getText();
    	  String experienceChoice = (String) educationChoiceBox.getValue();
    	  String distanceChoice = (String) distanceChoiceBox.getValue();
    	  String distanceChoiceNumber = distanceConverter(distanceChoice);
    	  
    	  
    	  System.out.println("Job Title: " + userJobTitle);
    	  System.out.println("State: " + state);
    	  System.out.println("City: " + city);
    	  System.out.println("Estimated Salary: " + estimatedSalary);
    	  System.out.println("Type: " + userInputToggleJobType);
    	  System.out.println("Experience: " + experienceChoice);
    	  System.out.println("Distance: " + distanceChoiceNumber);
    	  
    	  URLGenerator g = new URLGenerator(userJobTitle, estimatedSalary, city, state, distanceChoiceNumber, userInputToggleJobType, experienceChoice, "None");
    	  System.out.println(g.getURL());

    	  
      
      
      
      });
      
      //buttonRegister.setOnAction(this) ;
      
      //Creating a Grid Pane 
      GridPane gridPane = new GridPane();    
      
      //Setting size for the pane 
      gridPane.setMinSize(500, 500); 
       
      //Setting the padding    
      gridPane.setPadding(new Insets(10, 10, 10, 10));  
      
      //Setting the vertical and horizontal gaps between the columns 
      gridPane.setVgap(5); 
      gridPane.setHgap(5);       
      
      //Setting the Grid alignment 
      gridPane.setAlignment(Pos.CENTER); 
       
      //Arranging all the nodes in the grid 
      
      gridPane.add(jobTitleLabel,  0,  0);
      gridPane.add(jobTitleText,  1,  0);;
      
      gridPane.add(stateOfInterestLabel, 0, 1); 
      gridPane.add(stateOfInterestText, 1, 1); 
      
      gridPane.add(cityOfInterestLabel, 0, 2); 
      gridPane.add(cityOfInterestText, 1, 2); 
       
      gridPane.add(estimatedSalaryLabel, 0, 3);
      gridPane.add(estimatedSalaryText, 1, 3);;
      
      gridPane.add(typeLabel, 0, 4); 
      gridPane.add(internshipRadio, 1, 4);       
      gridPane.add(fullTimeRadio, 2, 4); 
      gridPane.add(contractRadio,  3,  4);
      gridPane.add(partTimeRadio,  1,  5);
      gridPane.add(temporaryRadio,  2,  5);
      gridPane.add(commissionRadio,  3,  5);
      
      //gridPane.add(salaryLabel, 0, 5); 
      //gridPane.add(salaryOptionsListView, 1, 5);      
       
      gridPane.add(educationLabel, 0, 6); 
      gridPane.add(educationChoiceBox, 1, 6);    
      
      gridPane.add(distanceLabel, 0, 7); 
      gridPane.add(distanceChoiceBox, 1, 7);
       
      gridPane.add(buttonRegister, 2, 8);      
      
      //Styling nodes   
      buttonRegister.setStyle(
         "-fx-background-color: Green; -fx-textfill: white;"); 
      
      stateOfInterestLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      cityOfInterestLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      estimatedSalaryLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      jobTitleLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      typeLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
      salaryLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      educationLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      distanceLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
       
      //Setting the back ground color 
      gridPane.setStyle("-fx-background-color: LIGHTBLUE;");       
       
      //Creating a scene object 
      Scene scene = new Scene(gridPane); 
      
      //Setting title to the Stage 
      stage.setTitle("CS Job Analyzer"); 
         
      //Adding scene to the stage 
      stage.setScene(scene);  
      
      //Displaying the contents of the stage 
      stage.show(); 
      
      //Testing retrieving data from user input
      
     
   }
   
   //@Override
   //public void handle(ActionEvent event) {
	   //System.out.println("Hello world.");
	   
	   //String cityOfInterestTextExtracted = cityOfInterestText.getText() ;
	   
	   //System.out.println(cityOfInterestTextExtracted);
   //}
   
   String TextfieldCheck(String stringToCheck) {
	   
	   if(stringToCheck.equals("")) {
		   return "none" ;
	   }
	   
	   return stringToCheck;
   }
   
   
   String distanceConverter(String distance) {
	   
	   String distanceNumber = "None" ;
	  
	   if (distance.contains("10")) {
		   distanceNumber = "10";
	   } else if (distance.contains("50")) {
		   distanceNumber = "50";
	   } else if (distance.contains("100")) {
		   distanceNumber = "100";
	   }
	   
	   return distanceNumber;
   }
   
   public static void main(String args[]){ 
      launch(args); 
   } 
}