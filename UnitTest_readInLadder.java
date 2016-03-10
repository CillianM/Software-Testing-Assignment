import java.util.Scanner;
public class UnitTest_readInLadder
{
	//This is the driver for the unit test
	public static void main()
	{
		Scanner scan = new Scanner(System.in);
		//Test case (input size of the array)
		int n = scan.nextInt();
		int myArray[n];

		readInLadder(scan, myArray);

	}

	//This is the unit under test
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
	
}