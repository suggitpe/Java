/*
 * AbstractBeverage.java created on 28 Aug 2007 17:27:07 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator;

/**
 * Abstract base class for a beverage
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2007
 */
public abstract class AbstractBeverage implements IBeverage
{

    protected String mDescription_;

    /**
     * @see org.suggs.sandbox.patterns.structural.decorator.IBeverage#getDescription()
     */
    public String getDescription()
    {
        return mDescription_;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Beverage: description=[" + getDescription() + "], cost=[" + cost() + "]";
    }
}
