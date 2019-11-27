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
		// initialises the local graph path
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
			
			// adds the flight code 
			flightsCodeList.add(tempFlight.toString());
		}

		// return flight code string list
		return flightsCodeList;
	}

	@Override
	public int totalHop() 
	{
		// getting flight objects list from graph path 
		List<Flight> flightsList = this.graphPath.getEdgeList();
		
		// number of flights which is the same as hops
		int numberHops = flightsList.size();
		
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
	public int airTime() 
	{
		// getting flight objects list from graph path 
		List<Flight> flightsList  = this.graphPath.getEdgeList();
		
		int airTimeTotalMin = 0;
		
		for(int i = 0; i < flightsList.size(); i++)
		{
			Flight tempFlight = flightsList.get(i);
			
			int airTimeMin = this.getAirTimeMinFlight(tempFlight);
			
			airTimeTotalMin += airTimeMin;
		}

		
		return airTimeTotalMin;
	}

	@Override
	public int connectingTime() 
	{
		// getting flight objects list from graph path 
		List<Flight> flightsList  = this.graphPath.getEdgeList();
		
		Flight previousFlight;
		
		Flight nextFlight;
		
		int connectTimeTotalMin = 0;
		
		for(int i = 0; i < flightsList.size() - 1; i++)
		{
			previousFlight = flightsList.get(i);

			nextFlight = flightsList.get(i + 1);
			
			int tConnectTimeMin = this.getConnectionTimeMinFlights(previousFlight, nextFlight);
			
			previousFlight = nextFlight;
			
			connectTimeTotalMin += tConnectTimeMin;
		}
		
		return connectTimeTotalMin;
	}

	@Override
	public int totalTime() 
	{
		int airTime = this.airTime();
		
		int connectionTime = this.connectingTime();
		
		int totalTime = airTime + connectionTime;
		
		return totalTime;
	}
	
	private int getAirTimeMinFlight(Flight flight)
	{
		
		String departureTime = flight.getFromGMTime();
		
		String arrivalTime = flight.getToGMTime();
		
		int minutesAirTime = this.getMinutesSubStr(arrivalTime, departureTime);
		
		return minutesAirTime;
	}
	
	
	private int getConnectionTimeMinFlights(Flight previousFlight, Flight nextFlight)
	{
		String arrivalTime = previousFlight.getToGMTime();
		
		String departureTime = nextFlight.getFromGMTime();

		int minutesConnectionTime = this.getMinutesSubStr(departureTime, arrivalTime);
		
		return minutesConnectionTime;
	}

	private int getMinutesSubStr(String time1, String time2)
	{
		float decTime1 = Time.getDecFullTime(time1);
		
		float decTime2 = Time.getDecFullTime(time2);
		
		float decTotalTime = Time.subDecTimes(decTime1, decTime2);
		
		int totalMinutes = Time.getMinutes(decTotalTime);
		
		return totalMinutes;
	}


}
