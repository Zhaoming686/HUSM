
package dataStructures;


public interface IntIterator {

	/** @return <code>true</code> if the iteration has more elements. */
	public boolean hasNext();

	/** @return the next element in the iteration */
	public int next();

	/**
	 * Removes from the underlying source the last element returned by the
	 * iterator (optional operation).
	 */
	public void remove();

}
