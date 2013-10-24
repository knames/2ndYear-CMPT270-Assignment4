import java.util.InputMismatchException;
import java.util.Scanner;

/** 
 * The class to run the driver for the kennel system.  It issues a menu for the user
 * to select an operation, and then carries out the operation.   */
public class KennelSystem 
{ 
	/** The kennel for the system.*/
	private Kennel kennel;

	/** The scanner used to read input from the user.  */
	private Scanner consoleIn;

	/**
	 * Initialize the system by creating the kennel object.
	 */
	public void initialize()
	{
		consoleIn = new Scanner(System.in);
		System.out.print("Enter the size for the kennel: ");
		int size = readInt();
		kennel = new Kennel(size);
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
					addOwner();
					break;
				case 2:
					addDog();
					break;
				case 3:
					addCat();
					break;
				case 4:
					displayPens();
					break;
				case 5:
					assignPen();
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
		int id;
		System.out.print("Please select an operation to do"
		                 + "\n0: quit"
		                 + "\n1: add a new owner"
		                 + "\n2: add a new dog"
		                 + "\n3: add a new cat"
		                 + "\n4: display the contents of the pens"
		                 + "\n5: assign a pet a pen"
		                 + "\n6: discharge a pet"
		                 + "\n7: display current system state"
		                 + "\nEnter the number of your selection: ");
		id = readInt();
		return id;
	}

	/**
	 * Read in an int value.  If a non-int value is entered, then issue another request.  
	 * @return the int value read in
	 */
	public int readInt()
	{
		int result = 0;    // must be initialized
		boolean successful ;
		do
		{
			successful = true;
			try
			{
				result = consoleIn.nextInt();
			} catch (InputMismatchException e)
			{
				successful = false;
				String faultyInput = consoleIn.nextLine();
				System.out.print("You entered \"" + faultyInput 
				                 + "\" which is not an int." 
				                 + "\nPlease try again: ");
			}
		}
		while (!successful);
		consoleIn.nextLine();  // discard the remainder of the line
		return result;
	}

	
	/**
	 * Read the information for a new owner and then add the owner
	 * to the dictionary of all owners for the kennel.
	 */
	public void addOwner()
	{
		System.out.print("Enter the name of the owner: ");
		String name = consoleIn.nextLine();
		if (kennel.hasOwner(name))
		{
			throw new RuntimeException("Trying to add a new owner when the name '" + name
				       + "' is already in the system.  New owner not added.");
		}
		else
		{
			System.out.print("Enter the address of the owner: ");
			String address = consoleIn.nextLine();
			PetOwner owner = new PetOwner(name, address);
			kennel.addOwner(owner);
		}
	}

	/**
	 * Read the information for a new dog and then add the dog
	 * to the list of pets for its owner.
	 */
	public void addDog()
	{
		System.out.print("Enter the name of the owner for the dog: ");
		String ownerName = consoleIn.nextLine();
		if (!kennel.hasOwner(ownerName))
			throw new RuntimeException("The name " + ownerName 
			                           + " is not the name of an owner for the kennel.");
		else
		{
			PetOwner owner = kennel.getOwner(ownerName);
			System.out.print("Enter the name of the dog: ");
			String name = consoleIn.nextLine();
			if (owner.hasPet(name))
				throw new RuntimeException("The name " + name + " is already the name" 
			                               + " of a pet for " + ownerName + ".");
			else
			{
				System.out.print("Enter the breed of the dog: ");
				String breed = consoleIn.nextLine();
				Dog d = new Dog(name, owner, breed);
				owner.addPet(d);
			}
		}
	}

	/**
	 * Read the information for a new cat and then add the cat
	 * to the list of pets for its owner.
	 */
	public void addCat()
	{
		System.out.print("Enter the name of the owner for the cat: ");
		String ownerName = consoleIn.nextLine();
		if (!kennel.hasOwner(ownerName))
			throw new RuntimeException("The name " + ownerName 
			                           + " is not the name of an owner for the kennel.");
		else
		{
			PetOwner owner = kennel.getOwner(ownerName);
			System.out.print("Enter the name of the cat: ");
			String name = consoleIn.nextLine();
			if (owner.hasPet(name))
				throw new RuntimeException("The name " + name + " is already the name" 
			                               + " of a pet for " + ownerName + ".");
			else
			{
				System.out.print("Enter the colour of the cat: ");
				String colour = consoleIn.nextLine();
				Cat c = new Cat(name, owner, colour);
				owner.addPet(c);
			}
		}
	}

	/**
	 * Display the pet in each of the pens of the kennel.
	 */
	public void displayPens()
	{
		System.out.print(kennel.toStringOfBasicKennel());
	}

	/**
	 * Read the name of an owner, the name of a pet for the owner, 
	 * and the number for pen, and then assign the pet to the pen.
	 */
	public void assignPen()
	{
		System.out.print("Enter the name of the owner: ");
		String ownerName = consoleIn.nextLine();
		if (!kennel.hasOwner(ownerName))
			throw new RuntimeException("The name " + ownerName 
			                  + " is not the name of an owner registered with the kennel.");
		else
		{
			PetOwner owner = kennel.getOwner(ownerName);
			System.out.print("Enter the name of the pet: ");
			String petName = consoleIn.nextLine();
			if (!owner.hasPet(petName))
				throw new RuntimeException("The name " + petName + " is not the name" 
			                               + " of a pet for " + ownerName + ".");
			else
			{
				Pet p = owner.getPet(petName);
				System.out.print("Enter the number for the pen of the pet: ");
				int penNumber = readInt();
				if (penNumber < 1 || penNumber > kennel.size())
					throw new RuntimeException("Pen number " + penNumber + " is illegal.");
				if (kennel.hasOccupant(penNumber))
					throw new RuntimeException("Pen number " + penNumber + " is already "
					                    + "occupied by " + kennel.occupantOfPen(penNumber));
				kennel.insert(p, penNumber);
			}
		}
	}

	/**
	 * Read the name of a pet, and discharge the pet from its pen.
	 */
	public void dischargePet()
	{
		System.out.print("Enter the name of the pet: ");
		String petName = consoleIn.nextLine();
		if (!kennel.hasPet(petName))
			throw new RuntimeException("The name " + petName 
			                  + " is not the name of a pet in the kennel.");
		else
			kennel.remove(petName);
	}
	
	/** A string representation of the system. */
	public String toString()
	{
		return kennel.toString();
	}

	/** Start and run the kennel system.  */
	public static void main(String[] args) 
	{
		KennelSystem sys = new KennelSystem();
		sys.run();
	}
}
