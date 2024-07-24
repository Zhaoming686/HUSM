
package search;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import utilities.DfscodesCache;

import AlgorithmInterface.Algorithm;

import dataStructures.DFSCode;
import dataStructures.HPListGraph;
import dataStructures.StaticData;
import utilities.Settings;


//import de.parsemis.utils.Frequented;

public class RecursiveStrategy<NodeType, EdgeType> implements
		Strategy<NodeType, EdgeType> {

	private Extender<NodeType, EdgeType> extender;

	private Collection<HPListGraph<NodeType, EdgeType>> ret;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.parsemis.strategy.Strategy#search(de.parsemis.miner.Algorithm,
	 *      int)
	 */
	public Collection<HPListGraph<NodeType, EdgeType>> search(  //INITIAL NODES SEARCH
			final Algorithm<NodeType, EdgeType> algo,int freqThresh) {
		ret = new ArrayList<HPListGraph<NodeType, EdgeType>>();
		
		extender = algo.getExtender(freqThresh);

		for (final Iterator<SearchLatticeNode<NodeType, EdgeType>> it = algo
				.initialNodes(); it.hasNext();) {
			final SearchLatticeNode<NodeType, EdgeType> code = it.next();
			final long time = System.currentTimeMillis();
//			if (VERBOSE) {
//				out.print("doing seed " + code + " ...");
//			}
//			if (VVERBOSE) {
//				out.println();
//			}
			
			search(code);

			it.remove();
			
			//remove frequent edge labels that are already processed - test test test before approval
			double edgeLabel = Double.parseDouble(code.getHPlistGraph().getEdgeLabel(code.getHPlistGraph().getEdge(0, 1)).toString());
			int node1Label = Integer.parseInt(code.getHPlistGraph().getNodeLabel(0).toString());
			int node2Label = Integer.parseInt(code.getHPlistGraph().getNodeLabel(1).toString());
			String signature;
			if(node1Label<node2Label)
				signature = node1Label+"_"+edgeLabel+"_"+node2Label;
			else
				signature = node2Label+"_"+edgeLabel+"_"+node1Label;
			StaticData.hashedEdges.remove(signature);

//			if (VERBOSE) {
//				out.println("\tdone (" + (System.currentTimeMillis() - time)
//						+ " ms)");
			//}
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	private void search(final SearchLatticeNode<NodeType, EdgeType> node) {  //RECURSIVE NODES SEARCH

		//System.out.println("Getting Children");
		final Collection<SearchLatticeNode<NodeType, EdgeType>> tmp = extender
				.getChildren(node);
		//System.out.println("finished Getting Children");
		//System.out.println(node.getLevel());
		for (final SearchLatticeNode<NodeType, EdgeType> child : tmp) {
//			if (VVVERBOSE) {
//				out.println("doing " + child);
//			}
			//System.out.println("   branching into: "+child);
			//System.out.println("   ---------------------");
			search(child);
//			Settings.CanCount++;
		}
//		if (VVERBOSE) {
//			out.println("node " + node + " done. Store: " + node.store()
//					+ " children " + tmp.size() + " freq "
//					+ ((Frequented) node).frequency());
//		}
		if (node.store()) {
			Double utility = ((DFSCode) node).getUtility();
			if (utility >= Settings.utilityThre) {
				node.store(ret);
				Settings.PatternCount++;
			}
		} else {
			node.release();
		}
//		if (node.store()) {
//			node.store(ret);
//		} else {
//			node.release();
//		}

		node.finalizeIt();
	}

}
