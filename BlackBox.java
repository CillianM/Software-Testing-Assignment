import java.util.Scanner;

public class BlackBox
{
	public static void main(String [] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("How many steps: ");
		int amountOfSteps = getSteps(scan);
		int [] steps = new int [amountOfSteps];
		System.out.println("Enter the locations: ");
		System.out.println("The largest gap is " + readArray(scan,steps));
	}
	
	static int readArray(Scanner scan, int [] steps)
	{
		int difference = 0;
		
		for(int i = 0; i < steps.length; i++)
		{
			if(i == 0)
			{
				steps[0] = scan.nextInt();
				while (steps[0] < 0)
				{
					System.out.println("Enter a number greater than 0");
					steps[0] = scan.nextInt();
				}
			}
			else
			{
				int tmp = scan.nextInt();
				while (tmp < 0)
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
	
	static int getSteps(Scanner scan)
	{
		int tmp = scan.nextInt();
		while (tmp < 0)
		{
			System.out.println("Enter a number greater than 0");
			tmp = scan.nextInt();
		}
		
		return tmp;
	}
	
}
