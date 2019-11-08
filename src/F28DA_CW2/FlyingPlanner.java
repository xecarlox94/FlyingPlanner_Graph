package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FlyingPlanner implements IFlyingPlannerPartB<Airport,Flight>, IFlyingPlannerPartC<Airport,Flight> {
	
	HashSet<Airport> airports;
	
	LinkedList<Flight> flights;

	@Override
	public boolean populate(FlightsReader fr) 
	{
		HashSet<String[]> airports = fr.getAirports();
		
		HashSet<String[]> flights = fr.getFlights();
		
		// overloading he method
		return  this.populate(airports, flights);
	}

	@Override
	public boolean populate(HashSet<String[]> airports, HashSet<String[]> flights) {

		this.airports = new HashSet<Airport>();
		
		this.flights = new LinkedList<Flight>();
		
		
		for( String[] airport: airports)
		{
			Airport temp = new Airport(airport[0], airport[2], airport[1]);
			this.airports.add(temp);
		}

		Iterator<Airport> airportsIterator = this.airports.iterator();
		
		
		Hashtable<String, Airport> airportHashTable = new Hashtable<String, Airport>();
		while(airportsIterator.hasNext())
		{
			Airport tempAirport = airportsIterator.next();

			airportHashTable.put(tempAirport.getCode(), tempAirport);
		}
		
		
		for( String[] flight: flights)
		{

			String originCode = flight[1];
			String destinationCode = flight[3];
			
			Airport originAirport = airportHashTable.get(originCode);
			Airport destinationAirport = airportHashTable.get(destinationCode);
			
			int price = Integer.parseInt(flight[5]);
			
			Flight newFlight = new Flight(flight[0],flight[1],originAirport,flight[3],destinationAirport,price);
			
			this.flights.add(newFlight);
			
		}
		
		return true;
	}

	@Override
	public Airport airport(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flight flight(String code) {
		// TODO Auto-generated method stub
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
	public Set<Airport> directlyConnected(Airport airport) {
		// TODO Auto-generated method stub
		return null;
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
