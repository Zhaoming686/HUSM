
package search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import dataStructures.DFSCode;
import dataStructures.GSpanEdge;
import dataStructures.IntFrequency;


public class Algorithm<NodeType, EdgeType> implements
		AlgorithmInterface.Algorithm<NodeType, EdgeType>,
		Generic<NodeType, EdgeType> {

	/**
	 * Inner class to iterate over the initial edges
	 * 
	 * @author Marc Woerlein (woerlein@informatik.uni-erlangen.de)
	 * 
	 */
	private class MyIterator implements
			Iterator<SearchLatticeNode<NodeType, EdgeType>> {
		final Iterator<Map.Entry<GSpanEdge<NodeType, EdgeType>, DFSCode<NodeType, EdgeType>>> entryit;

		final boolean del;

		Map.Entry<GSpanEdge<NodeType, EdgeType>, DFSCode<NodeType, EdgeType>> last = null;

		MyIterator(
				final Map<GSpanEdge<NodeType, EdgeType>, DFSCode<NodeType, EdgeType>> initials,
				final boolean del) {
			entryit = initials.entrySet().iterator();
			this.del = del;
		}

		public boolean hasNext() {
			return entryit.hasNext();
		}

		public SearchLatticeNode<NodeType, EdgeType> next() {
			last = entryit.next();
			return last.getValue();
		}

		public void remove() {
			if (del) {
				//removeEdge(last.getValue());
			}
			entryit.remove();
		}
	}

	/**
	 * This class represents a Label that is used for the pseudo node required
	 * for unconnected fragemtn search in gSpan
	 * 
	 * @author Marc Woerlein (woerlein@informatik.uni-erlangen.de)
	 * 
	 */
	static class PseudoLabel implements Serializable {
		/**	 */
		private static final long serialVersionUID = -4215112903761599420L;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(final Object o) {
			return (o instanceof PseudoLabel);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "pseudo";
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean keep;

	private transient/* final */Map<GSpanEdge<NodeType, EdgeType>, DFSCode<NodeType, EdgeType>> initials;

	/**
	 * generates a new (GSpan) algorithm
	 */
	public Algorithm() {
		//initials = new TreeMap<GSpanEdge<NodeType, EdgeType>, DFSCode<NodeType, EdgeType>>();
	}
	
	public void setInitials(Map<GSpanEdge<NodeType, EdgeType>, DFSCode<NodeType, EdgeType>> initials)
	{
		this.initials=initials;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.parsemis.miner.Algorithm#getExtender(int)
	 */
	public Extender<NodeType, EdgeType> getExtender(int minFreq) 
	{
		// configure mining chain
		
		final GSpanExtender<NodeType, EdgeType> extender = new GSpanExtender<NodeType, EdgeType>();
		// from last steps (filters after child computation) ...
		MiningStep<NodeType, EdgeType> curFirst = extender;
		GenerationStep<NodeType, EdgeType> gen;
		

		//if (env.embeddingBased) { //Yes
			// ... over generation ...
			curFirst = gen = new EmbeddingBasedGenerationStep<NodeType, EdgeType>(
					curFirst);
			// .. to prefilters
			
			curFirst = new FrequencyPruningStep<NodeType, EdgeType>(curFirst,
					new IntFrequency(minFreq), null);
			curFirst = new CanonicalPruningStep<NodeType, EdgeType>(curFirst);

		//} 

		// build generation chain
		GenerationPartialStep<NodeType, EdgeType> generationFirst = gen
				.getLast();

		
			//YES
			generationFirst = new RightMostExtension<NodeType, EdgeType>(generationFirst);
		 

		// insert generation chain
		gen.setFirst(generationFirst);

		// insert mining chain
		extender.setFirst(curFirst);
		return extender;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.parsemis.miner.Algorithm#initialNodes()
	 */
	public Iterator<SearchLatticeNode<NodeType, EdgeType>> initialNodes() {
		return new MyIterator(initials, !keep);
	}
}
