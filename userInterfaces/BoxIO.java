package userInterfaces;

import javax.swing.JOptionPane;

public class BoxIO extends AbstractDialogIO
{

	@Override
	public String readString(String prompt) {
		String selection = (String) JOptionPane.showInputDialog(
            null,                            // parent component
            "Please select an option ",      // prompt
            "Choice Selection",              // window title
            JOptionPane.QUESTION_MESSAGE,    // type of message
            null,                            // icon displayed
            options,                         // choices for the Combo box
            options[0]);                     // initial selection
	if (selection == null)
		return "";  							// Cancel or X button clicked
	for (int i = 0; i < options.length; i++)
		if (selection.equals(options[i]))
			return i;
	JOptionPane.showMessageDialog(null, "Illegal choice: " + selection + "\n");
	return readChoice(options);
		return null;
	}

	@Override
	public int readInt(String prompt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void outputString(String outString) {
		// TODO Auto-generated method stub
		
	}
	
}