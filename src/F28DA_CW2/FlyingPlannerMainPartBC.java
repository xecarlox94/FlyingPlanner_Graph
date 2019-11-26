package F28DA_CW2;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
			
//			
//			List<String> excluding = new LinkedList<String>();
//
//			excluding.add("AMS");
//			excluding.add("LHR");
//			excluding.add("DXB");
//			excluding.add("CDG");
//			excluding.add("FRA");
//			excluding.add("IST");

//    		Journey journey = fi.leastCost("EDI", "DXB", excluding);
//    		Journey journey = fi.leastCost("EDI", "DXB");

//    		Journey journey = fi.leastHop("EDI", "DXB", excluding);
//    		Journey journey = fi.leastHop("EDI", "DXB");
    		
    		

//    		System.out.println("\n\nFlights: ");
//    		List<String> flights = journey.getFlights();
//    		for(int i =0; i < flights.size(); i++)
//    		{
//    			System.out.println(flights.get(i));
//    		}
//    		
//    		System.out.println("\n\nStops: ");
//    		List<String> stops = journey.getStops();
//    		for(int i =0; i < stops.size(); i++)
//    		{
//    			System.out.println(stops.get(i));
//    		}
    		
    		
//    		System.out.println("\nTotal cost: " + journey.totalCost());
//    		System.out.println("\nTotal hop: " + journey.totalHop());
//    		System.out.println("\nTotal air time: " + journey.airTime());
//    		System.out.println("\nTotal con time: " + journey.connectingTime());
//    		System.out.println("\nTotal tot time: " + journey.totalTime());


//    		Airport lhr = fi.airport("LHR");
//    		Set<Airport> s = fi.directlyConnected(lhr);
//    		
//    		System.out.println("FINAL RESULT: " + s.size());
    		
    		

//    		int sum = fi.setDirectlyConnected();
//    		
//    		System.out.println("FINAL RESULT: " + sum);
			
			
			
			
			
			System.out.println("Finished");
			

		} 
		catch (FileNotFoundException | FlyingPlannerException e) 
		{
			e.printStackTrace();
		}

	}

}
