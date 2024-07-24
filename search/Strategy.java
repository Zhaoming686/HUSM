
package search;

import java.util.Collection;

import AlgorithmInterface.Algorithm;

import dataStructures.HPListGraph;

public interface Strategy<NodeType, EdgeType> extends
		Generic<NodeType, EdgeType> {

	/**
	 * starts the corresponding strategy
	 * 
	 * @param algo
	 *            the algorithm which search space will be used
	 * @return the set of found frequent Fragments
	 */
	public Collection<HPListGraph<NodeType, EdgeType>> search(
			Algorithm<NodeType, EdgeType> algo,int freqThresh);

}
