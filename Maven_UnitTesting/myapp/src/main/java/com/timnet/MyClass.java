package com.timnet;

/**
 * Created by George.Haraga on 7/10/2017.
 */
public class MyClass {
    public String concatenate(String one,String two)
    {
        if(one==null)
            return two;
        else if(two==null)
            return one;
        else
        return one+two;
    }

    public boolean getBoolean() {
        return new java.util.Random().nextBoolean();
    }
}
