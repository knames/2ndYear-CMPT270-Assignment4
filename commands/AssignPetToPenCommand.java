package commands;

import containers.KennelAccess;
import systemEntities.Pet;
import systemEntities.PetOwner;
import userInterfaces.InputOutputInterface;
import userInterfaces.UserInterface;

/** 
 * Command to assign a pet to the kennel
 */

public class AssignPetToPenCommand extends CommandStatus
{


	 /**
	 * Read the name of an owner, the name of a pet for the owner, 
	 * and the number for pen, and then assign the pet to the pen.
	 */
	public void assignPen()
	{
		InputOutputInterface userInput = UserInterface.getUI();
		String ownerName = userInput.readString("Enter the name of the owner: ");
		if (!KennelAccess.Kennel().hasOwner(ownerName))
		{
			successful = false;
			errorMessage = "The name " + ownerName 
               + " is not the name of an owner registered with the kennel.";
		}
		else
		{
			PetOwner owner = KennelAccess.Kennel().getOwner(ownerName);
			String petName = userInput.readString("Enter the name of the pet: ");
			if (!owner.hasPet(petName))
			{
				successful = false;
				errorMessage = "The name " + petName + " is not the name" 
                  + " of a pet for " + ownerName + ".";
			}
			else
			{
				Pet p = owner.getPet(petName);
				int penNumber = userInput.readInt("Enter the number for the pen of the pet: ");
				if (penNumber < 0 || penNumber > KennelAccess.Kennel().size())
				{
					successful = false;
					errorMessage = "Pen number " + penNumber + " is illegal.";
				}
				else if (penNumber == 0)
				{
					successful = false;
					errorMessage = "Cancelled operation.";
				}
				else if (KennelAccess.Kennel().hasOccupant(penNumber))
				{
					successful = false;
					errorMessage = "Pen number " + penNumber + " is already "
	                    + "occupied by " + KennelAccess.Kennel().occupantOfPen(penNumber);
				}
				else
				{
					successful = true;
					KennelAccess.Kennel().insert(p, penNumber);
					userInput.outputString(petName + " has been added.");
				}
			}
		}
		if (successful == false)
			userInput.outputString(errorMessage);
	}
	
	/** For JUnit testing*/
	public void testAssignPetToPen(Pet p, int penNumber)
	{
		KennelAccess.Kennel().insert(p, penNumber);
		if (KennelAccess.Kennel().occupantOfPen(penNumber).equals(p.getName()))
			successful = true;
	}


}
