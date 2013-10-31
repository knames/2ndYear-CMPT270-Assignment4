package commands;

import containers.KennelAccess;
import systemEntities.Dog;
import systemEntities.PetOwner;
import userInterfaces.InputOutputInterface;
import userInterfaces.UserInterface;


/** 
 * Command to Add a dog to the kennel
 */

public class AddDogCommand extends CommandStatus
{
 
	/**
	 * Read the information for a new dog and then add the dog
	 * to the list of pets for its owner.
	 */
	public void addDog()
	{
		InputOutputInterface userInput = UserInterface.getUI();
		String ownerName = userInput.readString("Enter the name of the owner for the dog: ");
		if (!KennelAccess.Kennel().hasOwner(ownerName))
		{
			errorMessage = "The name " + ownerName 
               + " is not the name of an owner for the kennel.";
			successful = false;
			throw new RuntimeException("The name " + ownerName 
			                           + " is not the name of an owner for the kennel.");

		}
		else
		{
			PetOwner owner = KennelAccess.Kennel().getOwner(ownerName);
			String name = userInput.readString("Enter the name of the dog: ");
			if (owner.hasPet(name))
			{
				errorMessage = "The name " + name + " is already the name" 
			                               + " of a pet for " + ownerName + ".";
				successful = false;
				System.out.println("The name " + name + " is already the name" 
			                               + " of a pet for " + ownerName + ".");
			}
			else
			{
				successful = true;
				String breed = userInput.readString("Enter the breed of the dog: ");
				Dog d = new Dog(name, owner, breed);
				owner.addPet(d);
			}
		}
	}

}
