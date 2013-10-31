package userInterfaces;

import java.util.Scanner;

/** 
 * Selects whether to use the ConsoleIO or BoxIO
 * */
public class UserInterface implements InputOutputInterface
{
	/** Scans for valid input*/
	private Scanner scan = new Scanner(System.in);
	
	/** Determines whether the Console or Box IO is used*/
	private static InputOutputInterface useUI;
	
	/** Initializes either Console or Box IO*/
	public UserInterface()
	{
		String input;
		boolean success = false;
		do
		{
			System.out.println("Would you like to use the Console or Dialog boxes?" 
					+ "\nEnter \"c\" for Console, or \"d\" for Dialog boxes.");
			input = scan.next();
			input = input.toLowerCase();
			
			if (input.equals("c"))
			{
				useUI = new ConsoleIO();
				success = true;
			}
			else if (input.equals("d"))
			{
				useUI = new BoxIO();
				success = true;
			}
			else
			{
				System.out.println("Please enter a valid \"c\" or \"d\".");
			}
		}while(!success);		
	}

	/** 
	 * Read's a string into useUI
	 * @param prompt  the prompt to be read
	 * @return returns proper user input if successful, empty string otherwise*/
	public String readString(String prompt) 
	{
		final String FAIL = "";
		try
		{
			return useUI.readString(prompt);
		}
		catch(RuntimeException e)
		{
			System.out.println(e.getMessage());
		}
		
		return FAIL;
	}

	/** 
	 * Read's an int into useUI
	 * @param prompt  the prompt to be read
	 * @return returns proper user input if successful, 0 otherwise*/
	public int readInt(String prompt) 
	{
		final int FAIL = 0;
		try
		{
			return useUI.readInt(prompt);
		}
		catch(RuntimeException e)
		{
			System.out.println(e.getMessage());
		}
		
		return FAIL;
	}

	/** 
	 * Read's an int into useUI
	 * @param prompt  the prompt to be read
	 * @return returns proper user input if successful, 0 otherwise*/
	public int readChoice(String[] options) 
	{
		final int FAIL = 0;
		try
		{
			return useUI.readChoice(options);
		}
		catch(RuntimeException e)
		{
			System.out.println(e.getMessage());
		}
		
		return FAIL;
	}

	/**
	 * Output the String parameter.
	 * @param outString  the string whose value is to be displayed	 */
	public void outputString(String outString) 
	{
		try
		{
			useUI.outputString(outString);
		}
		catch(RuntimeException e)
		{
			System.out.println(e.getMessage());
		}		
	}
	
	/** Returns the style of IO
	 * @return useUI whether the IO is Console or Box*/
	public static InputOutputInterface getUI()
	{
		return useUI;
	}
}