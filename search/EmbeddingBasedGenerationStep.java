
package search;

import java.util.Collection;
import java.util.Iterator;

import dataStructures.Extension;

public class EmbeddingBasedGenerationStep<NodeType, EdgeType> extends
		GenerationStep<NodeType, EdgeType> {
	/**
	 * creates a new GenerationStep
	 * 
	 * @param next
	 *            the next step of the mining chain
	 */
	public EmbeddingBasedGenerationStep(
			final MiningStep<NodeType, EdgeType> next) {
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
		super.reset();
		super.call(node);
		super.call(node, extensions);
	}

}
