import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class UnitTest_readInLadderPlus
{
	//This is the driver for the unit test
	public static void main(String [] args)
	{
		try
		{
			Scanner scan = new Scanner(new File("output.txt"));
			//Test case (input size of the array)
			String tmp = scan.next();
			int n = Integer.parseInt(tmp);
			int[] myArray = new int[n];
			
			//Call to method under test 
			int diff = readInLadder(scan, myArray);

			
			System.out.println("Array (Ladder) now has: ");
			
			//Show array created
			{
				for(int i = 0; i < myArray.length; i++)
				{
					System.out.println(myArray[i]);
				}
			}
			
			System.out.println("The largest difference is (stub value for islarger(): " + diff);
		}
		catch (FileNotFoundException e)
		{

		}
		

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
				String tmp = scan.next();
				steps[0] = Integer.parseInt(tmp);
				while (steps[0] <= 0)
				{
					System.out.println("A number greater than 0 was entered");
					tmp = scan.next();
					steps[0] = Integer.parseInt(tmp);
				}
			}
			//All other elements need to be compared against the previous element
			else
			{
				String str_tmp = scan.next();
				int tmp = Integer.parseInt(str_tmp);

				//Is number less than 0?
				while (tmp <= 0)
				{
					System.out.println("A number greater than 0 was entered");
					str_tmp = scan.next();
					tmp = Integer.parseInt(str_tmp);
				}
				if(tmp > steps[i-1])
				{
					steps[i] = tmp;
					difference = stub_isLarger(steps[i],steps[i-1], difference);
				}
				
				else
				{
					System.out.println("Number entered was less than the previous number");
					//Set i back to allow a correct input
					i--;
				}
			}
			
		}
		
		//difference value should always return 10 if it goes throguh everything correctly
		return difference;
	}

	//Stub to return a dummy difference value
	static int stub_isLarger(int a, int b, int originalDifference)
	{
		return 1;
	}
	
}
