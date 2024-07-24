
package search;

import java.util.Collection;

import dataStructures.Extension;


public abstract class MiningStep<NodeType, EdgeType> implements
		Generic<NodeType, EdgeType> {
	protected final MiningStep<NodeType, EdgeType> next;

	/**
	 * creates a new MiningStep, with will call the given <code>next</code>
	 * step during the search if necessary
	 * 
	 * @param next
	 */
	public MiningStep(final MiningStep<NodeType, EdgeType> next) {
		this.next = next;
	}

	/**
	 * tells this step that it is to be used for the given <code>node</code>
	 * with the given <code>extensions</code> this function has to call the
	 * next step, if necessary.
	 * 
	 * @param node
	 * @param extensions
	 */
	public abstract void call(SearchLatticeNode<NodeType, EdgeType> node,
			Collection<Extension<NodeType, EdgeType>> extensions);

	/**
	 * calls the next step for the given <code>node</code> with the given
	 * <code>extensions</code>
	 * 
	 * @param node
	 * @param extensions
	 */
	protected final void callNext(
			final SearchLatticeNode<NodeType, EdgeType> node,
			final Collection<Extension<NodeType, EdgeType>> extensions) {
		if (next != null) {
			next.call(node, extensions);
		}
	}

	/**
	 * @return the next MiningStep
	 */
	public MiningStep<NodeType, EdgeType> getNext() {
		return next;
	}

}
