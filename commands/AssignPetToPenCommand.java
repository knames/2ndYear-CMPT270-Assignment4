package commands;

import containers.KennelAccess;
import systemEntities.Pet;
import systemEntities.PetOwner;
import startup.KennelSystem;

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
		System.out.print("Enter the name of the owner: ");
		String ownerName = KennelSystem.consoleIn.nextLine();
		if (!KennelAccess.Kennel().hasOwner(ownerName))
		{
			successful = false;
			errorMessage = "The name " + ownerName 
               + " is not the name of an owner registered with the kennel.";
			throw new RuntimeException("The name " + ownerName 
			                  + " is not the name of an owner registered with the kennel.");
		}
		else
		{
			PetOwner owner = KennelAccess.Kennel().getOwner(ownerName);
			System.out.print("Enter the name of the pet: ");
			String petName = KennelSystem.consoleIn.nextLine();
			if (!owner.hasPet(petName))
			{
				successful = false;
				errorMessage = "The name " + petName + " is not the name" 
                  + " of a pet for " + ownerName + ".";
				throw new RuntimeException("The name " + petName + " is not the name" 
			                               + " of a pet for " + ownerName + ".");
			}
			else
			{
				Pet p = owner.getPet(petName);
				System.out.print("Enter the number for the pen of the pet: ");
				int penNumber = KennelSystem.readInt();
				if (penNumber < 1 || penNumber > KennelAccess.Kennel().size())
				{
					successful = false;
					errorMessage = "Pen number " + penNumber + " is illegal.";
					throw new RuntimeException("Pen number " + penNumber + " is illegal.");
				}
				if (KennelAccess.Kennel().hasOccupant(penNumber))
				{
					successful = false;
					errorMessage = "Pen number " + penNumber + " is already "
	                    + "occupied by " + KennelAccess.Kennel().occupantOfPen(penNumber);
					throw new RuntimeException("Pen number " + penNumber + " is already "
					                    + "occupied by " + KennelAccess.Kennel().occupantOfPen(penNumber));
				}
				successful = true;
				KennelAccess.Kennel().insert(p, penNumber);
			}
		}
	}



}
