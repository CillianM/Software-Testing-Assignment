import java.util.Scanner;

public class UnitTest_isLarger {
	//The main method is the driver for the unit under test
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a number:");
		int one = scan.nextInt();
		System.out.println("Enter a second number which is larger than the first:");
		int two = scan.nextInt();
		System.out.println("Enter a number representing a difference to be tested:");
		int difference = scan.nextInt();
		System.out.println("The larger difference is " + isLarger(one,two,difference));
	}
	//This function is the unit being tested
	static int isLarger(int a, int b, int originalDifference) {
		int newDifference = a-b;
		if(newDifference > originalDifference) {
			return newDifference;
		} else {
			return originalDifference;
		}
	}
}