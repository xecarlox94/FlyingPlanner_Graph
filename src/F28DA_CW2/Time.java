package F28DA_CW2;


/**
 * The time helper class has general functions to deal with time in the string, float and integer formats. 
 * It is needed because the time given by the flight is in String format and the journey class returns the total number of minutes in the integer format.
 * */

public class Time 
{

	/**
	 * It gets the total number of minutes from a float that stores the time in the float format. The float format stores the time in a decimal hour system. 
	 * */
	public static int getMinutes(float time)
	{
		// get minutes from float (hours plus minutes)
		int hours = 0;
		
		
		// if the time is still bigger that 1 float
		while( time >= 1f )
		{
			// decrease the time by one
			time -= 1;
			
			// increase the number of hours
			hours++;
		}

		// initialises the final number of minutes with the value of zero
		int finalMinutes = 0;
		
		
		// if the time is different than zero
		if (time != 0) 
		{
			
			// it multiplies the remaining minutes in the decimal format back to clockwise format
			time = time * 60;
			
			// rounds the remaining clockwise float time
			// converts it back to integer
			int remainingMinutes = Math.round(time);
			
			// adds this to the total number of minutes 
			finalMinutes += remainingMinutes;
		}

		// multiplies the number of hours by 60 to get its value in minutes
		// adds the value to the total amount of minutes
		finalMinutes += (hours * 60);
		
		
		return finalMinutes;
	}
	
	/**
	 * It subtracts the float values in a clockwise system. Gets two float values and returns the clockwise result
	 * **/
	public static float subDecTimes(float time1, float time2)
	{
		// it first simply subtracts the two values
		float result = time1 - time2;
		
		// if the is negative
		if (result < 0) 
		{
			// adds 24 hours to the first value
			// It assumes the first value is on the next day
			time1 += 24;
			
			// It subtracts the two values again
			// it is the final result if the result was negative previously
			result = time1 - time2;
		}
		
		return result;
	}
	
	/**
	 * Gets the time in the String format and returns a float in decimal time format. The time retrieved is the total hours and minutes of that string.
	 * */
	public static float getDecFullTime(String stringTime) 
	{
		// assigning hours time
		Float finalDecTime = getDecHrs(stringTime);
		
		// adding the minutes time to full time
		finalDecTime += getDecMin(stringTime);
		
		return finalDecTime;
	}
	
	/**
	 * It gets the amount of hours in the float format, but does not include the number of minutes.
	 * */
	public static float getDecHrs(String stringTime)
	{
		// all sting have length 4
		// the first 2 characters are reserved for hours
		String hoursString = stringTime.substring(0, 2);
		
		// parses the substring containing the hours segment to float
		float hours = Float.parseFloat(hoursString);
		
		return hours;
	}
	
	/**
	 * It gets the amount of minutes in the float format, but does not include the number of hours
	 * */
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
