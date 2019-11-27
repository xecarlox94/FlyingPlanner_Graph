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
	public String toString() {
		// TODO Auto-generated method stub
		return "Flight code: " + this.getFlightCode() + ",	departure time: " + this.getFromGMTime() + " ,	from: " + this.getFrom().toString() + ",	to: " + this.getTo().toString() + ",	arrival time: " + this.getToGMTime() + ",	price: " + this.getCost();
	}

	

}
