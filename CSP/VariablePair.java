package CSP;

public class VariablePair 
{
	public Variable v1;
	public Variable v2;
	public double edgeLabel;
	
	public VariablePair(Variable v1,Variable v2, double edgeLabel) 
	{
		this.v1=v1;
		this.v2=v2;
		this.edgeLabel = edgeLabel;
	}
	
	public String getString()
	{
		String x = v1.getID()+"-"+edgeLabel+"-"+v2.getID();
		return x;
	}
	
	public boolean containsNewVariable()
	{
		return v1.isNew()||v2.isNew();
	}
	
	public int getValuesLength()
	{
		return v1.getListSize()+v2.getListSize();
	}
	
	public int getMinValuesLength()
	{
		if(v1.getListSize()<v2.getListSize())
			return v1.getListSize();
		else
			return v2.getListSize();
	}
}
