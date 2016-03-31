import java.io.*;
import java.util.*;

public class RandomNumberGenerator {
	public static void main(String [] args) {
		//Variables
		String answer;
		boolean increasing = false;
		int size;
		//Code
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want numbers to be in ascending order? y/n");
		answer = scan.nextLine();
		System.out.println("Input the size of the array:");
		size = scan.nextInt();
		
		if(answer.equals("y")) {
			increasing = true;
		} 
		
		generateNumber(increasing, size);
	}
	
	static void swap(int[] array, int a, int b) {
		int tmp = array[a];
		array[a]=array[b];
		array[b]=tmp;
	}
	
	static void generateNumber(boolean increasing, int size) {
		int [] num = new int[size];
		
		for(int i = 0; i < size; i++) {
			num[i] = (int)(Math.random() * 65535) + 1;
		}
		
		if(increasing) {
			for(int i = 1; i < size-1; i++){
				for(int j = 0; j < size-i; j++){
					if(num[j] > num[j+1]){
						swap(num, j, j+1);
					}
				}
			}
		}
		
		try {
			File output = new File("output.txt");
			PrintWriter printer = new PrintWriter(output);
			for(int i = 0; i < size; i++) {
				printer.write("" + num[i]+ "\n");
			}
			printer.flush();
			printer.close();
		}
		catch(FileNotFoundException e) {
			System.err.println("File not found. Please scan in new file.");
		}
	}
}