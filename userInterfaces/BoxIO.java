package userInterfaces;

import javax.swing.JOptionPane;

public class BoxIO extends AbstractDialogIO
{

	@Override
	public String readString(String prompt) {
		String userInput = (String) JOptionPane.showInputDialog(
            null,                            // parent component
            prompt,      							// prompt
            "User Input",             			// window title
            JOptionPane.QUESTION_MESSAGE,    // type of message
            null,                            // icon displayed
            null,                         // choices for the Combo box
            null);                     // initial selection
		//if (userInput == null || userInput.isEmpty())
			//return readString(prompt);
		return userInput; 
	}

	@Override
	public int readInt(String prompt) {
		String selection = (String) JOptionPane.showInputDialog(
            null,                            // parent component
            prompt,     					 		// prompt
            "User Input",              		// window title
            JOptionPane.QUESTION_MESSAGE,    // type of message
            null,                            // icon displayed
            null,                         // choices for the Combo box
            null);                     // initial selection
		int number;
		try
		{
		number = Integer.parseInt(selection);
		}
		catch(NumberFormatException e)
		{
			return readInt(prompt);
		}
		return number;
	}

	@Override
	public void outputString(String outString) {
		@SuppressWarnings("unused")
		String userInput = (String) JOptionPane.showInputDialog(
            null,                            // parent component
            outString,      							// prompt
            "User Input",             			// window title
            JOptionPane.INFORMATION_MESSAGE,    // type of message
            null,                            // icon displayed
            null,                         // choices for the Combo box
            null);                     // initial selection
	}
}