

package search;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import dataStructures.*;
import statistics.Statistics;
import utilities.MyPair;
import utilities.Settings;
import utilities.StopWatch;
import Dijkstra.*;

public class Searcher<NodeType, EdgeType> 
{

	private Graph singleGraph;
	private IntFrequency freqThreshold;
	private int distanceThreshold;
	private ArrayList<Integer> sortedFrequentLabels;
	private ArrayList<Double> freqEdgeLabels;
	Map<GSpanEdge<NodeType, EdgeType>, DFSCode<NodeType, EdgeType>> initials;
	private int type;
	public ArrayList<HPListGraph<NodeType, EdgeType>> result;
	public static Hashtable<Integer, Vector<Integer>> neighborLabels;
	private String path;
	
	
	public Searcher(String path, int freqThreshold,int shortestDistance) throws Exception
	{
		this.freqThreshold= new IntFrequency(freqThreshold);
		this.distanceThreshold=shortestDistance;
		singleGraph = new Graph(1,freqThreshold);
		singleGraph.loadFromFile(path);
		this.path = path;
		sortedFrequentLabels=singleGraph.getSortedFreqLabels();
		freqEdgeLabels = singleGraph.getFreqEdgeLabels();
		singleGraph.printFreqNodes();	
        singleGraph.setShortestPaths_1hop();
	}
	
	public void initialize()
	{
		initials= new TreeMap<GSpanEdge<NodeType, EdgeType>, DFSCode<NodeType, EdgeType>>(new gEdgeComparator<NodeType, EdgeType>());
		HashMap<Integer, HashMap<Integer,myNode>> freqNodesByLabel=  singleGraph.getFreqNodesByLabel();
		HashSet<Integer> contains= new HashSet<Integer>();
		for (Iterator<  java.util.Map.Entry< Integer, HashMap<Integer,myNode> > >  it= freqNodesByLabel.entrySet().iterator(); it.hasNext();) 
		{
			
			java.util.Map.Entry< Integer, HashMap<Integer,myNode> > ar =  it.next();
			int firstLabel=ar.getKey();
			contains.clear();
			HashMap<Integer,myNode> tmp = ar.getValue();
			for (Iterator<myNode> iterator = tmp.values().iterator(); iterator.hasNext();) 
			{
				myNode node =  iterator.next();
				int firstNodeID = node.getID();
				HashMap<Integer, ArrayList<MyPair<Integer, Double>>> neighbours=node.getReachableWithNodes();
				if(neighbours!=null)
				for (Iterator<Integer>  iter= neighbours.keySet().iterator(); iter.hasNext();) 
				{
					int secondLabel = iter.next();
					int labelA=sortedFrequentLabels.indexOf(firstLabel);
					int labelB=sortedFrequentLabels.indexOf(secondLabel);
					//iterate over all neighbor nodes to get edge labels as well
					for (Iterator<MyPair<Integer, Double>>  iter1= neighbours.get(secondLabel).iterator(); iter1.hasNext();)
					{
						MyPair<Integer, Double> mp = iter1.next();
						double edgeLabel = mp.getB();
						if(!freqEdgeLabels.contains(edgeLabel))
							continue;
						
						int secondNodeID = mp.getA();
						
						final GSpanEdge<NodeType, EdgeType> gedge = new GSpanEdge <NodeType, EdgeType>().set(0, 1, labelA, (int)edgeLabel, labelB, 0, firstLabel, secondLabel);
						
						if(!initials.containsKey(gedge))
						{
							final ArrayList<GSpanEdge<NodeType, EdgeType>> parents = new ArrayList<GSpanEdge<NodeType, EdgeType>>(
									2);
							parents.add(gedge);
							parents.add(gedge);
							
							HPListGraph<NodeType, EdgeType> lg = new HPListGraph<NodeType, EdgeType>();
							gedge.addTo(lg);
							DFSCode<NodeType, EdgeType> code = new DFSCode<NodeType,EdgeType>(sortedFrequentLabels, freqEdgeLabels,singleGraph,null).set(lg, gedge, gedge, parents);
							
							initials.put(gedge, code);
						}
					}
				}
			}
		}
		
		for (final Iterator<Map.Entry<GSpanEdge<NodeType, EdgeType>, DFSCode<NodeType, EdgeType>>> eit = initials
				.entrySet().iterator(); eit.hasNext();)
		{
			final DFSCode<NodeType, EdgeType> code = eit.next().getValue();
			Frequency frequency = code.frequency();
			if (Settings.utilityThre > code.getUpperBound())
			{
				eit.remove();
			}
			else{if (freqThreshold.compareTo(frequency) > 0)
			{
				eit.remove();
			}
			else{
				Settings.CanCount++;
			}
			}

		}
				
		neighborLabels = new Hashtable();
		
		for (final Iterator<Map.Entry<GSpanEdge<NodeType, EdgeType>, DFSCode<NodeType, EdgeType>>> eit = initials
				.entrySet().iterator(); eit.hasNext();) 
		{
			final DFSCode<NodeType, EdgeType> code = eit.next().getValue();
			
			//add possible neighbor labels for each label
			int labelA;
			int labelB;
			GSpanEdge<NodeType, EdgeType> edge = code.getFirst();
			labelA = edge.getThelabelA();
			labelB = edge.getThelabelB();
			Vector temp = neighborLabels.get(labelA);
			if(temp==null)
			{
				temp = new Vector();
				neighborLabels.put(labelA, temp);
			}
			temp.addElement(labelB);
			
			//now the reverse
			temp = neighborLabels.get(labelB);
			if(temp==null)
			{
				temp = new Vector();
				neighborLabels.put(labelB, temp);
			}
			temp.addElement(labelA);
		}
	}
	
	public void search()
	{
		Algorithm<NodeType, EdgeType> algo = new Algorithm<NodeType, EdgeType>();
		algo.setInitials(initials);
		RecursiveStrategy<NodeType, EdgeType> rs = new RecursiveStrategy<NodeType, EdgeType>();
		result= (ArrayList<HPListGraph<NodeType, EdgeType>>)rs.search(algo,this.freqThreshold.intValue());
	}
	
	private int getNumOfDistinctLabels(HPListGraph<NodeType, EdgeType> list)
    {
        HashSet<Integer> difflabels= new HashSet<Integer>();
        for (int i = 0; i < list.getNodeCount(); i++) 
        {
            int label= (Integer)list.getNodeLabel(i);
            if(!difflabels.contains(label))
                difflabels.add(label);
        }
        
        return difflabels.size();
    }
	
	public Graph getSingleGraph()
	{
		return singleGraph;
	}
	
}
