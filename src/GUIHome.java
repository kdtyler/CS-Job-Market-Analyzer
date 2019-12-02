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
      //Label for city of interest 
      Text cityOfInterestLabel = new Text("City of Interest"); 
      
      
      //Text field for city of interest 
      TextField cityOfInterestText = new TextField(); 
      //String cityOfInterestTextExtracted = cityOfInterestText.getText() ;
      
      
      //Label for company 
      Text companyLabel = new Text("Company"); 
      
      //Text field for company
      TextField companyText = new TextField();
      
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
      ObservableList<String> salaryOptions = FXCollections.observableArrayList( 
         "<50k", "50-60k", "60-70k", "70-80k", "80-90k", "90-100k", "100-110k", "110-120k", "120-130k", "130-140k", "140-150k", "150k+"); 
      ListView<String> salaryOptionsListView = new ListView<String>(salaryOptions); 
      salaryOptionsListView.setPrefHeight(salaryOptions.size() * ROW_HEIGHT + 2);
      
      //Label for education required
      Text educationLabel = new Text("Education Required"); 
      
      //Choice box for education required
      ChoiceBox educationChoiceBox = new ChoiceBox(); 
      educationChoiceBox.getItems().addAll
         ("BS", "MS", "PhD"); 
       
      //Search Button
      Button buttonRegister = new Button("Search");  
      //root.getChildren().add(buttonRegister) ;
      buttonRegister.setOnAction(e -> {
      System.out.println("City is: " + cityOfInterestText.getText());
      System.out.println("Works again");
      String cityOfInterestTextExtracted = cityOfInterestText.getText();
      JavaFXPassingTest.printArgument(cityOfInterestTextExtracted) ;
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
      gridPane.add(cityOfInterestLabel, 0, 0); 
      gridPane.add(cityOfInterestText, 1, 0); 
       
      gridPane.add(companyLabel, 0, 1);
      gridPane.add(companyText, 1, 1);;
      
      gridPane.add(typeLabel, 0, 2); 
      gridPane.add(internshipRadio, 1, 2);       
      gridPane.add(fullTimeRadio, 2, 2); 
      
      gridPane.add(salaryLabel, 0, 5); 
      gridPane.add(salaryOptionsListView, 1, 5);      
       
      gridPane.add(educationLabel, 0, 6); 
      gridPane.add(educationChoiceBox, 1, 6);    
       
      gridPane.add(buttonRegister, 2, 8);      
      
      //Styling nodes   
      buttonRegister.setStyle(
         "-fx-background-color: Green; -fx-textfill: white;"); 
      
      cityOfInterestLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      companyLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      typeLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
      salaryLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
      educationLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
       
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