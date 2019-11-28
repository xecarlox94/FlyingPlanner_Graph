package F28DA_CW2;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class FlyingPlannerTest {

	FlyingPlanner fi;

	/**
	 * Tests the least cost
	 * */
	@Test
	public void leastCost() 
	{
		try 
		{
			Journey journey = fi.leastCost("APT0", "APT4");
			assertEquals(4, journey.totalHop());
			assertEquals(225, journey.totalCost());
		} 
		catch (FlyingPlannerException e) 
		{
			fail("leastCost test case");
		}
	}
	

	/**
	 * Tests the least cost, excluding airports
	 * */
	@Test
	public void leastCostExclude() 
	{
		try 
		{
			LinkedList<String> exclude = new LinkedList<String>();
			exclude.add("APT5");
			Journey journey = fi.leastCost("APT0", "APT4",exclude);
			assertEquals(2, journey.totalHop());
			assertEquals(265, journey.totalCost());
		} 
		catch (FlyingPlannerException e) 
		{
			fail("leastCost exclude test case");
		}
	}


	/**
	 * Tests the least hop
	 * */
	@Test
	public void leastHop() 
	{
		try 
		{
			Journey journey = fi.leastHop("APT3", "APT7");
			assertEquals(2, journey.totalHop());
			assertEquals(315, journey.totalCost());
		} 
		catch (FlyingPlannerException e) 
		{
			fail("leastHop test case");
		}
	}


	/**
	 * Tests the least hop, excluding airports
	 * */
	@Test
	public void leastHopExclude() 
	{
		try 
		{
			LinkedList<String> exclude = new LinkedList<String>();
			exclude.add("APT4");
			Journey journey = fi.leastHop("APT3", "APT7",exclude);
			assertEquals(2, journey.totalHop());
			assertEquals(370, journey.totalCost());
		} 
		catch (FlyingPlannerException e) 
		{
			fail("leastHop test case exclude");
		}
	}

	
	
	/**
	 * Initialises a custom graph
	 * */
	@Before
	public void initialize() {
		fi = new FlyingPlanner();

		HashSet<String[]> airportsSet = new HashSet<String[]>();
		HashSet<String[]> flightsSet = new HashSet<String[]>();
		
		int length = 8;
		
		for(int i = 0; i < length; i++)
		{
			String code = "APT" + i;
			String location = "location" + i;
			String airportName = "Airport number " + i;
			String[] tempAirport = { code, location, airportName};

			airportsSet.add(tempAirport);
		}

		String[] flight0 = {"F00","APT0","1245","APT1","1320","75"}; flightsSet.add(flight0);
		String[] flight1 = {"F01","APT1","0550","APT0","0700","30"}; flightsSet.add(flight1);
		String[] flight2 = {"F02","APT0","1020","APT2","1400","100"};  flightsSet.add(flight2);
		String[] flight3 = {"F03","APT0","2300","APT6","0400","200"};  flightsSet.add(flight3);
		String[] flight4 = {"F04","APT1","1600","APT5","1830","220"};  flightsSet.add(flight4);
		String[] flight5 = {"F05","APT2","1500","APT3","1900","190"};  flightsSet.add(flight5);
		String[] flight6 = {"F06","APT2","1500","APT5","1900","35"};  flightsSet.add(flight6);
		String[] flight7 = {"F07","APT2","1500","APT1","1900","20"};  flightsSet.add(flight7);
		String[] flight8 = {"F08","APT1","1500","APT2","1900","25"};  flightsSet.add(flight8);
		String[] flight9 = {"F09","APT3","1500","APT0","1900","20"};  flightsSet.add(flight9);
		String[] flight10 = {"F10","APT0","1500","APT3","1900","35"};  flightsSet.add(flight10);
		String[] flight11 = {"F11","APT3","1500","APT4","1900","275"};  flightsSet.add(flight11);
		String[] flight12 = {"F12","APT4","1500","APT5","1900","75"};  flightsSet.add(flight12);
		String[] flight13 = {"F13","APT4","1500","APT7","1900","40"};  flightsSet.add(flight13);
		String[] flight14 = {"F14","APT4","1500","APT6","1900","55"};  flightsSet.add(flight14);
		String[] flight15 = {"F15","APT5","1500","APT2","1900","25"};  flightsSet.add(flight15);
		String[] flight16 = {"F16","APT5","1500","APT7","1900","60"};  flightsSet.add(flight16);
		String[] flight17 = {"F17","APT5","1500","APT6","1900","25"};  flightsSet.add(flight17);
		String[] flight18 = {"F18","APT5","1500","APT0","1900","200"};  flightsSet.add(flight18);
		String[] flight19 = {"F19","APT6","1500","APT3","1900","95"};  flightsSet.add(flight19);
		String[] flight20 = {"F20","APT6","1500","APT4","1900","65"};  flightsSet.add(flight20);
		String[] flight21 = {"F21","APT6","1500","APT5","1900","30"};  flightsSet.add(flight21);
		String[] flight22 = {"F22","APT6","1500","APT7","1900","285"};  flightsSet.add(flight22);
		String[] flight23 = {"F23","APT6","1500","APT0","1900","190"};  flightsSet.add(flight23);
		String[] flight24 = {"F24","APT7","1500","APT5","1900","50"};  flightsSet.add(flight24);
		String[] flight25 = {"F25","APT7","1500","APT6","1900","265"};  flightsSet.add(flight25);
		String[] flight26 = {"F26","APT7","1500","APT4","1900","35"};  flightsSet.add(flight26);
		String[] flight27 = {"F27","APT7","1500","APT0","1900","375"};  flightsSet.add(flight27);
		String[] flight28 = {"F28","APT0","1500","APT7","1900","350"};  flightsSet.add(flight28);
		
		
		fi.populate(airportsSet, flightsSet);
	}

}
