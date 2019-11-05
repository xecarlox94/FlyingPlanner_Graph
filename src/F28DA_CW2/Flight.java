package F28DA_CW2;

public class Flight implements IFlight {
	
	// flight code
	private String flightCode;

	// departure GM time
	private String fromGMTime;
	
	// arrival GM time
	private String toGMTime;
	
	// airport of destination
	private String to;
	
	// airport of departure
	private String from;
	
	// flight cost
	private int cost;
	
	
	// constructor
	public Flight(String flightCode, String fromGMTime, String from, String toGMTime, String to, int price)
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
	public Airport getTo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airport getFrom() {
		// TODO Auto-generated method stub
		return null;
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


}
