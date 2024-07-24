package dataStructures;

import java.io.Serializable;

public interface Frequency extends Comparable<Frequency>, Serializable {

	/**
	 * increases this frequency by the given amount
	 * 
	 * @param freq
	 */
	public void add(Frequency freq);

	/**
	 * @return a clone of the Frequency
	 */
	public Frequency clone();

	/**
	 * (skalar) multiplies the frequency with the given factor
	 * 
	 * @param fac
	 */
	public void smul(float fac);

	/**
	 * decreases this frequency by the given amount
	 * 
	 * @param freq
	 */
	public void sub(Frequency freq);

}
