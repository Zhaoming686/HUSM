
package CSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import pruning.SPpruner;

import dataStructures.ConnectedComponent;
import dataStructures.Graph;
import dataStructures.Query;
import dataStructures.myNode;

public class ConstraintGraph 
{
	private Variable[] variables;
	private Query qry;
	
	
	
	public ConstraintGraph(Graph graph,Query qry,HashMap<Integer, HashSet<Integer>> nonCandidates) 
	{
		this.qry=qry;		
		SPpruner sp = new SPpruner();
		sp.getPrunedLists(graph, qry,nonCandidates);
		variables= sp.getVariables();
	}
	
	public Query getQuery()
	{
		return qry;
	}

	public Variable[] getVariables() {
		return variables;
	}

	
}
