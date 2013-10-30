package commands;

import systemEntities.PetOwner;
import startup.KennelSystem;

/** 
 * Command to Add an owner to the kennel
 */

public class AddOwnerCommand extends CommandStatus
{

	
	/**
	 * Read the information for a new owner and then add the owner
	 * to the dictionary of all owners for the kennel.
	 */
	public static void addOwner()
	{
		System.out.print("Enter the name of the owner: ");
		String name = KennelSystem.consoleIn.nextLine();
		if (KennelSystem.kennel.hasOwner(name))
		{
			successful = false;
			errorMessage = "Trying to add a new owner when the name '" + name
				       + "' is already in the system.  New owner not added.";
			System.out.println("Trying to add a new owner when the name '" + name
				       + "' is already in the system.  New owner not added.");
		}
		else
		{
			successful = true;
			System.out.print("Enter the address of the owner: ");
			String address = KennelSystem.consoleIn.nextLine();
			PetOwner owner = new PetOwner(name, address);
			KennelSystem.kennel.addOwner(owner);
		}
	}


}
