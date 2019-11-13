package F28DA_CW2;

import java.util.Set;

public class Airport implements IAirportPartB, IAirportPartC 
{
	// unique code
	private String code;
	
	// airport name
	private String name;
	
	// city or location name
	private String location;
	
	// stores the airports with common edges
	private Set<Airport> directlyConnected;
	
	
	// constructor
	public Airport(String code, String name, String location)
	{
		this.code = code;
		this.name = name;
		this.location = location;
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
	
	public String getLocation()
	{
		// it returns the airport location
		return this.location;
	}

	@Override
	public void setDicrectlyConnected(Set<Airport> directlyConnected) 
	{
		this.directlyConnected = directlyConnected;
	}

	@Override
	public Set<Airport> getDicrectlyConnected() 
	{
		return this.directlyConnected;
	}


	@Override
	public void setDicrectlyConnectedOrder(int order) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getDirectlyConnectedOrder() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String toString() {
		return "Airport [code=" + code + ", name=" + name + ", location=" + location + ", directlyConnected="
				+ directlyConnected + ", toString()=" + super.toString() + "]";
	}
	
	

}
