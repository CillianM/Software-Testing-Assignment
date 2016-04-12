import java.util.Scanner;

public class LadderProgramme //renamed from group name as it was confusing
{
	public static void main(String [] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many steps: ");
		int amountOfSteps = scanner.nextInt(); // just get int over method for simplified flow control
		int [] steps = new int [amountOfSteps];
		Ladder ladder = new Ladder(steps);
		System.out.println("Enter the locations: ");
		System.out.println("The largest gap is " + ladder.readInLadder());
	}	
}
