package containers;

import systemEntities.*;

/** 
 * A singleton class to access the kennel.
 */
public class KennelAccess
{
	/** 
	 * Private constructor to ensure that no instance is created
	 */
	public KennelAccess() {}
	
	/** Private Kennel*/
	private static Kennel Kennel;
	
	/** Public size of Kennel*/
	public static int Size;
	
	/**
	 * Return the Kennel.
	 * @return kennel
	 */ 
	public static systemEntities.Kennel Kennel()
	{
		if (Kennel == null)
		{
			Kennel = new Kennel(Size);
		}
		return Kennel;
	}
} 