package dataStructures;

public class ConnectedComponent 
{

	private int indexA;
	private int indexB;
	private int labelA;
	private int labelB;
	private double edgeLabel;
	
	public ConnectedComponent(int indexA,int labelA,int indexB ,int labelB, double edgeLabel) 
	{
		this.labelA=labelA;
		this.labelB= labelB;
		this.indexA=indexA;
		this.indexB=indexB;
		this.edgeLabel=edgeLabel;
	}

	public int getLabelA() {
		return labelA;
	}

	public int getLabelB() {
		return labelB;
	}
	
	public int getIndexA()
	{
		return indexA;
	}
	
	public int getIndexB()
	{
		return indexB;
	}
	
	public double getEdgeLabel()
	{
		return edgeLabel;
	}
	
	
}
