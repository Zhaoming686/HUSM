

package statistics;

import java.awt.Point;

public class DistinctLabelStat 
{
	int numberOfPatterns;
	Point maxNode;
	Point maxEdge;
	int maxNodeIndex;
	int maxEdgeIndex;
	public DistinctLabelStat(int numberOfPatterns,Point maxNode,Point maxEdge,int maxNodeIndex,int maxEdgeIndex) 
	{
		this.numberOfPatterns=numberOfPatterns;
		this.maxNode=maxNode;
		this.maxEdge=maxEdge;
		this.maxNodeIndex=maxNodeIndex;
		this.maxEdgeIndex=maxEdgeIndex;
	}
	
	public String toString2() 
	{
		String out =""+numberOfPatterns+",";
		out+=printPoint2(maxNode,maxNodeIndex)+",";
		out+=printPoint2(maxEdge,maxEdgeIndex);
		//out+="";
		return out;
	}
	
	private String printPoint2(Point p,int index)
	{
		String pair="";
		pair+=p.x+"-"+p.y;
		return pair;
	}
	
	@Override
	public String toString() 
	{
		String out ="{ "+numberOfPatterns+",";
		out+=printPoint(maxNode,maxNodeIndex)+",";
		out+=printPoint(maxEdge,maxEdgeIndex);
		out+=" }";
		return out;
	}
	
	private String printPoint(Point p,int index)
	{
		String pair="(";
		pair+=p.x+","+p.y+")"+":"+index;
		return pair;
	}
}
