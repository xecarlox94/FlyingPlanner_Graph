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
		
		// initialises the total air time minutes to zero
		int airTimeTotalMin = 0;
		
		// iterates through the flight set
		for(int i = 0; i < flightsList.size(); i++)
		{
			// gets each flight
			Flight tempFlight = flightsList.get(i);
			
			// gets its air time
			int airTimeMin = this.getAirTimeMinFlight(tempFlight);
			
			// adds each air time to the total air time
			airTimeTotalMin += airTimeMin;
		}

		return airTimeTotalMin;
	}

	@Override
	public int connectingTime() 
	{
		// getting flight objects list from graph path 
		List<Flight> flightsList  = this.graphPath.getEdgeList();
		
		// stores the previous flight
		Flight previousFlight;
		
		// stores the next flight
		Flight nextFlight;
		
		// initialises the total connecting time between all the flights
		// and assigns 0 minutes to it
		int connectTimeTotalMin = 0;
		
		// iterates through the flight list
		for(int i = 0; i < flightsList.size() - 1; i++)
		{
			// gets the current flight
			previousFlight = flightsList.get(i);

			// gets the following flight 
			nextFlight = flightsList.get(i + 1);
			
			// gets the connecting time between the two flights
			int tConnectTimeMin = this.getConnectionTimeMinFlights(previousFlight, nextFlight);
			
			// adds the connecting time to the total connecting time
			connectTimeTotalMin += tConnectTimeMin;
		}
		
		return connectTimeTotalMin;
	}

	@Override
	public int totalTime() 
	{
		// gets the journey total air time
		int airTime = this.airTime();
		
		// gets the journey total connection time
		int connectionTime = this.connectingTime();
		
		// adds the connection and the air time
		// to calculate the total journey time
		int totalTime = airTime + connectionTime;
		
		return totalTime;
	}
	
	
	/**
	 * It gets the air time of each flight
	 * */
	private int getAirTimeMinFlight(Flight flight)
	{
		// store the departure time string
		String departureTime = flight.getFromGMTime();
		
		// stores the arrival time string
		String arrivalTime = flight.getToGMTime();
		
		// gets the minutes resultant from the subtraction
		// of arrival minus the departure time
		int minutesAirTime = this.getMinutesSubStr(arrivalTime, departureTime);
		
		return minutesAirTime;
	}
	
	/**
	 * It gets the connection time between two successive flights
	 * */
	private int getConnectionTimeMinFlights(Flight previousFlight, Flight nextFlight)
	{
		// gets the arrival time of the previous flight
		String arrivalTime = previousFlight.getToGMTime();
		
		// gets the departure time of the next flight
		String departureTime = nextFlight.getFromGMTime();

		// gets the minutes resultant from the subtraction
		// of next flight departure time minus the previous flight arrival
		int minutesConnectionTime = this.getMinutesSubStr(departureTime, arrivalTime);
		
		return minutesConnectionTime;
	}

	/**
	 * It subtracts two times in string format
	 * */
	private int getMinutesSubStr(String time1, String time2)
	{
		// gets the first string total decimal full time in float format
		float decTime1 = Time.getDecFullTime(time1);

		// gets the second string total decimal full time in float format
		float decTime2 = Time.getDecFullTime(time2);
		
		// subtracts two decimal float time values
		// and stores its float decimal result in a variable
		float decTotalTime = Time.subDecTimes(decTime1, decTime2);
		
		// gets the integer total minutes 
		// resultant from the total decimal time in float format 
		int totalMinutes = Time.getMinutes(decTotalTime);
		
		return totalMinutes;
	}


}
