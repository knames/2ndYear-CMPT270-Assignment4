package commands;

import systemEntities.Dog;
import systemEntities.PetOwner;
import startup.KennelSystem;


/** 
 * Command to Add a dog to the kennel
 */

public class AddDogCommand extends CommandStatus
{
 
	/**
	 * Read the information for a new dog and then add the dog
	 * to the list of pets for its owner.
	 */
	public static void addDog()
	{
		System.out.print("Enter the name of the owner for the dog: ");
		String ownerName = KennelSystem.consoleIn.nextLine();
		if (!KennelSystem.kennel.hasOwner(ownerName))
		{
			errorMessage = "The name " + ownerName 
               + " is not the name of an owner for the kennel.";
			successful = false;
			throw new RuntimeException("The name " + ownerName 
			                           + " is not the name of an owner for the kennel.");

		}
		else
		{
			PetOwner owner = KennelSystem.kennel.getOwner(ownerName);
			System.out.print("Enter the name of the dog: ");
			String name = KennelSystem.consoleIn.nextLine();
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
				System.out.print("Enter the breed of the dog: ");
				String breed = KennelSystem.consoleIn.nextLine();
				Dog d = new Dog(name, owner, breed);
				owner.addPet(d);
			}
		}
	}

}
