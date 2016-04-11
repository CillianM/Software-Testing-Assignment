import java.io.*;
import java.util.*;

public class RandomNumberGenerator {
	public static void main(String [] args) {
		//Variables
		boolean increasing = false;
		int min;
		int max;
		int size;
		//Code
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want numbers to be in ascending order? y/n");
		if(scan.nextLine().equals("y")){
			increasing = true;
		}
		System.out.println("Input min and max values, with max > 0 (min max)");
		min = scan.nextInt();
		max = scan.nextInt();
		System.out.println("Input the size of the array:");
		size = scan.nextInt();
		
		
		generateList(increasing, size, min, max);
	}
	
	static int generateRandomInt(int min, int max){
		return min + (int)(Math.random() * ((max - min) + 1));
	}

	static void swap(ArrayList<Integer> arrayList, int a, int b) {
		int tmp = arrayList.get(a);
		arrayList.set(a, arrayList.get(b));
		arrayList.set(b, tmp);
	}
	
	static void addAdditionalElements(ArrayList<Integer> arrayList , int inputtedSize)  
	{
		int countOfValidElements = 0;
		int highestValidElementSoFar = 0;
		
		//count how many elements valid elements we have

		for(int i = 0; i < arrayList.size(); i++)
		{
			if(arrayList.get(i) > highestValidElementSoFar)
			{
				
				highestValidElementSoFar = arrayList.get(i);
				countOfValidElements++;
			}
			
		}

		
		//calculate how many elements need to be added
		int additionalSize = inputtedSize - countOfValidElements;


		if(highestValidElementSoFar <= 0) //make sure that the highestValidElementSoFar is indeed a valid element (incase no values in the list were valid)
		{
			highestValidElementSoFar = 1;
		}

		//start adding additional elements.  Note we may go over the max value here, but the max value is an arbitrary maximum
		for(int j = 0; j < additionalSize; j++){
			highestValidElementSoFar++;	//add a new highest element to the end of the list
			arrayList.add(highestValidElementSoFar);
		}


	}

	static void generateList(boolean increasing, int size, int min, int max) {
		ArrayList<Integer> num = new ArrayList<>();
		int randomNum;
		
		for(int i = 0; i < size; i++) {
			randomNum = generateRandomInt(min, max);
			num.add(randomNum);
		}
		
		if(increasing) {
			for(int i = 1; i < size-1; i++){
				for(int j = 0; j < size-i; j++){
					if(num.get(j) > num.get(j+1)){
						swap(num, j, j+1);
					}
				}
			}
		}

		
		addAdditionalElements(num, size);  
		
		
		try {
			File output = new File("output.txt");
			PrintWriter printer = new PrintWriter(output);
			printer.write(size + "\n");  //The first number must be the size of the array
			for(int i = 0; i < num.size(); i++) {
				printer.write("" + num.get(i)+ "\n");
			}
			printer.flush();
			printer.close();
		}
		catch(FileNotFoundException e) {
			System.err.println("File not found. Please scan in new file.");
		}
	}
}