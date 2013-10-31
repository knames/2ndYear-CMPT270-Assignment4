package startup;

import java.util.Scanner;

import commands.*;

import systemEntities.*;
import userInterfaces.InputOutputInterface;
import userInterfaces.UserInterface;
import containers.KennelAccess;

/** 
 * The class to run the driver for the kennel system.  It issues a menu for the user
 * to select an operation, and then carries out the operation.   */
public class KennelSystem 
{ 
	UserInterface userInterface;
	
	/** The scanner used to read input from the user.  */
	public static Scanner consoleIn;

	/**
	 * Initialize the system by creating the kennel object.
	 */
	public void initialize()
	{
		userInterface = new UserInterface();
		consoleIn = new Scanner(System.in);
		KennelAccess.Size = userInterface.readInt("Enter the size for the kennel:");
		KennelAccess.Kennel();
	}

	/**
	 * Run the kennel system: initialize, and then accept and carry out operations.
	 * Output the kennel contents when finishing.
	 */
	public void run()
	{
		initialize();
		int opId = readOpId();
		while (opId != 0)
		{
			try
			{
				switch (opId)
				{
				case 1:
					AddOwnerCommand addOwner = new AddOwnerCommand();
					addOwner.addOwner();
					break;
				case 2:
					AddDogCommand addDog = new AddDogCommand();
					addDog.addDog();
					break;
				case 3:
					addCat();
					break;
				case 4:
					DisplayOccupantCommand displayPens = new DisplayOccupantCommand();
					displayPens.displayPens();
					break;
				case 5:
					AssignPetToPenCommand assignPen = new AssignPetToPenCommand();
					assignPen.assignPen();
					break;
				case 6:
					dischargePet();
					break;
				case 7:
					System.out.println("The system is as follows: " + toString());
					break;
				default:
					System.out.println("Invalid int value; try again\n");
				}
			} catch (RuntimeException e)
			{
				System.out.println(e.getMessage());
			}
			
			opId = readOpId();
		}
		
		System.out.println("The system at shutdown is as follows: " + toString());
	}

	/**
	 * Output the prompt that lists the possible operations, 
	 * and read the selection chosen by the user.
	 * @return  the int corresponding to the operation selected
	 */
	public int readOpId()
	{
		InputOutputInterface userInput = UserInterface.getUI();
		int id = userInput.readInt("Please select an operation to do"
		                 + "\n0: quit"
		                 + "\n1: add a new owner"
		                 + "\n2: add a new dog"
		                 + "\n3: add a new cat"
		                 + "\n4: display the contents of the pens"
		                 + "\n5: assign a pet a pen"
		                 + "\n6: discharge a pet"
		                 + "\n7: display current system state"
		                 + "\nEnter the number of your selection: ");
		return id;
	}

	/**
	 * Read the information for a new cat and then add the cat
	 * to the list of pets for its owner.
	 */
	public void addCat()
	{
		InputOutputInterface userInput = UserInterface.getUI();
		String ownerName = userInput.readString("Enter the name of the owner for the cat: ");
		if (!KennelAccess.Kennel().hasOwner(ownerName))
			throw new RuntimeException("The name " + ownerName 
			                           + " is not the name of an owner for the kennel.");
		else
		{
			PetOwner owner = KennelAccess.Kennel().getOwner(ownerName);
			String name = userInput.readString("Enter the name of the cat: ");
			if (owner.hasPet(name))
				throw new RuntimeException("The name " + name + " is already the name" 
			                               + " of a pet for " + ownerName + ".");
			else
			{
				String colour = userInput.readString("Enter the colour of the cat: ");
				Cat c = new Cat(name, owner, colour);
				owner.addPet(c);
			}
		}
	}


	/**
	 * Read the name of a pet, and discharge the pet from its pen.
	 */
	public void dischargePet()
	{
		InputOutputInterface userInput = UserInterface.getUI();
		String petName = userInput.readString("Enter the name of the pet: ");
		if (!KennelAccess.Kennel().hasPet(petName))
			throw new RuntimeException("The name " + petName 
			                  + " is not the name of a pet in the kennel.");
		else
			KennelAccess.Kennel().remove(petName);
	}
	
	/** A string representation of the system. */
	public String toString()
	{
		return KennelAccess.Kennel().toString();
	}

	/** Start and run the kennel system.  */
	public static void main(String[] args) 
	{
		KennelSystem sys = new KennelSystem();
		sys.run();
	}
}
