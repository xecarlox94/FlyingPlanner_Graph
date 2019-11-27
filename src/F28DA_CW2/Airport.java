package F28DA_CW2;

import java.util.Set;

public class Airport implements IAirportPartB, IAirportPartC 
{
	// unique code
	private String code;
	
	// airport name
	private String name;
	
	// set containing directed connected airports
	private Set<Airport> directlyConnected;
	
	// order directly connected
	private int order;
	
	// constructor
	public Airport(String code, String name)
	{
		this.code = code;
		this.name = name;
	}
	
	
	@Override
	public String getCode() 
	{
		// code getter
		return this.code;
	}

	@Override
	public String getName() 
	{
		// name getter
		return this.name;
	}


	@Override
	public void setDicrectlyConnectedOrder(int order) 
	{
		this.order = order;
	}

	@Override
	public int getDirectlyConnectedOrder() 
	{
		return this.order;
	}


	@Override
	public String toString() {
		return "airport code: " + this.code + ",	name: " + this.getName();
	}


	@Override
	public Set<Airport> getDicrectlyConnected() 
	{
		return this.directlyConnected;
	}


	@Override
	public void setDicrectlyConnected(Set<Airport> dicrectlyConnected) 
	{
		this.directlyConnected = dicrectlyConnected;
	}
	

}
