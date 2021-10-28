import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MatchData extends event{
	ArrayList<String> athNameWinner = new ArrayList<String>();
	ArrayList<Double> heightWinner = new ArrayList<Double>();
	ArrayList<Double> weightWinner = new ArrayList<Double>();
	ArrayList<String> yearWinner= new ArrayList<String>();
	MatchData(String a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	void matchName()
	{
		ArrayList<String> athName = new ArrayList<String>();
		ArrayList<Double> height = new ArrayList<Double>();
		ArrayList<Double> weight = new ArrayList<Double>();
		String path = "D:\\Raghav\\CS project\\Data sets\\AthleteDataOnlyWinnersFinal.csv";//CHANGE THIS PATH TO THE DOWNLOAD LOCATION OF THE APPROPRIATE CSV FILE
		String line = "";
		int d = 0;
	
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null)
			{
				String values[] = line.split(",");
				athName.add(values[1]);
				if(Character.isDigit(values[4].charAt(0)))
					height.add(Double.parseDouble(values[4]));
				else
					height.add(0.0);
				if(Character.isDigit(values[5].charAt(0)))
					weight.add(Double.parseDouble(values[5]));
				else
					weight.add(0.0);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		ArrayList<String> sort = new ArrayList<String>();
		//getAthleteName();
		int c =0;
		for(int i = 0 ; i<name.size();i++)
		{	
			if(!medal.get(i).equals("G"))
				continue;
			for(int j = 0 ; j<athName.size();j++)
			{
				String name1,name2;
				String checkQuotes = athName.get(j).substring(1,athName.get(j).length()-1);
				if(name.get(i).indexOf(' ' ) == -1)
					name1 = name.get(i);
				else
					name1 = name.get(i).substring(0, name.get(i).indexOf(' ')) + name.get(i).substring(name.get(i).lastIndexOf(' ' ),name.get(i).length() );
				if(athName.get(j).indexOf(' ' ) == -1)
					name2 = athName.get(j);
				else
					name2 = athName.get(j).substring(1, athName.get(j).indexOf(' ')) + athName.get(j).substring(athName.get(j).lastIndexOf(' ' ),athName.get(j).length()-1 );//first letter is "
				//System.out.println(name1 + "  " + name2);
				
				if(checkQuotes.indexOf('\"') !=-1)
				{
					name2 = checkQuotes.substring(checkQuotes.indexOf('\"')+2, checkQuotes.lastIndexOf('\"')-1) + checkQuotes.substring(checkQuotes.lastIndexOf(' '));
				}
				if(name1.equalsIgnoreCase(name2))
				{
					athNameWinner.add(name.get(i));
					heightWinner.add(height.get(j));
					weightWinner.add(weight.get(j));
					yearWinner.add(String.valueOf(year.get(i)));
					//sort.add("Year: " + year.get(i)+ " Name: "  + name.get(i)+ " \nWeight: "+ weight.get(j)  + " Height : " + height.get(j) );
					sort.add(year.get(i) +"	"+ name.get(i) + "	" + weight.get(j) + "	" + height.get(j)  );
					c++;
					//System.out.println("Name: "  + name.get(i)+ " Weight: "+ weight.get(j)  + " Height : " + height.get(j) + "Year : " + year.get(i));
					break;
				}
			}
				//System.out.println(name.get(i));
		}
		Collections.sort(sort);
		System.out.println("Year" + "	Name" + "		Weight" + "	Height");
		for(int i =0;i<sort.size();i++)
			System.out.println(sort.get(i));
	}
	
	private Object indexOf(char c) {
		// TODO Auto-generated method stub
		return null;
	}


	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter event: ");
		String b = sc.nextLine();
	    MatchData obj = new MatchData(b);
	    obj.matchName();
	}
	
}
