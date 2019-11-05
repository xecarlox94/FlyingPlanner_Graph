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

	@Override
	public void setDicrectlyConnected(Set<Airport> dicrectlyConnected) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Airport> getDicrectlyConnected() {
		// TODO Auto-generated method stub
		return null;
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

}
