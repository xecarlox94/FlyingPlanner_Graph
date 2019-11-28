package F28DA_CW2;

public class Flight implements IFlight {
	
	// flight code
	private String flightCode;

	// departure GM time
	private String fromGMTime;
	
	// arrival GM time
	private String toGMTime;
	
	// airport of destination
	private Airport to;
	
	// airport of departure
	private Airport from;
	
	// flight cost
	private int cost;
	
	
	// constructor
	public Flight(String flightCode, String fromGMTime, Airport from, String toGMTime, Airport to, int cost)
	{
		this.flightCode = flightCode;
		this.fromGMTime = fromGMTime;
		this.from = from;
		this.toGMTime = toGMTime;
		this.to = to;
		this.cost = cost;
	}
	

	@Override
	public String getFlightCode() 
	{
		// returns the flight code
		return this.flightCode;
	}

	@Override
	public Airport getTo() 
	{
		// returns destination airport
		return this.to;
	}
	
	public void setTo(Airport airport)
	{
		// sets the destination airport for this flight
		this.to = airport;
	}
	
	public void setFrom(Airport airport)
	{
		// sets the origin airport for this flight
		this.from = airport;
	}
	

	@Override
	public Airport getFrom()
	{
		// returns origin airport
		return this.from;
	}

	@Override
	public String getFromGMTime() 
	{
		// returns departure GM time
		return this.fromGMTime;
	}

	@Override
	public String getToGMTime() 
	{
		// returns arrival GM time
		return this.toGMTime;
	}

	@Override
	public int getCost() 
	{
		// returns the flight cost
		return this.cost;
	}


	@Override
	public String toString() 
	{

		Airport originAirport = this.getFrom();
		Airport destinationAirport = this.getTo();

		String originAptStr = String.format("%-20s", originAirport.toString() );
		String fromTimeStr = String.format("%-6s", this.fromGMTime );
		String flightCodeStr = String.format("%-8s", this.flightCode );
		String destinationAptStr = String.format("%-20s", destinationAirport.toString() );
		String toTimeStr = String.format("%-6s", this.toGMTime );
        
//        String cityStr = String.format("%-27s", this.city );
//        String popGrowthStr = String.format("%-7s", this.popGrowth );
//        String latStr = String.format("%-5s", this.lat );
//        String lonStr = String.format("%-7s", this.lon );
//        String popStr = String.format("%-7s", this.population );
//        String rankStr = String.format("%-4s", this.rank );
//        String stateStr = String.format("%-20s", this.state );

		return originAptStr + fromTimeStr + flightCodeStr + destinationAptStr  + toTimeStr;
	}

	

}
