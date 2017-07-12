package ro.teamnet.zerotohero.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //TODO get the class for a String object, and print it
        String s = new String();
        System.out.println(s.getClass());
        //TODO get the class of an Enum, and print it
        Class enumClass = MyEnum.a.getClass();
        System.out.println(enumClass);
        //TODO get the class of a collection, and print it
        Class collClass = (new HashMap()).getClass();
        System.out.println(collClass);

        //TODO get the class of a primitive type, and print it


        double d = 1.1;
        System.out.println(Double.valueOf(d).getClass().getTypeName());
        //TODO get and print the class for a field of primitive type

        Field f = null;
        try {
            f = MyClass.class.getDeclaredField("i");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println(f.getType());
        //TODO get and print the class for a primitive type, using the wrapper class

        Class wrapperClass = Integer.TYPE;
        System.out.println(wrapperClass);
        //TODO get the class for a specified class name
        Class aClass = null;
        try {
            aClass = Class.forName("ro.teamnet.zerotohero.reflection.MyClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(aClass);

        //TODO get the superclass of a class, and print it
        Class superClass = MyClass.class.getSuperclass();
        System.out.println(superClass);
        //TODO get the superclass of the superclass above, and print it
        Class superSuperClass = SuperC.class.getSuperclass();
        System.out.println(superSuperClass);

        //TODO get and print the declared classes within some other class
        Class[] declClasses = MyClass.class.getDeclaredClasses();
        Class[] japClase = declClasses;

        for(int i = 0; i < declClasses.length; ++i) {
            Class bij = japClase[i];
            System.out.println(bij);
        }

        //TODO print the number of constructors of a class
        int n = MyClass.class.getDeclaredConstructors().length;
        System.out.println(n);
        //TODO get and invoke a public constructor of a class
        MyClass myClass = null;
        try {
            myClass = (MyClass)MyClass.class.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(myClass);

        //TODO get and print the class of one private field 
        Field fieldType = MyClass.class.getDeclaredField("i");
        System.out.println(fieldType.getType());
		
		//TODO set and print the value of one private field for an object
        Field[] fieldsType = MyClass.class.getFields();
        Field[] var16 = fieldsType;
        int var17 = fieldsType.length;

        for(int var18 = 0; var18 < var17; ++var18) {
            Field f1 = var16[var18];
            System.out.println(f1.getType());
        }


        //TODO get and print only the public fields class
        

        //TODO get and invoke one public method of a class
        Method method = MyClass.class.getDeclaredMethod("bateTarabana", new Class[0]);
        method.invoke(new MyClass(), new Object[0]);

        //TODO get and invoke one inherited method of a class
        Method methodSuperClass = MyClass.class.getMethod("cantaSaxofonul");
        methodSuperClass.invoke(new MyClass(), new Object[0]);
		
		//TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
        for(int j = 0; j<9;j++)
        {
            MyClass.bateTarabana();
            System.out.println(System.currentTimeMillis());
            System.out.println(j);
        }
        for(int k = 0; k<99;k++)
        {
            Method method2 = MyClass.class.getDeclaredMethod("bateTarabana", new Class[0]);
            method2.invoke(new MyClass(), new Object[0]);
            System.out.println(System.currentTimeMillis());
            System.out.println(k);
        }
		//TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
		//what do you observe?
		
    }
}
