package commands;

import containers.KennelAccess;
import systemEntities.Cat;
import systemEntities.PetOwner;
import userInterfaces.InputOutputInterface;
import userInterfaces.UserInterface;


/** 
 * Command to Add a cat to the kennel 
 */
public class AddCatCommand extends CommandStatus
{
	/**
	 * Read the information for a new cat and then add the cat
	 * to the list of pets for its owner.
	 */
	public void addCat()
	{
		InputOutputInterface userInput = UserInterface.getUI();
		String ownerName = userInput.readString("Enter the name of the owner for the cat: ");
		if (!KennelAccess.Kennel().hasOwner(ownerName))
		{
			errorMessage = "The name " + ownerName 
            + " is not the name of an owner for the kennel.";
			successful = false;
		}
		else
		{
			PetOwner owner = KennelAccess.Kennel().getOwner(ownerName);
			String name = userInput.readString("Enter the name of the cat: ");
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
				String colour = userInput.readString("Enter the colour of the cat: ");
				if(colour == null || colour.isEmpty())
				{
					successful = false;
					errorMessage = "Null or empty colour. New cat not added.";
				}
				else
				{
					Cat d = new Cat(name, owner, colour);
					owner.addPet(d);
				}
			}
		}
		if (successful == false)
			userInput.outputString(errorMessage);
	}
	
	/** For JUnit testing*/
	public void testAddCat(String name, PetOwner owner, String colour)
	{
		Cat d = new Cat(name, owner, colour);
		owner.addPet(d);
		if (owner.hasPet(name))
			successful = true;
	}
}