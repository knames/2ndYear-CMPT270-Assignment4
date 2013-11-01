package userInterfaces;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.InputMismatchException;


/**
 * An interface for input and output methods to read a String, read an int, 
 * output a message, and display a list of choices from which the user must
 * select the index of a choice.
 */
public class ConsoleIO implements InputOutputInterface
{
	/*reads input*/
	private Scanner scan = new Scanner(System.in);
	 
	/**
	 * Display a prompt, and read and return the int entered.
	 * @param prompt the string to be displayed as a prompt
	 * @return  the int read
	 */
	public String readString(String prompt)
	{
		outputString(prompt);
		return scan.nextLine();
	}
	
	/**
	 * Display a prompt, and read and return the int entered.
	 * @param prompt the string to be displayed as a prompt
	 * @return  the int read
	 */
	public int readInt(String prompt)
	{
		//bool to show if it was successful
		boolean successful;
		
		//int user input
		int input = 0;
	
		do
		{
			outputString(prompt);
			successful = true;
			try
			{
				input = scan.nextInt();
				scan.nextLine();
			}
			catch(InputMismatchException e)
			{
				successful = false;
				outputString("Please enter a valid integer: ");
				@SuppressWarnings("unused")
				String incorrect = scan.next(); 
			}
		}while(!successful);
		 
		return input;
	}
	 
		/**
		 * Display the list of options, and read and return an int that is the index of 
		 * one of the options.  The first option is the default.
		 * @param options  an array with the options that are presented to the user
		 * @return  the int specifying the array index for the option selected by the user
		 */
		public int readChoice(String[] options)
		{
	 		//bool to show if it was successful
	 		boolean successful = false;
	 		
	 		//int the number from the menu the user chooses
			int input;

	 		
	 		//int  gets the size of the menu
	 		int totalChoices = Array.getLength(options);
	 		
	 		do
			{
	 			for(int i = 0; i < Array.getLength(options); i++)
	 			{
	 				outputString(Integer.toString(i+1) + ". " + options[i]);
	 			}
	 			
	 			input = readInt("Select an option.\n");

	 			if(input >= 0 && input <= totalChoices)
	 			{
	 				successful = true;           
	 			}
	 			else
	 			{
	 				outputString(input + " is an invalid input. Select an option.\n");
	 			}
			}while(!successful); 
			return (input - 1);
		}
		
		/**
		 * Output the String parameter.
		 * @param outString  the string whose value is to be displayed
		 */
		 public void outputString(String outString)
		{
			 System.out.print(outString);
		}
		 
		/** Test Method */
		public static void main(String[] args) 
		{
			ConsoleIO userInput;
			userInput = new ConsoleIO();
			String testString = userInput.readString("Enter a String ");
			int testInt = userInput.readInt("Enter an integer");
			userInput.outputString("This is from the output String." 
					+ "\n The String you entered was: " + testString 
					+ "\n The integer you entered was: " + testInt);
		}

}