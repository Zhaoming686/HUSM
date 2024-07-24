
package CSP;

import java.util.ArrayList;

import utilities.MyPair;

public class VariableCandidates 
{

	private int variableID;
	private ArrayList<MyPair<Integer, Double>> candidates;
	
	public VariableCandidates(int variableID,ArrayList<MyPair<Integer, Double>> candidates) 
	{
		this.variableID=variableID;
		this.candidates=candidates;
	}

	public int getVariableID() {
		return variableID;
	}

	public ArrayList<MyPair<Integer, Double>> getCandidates() {
		return candidates;
	}
	
	
}
