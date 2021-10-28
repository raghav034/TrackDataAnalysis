import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class event {
	String s;
	ArrayList<Double> timings = new ArrayList<Double>();
	ArrayList<Integer> year = new ArrayList<Integer>();
	ArrayList<String> medal = new ArrayList<String>();
	ArrayList<String> name = new ArrayList<String>();
	double[] avg = new double[28];
	
	String path, line;
	
	event(String a){
		s = a;
		path = "D:\\Raghav\\CS project\\Data sets\\results.csv";//CHANGE THIS PATH TO WHEREVER YOU HAVE DOWNLOADED THE CSV FILE
		line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null)
			{
				String values[] = line.split(",");
				if(values[1].equals(s))
					if(!values[7].equals("None"))
					{
						try {
						if(values[7].indexOf(':')!=-1)
						{
							timings.add(Double.parseDouble(values[7].substring(0,2))+(Double.parseDouble(values[7].substring(3,5))/60));
						}
						else	
							timings.add(Double.parseDouble(values[7]));
						}catch(Exception e) {
							continue;
						}
						year.add(Integer.parseInt(values[3]));
						medal.add(values[4]);
						name.add(values[5]);
					}
					
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
		void getAthleteName()
		{
			try {
				BufferedReader br = new BufferedReader(new FileReader(path));
				while((line = br.readLine())!= null)
				{
					String values[] = line.split(",");
					if(values[1].equals(s))
						if(!values[7].equals("None"))
							name.add(values[5]);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		
		}
		
		void getTimings()
		{
			
			for(int i=0; i<timings.size();i++)
				System.out.println(timings.get(i));
			System.out.println(year.toString());
		}
		
		void percIncrease()
		{
			
			Double[] timings2 = new Double[timings.size()];
			timings.toArray(timings2);
			Integer[] year2 = new Integer[year.size()];
			year.toArray(year2);
			String[] medal2 = new String[medal.size()];
			medal.toArray(medal2);
			int len = year2.length;
			for (int i = 0; i < len; i++)
	        {
				
	            // Find the minimum element in unsorted array
	            int min_idx = i;
	            for (int j = i+1; j < len; j++)
	                if (year2[j] < year2[min_idx])
	                    min_idx = j;
	 
	            // Swap the found minimum element with the first
	            // element
	            Double temp = timings2[min_idx];
	            timings2[min_idx] = timings2[i];
	            timings2[i] = temp;
	            
	            //create a SWAP function to swap strings
	            
	            int temp0 = year2[min_idx];
	            year2[min_idx] = year2[i];
	            year2[i] = temp0;
	            
	            String temp1 = medal2[min_idx];
	            medal2[min_idx] = medal2[i];
	            medal2[i] = temp1;
	            
	        }
			double lastTime = 0;
			System.out.println("Timing " + "	Year " + "   Percentage Change from LAST YEAR ");
			for(int i=0;i<len;i++) 
			{
				if(year2[i] < 1896 ||!medal2[i].equals("G"))
					continue;
				else if(i==0 || lastTime == 0)
				{
					
					System.out.println(timings2[i] + "	" + year2[i] + "	" + "First Olympics");
					lastTime = timings2[i];
				}
				else
				{
					double percChange = ((timings2[i] - lastTime)/lastTime)*100;
					System.out.println(timings2[i] + "	" + year2[i] + "	" + Math.floor(percChange* 100) / 100 + "%");
					lastTime = timings2[i];
				}
				
			}
		}//FOR SHOT PUT BETTET TIME = BETTER SO NEED TO CHANGE THAT
		
		public static void main(String[] args)
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter event: ");
			String b = sc.nextLine();
		    event obj = new event(b);
		    obj.percIncrease();
		}
}
