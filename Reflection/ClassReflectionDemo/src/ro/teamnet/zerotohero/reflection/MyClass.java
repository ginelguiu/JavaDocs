package ro.teamnet.zerotohero.reflection;

/**
 * Created by Ginel.Guiu on 7/12/2017.
 */
public class MyClass extends SuperC{
    public int i;
    public MyClass()
    {}
    public MyClass(int j)
    {
        this.i = j;
    }
    static class StaticNestedClass {
    }
    class InnerClass {
    }
    public static void bateTarabana()
    {
        System.out.println("jap");
    }
}
