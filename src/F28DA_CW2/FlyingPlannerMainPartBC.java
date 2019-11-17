package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.List;
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

    		
    		Journey journey = fi.leastCost("EDI", "DWC");
    		

    		System.out.println("Air time: " + journey.airTime());
    		System.out.println("Connecting time: " + journey.connectingTime());
    		System.out.println("Total cost: " + journey.totalCost());
    		System.out.println("Total hop: " + journey.totalHop());
    		System.out.println("Total cost: " + journey.totalTime());

    		System.out.println("Flights: ");
    		List<String> flights = journey.getFlights();
    		for(int i =0; i < flights.size(); i++)
    		{
    			System.out.println(flights.get(i));
    		}
    		
    		System.out.println("Stops: ");
    		List<String> stops = journey.getStops();
    		for(int i =0; i < stops.size(); i++)
    		{
    			System.out.println(stops.get(i));
    		}
    		

			
			
			System.out.println("Finished");
			

		} 
		catch (FileNotFoundException | FlyingPlannerException e) 
		{
			e.printStackTrace();
		}

	}

}
