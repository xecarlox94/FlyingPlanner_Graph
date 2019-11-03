package F28DA_CW2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FlyingPlanner implements IFlyingPlannerPartB<Airport,Flight>, IFlyingPlannerPartC<Airport,Flight> {

	@Override
	public boolean populate(FlightsReader fr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean populate(HashSet<String[]> airports, HashSet<String[]> flights) {
		// TODO Auto-generated method stub
		return false;
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
