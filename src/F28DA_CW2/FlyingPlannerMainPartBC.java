package F28DA_CW2;


import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class FlyingPlannerMainPartBC {

	public static void main(String[] args) {

		// Your implementation should be in FlyingPlanner.java, this class is only to
		// run the user interface of your programme.

		FlyingPlanner fi;
		fi = new FlyingPlanner();
		try 
		{
			// It populates the flight planner
			fi.populate(new FlightsReader());

			
			// It is a public that does all the necessary queries to the user, for parts B and C.
			// This method is public to initialise a single Scanner object and to close it.
			// The reason is also to make this the methods that the user runs on the part B and C, 
			// and all the remaining public methods are public to be tested 
			fi.userQueriesPartsBC();
			

		} 
		catch (FileNotFoundException | FlyingPlannerException e) 
		{
			e.printStackTrace();
		}

	}

}
