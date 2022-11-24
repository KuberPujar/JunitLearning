package com.junit;

import java.io.Serial;
import java.io.Serializable;

public class MathUtils implements Serializable,Cloneable {

    private static MathUtils instance;
    
    private MathUtils()
    {

    }

    // implement readResolve method
    @Serial
    protected Object readResolve()
    {
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return instance;
    }

    public static MathUtils getInstance()
    {
        if(instance==null)
        {
            instance= new MathUtils();
        }
        return instance;
    }
    public int add(int a,int b)
    {
        return a+b;
    }

    public double computeCircleArea(double radius)
    {
        return Math.PI * Math.pow(radius,2);
    }

    public int subtract(int a,int b)
    {
        return a-b;
    }

    public double divide(double a,double b)
    {
        return a/b;
    }

    public int multiply(int a,int b)
    {
        return a * b;
    }
}
