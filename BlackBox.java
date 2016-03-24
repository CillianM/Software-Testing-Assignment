import java.util.Scanner;

public class BlackBox
{
	public static void main(String [] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("How many steps: ");
		int amountOfSteps = getAmountOfSteps(scan);
		int [] steps = new int [amountOfSteps];
		System.out.println("Enter the locations: ");
		System.out.println("The largest gap is " + readInLadder(scan,steps));
	}
	
	static int readInLadder(Scanner scan, int [] steps)
	{
		int difference = 0;
		
		for(int i = 0; i < steps.length; i++)
		{
			//Ensure first element is not compared against anything before it
			if(i == 0)
			{
				steps[0] = scan.nextInt();
				while (steps[0] <= 0)
				{
					System.out.println("Enter a number greater than 0");
					steps[0] = scan.nextInt();
				}
			}
			//All other elements need to be compared against the previous element
			else
			{
				int tmp = scan.nextInt();

				//Is number less than 0?
				while (tmp <= 0)
				{
					System.out.println("Enter a number greater than 0");
					tmp = scan.nextInt();
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
				}
			}
			
		}
		
		return difference;
	}
	
	static int isLarger(int a, int b, int originalDifference)
	{
		int newDifference = a-b;
		if(newDifference > originalDifference)
		{
			return newDifference;
		}
		
		else
		{
			return originalDifference;
		}
	}
	
	static int getAmountOfSteps(Scanner scan)
	{
		int tmp = scan.nextInt();
		while (tmp <= 0)  // just return int? Remove loop
		{
			System.out.println("Enter a number greater than 0");
			tmp = scan.nextInt();
		}
		
		return tmp;
	}
	
}
