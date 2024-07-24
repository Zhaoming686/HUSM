

package utilities;

import java.util.ArrayList;
import java.util.Iterator;

public class MyPair<A, B> {
	private A a;
	private B b;
	
	public MyPair(A a, B b)
	{
		this.a = a;
		this.b = b;
	}
	
	public A getA() {return a;}
	public B getB() {return b;}
	
	  @Override public boolean equals(Object aThat) {
		  Object t = null;
		    //check for self-comparison
		    if ( this == aThat ) return true;
		    //actual comparison
		    if(((Integer)this.a).intValue()==((Integer)((MyPair)aThat).getA()).intValue()) return true;
		    return false;
	  }
	  
	  public static int getIndexOf(ArrayList<MyPair<Integer, Double>> arr, int a)
	  {
		  int i = 0;
		  Iterator<MyPair<Integer, Double>> itr = arr.iterator(); 
		  while(itr.hasNext()) {
			  if(itr.next().getA().intValue()==a)
				  return i;
			  i++;
		  } 
		  return -1;
	  }

}
