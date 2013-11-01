package commands;

import userInterfaces.InputOutputInterface;
import userInterfaces.UserInterface;
import containers.KennelAccess;

/** 
 * Command to display the System State 
 */
public class DisplayStateCommand extends CommandStatus
{
	/** Display's the current System State */
	public void displayAll()
	{
		InputOutputInterface userInput = UserInterface.getUI();
		userInput.outputString(KennelAccess.Kennel().toString());
	}
	
	public String testDisplayState()
	{
		String display = KennelAccess.Kennel().toString();
		return display;
	}
}