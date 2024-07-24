

package statistics;

import java.util.ArrayList;

import dataStructures.myNode;

public class TimedOutSearchStats {
	public static long totalNumber;
	public static long numberOfDomains;
	public static long maximum = 0;
		
	public static long getElementSize()
	{
		return 56;
	}
	
	public static double getAverage()
	{
		return totalNumber/numberOfDomains;
	}
	
	public static String getData()
	{
		return "Total: "+totalNumber+", Domains: "+numberOfDomains+", Average:"+getAverage()+", maximum: "+maximum;
	}
}
