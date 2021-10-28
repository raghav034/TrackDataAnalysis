import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class percIncrease{
	
	 public static void main(String[] args)
	 {
		 double[] timings = new double[80];
			int[] year = new int[80];
			String[] medal = new String[80];
		
			String path = "D:\\Raghav\\CS project\\Data sets\\results.csv";
			String line = "";
			int d = 0;
			try {
				BufferedReader br = new BufferedReader(new FileReader(path));
				while((line = br.readLine())!= null)
				{
					String values[] = line.split(",");
					if(values[1].equals("100M Men")&& values[4].equals("G") )
						if(!values[7].equals("None"))
						{
							timings[d] = Double.parseDouble(values[7]);
							year[d] = Integer.parseInt(values[3]);
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
			for (int i = 0; i < 79; i++)//WE CAN REMOVE THE SORT FOR SCATTERPLOT
	        {
				
	            // Find the minimum element in unsorted array
	            int min_idx = i;
	            for (int j = i+1; j < 80; j++)
	                if (year[j] < year[min_idx])
	                    min_idx = j;
	 
	            // Swap the found minimum element with the first
	            // element
	            Double temp = timings[min_idx];
	            timings[min_idx] = timings[i];
	            timings[i] = temp;
	            
	            //create a SWAP function to swap strings
	            
	            int temp0 = year[min_idx];
	            year[min_idx] = year[i];
	            year[i] = temp0;
	            
	            String temp1 = medal[min_idx];
	            medal[min_idx] = medal[i];
	            medal[i] = temp1;
	            
	        }
			int x = 0;
			for(int i=1;i<80;i++)
			{
				if(year[i] < 1896)
					continue;
				else if(year[i] == 1896)
					System.out.println("Timing = " + timings[i] + ",  Year = " + year[i] + ",  Medal = " + medal[i] + ",  FIRST EVER OLYMPICS");
				else
				{
					double percChange = ((timings[i-1] - timings[i])/timings[i-1])*100;
					System.out.println("Timing = " + timings[i] + ",  Year = " + year[i] + ",  Medal = " + medal[i] + ",  Percentage Change from LAST YEAR = " + percChange);
				}
				
			}
	 }
	 
}
	
