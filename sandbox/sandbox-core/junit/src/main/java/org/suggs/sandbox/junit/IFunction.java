package org.suggs.sandbox.junit;

/**
 * Interface to define an executable function following the Command
 * pattern
 * 
 * @author suggitpe
 * @version 1.0 19 Nov 2009
 * @param <T>
 *            type information
 */
public interface IFunction<T>
{

    /**
     * Command pattern execution
     * 
     * @return type T for class
     * @throws Exception
     */
    public T execute() throws Exception;
}
