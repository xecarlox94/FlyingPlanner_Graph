package F28DA_CW2;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.KShortestSimplePaths;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class FlyingPlanner implements IFlyingPlannerPartB<Airport,Flight>, IFlyingPlannerPartC<Airport,Flight> 
{

	// storing the graph
	private Graph<Airport, Flight> graph;

	@Override
	public boolean populate(FlightsReader fr) 
	{
		// it stores the airport string array set locally
		HashSet<String[]> airports = fr.getAirports();
		

		// it stores the airport string array set locally
		HashSet<String[]> flights = fr.getFlights();
		
		
		return this.populate(airports, flights);
	}

	@Override
	public boolean populate(HashSet<String[]> airports, HashSet<String[]> flights) 
	{
		
		// instantiates the graph 
		this.graph = new SimpleDirectedWeightedGraph<Airport,Flight>(Flight.class);
		
		
		// creates an airport hash table, with the airport code as the key
		Hashtable<String, Airport> airportHashTable = new Hashtable<String, Airport>();
		
		
		// it iterates per each airport string array
		for( String[] airport: airports)
		{
			// creates an airport using the string
			Airport tempAirport = new Airport(airport[0], airport[2], airport[1]);

			
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
			
		}    		
		
		
		return true;
	}

	@Override
	public Airport airport(String code) 
	{
		// gets an airport iterator
		Iterator<Airport> airportIterator = this.graph.vertexSet().iterator();
		
		// looping through the iterator
		while(airportIterator.hasNext())
		{
			// stores the current airport
			Airport tempAirport = airportIterator.next();
			
			// stores the airport code
			String codeAirport = tempAirport.getCode();
			
			// if the code is the same, the airport was found
			if( code.equals(codeAirport))
			{
				// returning the airport
				return tempAirport;
			}
		}
		
		// returns null if not found, to potentially throw an error on the scope above
		return null;
	}

	@Override
	public Flight flight(String code) 
	{
		// gets an airport iterator
		Iterator<Flight> flightIterator = this.graph.edgeSet().iterator();
		
		
		// loops through the flights
		while(flightIterator.hasNext())
		{
			// stores the temporary flight
			Flight tempFlight = flightIterator.next();
			
			// stores the airport code
			String codeFlight = tempFlight.getFlightCode();
			
			// if the flight code matches
			if( code.equals(codeFlight))
			{
				// returns the code
				return tempFlight;
			}
		}
		
		// returns null if not found, to potentially throw an error on the scope above
		return null;
	}


	@Override
	public int setDirectlyConnected() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDirectlyConnectedOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Airport> getBetterConnectedInOrder(Airport airport) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String leastCostMeetUp(String at1, String at2) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String leastHopMeetUp(String at1, String at2) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String leastTimeMeetUp(String at1, String at2, String startTime) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Journey leastCost(String from, String to) throws FlyingPlannerException 
	{
		// overloading the method
		return this.leastCost(from, to, null);
	}
	

	@Override
	public Journey leastCost(String from, String to, List<String> excluding) throws FlyingPlannerException 
	{
		// loading the graph into the find all paths algorithm
		KShortestSimplePaths<Airport,Flight> kShortestPathAlg = new KShortestSimplePaths<Airport, Flight>(this.graph);
		
		// getting the departure airport
		Airport departureAirport = this.airport(from);

		// getting the departure airport
		Airport destinationAirport = this.airport(to);
		

		System.out.println(departureAirport);
		System.out.println(destinationAirport);
		
		
		List<GraphPath<Airport, Flight>> shortestPaths = kShortestPathAlg.getPaths(departureAirport, destinationAirport, 1);
		
		
		

		// gets the shortest paths from the departure to the destination nodes
		Journey[] journeys = getJourneyArray(shortestPaths);
		

		
		// TODO Auto-generated method stub
		return journeys[0];
	}
	
	// gets the different paths 
	private Journey[] getJourneyArray(List<GraphPath<Airport, Flight>> paths)
	{
		// initialises the local journey array
		Journey[] journeys = new Journey[paths.size()];
		
		for(int i = 0; i < paths.size(); i++)
		{
			// stores the temporary path
			GraphPath<Airport, Flight> tempPath = paths.get(i);
			
			// constructs a new journey and assigns it, in the journeys array
			journeys[i] = new Journey(tempPath);
		}
		
		return journeys;
	}

	@Override
	public Journey leastHop(String from, String to) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Journey leastHop(String from, String to, List<String> excluding) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Airport> directlyConnected(Airport airport) 
	{
		HashSet<Airport> tempAirportSet = new HashSet<Airport>();
		
		// returns an iterator with all the outgoing flights
		Iterator<Flight> outgoingFlightSet = this.graph.outgoingEdgesOf(airport).iterator();
		
		// loops through the iterator
		while(outgoingFlightSet.hasNext())
		{
			// returns the temporary flight
			Flight temp = outgoingFlightSet.next();
			
			// returns the destination airport
			Airport tempAirport = temp.getTo();
			
			// add the destination airport
			tempAirportSet.add(tempAirport);
		}
		
		// returns an iterator with all the incoming flights
		Iterator<Flight> incomingFlightSet = this.graph.incomingEdgesOf(airport).iterator();
		
		// loops through the iterator
		while(incomingFlightSet.hasNext())
		{
			// returns the temporary flight
			Flight temp = incomingFlightSet.next();
			
			// returns the origin airport
			Airport tempAirport = temp.getTo();
			
			// add the destination airport
			tempAirportSet.add(tempAirport);
		}

		
		return tempAirportSet;
	}

	
	
	


}
