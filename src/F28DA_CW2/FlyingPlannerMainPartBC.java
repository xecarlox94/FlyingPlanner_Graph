package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class FlyingPlannerMainPartBC {

	public static void main(String[] args) {

		// Your implementation should be in FlyingPlanner.java, this class is only to
		// run the user interface of your programme.

		FlyingPlanner fi;
		fi = new FlyingPlanner();
		try 
		{
			fi.populate(new FlightsReader());

			// Implement here your user interface using the methods of Part B. You could
			// optionally expand it to use the methods of Part C.
			
			System.out.println("b part");
			
//			Scanner sc = new Scanner(System.in);
//
//
//			// questions the origin airport
//    		System.out.println("Please enter the start airport code");
//    		
//    		// stores the origin airport location string
//    		String originCode = sc.nextLine();
//    		
//    		
//    		System.out.println("Please enter the destination airport code");
//    		
//    		// stores the departure airport location string
//    		String destinationCode = sc.nextLine();

    		
    		fi.leastCost("EDI", "DWC");
    		

			
			
			System.out.println("Finished");
			

		} 
		catch (FileNotFoundException | FlyingPlannerException e) 
		{
			e.printStackTrace();
		}

	}

}
