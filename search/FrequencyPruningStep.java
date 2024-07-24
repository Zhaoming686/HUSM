
package search;

import java.util.Collection;

import dataStructures.DFSCode;
import dataStructures.Extension;
import dataStructures.Frequency;
import dataStructures.Frequented;
import utilities.Settings;


public class FrequencyPruningStep<NodeType, EdgeType> extends
		MiningStep<NodeType, EdgeType> {

	private final Frequency min, max;

	/**
	 * creates a new frequency pruning
	 * 
	 * @param next
	 * @param min
	 * @param max
	 */
	public FrequencyPruningStep(final MiningStep<NodeType, EdgeType> next,
			final Frequency min, final Frequency max) {
		super(next);
		this.min = min;
		this.max = max;
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
		final Frequency freq = ((Frequented) node).frequency();  //HERE THE FREQUENCY CALCULATION OCCURS !!!

		double UpperBound = ((DFSCode)node).getUpperBound();

		if (max != null && max.compareTo(freq) < 0) {
			node.store(false);
		}
		if (min.compareTo(freq) > 0) {
			node.store(false);
		} else {
			if (UpperBound < Settings.utilityThre){
				node.store(false);
			}else {
				Settings.CanCount++;
				callNext(node, extensions);
			}
		}
	}

}
