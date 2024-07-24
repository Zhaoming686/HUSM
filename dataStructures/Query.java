
package dataStructures;

import java.util.ArrayList;

public class Query 
{
	//private Graph graph;
	private HPListGraph<Integer, Double> listGraph; 
	
	public Query(Graph g) {
		listGraph=g.getListGraph();
	}
	
	public Query(HPListGraph<Integer, Double> lsGraph) {
		listGraph=lsGraph;
	}
	
	

	public HPListGraph<Integer, Double> getListGraph() {
		return listGraph;
	}

	
	public ArrayList<ConnectedComponent> getConnectedLabels()
	{
		ArrayList<ConnectedComponent> cls = new ArrayList<ConnectedComponent>();
		
		HPListGraph<Integer, Double> hp = listGraph;
		for (int edge = hp.getEdges().nextSetBit(0); edge >= 0; edge = hp.getEdges().nextSetBit(edge + 1)) 
		{
			int nodeA,nodeB,labelA,labelB;
			
			if(hp.getDirection(edge)>=0)
			{
			nodeA= hp.getNodeA(edge); labelA=hp.getNodeLabel(nodeA);
			nodeB = hp.getNodeB(edge);labelB=hp.getNodeLabel(nodeB);
			}
			else
			{
				nodeB= hp.getNodeA(edge); labelB=hp.getNodeLabel(nodeB);
				nodeA = hp.getNodeB(edge);labelA=hp.getNodeLabel(nodeA);
			}
			
			ConnectedComponent cl = new ConnectedComponent(nodeA,labelA, nodeB,labelB, Double.parseDouble(listGraph.getEdgeLabel(nodeA, nodeB)+"")); 
			cls.add(cl);
		}
		return cls;
	}
	 
	

}
