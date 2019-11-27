package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class FlyingPlannerMainPartA {	
	




	public static void main(String[] args) 
	{
        
        try 
        {
        	// it initialises the file reader, which
        	FlightsReader fr = new FlightsReader();
        	
        	
        	// it reads and stores a string array set, containing information about each airport
    		HashSet<String[]> airports = fr.getAirports();

    		
        	// it reads and stores a string array set, containing information about each flight
    		HashSet<String[]> flights = fr.getFlights();            
            
    		
    		// it initialises the graph which will hold the information about the airports and flights
            Graph<Airport, Flight> graph = new SimpleDirectedWeightedGraph<Airport, Flight>(Flight.class);


    		// creates an airport hash table, with the airport code as the key
    		Hashtable<String, Airport> airportHashTable = new Hashtable<String, Airport>();
    		
    		
    		
    		// it iterates per each airport string array
    		for( String[] airport: airports)
    		{
    			// creates an airport using the string
    			Airport tempAirport = new Airport(airport[0], airport[2]);

    			
    			// adds the airport as a graph vertex
    			graph.addVertex(tempAirport);

    			
    			// adds the same temporary airport to a hash table
    			airportHashTable.put(tempAirport.getCode(), tempAirport);
    		}

    		
    		
    		// it iterates over the flight string array set
    		for( String[] flight: flights )
    		{

    			// stores the temporary flight code string
    			String flightCode = flight[0];

    			
    			// stores the temporary origin airport code string
    			String originAirportCode = flight[1];

    			
    			// stores the temporary departure time
    			String fromTime = flight[2];

    			
    			// stores the temporary origin airport code string
    			String destinationAirportCode = flight[3];

    			
    			// stores the temporary arrival time
    			String toTime = flight[4];
    			
    			
    			// it parses the temporary flight price string into an integer
    			int price = Integer.parseInt(flight[5]);
    			
    			
    			// it retrieves the corresponding origin airport corresponding to the code
    			Airport originAirport = airportHashTable.get(originAirportCode);

    			
    			// it retrieves the corresponding departure airport corresponding to the code
    			Airport destinationAirport = airportHashTable.get(destinationAirportCode);
    			
    			
    			// it creates the new flight variable
    			Flight newFlight = new Flight(flightCode,fromTime,originAirport,toTime,destinationAirport,price);

    			
    			// it adds the flight as a graph edge
    			graph.addEdge(originAirport, destinationAirport, newFlight);
    			
    			
    			// cast integer price to double
    			double dPrice = (double) price;
    			
    			
    			// set the double price as the weight of each edge
    			graph.setEdgeWeight(newFlight, dPrice);
    		}    		
    		
    		// printing the list of airports
    		System.out.println("The following airports are used");
    		
    		
    		// gets an iterator of the vertex set, for display purposes
    		Iterator<Airport> airportSet = graph.vertexSet().iterator();
    		
    		
    		// loops through the iterator, to display the names of the available airports
    		while(airportSet.hasNext())
    		{
    			// stores the airport
    			Airport tempAirport = airportSet.next();

    			// prints the airport name
    			System.out.println("( " + tempAirport.getCode() + " ) - " + tempAirport.getName());
    			
    		}
    		
    		// to extract the string
    		Scanner sc = new Scanner(System.in);
    		
    		// makes sure the airports are found, otherwise asks user again
    		boolean airportsFound = false;
    		
    		// origin airport temporarily to null
    		Airport originAirport = null;
    		
    		// departure airport temporarily to null
    		Airport departureAirport = null;
    		
    		while ( !airportsFound )
    		{
    			// questions the origin airport
        		System.out.println("Please enter the start airport code");
        		
        		// stores the origin airport location string
        		String originCode = sc.nextLine();
        		
        		// gets the origin airport
        		originAirport = airportHashTable.get(originCode);
        		
        		// if no airport found, ask user again
        		if(originAirport == null) continue;
        		
        		System.out.println("Please enter the destination airport code");
        		
        		// stores the departure airport location string
        		String destinationCode = sc.nextLine();

        		// gets the departure airport
        		departureAirport = airportHashTable.get(destinationCode);
        		

        		// if no airport found, ask user again
        		if(departureAirport == null) continue;
        		
        		airportsFound = true;
    		}
    		
    		
    		// it initialises the dijkstra algorithm, by passing the graph as a parameter
    		DijkstraShortestPath<Airport,Flight> dijkstra = new DijkstraShortestPath<Airport,Flight>(graph);
    		
    		// gets the shortest path
    		GraphPath<Airport, Flight> path = dijkstra.getPath(originAirport, departureAirport);
            
    		//IMPROVE PRINTING
    		
    		List<Flight> pathFlights = path.getEdgeList();
    		
    		for(int i = 0; i < pathFlights.size(); i++)
    		{
    			System.out.println(pathFlights.get(i));
    		}
    		
    		//IMPROVE PRINTING
    		System.out.println(path.getWeight() + " pounds");
    		
    		
    		
    		// close scanner
    		sc.close();
    		
		} 
        catch (FileNotFoundException | FlyingPlannerException e) 
        {
			System.err.println(e);
		}
        
        
	}
	
	
}
