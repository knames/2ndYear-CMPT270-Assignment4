package commands;

import userInterfaces.InputOutputInterface;
import userInterfaces.UserInterface;
import containers.KennelAccess;

/** 
 * Command to display the occupants of the kennel
 */

public class DisplayOccupantCommand extends CommandStatus
{


	/**
	 * Display the pet in each of the pens of the kennel.
	 */
	public void displayPens()
	{
		InputOutputInterface userInput = UserInterface.getUI();
		successful = true;
		userInput.outputString(KennelAccess.Kennel().toStringOfBasicKennel());
	}

	public String testDisplayPens()
	{
		String display = KennelAccess.Kennel().toStringOfBasicKennel();
		return display;
	}

}
