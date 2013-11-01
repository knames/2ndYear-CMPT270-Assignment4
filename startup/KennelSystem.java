/*TODO
 * J unit testing
 * UML garbage
 * Can't use Cancel, how to fix that for ints
 */
package startup;

import java.util.Scanner;

import commands.*;

import userInterfaces.InputOutputInterface;
import userInterfaces.UserInterface;
import containers.KennelAccess;

/** 
 * The class to run the driver for the kennel system.  It issues a menu for the user
 * to select an operation, and then carries out the operation.   */
public class KennelSystem 
{ 
	private UserInterface userInterface;
	
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
		while(KennelAccess.Size <1)
		{
			userInterface.outputString("Please enter a valid size above zero.");
			KennelAccess.Size = userInterface.readInt("Enter the size for the kennel:");
		}
		KennelAccess.Kennel();
	}

	/**
	 * Run the kennel system: initialize, and then accept and carry out operations.
	 * Output the kennel contents when finishing.
	 */
	public void run()
	{
		InputOutputInterface userInput = UserInterface.getUI();
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
					AddCatCommand addCat = new AddCatCommand();
					addCat.addCat();
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
					DischargePetCommand dischargePet = new DischargePetCommand();
					dischargePet.dischargePet();
					break;
				case 7:
					DisplayStateCommand displayAll = new DisplayStateCommand();
					displayAll.displayAll();
					break;
				default:
					userInput.outputString("Invalid int value; try again\n");
				}
			} catch (RuntimeException e)
			{
				System.out.println(e.getMessage());
			}
			
			opId = readOpId();
		}
		DisplayStateCommand displayAll = new DisplayStateCommand();
		displayAll.displayAll();
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

	/** Start and run the kennel system.  */
	public static void main(String[] args) 
	{
		KennelSystem sys = new KennelSystem();
		sys.run();
	}
}
