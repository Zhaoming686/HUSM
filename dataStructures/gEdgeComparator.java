
package dataStructures;

import java.util.Comparator;

public class gEdgeComparator<NodeType, EdgeType> implements Comparator {

		  public int compare(Object a, Object b) 
		  {

		     return ((GSpanEdge<NodeType, EdgeType>)a).compareTo(((GSpanEdge<NodeType, EdgeType>)b));
		  
		  }
	
}
