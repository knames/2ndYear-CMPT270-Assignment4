package commands;

import containers.KennelAccess;
import systemEntities.PetOwner;
import userInterfaces.InputOutputInterface;
import userInterfaces.UserInterface;

/** 
 * Command to Add an owner to the kennel
 */

public class AddOwnerCommand extends CommandStatus
{

	
	/**
	 * Read the information for a new owner and then add the owner
	 * to the dictionary of all owners for the kennel.
	 */
	public void addOwner()
	{
		InputOutputInterface userInput = UserInterface.getUI();
		String name = userInput.readString("Enter the name of the owner: ");
		if (KennelAccess.Kennel().hasOwner(name))
		{
			successful = false;
			errorMessage = "Trying to add a new owner when the name '" + name
				       + "' is already in the system.  New owner not added.";
			System.out.println("Trying to add a new owner when the name '" + name
				       + "' is already in the system.  New owner not added.");
		}
		else if(name == null || name.isEmpty())
		{
			successful = false;
			errorMessage = "Null or empty name. New owner not added.";
		}
		else
		{
			successful = true;
			String address = userInput.readString("Enter the address of the owner: ");
			if (address == null || address.isEmpty())
			{
				successful = false;
				errorMessage = "Null or empty address. New owner not added";
			}
			else
			{
				PetOwner owner = new PetOwner(name, address);
				KennelAccess.Kennel().addOwner(owner);
			}
		}
		
		if (successful == false)
			userInput.outputString(errorMessage);
	}
	
	/** For JUnit testing*/
	public void testAddOwner(String name, String address)
	{
		PetOwner owner = new PetOwner(name, address);
		KennelAccess.Kennel().addOwner(owner);
		if (KennelAccess.Kennel().hasOwner(name))
			successful = true;
	}


}
