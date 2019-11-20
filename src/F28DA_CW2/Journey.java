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
		
		// initialising airport string list
		LinkedList<String> airportsStringList = new LinkedList<String>();
		
		// iterating through the airport object list
		for(int i = 0; i < airportList.size(); i++)
		{
			// storing the temporary airport variable
			Airport tempFlight = airportList.get(i);
			
			// stores the airport code
			String flightCode = tempFlight.getCode();
			
			// adds the airport code 
			airportsStringList.add(flightCode);
		}
				
		return airportsStringList;
	}

	@Override
	public List<String> getFlights() 
	{
		// getting flight objects list from graph path 
		List<Flight> flightsList  = this.graphPath.getEdgeList();
		
		// initialising flight string list
		LinkedList<String> flightsStringList = new LinkedList<String>();
		
		// iterating through the flight object list
		for(int i = 0; i < flightsList.size(); i++)
		{
			// storing the temporary flight variable
			Flight tempFlight = flightsList.get(i);
			
			// stores the flight code
			String flightCode = tempFlight.getFlightCode();
			
			// adds the flight code 
			flightsStringList.add(flightCode);
		}
				
		return flightsStringList;
	}

	@Override
	public int totalHop() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalCost() {
		// TODO Auto-generated method stub
		return 0;
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

}
