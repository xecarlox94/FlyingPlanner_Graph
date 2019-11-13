package F28DA_CW2;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jgrapht.Graph;

public class FlyingPlanner implements IFlyingPlannerPartB<Airport,Flight>, IFlyingPlannerPartC<Airport,Flight> {
	
	private Graph<Airport, Flight> graph;

	@Override
	public boolean populate(FlightsReader fr) 
	{
		HashSet<String[]> airports = fr.getAirports();
		
		HashSet<String[]> flights = fr.getFlights();
		
		return this.populate(airports, flights);
	}

	@Override
	public boolean populate(HashSet<String[]> airports, HashSet<String[]> flights) 
	{

		HashSet<Airport> airportsSet = new HashSet<Airport>();
		
		
		for( String[] airport: airports)
		{
			Airport temp = new Airport(airport[0], airport[2], airport[1]);
			airportsSet.add(temp);
		}

		Iterator<Airport> airportsIterator = airportsSet.iterator();
		
		
		Hashtable<String, Airport> airportHashTable = new Hashtable<String, Airport>();
		while(airportsIterator.hasNext())
		{
			Airport tempAirport = airportsIterator.next();
			
			this.graph.addVertex(tempAirport);

			airportHashTable.put(tempAirport.getCode(), tempAirport);
		}
		
		
		for( String[] flight: flights )
		{

			String originCode = flight[1];
			String destinationCode = flight[3];
			
			Airport originAirport = airportHashTable.get(originCode);
			Airport destinationAirport = airportHashTable.get(destinationCode);
			
			int price = Integer.parseInt(flight[5]);
			
			Flight newFlight = new Flight(flight[0],flight[1],originAirport,flight[3],destinationAirport,price);
			
			this.graph.addEdge(originAirport, destinationAirport, newFlight);
			
		}
		
		return true;
	}

	@Override
	public Airport airport(String code) 
	{
		Set<Airport> airportSet = this.graph.vertexSet();
		
		Iterator<Airport> airportIterator = airportSet.stream().iterator();
		
		while(airportIterator.hasNext())
		{
			Airport tempAirport = airportIterator.next();
			
			String codeAirport = tempAirport.getCode();
			
			if( code.equals(codeAirport))
			{
				return tempAirport;
			}
		}
		
		return null;
	}

	@Override
	public Flight flight(String code) 
	{
		Set<Flight> flightSet = this.graph.edgeSet();
		
		Iterator<Flight> flightIterator = flightSet.iterator();
		
		while(flightIterator.hasNext())
		{
			Flight tempFlight = flightIterator.next();
			
			String codeAirport = tempFlight.getFlightCode();
			
			if( code.equals(codeAirport))
			{
				return tempFlight;
			}
		}
		
		return null;
	}

	@Override
	public Journey leastCost(String from, String to) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Journey leastHop(String from, String to) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Journey leastCost(String from, String to, List<String> excluding)
			throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Journey leastHop(String from, String to, List<String> excluding)
			throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<Airport> directlyConnected(Airport airport) 
	{
		Set<Airport> connectedAirports = new HashSet<Airport>();
		
		
		Set<Flight> egdesOfAirport = this.graph.edgesOf(airport);
		

		Iterator<Flight> flightsIterator = egdesOfAirport.stream().iterator();
		
		while(flightsIterator.hasNext())
		{
			Flight flight = flightsIterator.next();
			
			Airport originAirport = flight.getFrom();
//			Airport
		}
		
		// temporary return
		return new HashSet<Airport>();
	}

	@Override
	public int setDirectlyConnected() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDirectlyConnectedOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Airport> getBetterConnectedInOrder(Airport airport) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String leastCostMeetUp(String at1, String at2) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String leastHopMeetUp(String at1, String at2) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String leastTimeMeetUp(String at1, String at2, String startTime) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
