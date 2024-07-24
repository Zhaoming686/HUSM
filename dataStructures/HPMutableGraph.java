
package dataStructures;

public interface HPMutableGraph<NodeType, EdgeType> extends
		HPGraph<NodeType, EdgeType> {

	/**
	 * adds a new edge between the given nodes
	 *
	 * @param nodeAIdx
	 *            the index of the nodeA
	 * @param nodeBIdx
	 *            the index of the nodeB
	 * @param label
	 *            the label of the new edge
	 * @param direction
	 *            the direction of the new edge (in respect to nodeA)
	 * @return the index of the new edge
	 */
	public int addEdgeIndex(int nodeAIdx, int nodeBIdx, EdgeType label,
			int direction);

	/**
	 * adds a new node and connect if with a new edge to the given nodeA
	 * 
	 * @param nodeAIdx
	 *            the index of the nodeA
	 * @param nodeLabel
	 *            the label of the new node
	 * @param edgeLabel
	 *            the label of the new edge
	 * @param direction
	 *            the direction of the new edge (in respect to nodeA)
	 * @return the index of the new node
	 */
	public int addNodeAndEdgeIndex(int nodeAIdx, NodeType nodeLabel,
			EdgeType edgeLabel, int direction);

	/**
	 * adds a new node
	 * 
	 * @param label
	 *            the label of the new node
	 * @return the index of the new node
	 */
	public int addNodeIndex(NodeType label);

	/**
	 * removes the given edge
	 * 
	 * @param edgeIdx
	 *            the index of the edge
	 * @return <code>true</code>, if the edge is removed succesfully
	 */
	public boolean removeEdge(int edgeIdx);

	/**
	 * removes the given node and all its connected edges
	 * 
	 * @param nodeIdx
	 *            the index of the node
	 * @return <code>true</code>, if the node is removed succesfully
	 */
	public boolean removeNode(final int nodeIdx);

}
