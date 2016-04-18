import java.util.Scanner;
public class DpathLadder
{
	int [] steps;
	Scanner scan = new Scanner(System.in);
	String ddPath  = "Start";

	public DpathLadder(int [] ladder)
	{
		this.steps = ladder;
	}

	public int readInLadder()
	{
		
		int difference = 0;
		
		
		for(int i = 0; i < steps.length; i++)
		{
			ddPath = ddPath + ", A";
			//Ensure first element is not compared against anything before it
			if(i == 0)
			{
				ddPath = ddPath + ", B";
				steps[0] = scan.nextInt();
				
				while (steps[0] <= 0)
				{
					ddPath = ddPath + ", C";
					System.out.println("Enter a number greater than 0");
					steps[0] = scan.nextInt();
					ddPath = ddPath + ", D";
				}
			}
			//All other elements need to be compared against the previous element
			else
			{
				int tmp = scan.nextInt();
				
				//Is number less than 0?
				while (tmp <= 0)
				{
					ddPath = ddPath + ", E";
					System.out.println("Enter a number greater than 0");
					tmp = scan.nextInt();
					ddPath = ddPath + ", F";
				}
				if(tmp > steps[i-1])
				{
					
					steps[i] = tmp;	
					difference = isLarger(steps[i],steps[i-1], difference);
				}
				else
				{
					System.out.println("Step can't be less than previous number!");
					//Set i back to allow a correct input
					i--;
					ddPath = ddPath + ", G";
				}
			}
			
		}
		System.out.println(ddPath + ", End");
		return difference;
	}

	private int isLarger(int a, int b, int originalDifference)
	{
		
		int newDifference = a-b;
		ddPath = ddPath + ", H";
		if(newDifference > originalDifference)
		{
			ddPath = ddPath + ", I";
			return newDifference;

		}
		else
		{
			ddPath = ddPath + ", J";
			return originalDifference;
		}
	}

}
