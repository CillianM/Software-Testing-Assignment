import java.util.Scanner;
public class IntegrationTest_isLarger_And_readInLadder
{
	//This is the driver for the integration test
	public static void main(String [] args)
	{
		Scanner scan = new Scanner(System.in);
		//Test case (input size of the array)
		System.out.println("Enter the size of the array: ");
		int n = scan.nextInt();
		int[] myArray = new int[n];
		
		//Call to one of the methods under test 
		System.out.println("Enter the elements in the array: ");
		int diff = readInLadder(scan, myArray);

		
		System.out.println("Array (Ladder) now has: ");
		
		//Show array created
		{
			for(int i = 0; i < myArray.length; i++)
			{
				System.out.println(myArray[i]);
			}
		}
		
		System.out.println("The largest difference is : " + diff);

	}

	//This is one of the methods under test
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
		
		//difference value should always return 10 if it goes throguh everything correctly
		return difference;
	}

	//This is the second method under test
	private static int isLarger(int a, int b, int originalDifference)
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
	
}
