package commands;

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
		successful = true;
		System.out.print(KennelAccess.Kennel().toStringOfBasicKennel());
	}


}
