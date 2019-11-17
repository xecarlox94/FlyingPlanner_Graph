package F28DA_CW2;

import java.util.List;

import org.jgrapht.GraphPath;

public class Journey implements IJourneyPartB<Airport, Flight>, IJourneyPartC<Airport, Flight> {
	
	// stores the graph path locally 
	private GraphPath<Airport,Flight> graphPath;
	
	public Journey(GraphPath<Airport,Flight> graphPath)
	{
		// initializes the local graph path
		this.graphPath = graphPath;
	}
	
	public Journey()
	{
		// DELETEEEE
	}
	

	@Override
	public List<String> getStops() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getFlights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int totalHop() {
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
