
package dataStructures;

import java.util.Iterator;
public interface Node<NodeType, EdgeType> extends Generic<NodeType, EdgeType> {

	/**
	 * @return an iterator over all connected edges (undirected, outgoing, and
	 *         incoming)
	 */
	public Iterator<Edge<NodeType, EdgeType>> edgeIterator();

	/**
	 * @return the number of all connected edges (undirected, outgoing, and
	 *         incomming)
	 */
	public int getDegree();


	/**
	 * @return the number of all incoming edges
	 */
	public int getInDegree();

	/**
	 * @return the node index of this node in the correponding graph
	 */
	public int getIndex();

	/**
	 * @return the connected label
	 */
	public NodeType getLabel();

	/**
	 * @return the number of all outgoing edges
	 */
	public int getOutDegree();

	/**
	 * @return an iterator over all incoming edges
	 */
	public Iterator<Edge<NodeType, EdgeType>> incommingEdgeIterator();

	/**
	 * @return an iterator over all outcoming edges
	 */
	public Iterator<Edge<NodeType, EdgeType>> outgoingEdgeIterator();

	/**
	 * set the label of this edge
	 * 
	 * @param label
	 */
	public void setLabel(NodeType label);

}
