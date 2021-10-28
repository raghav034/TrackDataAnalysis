import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;  
import javafx.scene.Group;  
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;  
import javafx.scene.chart.ScatterChart;  
import javafx.scene.chart.XYChart;  
import javafx.stage.Stage;  
import javafx.scene.shape.Line;
 
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
public class scatterPlot extends Application{  
	 	double[] timings = new double[28];
		int[] year = new int[28];
		String[] medal = new String[28];
    @Override  
    //X AXIS = TIME || Y AXIS = YEAR
    public void start(Stage primaryStage) throws Exception {  
        // TODO Auto-generated method stub  
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter event");
    	String str = sc.nextLine();
    	SimpleRegression getReg = new SimpleRegression();
    	PearsonsCorrelation cor = new PearsonsCorrelation();
    	String path = "D:\\Raghav\\CS project\\Data sets\\results.csv";
		String line = "";
		int d = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null)
			{
				String values[] = line.split(",");
				if(values[1].equals(str)&& values[4].equals("G") )
					if(!values[7].equals("None"))
					{
						try {
							if(values[7].indexOf(':')!=-1)
							{
								timings[d] = (Double.parseDouble(values[7].substring(0,2))+(Double.parseDouble(values[7].substring(3,5))/60));
								//System.out.println(Double.parseDouble(values[7].substring(0,2))+(Double.parseDouble(values[7].substring(3,5))/60));
							}
							else	
								timings[d] = (Double.parseDouble(values[7]));
							}catch(Exception e) {
								continue;
							}
						year[d] = Integer.parseInt(values[3]);
						medal[d] = values[4];
						//System.out.println(values[3]);
						//test.put(Double.parseDouble(values[7]), values[3]);
						d++;
					}
					//oneHundredM.add(values[7]);

				//System.out.println("Event:" + values[1] + " Performance:" + values[7]);
			}
			System.out.println(d);
			
		        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
        //Configuring Xaxis and Yaxis  
		int n = year.length;
		double minTime = timings[0], maxTime = timings[0];
		 for (int i = 0; i < n-1; i++)//this part of the code matches year with timings
	        {
			 	if(timings[i]>maxTime )
			 		maxTime = timings[i];
			 	if(timings[i]<minTime && timings[i] !=0)
			 		minTime = timings[i];
	            // Find the minimum element in unsorted array
	            int min_idx = i;
	            for (int j = i+1; j < n; j++)
	                if (year[j] > year[min_idx])
	                    min_idx = j;
	 
	            // Swap the found minimum element with the first
	            // element
	            int temp = year[min_idx];
	            year[min_idx] = year[i];
	            year[i] = temp;
	            
	            double temp2 = timings[min_idx];
	            timings[min_idx] = timings[i];
	            timings[i] = temp2;
	        }
        NumberAxis yaxis = new NumberAxis(Math.floor(minTime),Math.ceil(maxTime)+0.3,0.1);  
        System.out.println(minTime + "" + maxTime);
        NumberAxis xaxis = new NumberAxis(1892,2016,4);  
        xaxis.setLabel("Year");  
        yaxis.setLabel("Timing");  
          
        //Configuring ScatterChart    
        ScatterChart s = new ScatterChart(xaxis,yaxis);  
        s.setTitle(str);  
          
        //Configuring Series and adding data to the series    
        XYChart.Series series = new XYChart.Series();  
        series.setName("Timing for each corresponding year");  
        
        for(int i = 0; i < 28;i++)
        {
        if(year[i] == 0)
        	continue;
        series.getData().add(new XYChart.Data(year[i],timings[i]));  
        getReg.addData(year[i],timings[i]);
        }
        //getReg.regress();
        //System.out.println(getReg.predict(2008));
        //Adding series to the ScatterChart  
        s.getData().add(series);  
       
        System.out.println("Slope of regression line: " + getReg.getSlope() +" Y-intercept of regression line: " + getReg.getIntercept());  
        //Configuring group and Scene   
        Group root = new Group();  
        root.getChildren().add(s);  
        Scene scene = new Scene(root,600,400);  
        primaryStage.setScene(scene);  
        primaryStage.setTitle("ScatterPlot for" + str);  
        primaryStage.show();      
          
         //DIFFERENCE FROM REGRESSION
        
        for(int i = 0;i<27;i++)
        {
        	if(year[i] == 0)
            	continue;
        	System.out.println("Year = " + year[i] + " Timing = " + timings[i]+ " Regression Prediction = " + getReg.predict(year[i]));
        }
        
        for(int i = 0;i<27;i++)
        {
        	if(year[i] == 0)
            	continue;
        	System.out.println("Year = " + year[i] + " Difference =" + (getReg.predict(year[i])-timings[i]));
        }
    }  
    
    public static void main(String[] args) {  
        launch(args);  
    }  
  
}  