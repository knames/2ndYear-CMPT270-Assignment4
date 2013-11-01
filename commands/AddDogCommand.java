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
			else if(name == null || name.isEmpty())
			{
				successful = false;
				errorMessage = "Null or empty name. New owner not added.";
			}
			else
			{
				successful = true;
				String breed = userInput.readString("Enter the breed of the dog: ");
				if(breed == null || breed.isEmpty())
				{
					successful = false;
					errorMessage = "Null or empty breed. New dog not added.";
				}
				else
				{
					Dog d = new Dog(name, owner, breed);
					owner.addPet(d);
				}
			}
		}
		if (successful == false)
			userInput.outputString(errorMessage);
	}

	
	public void testAddDog(String name, PetOwner owner, String breed)
	{
		Dog d = new Dog(name, owner, breed);
		owner.addPet(d);
		if (owner.hasPet(name))
				successful = true;
	}
}
