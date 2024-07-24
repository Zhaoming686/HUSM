package dataStructures;

import java.util.BitSet;


public class DFScodeSerializer
{

	
	public static String serialize(final HPListGraph graph) {
		// serialize graph
		String text = "";
		//text += "t # " + graph.getName() + "\n";
		
		
		BitSet nodes= graph.getNodes();
		BitSet edges= graph.getEdges();
		for (int nodeIdx = nodes.nextSetBit(0); nodeIdx >= 0; nodeIdx = nodes.nextSetBit(nodeIdx + 1)) 
		{
			int label= (Integer)graph.getNodeLabel(nodeIdx);
			text += "v " + nodeIdx + " "
			+ label + "\n";
			
		}
		
		for (int edgeIdx = edges.nextSetBit(0); edgeIdx >= 0; edgeIdx = edges.nextSetBit(edgeIdx + 1)) 
		{
			int node1=graph.getNodeA(edgeIdx);
			int node2=graph.getNodeB(edgeIdx);
			int edgeLabel=Integer.parseInt((String)graph.getEdgeLabel(edgeIdx));
			if(graph.getDirection(edgeIdx)>=0)
			{text += "e " + node1
			+ " " + node2
			+ " " + edgeLabel + "\n";}
			else
			{text += "e " + node2
				+ " " + node1
				+ " " + edgeLabel + "\n";}
		}
		
		return text;
	}
}
