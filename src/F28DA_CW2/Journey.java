package F28DA_CW2;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.GraphPath;

public class Journey implements IJourneyPartB<Airport, Flight>, IJourneyPartC<Airport, Flight> 
{
	
	// stores the graph path locally 
	private GraphPath<Airport,Flight> graphPath;
	
	public Journey(GraphPath<Airport,Flight> graphPath)
	{
		// initializes the local graph path
		this.graphPath = graphPath;
	}
	
	

	@Override
	public List<String> getStops() 
	{
		// getting airport objects list from graph path 
		List<Airport> airportList  = this.graphPath.getVertexList();
		
		// initialising airport code string list
		LinkedList<String> airportsCodeList = new LinkedList<String>();
		
		// iterating through the airport object list
		for(int i = 0; i < airportList.size(); i++)
		{
			// storing the temporary airport variable
			Airport tempAirport = airportList.get(i);
			
			// stores the airport code
			String airportCode = tempAirport.getCode();
			
			// adds the airport code 
			airportsCodeList.add(airportCode);
		}
		
		// return the airport code list
		return airportsCodeList;
	}

	@Override
	public List<String> getFlights() 
	{
		// getting flight objects list from graph path 
		List<Flight> flightsList  = this.graphPath.getEdgeList();
		
		// initialising flight string list
		LinkedList<String> flightsCodeList = new LinkedList<String>();
		
		// iterating through the flight object list
		for(int i = 0; i < flightsList.size(); i++)
		{
			// storing the temporary flight variable
			Flight tempFlight = flightsList.get(i);
			
			// stores the flight code
			String flightCode = tempFlight.getFlightCode();
			
			// adds the flight code 
			flightsCodeList.add(flightCode);
		}

		// return flight code string list
		return flightsCodeList;
	}

	@Override
	public int totalHop() 
	{
		// gets the airports list (stops)
		List<Airport> airports = this.graphPath.getVertexList();
		
		// number of airport hops
		int numberHops = airports.size() - 1;
		
		return numberHops;
	}

	@Override
	public int totalCost() 
	{
		// getting flight objects list from graph path 
		List<Flight> flightsList = this.graphPath.getEdgeList();
		
		// initialises total cost accumulator
		int totalCost = 0;
		
		// loops through each flight in list
		for( int i = 0; i < flightsList.size(); i++ )
		{
			// stores the index flight
			Flight tempFlight = flightsList.get(i);

			// accumulates the fight cost into the total cost
			totalCost += tempFlight.getCost();
		}
		
		
		return totalCost;
	}

	@Override
	public int airTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int connectingTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	private int decTime(String stringTime) 
	{
		return 0;
	}

}
