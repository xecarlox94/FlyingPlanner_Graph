package F28DA_CW2;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
		Graph<Airport, Flight> tempGraph = this.graph;
		
		// if excluding list is not full modify the graph
		if ( excluding != null) 
		{
			if (excluding.size() > 0)
			{
				// populating a temporary graph with the current graph data
				tempGraph = this.populateTempGraph();
				
				// for each string in the excluding list
				for ( int i = 0; i < excluding.size(); i++)
				{
					
					// gets the airport code
					String airportCode = excluding.get(i);
					
					
					// gets the airport code
					Airport tempAirport = this.airport(airportCode);
					
					
					// gets all the edges of the temporary airport vertex
					Set<Flight> tempFlights = tempGraph.edgesOf(tempAirport);
					
					
					// removes all the flight set elements from the graph
					tempGraph.removeAllEdges(tempFlights);
					
					// removes the temporary airport vertex from the graph
					tempGraph.removeVertex(tempAirport);
				}
			}
		}
		
		
		// gets a edge set from the graph
		// and returns its iterator
		Iterator<Flight> flights = tempGraph.edgeSet().iterator();
		
		
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
		DijkstraShortestPath<Airport,Flight> dijkstra = new DijkstraShortestPath<Airport,Flight>(tempGraph);
		
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
		Graph<Airport, Flight> tempGraph = this.graph;
		
		// if excluding list is not full modify the graph
		if ( excluding != null) 
		{
			if (excluding.size() > 0)
			{
				// populating a temporary graph with the current graph data
				tempGraph = this.populateTempGraph();
				
				// for each string in the excluding list
				for ( int i = 0; i < excluding.size(); i++)
				{
					
					// gets the airport code
					String airportCode = excluding.get(i);
					
					
					// gets the airport code
					Airport tempAirport = this.airport(airportCode);
					
					
					// gets all the edges of the temporary airport vertex
					Set<Flight> tempFlights = tempGraph.edgesOf(tempAirport);
					
					
					// removes all the flight set elements from the graph
					tempGraph.removeAllEdges(tempFlights);
					
					// removes the temporary airport vertex from the graph
					tempGraph.removeVertex(tempAirport);
				}
			}
		}
		
		
		// gets a edge set from the graph
		// and returns its iterator
		Iterator<Flight> flights = tempGraph.edgeSet().iterator();
		
		
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
		DijkstraShortestPath<Airport,Flight> dijkstra = new DijkstraShortestPath<Airport,Flight>(tempGraph);
		
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

			if ( depth > 1) break;

			dirConnectedAirports.add(tempAirport);
		}
		
		
		Iterator<Airport> airportsIterator = dirConnectedAirports.iterator();
		
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
				
				if ( depth > 1 ) 
				{
					toRemoveAirports.add(temp);
					break;
				}
				
				if ( temp.equals(airport) ) break;
				
			}
			
		}
		
		dirConnectedAirports.removeAll(toRemoveAirports);
		
		return dirConnectedAirports;
	}


	@Override
	public int setDirectlyConnected() 
	{
		
		Iterator<Airport> airportIterator = this.graph.vertexSet().iterator();
		
		int total = 0;
		
		while ( airportIterator.hasNext() )
		{
			Airport tempAirport = airportIterator.next();
			
			Set<Airport> tempSet = this.directlyConnected(tempAirport);
			
			total += tempSet.size();
		}
		
		return total;
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
	public String leastCostMeetUp(String at1, String at2) throws FlyingPlannerException 
	{
		
		List<String> except = this.airportExceptCodes(at1, at2);
		

		System.out.println();
		
		boolean pathFound = false;
		
		String meetup = null;
		
		
		while ( !pathFound )
		{
			
			System.out.println("Loop");
			
			Journey j1 = this.leastCost(at1, at2, except);
			
			List<String> stps1 = j1.getStops();
			
			stps1.remove(0);
			stps1.remove(stps1.size() - 1);

			Journey j2 = this.leastCost(at2, at1, except);
			
			List<String> stps2 = j2.getStops();

			stps2.remove(0);
			stps2.remove(stps2.size() - 1);
			
			for ( int i = 0; i < stps1.size(); i++)
			{
				String temp = stps1.get(i);
				
				if ( stps2.contains(temp) )
				{
					meetup = temp;
					pathFound = true;
				}
				
			}
			
			
			if ( !pathFound ) 
			{
				except.add(stps1.get(0));
				except.add(stps2.get(0));
				
				for(int i = 0; i < except.size(); i++) System.out.println(except.get(i));
			}
			
		}

		return meetup;
	}

	@Override
	public String leastHopMeetUp(String at1, String at2) throws FlyingPlannerException 
	{
		
		List<String> except = this.airportExceptCodes(at1, at2);
		

		System.out.println();
		
		boolean pathFound = false;
		
		String meetup = null;
		
		
		while ( !pathFound )
		{
			
			System.out.println("Loop");
			
			Journey j1 = this.leastCost(at1, at2, except);
			
			List<String> stps1 = j1.getStops();
			
			stps1.remove(0);
			stps1.remove(stps1.size() - 1);

			Journey j2 = this.leastCost(at2, at1, except);
			
			List<String> stps2 = j2.getStops();

			stps2.remove(0);
			stps2.remove(stps2.size() - 1);
			
			for ( int i = 0; i < stps1.size(); i++)
			{
				String temp = stps1.get(i);
				
				if ( stps2.contains(temp) )
				{
					meetup = temp;
					pathFound = true;
				}
				
			}
			
			
			if ( !pathFound ) 
			{
				except.add(stps1.get(0));
				except.add(stps2.get(0));
				
				for(int i = 0; i < except.size(); i++) System.out.println(except.get(i));
			}
			
		}

		return meetup;
	}

	@Override
	public String leastTimeMeetUp(String at1, String at2, String startTime) throws FlyingPlannerException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	private List<String> airportExceptCodes(String at1, String at2)
	{

		Set<Airport> nonDirConnnected = this.nonDirConnected(at1);
		
		Set<Airport> tempNonDirCon = this.nonDirConnected(at2);

		
		
		nonDirConnnected.addAll(tempNonDirCon);
		
		Iterator<Airport> nonDirConnectedIterator = nonDirConnnected.iterator();
		
		List<String> except = new LinkedList<String>();
		
		while ( nonDirConnectedIterator.hasNext() )
		{
			Airport tempAirport = nonDirConnectedIterator.next();
			
			String airportCode = tempAirport.getCode();
			
			except.add(airportCode);
		}
		

		except.remove(at1);
		except.remove(at2);
		
		return except;
	}
	
	private Set<Airport> nonDirConnected(String airportCode)
	{
		Airport airport = this.airport(airportCode);

		Iterator<Flight> outFlights = this.graph.incomingEdgesOf(airport).iterator();
		Iterator<Flight> incFlights = this.graph.incomingEdgesOf(airport).iterator();
		
		Set<Airport> connectedAirport = this.directlyConnected(airport);

		while ( outFlights.hasNext() )
		{
			Flight tempFlight = outFlights.next();
			Airport tempAirport = tempFlight.getTo();
			connectedAirport.add(tempAirport);
		}
		
		while ( incFlights.hasNext() )
		{
			Flight tempFlight = incFlights.next();
			Airport tempAirport = tempFlight.getFrom();
			connectedAirport.add(tempAirport);
		}
		
		Set<Airport> dirConnectedAirport = this.directlyConnected(airport);

		connectedAirport.removeAll(dirConnectedAirport);
		
		return connectedAirport;
	}
	
	
	private SimpleDirectedWeightedGraph<Airport, Flight> populateTempGraph()
	{

		// retrieves the graph airport iterator
		Iterator<Airport> airportIterator = this.graph.vertexSet().iterator();

		// retrieves the graph flights iterator
		Iterator<Flight> flightsIterator = this.graph.edgeSet().iterator();
		
		// creates a new temporary graph
		SimpleDirectedWeightedGraph<Airport, Flight> tempGraph = new SimpleDirectedWeightedGraph<Airport,Flight>(Flight.class);

		
		// iterates through the the airport iterator
		while (airportIterator.hasNext())
		{
			// stores an airport from the iterator
			Airport tempAirport = airportIterator.next();
			
			// adds the airport as a graph vertex
			tempGraph.addVertex(tempAirport);
		}
		

		// iterates through the the flight iterator
		while (flightsIterator.hasNext())
		{		
			
			// it creates the new flight variable
			Flight tempFlight = flightsIterator.next();
			
			// it retrieves the corresponding origin airport
			Airport originAirport = tempFlight.getFrom();

			
			// it retrieves the corresponding departure airport
			Airport destinationAirport = tempFlight.getTo();

			
			// it adds the flight as a graph edge
			tempGraph.addEdge(originAirport, destinationAirport, tempFlight);
			
		}
		
		return tempGraph;
	}


}
