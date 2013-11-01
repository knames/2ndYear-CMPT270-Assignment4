package userInterfaces;

import javax.swing.JOptionPane;
/** A class that extends AbstractDialogIO, used to 
 * control the input/output with dialogue boxes*/
public class BoxIO extends AbstractDialogIO
{

	/** Reads a string to a diaogue box
	 * @param prompt  is the message to the user
	 * @return userInput the string input from the user*/
	public String readString(String prompt) 
	{
		String userInput = (String) JOptionPane.showInputDialog(
            null,                            // parent component
            prompt,      							// prompt
            "User Input",             			// window title
            JOptionPane.QUESTION_MESSAGE,    // type of message
            null,                            // icon displayed
            null,                         // choices for the Combo box
            null);                     // initial selection
		return userInput; 
	}

	/** Reads an int to a dialogue box
	 * @param prompt  is the message to the user
	 * @return userInput  int input from the user*/
	public int readInt(String prompt) 
	{
		int number = 0;
		String selection = (String) JOptionPane.showInputDialog(
            null,                            // parent component
            prompt,     					 		// prompt
            "User Input",              		// window title
            JOptionPane.QUESTION_MESSAGE,    // type of message
            null,                            // icon displayed
            null,                         // choices for the Combo box
            null);                     // initial selection

		try
		{
		number = Integer.parseInt(selection);
		}
		catch(NumberFormatException e)
		{
			if (selection == null) //returns 0 if cancel/x is clicked
				return 0;
			outputString("Please enter a valid integer."); //+ number + selection);
			return readInt(prompt);
		}
		return number;
	}

	/** 
	 * Reads output to a diaogue box
	 */
	public void outputString(String outString) 
	{
		JOptionPane.showMessageDialog(null, outString);
	}
	
	/** Test Method */
	public static void main(String[] args) 
	{
		BoxIO userInput;
		userInput = new BoxIO();
		String testString = userInput.readString("Enter a String ");
		int testInt = userInput.readInt("Enter an integer");
		userInput.outputString("This is from the output String." 
				+ "\n The String you entered was: " + testString 
				+ "\n The integer you entered was: " + testInt);
	}
}