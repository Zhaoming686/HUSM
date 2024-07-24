package dataStructures;

/**
 * record every value of each mapping
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class ValueCompute<A, B, C> {

    private A a;
    private B b;
    private C c;

    public ValueCompute(A a, B b, C c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public A getA() {return a;}
    public B getB() {return b;}
    public C getC() {return c;}

}
