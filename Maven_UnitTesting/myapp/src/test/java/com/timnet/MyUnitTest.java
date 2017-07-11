package com.timnet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by George.Haraga on 7/10/2017.
 */
public class MyUnitTest {
    @Test
    public void concatenate()
    {
        MyClass myC=new MyClass();
        String result=myC.concatenate("one","two");
        assertEquals("onetwo",result);
    }
    @Test
    public void testConcatNulls()
    {
        MyClass myC1=new MyClass();
        String result=myC1.concatenate(null,null);
        assertEquals(null,result);
        result=myC1.concatenate("one",null);
        assertNotNull("one",result);
       // result=myC1.concatenate();
        assertTrue(myC1.getBoolean());
    }
}
