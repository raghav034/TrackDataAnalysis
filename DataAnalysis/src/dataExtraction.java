import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
//csv format = M,High Jump Men,St Louis,1904,S,Garrett SERVISS,USA,1.77
//           sex,event,City,Year,Medal, Name,Country,Time/distance
import java.util.Scanner;

//we cant use HashMaps because some years have duplicate key values (timings)



public class dataExtraction {
	
	String s;
	double[] timings = new double[80];
	String[] year = new String[80];
	String[] medal = new String[80];
	double[] avg = new double[28];
	
	dataExtraction(String a){
		s = a;
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter event");
		String b = sc.nextLine();
	    dataExtraction obj = new dataExtraction(b);
	    
		obj.getName();
	}
	
	public void getName()
	{
		String path = "D:\\Raghav\\CS project\\Data sets\\results.csv";
		String line = "";
		int d = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null)
			{
				String values[] = line.split(",");
				System.out.println(values[5]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void getData()
	{
		String path = "D:\\Raghav\\CS project\\Data sets\\results.csv";
		String line = "";
		int d = 0;
		//HashMap<Double,String> test = new HashMap<Double,String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null)
			{
				String values[] = line.split(",");
				if(values[1].equals(s))
					if(!values[7].equals("None"))
					{
						timings[d] = Double.parseDouble(values[7]);
						year[d] = values[3];
						medal[d] = values[4];
						//System.out.println(values[3]);
						//test.put(Double.parseDouble(values[7]), values[3]);
						d++;
					}
					//oneHundredM.add(values[7]);

				//System.out.println("Event:" + values[1] + " Performance:" + values[7]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		avg[27] = (timings[79]+timings[78] + timings[77] + timings [76])/4 ;//Since this year has 4 recorded timings
		int c = 0;
		double sum;
		/*for(int i = 0;i<29;i++)
		{
			sum = 0;
			sum+= timings[c++] + timings[c++] + timings[c++]; //sum of all 3 timings G S B
			System.out.println(sum);
			avg[i] = sum/3;
		}*/
		c=0;
		for(int i=0;i<80;i++)
		{
			System.out.print("Timing = " + timings[i] + ",  Year = " + year[i] + ",  Medal = " + medal[i]);
			if(i%3 == 1)
				System.out.println(avg[c++]);
			else
				System.out.println();
		}
		System.out.println(d);
		//ArrayList<Double> sort = new ArrayList<Double>(test.keySet());
		//Collections.sort(sort);
		/*int c=0;
		for (Double x : sort)
		{
            System.out.println("Key = " + x
                               + ", Value = " + test.get(x));
            c++;
		}*/
		for (int i = 0; i < 79; i++)
        {
			
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < 80; j++)
                if (timings[j] < timings[min_idx])
                    min_idx = j;
 
            // Swap the found minimum element with the first
            // element
            Double temp = timings[min_idx];
            timings[min_idx] = timings[i];
            timings[i] = temp;
            
            //create a SWAP function to swap strings
            
            String temp1 = year[min_idx];
            year[min_idx] = year[i];
            year[i] = temp1;
            
            temp1 = medal[min_idx];
            medal[min_idx] = medal[i];
            medal[i] = temp1;
            
        }
		
		for(int i=0;i<80;i++)
		{
			System.out.println("Timing = " + timings[i] + ",  Year = " + year[i] + ",  Medal = " + medal[i]);
			
		}
	}
}
