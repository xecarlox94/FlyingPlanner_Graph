package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
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
	
	// to get the input data from the user
	private Scanner sc;

	@Override
	public boolean populate(FlightsReader fr) 
	{
		// it stores the airport string array set locally
		HashSet<String[]> airports = fr.getAirports();
		

		// it stores the airport string array set locally
		HashSet<String[]> flights = fr.getFlights();
		
		
		// overloading function
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
		
		// prints all the airports available
		this.printingAirports();
		

		// Initializes the scanner
		this.sc = new Scanner(System.in);
		
		// It queries the user about journeys data
		this.userQueries();
		
		// closes scanner
		this.sc.close();
		
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
		// assigns the local graph to the temporary graph variable
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
					try
					{

						// gets the airport code
						String airportCode = excluding.get(i);
						
						
						// gets the airport code
						Airport tempAirport = this.airport(airportCode);
						
						
						// if excluding airport was not found
						if ( tempAirport == null )
						{
							throw new FlyingPlannerException("Excluding airport not found: " + airportCode);
						}
						
						
						// gets all the edges of the temporary airport vertex
						Set<Flight> tempFlights = tempGraph.edgesOf(tempAirport);
						
						
						// removes all the flight set elements from the graph
						tempGraph.removeAllEdges(tempFlights);
						
						// removes the temporary airport vertex from the graph
						tempGraph.removeVertex(tempAirport);
					} 
					catch (FlyingPlannerException e)
					{
						// informing user that excluding airport was not found
						System.err.println(e);
					}
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
		
		
		// error handling
		if ( departureAirport == null )
		{
			// if departure was not found
			throw new FlyingPlannerException("Departure airport " + from + " not found!");
		} 
		else if ( destinationAirport == null)
		{
			// if destination was not found
			throw new FlyingPlannerException("Destination airport " + to + " not found!");
		}
		else if ( from == to)
		{
			// if both airports are the same
			throw new FlyingPlannerException("Departure and destination airports must be different!\n"
					+ "Departure: " + from + "\nDestination: " + to);
		}
		
		
		// gets the graph shortest path
		GraphPath<Airport, Flight> shortestPath = dijkstra.getPath(departureAirport, destinationAirport);


		// if the airports are not indirectly connected
		if ( shortestPath == null ) 
		{
			throw new FlyingPlannerException("Journey not found!");
		}
		
		// instantiates the journey object from the shortest path
		Journey journey = new Journey(shortestPath);
		

		// returns the object
		return journey;
	}


	@Override
	public Journey leastHop(String from, String to, List<String> excluding) throws FlyingPlannerException 
	{
		// assigns the local graph to the temporary graph variable
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
					try
					{

						// gets the airport code
						String airportCode = excluding.get(i);
						
						
						// gets the airport code
						Airport tempAirport = this.airport(airportCode);
						
						
						// if excluding airport was not found
						if ( tempAirport == null )
						{
							throw new FlyingPlannerException("Excluding airport not found: " + airportCode);
						}
						
						
						// gets all the edges of the temporary airport vertex
						Set<Flight> tempFlights = tempGraph.edgesOf(tempAirport);
						
						
						// removes all the flight set elements from the graph
						tempGraph.removeAllEdges(tempFlights);
						
						// removes the temporary airport vertex from the graph
						tempGraph.removeVertex(tempAirport);
					} 
					catch (FlyingPlannerException e)
					{
						// informing user that excluding airport was not found
						System.err.println(e);
					}
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
		

		// error handling
		if ( departureAirport == null )
		{
			// if departure was not found
			throw new FlyingPlannerException("Departure airport " + from + " not found!");
		} 
		else if ( destinationAirport == null)
		{
			// if destination was not found
			throw new FlyingPlannerException("Destination airport " + to + " not found!");
		}
		else if ( from == to)
		{
			// if both airports are the same
			throw new FlyingPlannerException("Departure and destination airports must be different!\n"
					+ "Departure: " + from + "\nDestination: " + to);
		}
		
		
		// gets the graph shortest path
		GraphPath<Airport, Flight> shortestPath = dijkstra.getPath(departureAirport, destinationAirport);


		// if the airports are not indirectly connected
		if ( shortestPath == null ) 
		{
			throw new FlyingPlannerException("Journey not found!");
		}

		
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
	public String leastCostMeetUp(String at1, String at2) throws FlyingPlannerException 
	{
		
		

		// airports should not be already connected
		
		
		
		// It gets the list with the union airports that are not completely directly connected
		List<String> except = this.airportExceptCodes(at1, at2);
		
		// Checks is the path was found
		boolean pathFound = false;
		
		// Stores the meet up airport
		String meetup = null;
		
		// it will loop while the path was not found
		while ( !pathFound )
		{
			// gets the first journey
			// avoiding certain airports contained in the except list
			Journey j1 = this.leastCost(at1, at2, except);
			
			// gets the stops list of first journey
			List<String> stps1 = j1.getStops();
			
			// removes the origin stop, from the first journey
			stps1.remove(0);
			// removes the destination stop, from the first journey
			stps1.remove(stps1.size() - 1);

			// gets the second journey (back)
			// avoiding certain airports contained in the except list
			Journey j2 = this.leastCost(at2, at1, except);

			// gets the stops list of first journey
			List<String> stps2 = j2.getStops();

			// removes the origin stop, from the second journey
			stps2.remove(0);
			// removes the destination stop, from the second journey
			stps2.remove(stps2.size() - 1);
			
			// loops though the first stop list
			for ( int i = 0; i < stps1.size(); i++)
			{
				// stores the String of each stop
				String temp = stps1.get(i);
				
				// checks if the second list of stops contains this stop string value
				if ( stps2.contains(temp) )
				{
					// assigns the meet up string with the current
					meetup = temp;
					
					// changes the path found to true to break the while loop
					pathFound = true;
					
					// breaks the inner loop
					break;
				}
				
			}
			
			
			if ( !pathFound ) 
			{
				// if path not found
				// remove the first stop of both journeys
				// by adding them to the except list
				except.add(stps1.get(0));
				except.add(stps2.get(0));
			}
			
		}

		return meetup;
	}

	@Override
	public String leastHopMeetUp(String at1, String at2) throws FlyingPlannerException 
	{
		

		
		

		// airports should not be already connected
		
		
		
		
		
		
		// It gets the list with the union airports that are not completely directly connected
		List<String> except = this.airportExceptCodes(at1, at2);
		
		// Checks is the path was found
		boolean pathFound = false;
		
		// Stores the meet up airport
		String meetup = null;
		
		// it will loop while the path was not found
		while ( !pathFound )
		{
			// gets the first journey
			// avoiding certain airports contained in the except list
			Journey j1 = this.leastHop(at1, at2, except);
			
			// gets the stops list of first journey
			List<String> stps1 = j1.getStops();
			
			// removes the origin stop, from the first journey
			stps1.remove(0);
			// removes the destination stop, from the first journey
			stps1.remove(stps1.size() - 1);

			// gets the second journey (back)
			// avoiding certain airports contained in the except list
			Journey j2 = this.leastHop(at2, at1, except);

			// gets the stops list of first journey
			List<String> stps2 = j2.getStops();

			// removes the origin stop, from the second journey
			stps2.remove(0);
			// removes the destination stop, from the second journey
			stps2.remove(stps2.size() - 1);
			
			// loops though the first stop list
			for ( int i = 0; i < stps1.size(); i++)
			{
				// stores the String of each stop
				String temp = stps1.get(i);
				
				// checks if the second list of stops contains this stop string value
				if ( stps2.contains(temp) )
				{
					// assigns the meet up string with the current
					meetup = temp;
					
					// changes the path found to true to break the while loop
					pathFound = true;
					
					// breaks the inner loop
					break;
				}
				
			}
			
			
			if ( !pathFound ) 
			{
				// if path not found
				// remove the first stop of both journeys
				// by adding them to the except list
				except.add(stps1.get(0));
				except.add(stps2.get(0));
			}
			
		}

		return meetup;
	}

	/**
	 * It gets the airport to initially remove from the shortest path search between airport 1 and airport 2 meetup
	 * */
	private List<String> airportExceptCodes(String at1, String at2)
	{
		// gets the airports non directly connected from airport 1
		Set<Airport> nonDirConnnected = this.nonDirConnected(at1);

		// gets the airports non directly connected from airport 2
		Set<Airport> tempNonDirCon = this.nonDirConnected(at2);

		// does the union of both sets of non directly connected sets from airport 1 and airport 2
		nonDirConnnected.addAll(tempNonDirCon);
		
		// retrieves an iterator from the non directed set
		Iterator<Airport> nonDirConnectedIterator = nonDirConnnected.iterator();
		
		// Initialises a new string list to hold the airport codes for set
		List<String> except = new LinkedList<String>();
		
		// iterates through the non directed iterator
		while ( nonDirConnectedIterator.hasNext() )
		{
			// stores the current airport
			Airport tempAirport = nonDirConnectedIterator.next();
			
			// stores its string code
			String airportCode = tempAirport.getCode();
			
			// adds the string code to the except string list
			except.add(airportCode);
		}
		
		// removes the strings codes from the airport 1 and airport 2
		except.remove(at1);
		except.remove(at2);
		
		return except;
	}
	
	
	/**
	 * It retrieves a set containing the airports connected, but not directly connect, to given airport 
	 * */
	private Set<Airport> nonDirConnected(String airportCode)
	{
		// gets the airport from its airport code
		Airport airport = this.airport(airportCode);
		
		
		
		
		// THROW ERROR!!!!!!!!!!!!!!!!
		
		
		

		// gets an Iterator holding the outgoing flights from this vertex
		Iterator<Flight> outFlights = this.graph.incomingEdgesOf(airport).iterator();
		
		// gets an Iterator holding the outgoing flights from this vertex
		Iterator<Flight> incFlights = this.graph.incomingEdgesOf(airport).iterator();
		
		// initialises a set to hold all non directly connected airports to given airport
		Set<Airport> nonDirConnected = new HashSet<Airport>();

		// iterates through the outgoing flights
		while ( outFlights.hasNext() )
		{
			// stores the current flight
			Flight tempFlight = outFlights.next();
			
			// gets the destination airport
			Airport tempAirport = tempFlight.getTo();
			
			// adds the airport to the non directly connected airports set
			nonDirConnected.add(tempAirport);
		}

		// iterates through the incoming flights
		while ( incFlights.hasNext() )
		{
			// stores the current flight
			Flight tempFlight = incFlights.next();
			
			// gets the origin airport
			Airport tempAirport = tempFlight.getFrom();
			
			// adds the airport to the non directly connected airports set
			nonDirConnected.add(tempAirport);
		}
		
		// gets a set containing the airports directly connected to the given airport
		Set<Airport> dirConnectedAirport = this.directlyConnected(airport);

		// removes all the directly connected airports from the set
		nonDirConnected.removeAll(dirConnectedAirport);

		// the set holds now the airports that are not connected 
		// in both ways with the airport
		return nonDirConnected;
	}
	
	/**
	 * It returns a new graph populated from the local graph data
	 * */
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
	


	@Override
	public Set<Airport> directlyConnected(Airport airport) 
	{
		// Initialises the BFS Algorithm with the local graph
		BreadthFirstIterator<Airport, Flight> bfs = new BreadthFirstIterator<Airport, Flight>(this.graph, airport);
		
		// stores the BFS depth
		int depth = 0;
		
		// Initialises a set to hold the connected airport (outgoing edges)
		HashSet<Airport> connectedAirports = new HashSet<Airport>();
		
		// iterating through the BFS traversal
		while ( bfs.hasNext() )
		{
			// stores the current airport
			Airport tempAirport = bfs.next();
			
			// gets the BFS depth
			depth = bfs.getDepth(tempAirport);
			
			// the continue keyword will ignore the first call
			// that gets the first element, the same airport
			if ( tempAirport.equals(airport) ) continue;

			// breaks the while loop if the depth is bigger than 1
			if ( depth > 1) break;

			// adds the current airport to the set
			connectedAirports.add(tempAirport);
		}
		
		// retrieves an airport iterator from the connected airport
		Iterator<Airport> airportsIterator = connectedAirports.iterator();
		
		// Initialises a set holding airports to remove
		HashSet<Airport> toRemoveAirports = new HashSet<Airport>();
		
		// Declares the BFS from the connected airports
		BreadthFirstIterator<Airport, Flight> bfsBack;
		
		// iterates through the airports iterator
		while ( airportsIterator.hasNext() )
		{
			// stores the current airport
			Airport tempAirport = airportsIterator.next();
			
			// Initialises a BFS from the temporary airport
			bfsBack = new BreadthFirstIterator<Airport, Flight>(this.graph, tempAirport);

			// changes the depth to zero, for the beginning of a new BFS traversal
			depth = 0;
			
			// Iterates through the BFS
			while ( bfsBack.hasNext() )
			{
				// stores the current airport, in the BFS
				Airport temp = bfsBack.next();
				
				// updates the depth
				depth = bfsBack.getDepth(temp);
				
				// if the depth is greater than 1
				// the temporary airport is not directly connected to the original airport
				if ( depth > 1 ) 
				{
					// adds the current airport to the remove set
					toRemoveAirports.add(temp);
					// stops the iteration
					break;
				}
				
				// if the given airport is found
				// stop the iteration
				if ( temp.equals(airport) ) break;
				
			}
			
		}
		
		// remove the non directed airports from all connected airports to the original
		connectedAirports.removeAll(toRemoveAirports);
		
		return connectedAirports;
	}


	@Override
	public int setDirectlyConnected() 
	{
		// gets the graph vertex iterator
		Iterator<Airport> airportIterator = this.graph.vertexSet().iterator();
		
		// initialises the total integer variable
		int total = 0;
		
		// iterates through the vertices
		while ( airportIterator.hasNext() )
		{
			// gets the current airport
			Airport tempAirport = airportIterator.next();
			
			// gets the directly connected airport set
			// of the given airport
			Set<Airport> tempSet = this.directlyConnected(tempAirport);
			
			// adds the set size to the total variable
			total += tempSet.size();
		}
		
		return total;
	}
	
	/**
	 * It prints all the airports available to the user, before doing the queries
	 * */
	private void printingAirports() 
	{
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
	}
	
	/**
	 * It does all the necessary queries to the user
	 * */
	private void userQueries() 
	{
		// asks for the journey
		this.queryingLeastCostJourney();
	}
	
	/**
	 * Queries user about journey between two airports
	 * */
	private void queryingLeastCostJourney() 
	{
		// Stores the journey to be printed
		Journey journey = null;
		
		// makes sure the airports are found, otherwise asks user again
		boolean inputValid = false;
		
		// origin airport temporarily to null
		Airport originAirport = null;
		
		// departure airport temporarily to null
		Airport departureAirport = null;
		
		
		System.out.println();
		
		while ( !inputValid )
		{
			try
			{

				
				// questions the origin airport
	    		System.out.println("Please enter the start airport code");
	    		
	    		// stores the origin airport location string
	    		String originCode = sc.nextLine();
	    		
	    		System.out.println("Please enter the destination airport code");
	    		
	    		// stores the departure airport location string
	    		String destinationCode = sc.nextLine();

	    		journey = this.leastCost(originCode, destinationCode);
	    		
	    		inputValid = true;
			} 
			catch (FlyingPlannerException e)
			{
				// printing error
				System.err.println(e);
				
				// input valid is assured to be false
				// to query the user again
				inputValid = false;
			}
		}
		
		
		this.printJourney(journey);
		
	}
	
	/**
	 * It prints a journey between two airports
	 * */
	private void printJourney(Journey journey) 
	{



//		System.out.println("\n\nFlights: ");
//		List<String> flights = journey.getFlights();
//		for(int i =0; i < flights.size(); i++)
//		{
//			System.out.println(flights.get(i));
//		}
//		
//		System.out.println("\n\nStops: ");
//		List<String> stops = journey.getStops();
//		for(int i =0; i < stops.size(); i++)
//		{
//			System.out.println(stops.get(i));
//		}
		
		
		System.out.println("\nTotal cost: " + journey.totalCost());
		System.out.println("\nTotal hop: " + journey.totalHop());
		System.out.println("\nTotal air time: " + journey.airTime());
		System.out.println("\nTotal con time: " + journey.connectingTime());
		System.out.println("\nTotal tot time: " + journey.totalTime());
		
		
		System.out.println("Shortest ( i . e . cheapest ) path :");
		
//		for(int i = 0; i < pathFlights.size(); i++)
//		{
//			Flight flight = pathFlights.get(i);
//			
//			Airport origin = flight.getFrom();
//			
//			Airport destination = flight.getTo();
//			
//			System.out.println(( i + 1 ) + " " + origin.toString() + " -> " + destination.toString());
//		}
//		
//		//IMPROVE PRINTING
//		System.out.println("Cost of shortest ( i . e . cheapest ) path = £" + path.getWeight() + " pounds");
		
	}

	@Override
	public String leastTimeMeetUp(String at1, String at2, String startTime) throws FlyingPlannerException 
	{
		

		
		

		// airports should not be already connected
		
		
		
		
		
		// TODO Auto-generated method stub
		return null;
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

}
