import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
//only used to generate a new file with ONLY athletics athlete names
public class athleteData {

	void generateOnlyAth()
	{
		String path = "D:\\Raghav\\CS project\\Data sets\\athlete_events.csv";
		String line = "";
		FileWriter fw = null;
		try
		{
			fw = new FileWriter("D:\\Raghav\\CS project\\Data sets\\AthleteDataOnlyWinnersFinal.csv");
			fw.append("ID,Name,Sex,Age,Height,Weight,Team,NOC,Games,Year,Season,City,Sport,Event,Medal");
			fw.append("\n");
			
			
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null)
			{
				
				String values[] = line.split(",");
				String eventName = values[13];			
				//System.out.println(values[14]);
				if(eventName.startsWith("\"Ath") && (values[14].startsWith("\"Bro") || values[14].startsWith("\"Gol")  || values[14].startsWith("\"Sil") ))
				{
					//System.out.print("Hello?");
					fw.append(line);
					fw.append("\n");
				} 
			}
		}
		 catch (IOException e) {
		     e.printStackTrace();
				}
		finally {
        try {
        	fw.flush();
        	fw.close();
        } catch (IOException e) {
      e.printStackTrace();
}
	
}
		
}
	public static void main(String[] args)
	{
		athleteData obj = new athleteData();
		obj.generateOnlyAth();
	}
	
}
