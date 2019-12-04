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
	   
	   
      //Label for location 
      Text cityOfInterestLabel = new Text("Location"); 
      
      
      //Text field for location
      TextField cityOfInterestText = new TextField(); 
      //String cityOfInterestTextExtracted = cityOfInterestText.getText() ;
      
      
      //Label for estimated salary 
      Text estimatedSalaryLabel = new Text("Estimated Salary"); 
      
      //Text field for company
      TextField estimatedSalaryText = new TextField();
      
      //Label for type of job (internship vs. full time)
      Text typeLabel = new Text("Type"); 
      
      //Toggle group of radio buttons for type of job      
      ToggleGroup internshipFullTime = new ToggleGroup(); 
      RadioButton internshipRadio = new RadioButton("Internship"); 
      internshipRadio.setToggleGroup(internshipFullTime); 
      RadioButton fullTimeRadio = new RadioButton("fullTime"); 
      fullTimeRadio.setToggleGroup(internshipFullTime); 
       
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
         ("All", "BS", "MS", "PhD"); 
      
    //Choice box for education required
      ChoiceBox distanceChoiceBox = new ChoiceBox(); 
      distanceChoiceBox.getItems().addAll
         ("All", "<10 miles", "<50 miles", "<100 miles"); 
       
      //Search Button
      Button buttonRegister = new Button("Search");  
      //root.getChildren().add(buttonRegister) ;
      buttonRegister.setOnAction(e -> {
    	  System.out.println("Job Title: " + jobTitleText.getText());
    	  System.out.println("Location: " + cityOfInterestText.getText());
    	  System.out.println("Estimated Salary: " + estimatedSalaryText.getText());
    	  System.out.println("Type: " + internshipFullTime.getSelectedToggle());
    	  System.out.println("Experience: " + educationChoiceBox.getValue());
    	  System.out.println("Distance: " + distanceChoiceBox.getValue());
    	  //System.out.println("Works again");
    	  //String cityOfInterestTextExtracted = cityOfInterestText.getText();
    	  //JavaFXPassingTest.printArgument(cityOfInterestTextExtracted) ;
    	  
      
      
      
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
      
      gridPane.add(cityOfInterestLabel, 0, 1); 
      gridPane.add(cityOfInterestText, 1, 1); 
       
      gridPane.add(estimatedSalaryLabel, 0, 2);
      gridPane.add(estimatedSalaryText, 1, 2);;
      
      gridPane.add(typeLabel, 0, 3); 
      gridPane.add(internshipRadio, 1, 3);       
      gridPane.add(fullTimeRadio, 2, 3); 
      
      //gridPane.add(salaryLabel, 0, 5); 
      //gridPane.add(salaryOptionsListView, 1, 5);      
       
      gridPane.add(educationLabel, 0, 4); 
      gridPane.add(educationChoiceBox, 1, 4);    
      
      gridPane.add(distanceLabel, 0, 5); 
      gridPane.add(distanceChoiceBox, 1, 5);
       
      gridPane.add(buttonRegister, 2, 6);      
      
      //Styling nodes   
      buttonRegister.setStyle(
         "-fx-background-color: Green; -fx-textfill: white;"); 
      
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
   
   public static void main(String args[]){ 
      launch(args); 
   } 
}