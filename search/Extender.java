
package search;

import java.util.Collection;


public interface Extender<NodeType, EdgeType> extends
		Generic<NodeType, EdgeType> {

	/**
	 * @param nodes
	 * @return an iterator over all children of the set of given nodes
	 * @throws UnsupportedOperationException
	 *             if not supported for the corresponding algorithm
	 */
	public Collection<SearchLatticeNode<NodeType, EdgeType>> getChildren(
			Collection<SearchLatticeNode<NodeType, EdgeType>> nodes)
			throws UnsupportedOperationException;

	/**
	 * @param node
	 * @return an iterator over all children of the given node
	 * @throws UnsupportedOperationException
	 *             if not supported for the corresponding algorithm
	 */
	public Collection<SearchLatticeNode<NodeType, EdgeType>> getChildren(
			SearchLatticeNode<NodeType, EdgeType> node)
			throws UnsupportedOperationException;

}
