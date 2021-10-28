import javafx.application.Application;
import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javafx.scene.Group;  
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;  
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;  
import javafx.stage.Stage;  
import javafx.scene.shape.Line;

public class barChart extends Application{
	public void start(Stage primaryStage) throws Exception {     
		//Defining the x axis               
		CategoryAxis xAxis = new CategoryAxis();    
		String years[] = new String[31];
		for(int i = 0 ; i < 31;i++)
			years[i] = (1896 + 4*i) + "";
		xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList
		   (years))); 
		xAxis.setLabel("Year");  

		//Defining the y axis 
		NumberAxis yAxis = new NumberAxis(); 
		yAxis.setLabel("Unit");
		
		Scanner sc = new Scanner(System.in);
		  System.out.print("Enter event: ");
		  String b = sc.nextLine();
		  MatchData obj = new MatchData(b);
		  obj.matchName();
		
		//Creating the Bar chart 
		StackedBarChart<String, Number> stackedBarChart = 
		new StackedBarChart<>(xAxis, yAxis);         
		stackedBarChart.setTitle("Event: " + b); 
		
		 
		  
		
		//Prepare XYChart.Series objects by setting data 
	      XYChart.Series<String, Number> series1 = new XYChart.Series<>();  
	      series1.setName("Weight"); 
	      for(int i =0; i < obj.weightWinner.size();i++)
	      series1.getData().add(new XYChart.Data(obj.yearWinner.get(i), obj.weightWinner.get(i))); 
	         
	      XYChart.Series<String, Number> series2 = new XYChart.Series<>(); 
	      series2.setName("Height"); 
	      for(int i =0; i < obj.heightWinner.size();i++)
		      series2.getData().add(new XYChart.Data(obj.yearWinner.get(i), obj.heightWinner.get(i))); 
	    
	      //Setting the data to bar chart
	      stackedBarChart.getData().addAll(series2, series1); 
	         
	      //Creating a Group object  
	      Group root = new Group(stackedBarChart); 
	         
	      //Creating a scene object 
	      Scene scene = new Scene(root, 600, 400);  
	      
	      primaryStage.setTitle("Height and Weight Comparision"); 
	      
	      primaryStage.setScene(scene);
	      primaryStage.getScene().getStylesheets().add("style.css"); 
	      primaryStage.show();
	      
	 
	    
	   } 
	   public static void main(String args[]){ 
	      launch(args); 
	   }  
}
