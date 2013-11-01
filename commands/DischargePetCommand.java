package commands;

import userInterfaces.InputOutputInterface;
import userInterfaces.UserInterface;
import containers.KennelAccess;


/**
 * Command to discharge a pet from the kennel
 */
public class DischargePetCommand extends CommandStatus
{
	/**
	 * Read the name of a pet, and discharge the pet from its pen.
	 */
	public void dischargePet()
	{
		InputOutputInterface userInput = UserInterface.getUI();
		String petName = userInput.readString("Enter the name of the pet: ");
		if (petName == null)
		{
			successful = false;
			errorMessage = "Cancelled operation.";
		}
		else if (!KennelAccess.Kennel().hasPet(petName))
		{
			successful = false;
			errorMessage = ("The name " + petName 
			                  + " is not the name of a pet in the kennel.");
		}
		else
		{
			successful = true;
			KennelAccess.Kennel().remove(petName);
			userInput.outputString(petName + " removed.");
		}
		if (successful == false)
			userInput.outputString(errorMessage);
	}
	
	public void testDischargePet(String name)
	{
		KennelAccess.Kennel().remove(name);
		if (!KennelAccess.Kennel().hasPet(name))
			successful = true;
	}
}