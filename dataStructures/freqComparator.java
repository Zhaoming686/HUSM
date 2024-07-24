package dataStructures;

import java.awt.Point;
import java.util.Comparator;

public class freqComparator implements Comparator<Point> {
	
		  public int compare(Point o1, Point o2) {
		    
			  if(o1.y>o2.y)
				  return -1;
			  else if(o1.y<o2.y)
				  return 1;
			  else
				  return 0;
		  }
}

