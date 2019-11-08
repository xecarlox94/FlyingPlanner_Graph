package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class FlyingPlannerMainPartA {	
	
	private static void deleteDemo() {
		
		// DELETEEE SOOOOOOOOOOOOOOOOOOOOOOOOOOOON !!!!!!!!!!!!!!!!!!!!!!!!

		// The following code is from HelloJGraphT.java of the org.jgrapth.demo package
		System.err.println("The example code is from HelloJGraphT.java from the org.jgrapt.demo package.");
		System.err.println("Use similar code to build the small graph from Part A by hand.");
		System.err.println("Note that you will need to use a different graph class as your graph is not just a Simple Graph.");
		System.err.println("Once you understand how to build such graph by hand, move to Part B to build a more substantial graph.");
		// Code is from HelloJGraphT.java of the org.jgrapth.demo package (start)
        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        // note undirected edges are printed as: {<v1>,<v2>}
        System.out.println("-- toString output");
        // @example:toString:begin
        System.out.println(g.toString());
        // @example:toString:end
        System.out.println();
		// Code is from HelloJGraphT.java of the org.jgrapth.demo package (start)
        
        
        
	}



	public static void main(String[] args) {
//		deleteDemo();

		try {
			FlightsReader rd = new FlightsReader();
			
			HashSet<String[]> airportsString = rd.getAirports();
			
			HashSet<Airport> airports = new HashSet<Airport>();
			
			LinkedList<Flight> flights = new LinkedList<Flight>();
			
			HashSet<String[]> flightsString = rd.getFlights();
			
			Iterator<Airport> airportsIterator = airports.iterator();
			
			Hashtable<String, Airport> airportHashTable = new Hashtable<String, Airport>();
			
			
			System.out.println("Airports");
			for( String[] airport: airportsString)
			{
				System.out.println("Code: " + airport[0] + ",		location: " + airport[1] + ",		name: " + airport[2]);
				Airport temp = new Airport(airport[0], airport[2], airport[1]);
				airports.add(temp);
			}
			
			
			while(airportsIterator.hasNext())
			{
				Airport tempAirport = airportsIterator.next();
				airportHashTable.put(tempAirport.getCode(), tempAirport);
			}
			
			
			System.out.println("Flights");
			for( String[] flight: flightsString)
			{
				System.out.println("code: " + 
						flight[0] + ", from: " + flight[1] + ", leave: " +
						flight[2] + ", to: " + flight[3] + ", arrive: " +
						flight[4] + ", price: " + flight[5]);

				String originCode = flight[1];
				String destinationCode = flight[3];
				
				Airport originAirport = airportHashTable.get(originCode);
				Airport destinationAirport = airportHashTable.get(destinationCode);
				
				int price = Integer.parseInt(flight[5]);
				
				Flight newFlight = new Flight(flight[0],flight[1],originAirport,flight[3],destinationAirport,price);
				
				flights.add(newFlight);
				
			}
			
			System.out.println("finished");
			
			
		} catch (FileNotFoundException | FlyingPlannerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	
}
