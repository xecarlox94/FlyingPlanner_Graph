package F28DA_CW2;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

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
	public Journey leastCost(String from, String to) throws FlyingPlannerException 
	{
		// overloading the method
		return this.leastCost(from, to, null);
	}
	

	@Override
	public Journey leastCost(String from, String to, List<String> excluding) throws FlyingPlannerException 
	{
		
		// if excluding list is not full modify the graph
		if ( excluding != null) 
		{
			
			// for each string in the excluding list
			for ( int i = 0; i < excluding.size(); i++)
			{
				
				// gets the airport code
				String airportCode = excluding.get(i);
				
				
				// gets the airport code
				Airport tempAirport = this.airport(airportCode);

				
				// gets all the edges of the temporary airport vertex
				Set<Flight> tempFlights = this.graph.edgesOf(tempAirport);
				
				
				// removes all the flight set elements from the graph
				this.graph.removeAllEdges(tempFlights);
				
				// removes the temporary airport vertex from the graph
				this.graph.removeVertex(tempAirport);
			}
		}
		
		
		// gets a edge set from the graph
		// and returns its iterator
		Iterator<Flight> flights = this.graph.edgeSet().iterator();
		
		
		// iterates through each edge in the set
		while (flights.hasNext())
		{
			
			// gets the next flight
			Flight tempFlight = flights.next();
			
			// gets the cost of the temporary flight
			double cost = (double) tempFlight.getCost();

			// sets the edge weight to its cost
			this.graph.setEdgeWeight(tempFlight, cost);
		}
		
		
		// it initialises the dijkstra algorithm, by passing the graph as a parameter
		DijkstraShortestPath<Airport,Flight> dijkstra = new DijkstraShortestPath<Airport,Flight>(this.graph);
		
		// getting the departure airport
		Airport departureAirport = this.airport(from);

		// getting the departure airport
		Airport destinationAirport = this.airport(to);
		
		
		// gets the graph shortest path
		GraphPath<Airport, Flight> shortestPath = dijkstra.getPath(departureAirport, destinationAirport);

		
		// instantiates the journey object from the shortest path
		Journey journey = new Journey(shortestPath);

		// returns the object
		return journey;
	}


	@Override
	public Journey leastHop(String from, String to, List<String> excluding) throws FlyingPlannerException 
	{
		
		// if excluding list is not full modify the graph
		if ( excluding != null) 
		{
			
			// for each string in the excluding list
			for ( int i = 0; i < excluding.size(); i++)
			{
				
				// gets the airport code
				String airportCode = excluding.get(i);
				
				
				// gets the airport code
				Airport tempAirport = this.airport(airportCode);

				
				// gets all the edges of the temporary airport vertex
				Set<Flight> tempFlights = this.graph.edgesOf(tempAirport);
				
				
				// removes all the flight set elements from the graph
				this.graph.removeAllEdges(tempFlights);
				
				// removes the temporary airport vertex from the graph
				this.graph.removeVertex(tempAirport);
			}
		}
		
		
		// it initialises the dijkstra algorithm, by passing the graph as a parameter
		DijkstraShortestPath<Airport,Flight> dijkstra = new DijkstraShortestPath<Airport,Flight>(this.graph);
		
		// getting the departure airport
		Airport departureAirport = this.airport(from);

		// getting the departure airport
		Airport destinationAirport = this.airport(to);
		
		
		// gets the graph shortest path
		GraphPath<Airport, Flight> shortestPath = dijkstra.getPath(departureAirport, destinationAirport);

		
		// instantiates the journey object from the shortest path
		Journey journey = new Journey(shortestPath);

		// returns the object
		return journey;
	}

	@Override
	public Journey leastHop(String from, String to) throws FlyingPlannerException 
	{
		// overloading the least hop function
		return this.leastHop(from, to, null);
	}

	@Override
	public Set<Airport> directlyConnected(Airport airport) 
	{
		BreadthFirstIterator<Airport, Flight> bfs = new BreadthFirstIterator<Airport, Flight>(this.graph, airport);
		
		int depth = 0;
		
		HashSet<Airport> dirConnectedAirports = new HashSet<Airport>();
		
		while ( bfs.hasNext() )
		{
			Airport tempAirport = bfs.next();
			depth = bfs.getDepth(tempAirport);
			
			if ( tempAirport.equals(airport) ) continue;

			if ( depth > 2) break;

			dirConnectedAirports.add(tempAirport);
		}
		
		
		Iterator<Airport> airportsIterator = dirConnectedAirports.iterator();
		
		System.out.println("airports set initial size: " + dirConnectedAirports.size());
		
		HashSet<Airport> toRemoveAirports = new HashSet<Airport>();
		
		BreadthFirstIterator<Airport, Flight> bfsBack;
		
		while ( airportsIterator.hasNext() )
		{
			
			Airport tempAirport = airportsIterator.next();
			
			bfsBack = new BreadthFirstIterator<Airport, Flight>(this.graph, tempAirport);

			depth = 0;
			
			while ( bfsBack.hasNext() )
			{
				Airport temp = bfsBack.next();
				
				depth = bfsBack.getDepth(temp);
				
				if ( depth > 2 ) 
				{
					toRemoveAirports.add(temp);
					break;
				}
				
				if ( temp.equals(airport) ) break;
				
			}
			
		}
		
		System.out.println("airports remove final size: " + toRemoveAirports.size());
		
		
		Iterator<Airport> toRemoveIterator = toRemoveAirports.iterator();
		while (toRemoveIterator.hasNext())
		{
			Airport removed = toRemoveIterator.next();
			System.out.println(removed);
			System.out.println(dirConnectedAirports.contains(removed));
		}
		
		dirConnectedAirports.removeAll(toRemoveAirports);
		System.out.println("REMOVED ELEMENTS");
		
		toRemoveIterator = toRemoveAirports.iterator();
		while (toRemoveIterator.hasNext())
		{
			Airport removed = toRemoveIterator.next();
			System.out.println(removed);
			System.out.println(dirConnectedAirports.contains(removed));
		}
		
		System.out.println("airports set final size: " + dirConnectedAirports.size());
		
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

	
	
	


}
