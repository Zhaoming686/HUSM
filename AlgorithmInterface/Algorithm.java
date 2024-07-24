
package AlgorithmInterface;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import search.Extender;
import search.Generic;
import search.SearchLatticeNode;

public interface Algorithm<NodeType, EdgeType> extends
		Generic<NodeType, EdgeType>, Serializable {

	/**
	 * @param threadIdx
	 * @return a (new) Extender Object for the given thread (index)
	 */
	public Extender<NodeType, EdgeType> getExtender(int minFreq);

	/**
	 * Initialize the algorithm
	 * 
	 * @param graphs
	 *            the set of graphs that will be search for frequent fragments
	 * @param factory
	 *            the factory new graphs will be created with
	 * @param settings
	 *            the settings for the search
	 * @return a collection with all fragments that will not be found by the
	 *         algorithm
	 */
//	public Collection<Fragment<NodeType, EdgeType>> initialize(
//			final Collection<Graph<NodeType, EdgeType>> graphs,
//			final GraphFactory<NodeType, EdgeType> factory,
//			final Settings<NodeType, EdgeType> settings);

	/**
	 * @return an iterator over the initial nodes for the search
	 */
	public Iterator<SearchLatticeNode<NodeType, EdgeType>> initialNodes();

}
