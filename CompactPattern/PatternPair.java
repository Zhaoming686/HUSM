package CompactPattern;

import java.util.ArrayList;
import java.util.Iterator;

public class PatternPair<A, B> {
    private A a;
    private B b;

    public PatternPair(A a, B b)
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
        if(((Integer)this.a).intValue()==((Integer)((utilities.MyPair)aThat).getA()).intValue()) return true;
        return false;
    }

    public static int getIndexOf(ArrayList<utilities.MyPair<Integer, Double>> arr, int a)
    {
        int i = 0;
        Iterator<utilities.MyPair<Integer, Double>> itr = arr.iterator();
        while(itr.hasNext()) {
            if(itr.next().getA().intValue()==a)
                return i;
            i++;
        }
        return -1;
    }

}
