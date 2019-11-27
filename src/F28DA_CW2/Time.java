package F28DA_CW2;

public class Time 
{

	
	public static int getMinutes(float time)
	{
		
		// get minutes from float (hours plus minutes)
		int hours = 0;
		
		while( time >= 1f )
		{
			time -= 1;
			
			hours++;
		}

		
		int finalMinutes = 0;
		
		
		if (time != 0) 
		{
			time = time * 60;
			
			int remainingMinutes = Math.round(time);
			
			finalMinutes += remainingMinutes;
			
		}

		finalMinutes += hours * 60;
		
		
		return finalMinutes;
	}
	
	
	public static float subDecTimes(float time1, float time2)
	{
		
		float result = time1 - time2;
		
		
		if (result < 0) 
		{
			time1 += 24;
			
			result = time1 - time2;
		}
		
		return result;
	}
	
	
	public static float getDecFullTime(String stringTime) 
	{
		// assigning hours time
		Float finalDecTime = getDecHrs(stringTime);
		
		// adding the minutes time to full time
		finalDecTime += getDecMin(stringTime);
		
		return finalDecTime;
	}
	

	public static float getDecHrs(String stringTime)
	{
		// all sting have length 4
		// the first 2 characters are reserved for hours
		String hoursString = stringTime.substring(0, 2);
		
		// parses the substring containing the hours segment to float
		float hours = Float.parseFloat(hoursString);
		
		return hours;
	}
	
	
	public static float getDecMin(String stringTime)
	{
		// all sting have length 4
		// the last 2 characters are reserved for minutes
		String minsString = stringTime.substring(2, 4);
		
		// parses the substring containing the minutes segment to float
		float mins = Float.parseFloat(minsString);
		
		// convert the time to decimal minutes
		mins = ( mins / 60f );
		
		
		return mins;
	}
	
	
}
