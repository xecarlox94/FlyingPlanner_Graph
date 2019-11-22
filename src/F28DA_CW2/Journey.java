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
//			
//			// stores the flight code
//			String flightCode = tempFlight.getFlightCode();
			
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
		
		
		
		for(int i = 0; i < flightsList.size(); i++)
		{
			
		}
		
		return 0;
	}

	@Override
	public int connectingTime() 
	{
		// getting flight objects list from graph path 
		List<Flight> flightsList  = this.graphPath.getEdgeList();
		
		for(int i = 0; i < flightsList.size(); i++)
		{
			
		}
		
		return 0;
	}

	@Override
	public int totalTime() 
	{
		int airTime = this.airTime();
		
		int connectionTime = this.connectingTime();
		
		int totalTime = airTime + connectionTime;
		
		return totalTime;
	}
	
	
	private int getMinutes(float time)
	{
		
		// get minutes from float (hours plus minutes)
		int hours = 0;
		
		while( time >= 1f )
		{
			time -= 1;
			
			hours++;
		}
		
		System.out.println("FINAL HOURS: "+ hours);

		
		int finalMinutes = 0;
		
		
		if (time != 0) 
		{
			time = time * 60;
			
			int remainingMinutes = (int) time;
			
			finalMinutes += remainingMinutes;
			
		}

		finalMinutes += hours * 60;
		
		return finalMinutes;
	}
	
	private int getAirTimeFlight(Flight flight)
	{
		
		String departure;
		
		String arrival;
		
		
		
		return 0;
	}
	
	private int getMinutesSumStr(String time1, String time2)
	{
		float decTime1 = this.getDecFullTime(time1);
		
		float decTime2 = this.getDecFullTime(time2);
		
		float decTotalTime = this.addDecTimes(decTime1, decTime2);
		
		int totalMinutes = this.getMinutes(decTotalTime);
		
		return totalMinutes;
	}
	
	private float addDecTimes(float time1, float time2)
	{
		// initialise the float result
		// if times the same it will not change the value
		float result = 0f;
		
		result = time1 + time2;

		// ensure result is within the clock bounds
		result %= 24f;
		
		return result;
	}
	
	private float subDecTimes(float time1, float time2)
	{
		// initialise the float result
		// if times the same it will not change the value
		float result = 0f;
		
		
		if (time1 == time2)
		{
			// returns initial result
			return result;
		} 
		else if (time1 < time2)
		{
			// adding 24 hours because it is the next day
			time1 += 24f;
		}

		// normal subtraction
		result = time1 - time2;

		// ensure result is within the clock bounds
		result %= 24f;
		
		return result;
	}
	
	
	private float getDecFullTime(String stringTime) 
	{
		// assigning hours time
		Float finalDecTime = this.getDecHrs(stringTime);
		
		// adding the minutes time to full time
		finalDecTime += this.getDecMin(stringTime);
		
		return finalDecTime;
	}
	

	private float getDecHrs(String stringTime)
	{
		// all sting have length 4
		// the first 2 characters are reserved for hours
		String hoursString = stringTime.substring(0, 2);
		
		// parses the substring containing the hours segment to float
		float hours = Float.parseFloat(hoursString);
		
		return hours;
	}
	
	
	private float getDecMin(String stringTime)
	{
		// all sting have length 4
		// the last 2 characters are reserved for minutes
		String minsString = stringTime.substring(2, 4);
		
		// parses the substring containing the minutes segment to float
		float mins = Float.parseFloat(minsString);
		
		// convert the time to decimal minutes
		mins = ( mins / 60f );
		
		return mins;
	}


}
