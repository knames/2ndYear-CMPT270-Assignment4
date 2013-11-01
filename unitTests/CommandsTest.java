package unitTests;//import entities.*;//import entities.*;

import org.junit.*;
import systemEntities.*;
import userInterfaces.BoxIO;
import userInterfaces.ConsoleIO;
import commands.*;
import containers.KennelAccess;

/** Contains the JUnit tests*/
public class CommandsTest
{
	
	/** Test Setup (first to run)*/
	@BeforeClass
	public static void setUp()
	{
		//initialize
		//UserInterface userInput = new UserInterface();
		KennelAccess.Size = 5;
		KennelAccess.Kennel();
		String temp = KennelAccess.Kennel().toString();
		Assert.assertTrue(temp != null);
		System.out.println(temp + "\n");
	}
	
	/** Test AddOwnerCommand*/
	@Test
	public void testAddOwner()
	{
		AddOwnerCommand owner = new AddOwnerCommand();
		owner.testAddOwner("Fred", "Broadway");
		System.out.println(KennelAccess.Kennel().getOwner("Fred"));
		Assert.assertTrue(owner.wasSuccessful());
		Assert.assertTrue(!owner.equals("Fred"));
	}
	
	/** Test addDogCommand*/
	@Test
	public void testAddDog()
	{
		AddOwnerCommand owner = new AddOwnerCommand();
		owner.testAddOwner("Bob", "Broadway");
		PetOwner Fred = KennelAccess.Kennel().getOwner("Fred");
		AddDogCommand dog = new AddDogCommand();
		String name = "Rex";
		String breed = "lab";
		dog.testAddDog(name, Fred, breed);
		Assert.assertTrue(dog.wasSuccessful());
	}
	
	/** Test addDogCommand*/
	@Test
	public void testAddCat()
	{
		AddOwnerCommand owner = new AddOwnerCommand();
		owner.testAddOwner("Frank", "Broadway");
		PetOwner Frank = KennelAccess.Kennel().getOwner("Frank");
		AddCatCommand cat = new AddCatCommand();
		String name = "Cat";
		String colour = "black";
		cat.testAddCat(name, Frank, colour);
		Assert.assertTrue(cat.wasSuccessful());
	}
	
	/** Test AssignPetToPenCommand */
	@Test
	public void testAssignPetToPen()
	{
		AddOwnerCommand owner = new AddOwnerCommand();
		owner.testAddOwner("Jim", "Broadway");
		PetOwner Jim = KennelAccess.Kennel().getOwner("Jim");
		Cat cat = new Cat("Tabby", Jim, "orange");
		
		AssignPetToPenCommand assign = new AssignPetToPenCommand();
		assign.testAssignPetToPen(cat, 1);
		Assert.assertTrue(assign.wasSuccessful());
	}
	
	/** Test DischargePetCommand */
	@Test
	public void testDischargePet()
	{
		AddOwnerCommand owner = new AddOwnerCommand();
		owner.testAddOwner("Alex", "Broadway");
		PetOwner Alex = KennelAccess.Kennel().getOwner("Jim");
		Cat cat = new Cat("KeyboardCat", Alex, "orange");
		
		AssignPetToPenCommand assign = new AssignPetToPenCommand();
		assign.testAssignPetToPen(cat, 2);
		Assert.assertTrue(assign.wasSuccessful());
		
		DischargePetCommand discharge = new DischargePetCommand();
		discharge.testDischargePet("KeyboardCat");
		Assert.assertTrue(discharge.wasSuccessful());
	}
	
	/** Test DisplayOccupantCommand*/
	@Test
	public void testDisplayOccupant()
	{
		DisplayOccupantCommand display = new DisplayOccupantCommand();
		Assert.assertTrue(display.testDisplayPens() != null);
	}
	
	/** Test DisplayStateCommand*/
	@Test
	public void testDisplayState()
	{
		DisplayStateCommand display = new DisplayStateCommand();
		Assert.assertTrue(display.testDisplayState() != null);
	}
	
	/** Test Entities*/
	@Test
	public void testEntities()
	{
		BasicKennel.main(null);
		Kennel.main(null);
		Person.main(null);
		PetOwner.main(null);
		Pet.main(null);
		Dog.main(null);
		Cat.main(null);
	}
	
	/** Test Console and Box IO */
	@Test
	public void testIO()
	{
		boolean onoff = true; //set this to false to disable this test
		if (onoff)
		{
			ConsoleIO.main(null);
			BoxIO.main(null);
		}
	}
	
	
}