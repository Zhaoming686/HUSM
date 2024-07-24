
package search;

import java.util.Collection;

import dataStructures.Extension;

public abstract class GenerationStep<NodeType, EdgeType> extends
		GenerationPartialStep<NodeType, EdgeType> {

	private GenerationPartialStep<NodeType, EdgeType> first;

	private final GenerationPartialStep<NodeType, EdgeType> finalize;

	/**
	 * creates a new GenerationStep
	 * 
	 * @param next
	 */
	public GenerationStep(final MiningStep<NodeType, EdgeType> next) {
		super(null);
		finalize = new GenerationPartialStep<NodeType, EdgeType>(next) {
			@Override
			public void call(final SearchLatticeNode<NodeType, EdgeType> node,
					final Collection<Extension<NodeType, EdgeType>> extensions) {
				// just call the next step of the outer GerenationStep
				callNext(node, extensions); //extend the node and add to search tree
			}

			@Override
			public void call(final SearchLatticeNode<NodeType, EdgeType> node) {
				// the final step for extension generation is nothing else than
				// stop
			}

			@Override
			public void reset() {
				// the final step for resetting is nothing else than stop
			}
		};
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
		if (first != null) {
			first.call(node, extensions);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.parsemis.miner.GenerationPartialStep#call(de.parsemis.miner.SearchLatticeNode,
	 *      de.parsemis.graph.Embedding)
	 */
	@Override
	public void call(final SearchLatticeNode<NodeType, EdgeType> node) {
		if (first != null) {
			first.call(node);
		}
		//first.closeFiles();
	}
	
//	public void closeFiles()
//	{
//		first.closeFiles();
//	}

	/**
	 * @return the final step for this generation chain
	 */
	public GenerationPartialStep<NodeType, EdgeType> getLast() {
		return finalize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.parsemis.miner.MiningStep#getNext()
	 */
	@Override
	public MiningStep<NodeType, EdgeType> getNext() {
		return finalize.getNext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.parsemis.miner.GenerationPartialStep#reset()
	 */
	@Override
	public void reset() {
		if (first != null) {
			first.reset();
		}
	}

	/**
	 * sets the start of the generation chain
	 * 
	 * @param first
	 */
	public void setFirst(final GenerationPartialStep<NodeType, EdgeType> first) {
		this.first = first;
	}

}
