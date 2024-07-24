
package search;


public abstract class GenerationPartialStep<NodeType, EdgeType> extends
		MiningStep<NodeType, EdgeType> {

	/**
	 * creates a new step, that will use the
	 * <code>next</next> MiningStep, if necessary
	 * @param next
	 */
	public GenerationPartialStep(final MiningStep<NodeType, EdgeType> next) {
		super(next);
	}

	/**
	 * tells this step, that it is to be used for the given
	 * <code>embedding</code> of the given <code>node</code> this function
	 * has to call the next step, if necessary.
	 * 
	 * @param node
	 * @param embedding
	 */
//	public abstract void call(SearchLatticeNode<NodeType, EdgeType> node,
//			HPEmbedding<NodeType, EdgeType> embedding);
	
	public abstract void call(SearchLatticeNode<NodeType, EdgeType> node);

	/**
	 * calls the next step for the given <code>embedding</code> of the given
	 * <code>node</code>
	 * 
	 * @param node
	 * @param embedding
	 */
//	protected final void callNext(
//			final SearchLatticeNode<NodeType, EdgeType> node,
//			final HPEmbedding<NodeType, EdgeType> embedding) {
//		@SuppressWarnings("unchecked")
//		final GenerationPartialStep<NodeType, EdgeType> gen = (GenerationPartialStep<NodeType, EdgeType>) next;
//		if (gen != null) {
//			gen.call(node, embedding);
//		}
//	}
	
	protected final void callNext(
			final SearchLatticeNode<NodeType, EdgeType> node) {
		@SuppressWarnings("unchecked")
		final GenerationPartialStep<NodeType, EdgeType> gen = (GenerationPartialStep<NodeType, EdgeType>) next;
		if (gen != null) {
			gen.call(node);
		}
	}

	/**
	 * tells this step that the extensions for the next node will be searched
	 * furthermore
	 */
	public void reset() {
		resetNext();
	}

	/**
	 * resets the next step
	 */
	protected final void resetNext() {
		@SuppressWarnings("unchecked")
		final GenerationPartialStep<NodeType, EdgeType> gen = (GenerationPartialStep<NodeType, EdgeType>) next;
		if (next != null) {
			gen.reset();
		}
	}
}
