import java.util.Scanner;

public class IntegrationTest_Functions_And_LadderClass 
{
	//The Driver for the integration test.  I will be calling to the Ladder class in this, and make sure that
	//the ladder object is created correctly.  Then i will run the methods of the class
	public static void main(String [] args) 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many steps: ");
		int amountOfSteps = scanner.nextInt(); // just get int over method for simplified flow control
		int [] inputArray = new int [amountOfSteps];
		Ladder ladder = new Ladder(inputArray);

		//Check to see if the array in the ladder object was created correctly compare input array length to 
		//ladder object array length
		System.out.println("The size of the input array is:         " + inputArray.length);
		System.out.println("The size of the ladder object array is: " + ladder.steps.length);
		System.out.println("Enter the locations: ");
		System.out.println("The largest gap is " + ladder.readInLadder());
	}	
}
