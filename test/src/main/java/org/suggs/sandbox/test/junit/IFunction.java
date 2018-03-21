package org.suggs.sandbox.test.junit;

/**
 * Interface to define an executable function following the Command pattern
 */
public interface IFunction<T> {

    public T execute() throws DaoException;
}
