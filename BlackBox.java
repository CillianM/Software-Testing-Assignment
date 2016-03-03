import java.util.Scanner;

public class BlackBox
{
	public static void main(String [] args)
	{
		Scanner scan = new Scanner(System.in);
		int difference = 0;
		
		
		System.out.println("How many steps: ");
		int amountOfSteps = scan.nextInt();
		int [] steps = new int [amountOfSteps];
		
		System.out.println("Enter the locations: ");
		
		for(int i = 0; i < amountOfSteps; i++)
		{
			if(i == 0)
				steps[0] = scan.nextInt();
			else
			{
				int tmp = scan.nextInt();
				if(tmp > steps[i-1])
				{
					steps[i] = tmp;
					if((steps[i] - steps[i-1]) > difference)
					{
						difference = steps[i] - steps[i-1];
					}
				}
				
				else
				{
					System.out.println("Step can't be less than previous number!");
					i--;
				}
			}
			
		}
		
		System.out.println("The largest gap is " + difference);
	
	}
	
}