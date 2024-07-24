

package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings
{

	public static boolean isApproximate=false;   //EXACT
	public static double approxEpsilon = 1.0;
	public static double approxConstant = 0;
	
	public static boolean isAutomorphismOn= true;
	
	public static boolean isDecomposeOn= true;

	public static boolean CACHING = true;
	
	public static boolean DISTINCTLABELS = true;
	
	public static boolean LimitedTime = true;

	public static boolean PRINT = false;
	
	public static boolean multipleAtts = false;
		
	//datasets folder
	public static String datasetsFolder = "C:\\Users\\Administrator\\Desktop\\";
	
	//given frequency, if not given then its value is -1
	public static int frequency = 1;

	//get哪种upper bound和规定数值
	public static String UpperBoundType = "RMU";
	public static double utilityThre = 140;
	public static int MVC = 3;
	public static int PatternCount = 0;
	public static int CanCount = 0;
	public static int Count = 0;
	public static List<Integer> list = new ArrayList<>();
	
    //the filename
	public static String fileName2 = "citeseer2Weight";
	public static String fileName = "citeseer2Weight.lg";
}
