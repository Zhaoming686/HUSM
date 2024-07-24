
package search;

import java.util.Collection;

import dataStructures.Canonizable;
import dataStructures.Extension;


public class CanonicalPruningStep<NodeType, EdgeType> extends
		MiningStep<NodeType, EdgeType> {

	/**
	 * creates a new canonical pruning object
	 * 
	 * @param next
	 */
	public CanonicalPruningStep(final MiningStep<NodeType, EdgeType> next) {
		super(next);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.parsemis.miner.MiningStep#call(de.parsemis.miner.SearchLatticeNode,
	 *      java.util.Collection)
	 */
	@Override
	public void call(final SearchLatticeNode<NodeType, EdgeType> node,
			final Collection<Extension<NodeType, EdgeType>> extensions) {
		final Canonizable can = (Canonizable) node;

		if (can.isCanonical()) {
			this.callNext(node, extensions);
			
		} else {
			node.store(false);
		}

	}

}
