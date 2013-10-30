package commands;

import startup.KennelSystem;

/** 
 * Command to display the occupants of the kennel
 */

public class DisplayOccupantCommand extends CommandStatus
{


	/**
	 * Display the pet in each of the pens of the kennel.
	 */
	public static void displayPens()
	{
		successful = true;
		System.out.print(KennelSystem.kennel.toStringOfBasicKennel());
	}


}
