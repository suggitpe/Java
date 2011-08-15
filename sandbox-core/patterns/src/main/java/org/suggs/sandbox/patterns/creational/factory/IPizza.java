/*
 * IPizza.java created on 22 Aug 2007 06:07:43 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory;

/**
 * Interface to represent the Pizza itself, encapsulating the actions that can be performed on the pizza
 * 
 * @author suggitpe
 * @version 1.0 22 Aug 2007
 */
public interface IPizza {

    /**
     * Prepare the pizza
     */
    void prepare();

    /**
     * Bake the pizza
     */
    void bake();

    /**
     * Cut the pizza
     */
    void cut();

    /**
     * Put the pizza in a box
     */
    void box();

}
