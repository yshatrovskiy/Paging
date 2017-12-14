import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Page {
	
	static int[] inputValues;
	static int[] checkValues;
	static int memorySize, numberOfPrograms;
	static String inputFile = "paging1-input.txt";
	
	public static void main(String args[]) throws FileNotFoundException {
		
		ArrayList<Integer> originalOrder = new ArrayList<Integer>();  
		
		BufferedReader brTest;
		try {
			brTest = new BufferedReader(new FileReader(inputFile));
			
			memorySize = Integer.parseInt(brTest.readLine());
			numberOfPrograms = Integer.parseInt(brTest.readLine());
			inputValues = new int[numberOfPrograms];
			checkValues = new int[numberOfPrograms];
			
			for(int i = 0; i < numberOfPrograms; i++) {
				String values = brTest.readLine();
				inputValues[Integer.parseInt(values.substring(0, 1))] = Integer.parseInt(values.substring(2));		
			}
			brTest.readLine();

			for(int j = 0; j < numberOfPrograms; j++) {
				String values = brTest.readLine();
				originalOrder.add(Integer.parseInt(values.substring(0, 1)));
				checkValues[Integer.parseInt(values.substring(0, 1))] = Integer.parseInt(values.substring(2));	
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		OutputStream output = new FileOutputStream("paging1-output.txt");
		PrintStream printStream = new PrintStream(output);
		
		printStream.println("Input file: " + inputFile);
		printStream.println("Page size is " + memorySize);
		printStream.println(inputValues.length + " processes created");
		printStream.println("Process memory allocation:");
		printStream.println("PROCESS #PAGES");
		for(int i = 0; i < inputValues.length; i ++) {
			printStream.println(i + " 	" + (int)Math.ceil(inputValues[i] / (float)memorySize ));
		}
		printStream.println("---------");
		printStream.println("Memory Accesses:");
		printStream.println("PROCESS P	D");
		
		for(int i = 0; i < inputValues.length; i ++) {
			if(checkValues[originalOrder.get(i)] > inputValues[originalOrder.get(i)]) {
				printStream.println(originalOrder.get(i) + "   	Illegal");
			}else {
				printStream.println(originalOrder.get(i) + " 	" + ((int)checkValues[originalOrder.get(i)]/memorySize) + " 	" + (int)checkValues[originalOrder.get(i)] % memorySize);
			}
		}
		
	}

}
