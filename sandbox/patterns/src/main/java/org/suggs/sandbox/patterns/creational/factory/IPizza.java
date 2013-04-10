/*
 * IPizza.java created on 22 Aug 2007 06:07:43 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory;

/**
 * Interface to represent the Pizza itself, encapsulating the actions that can be performed on the pizza
 */
public interface IPizza {

    void prepare();

    void bake();

    void cut();

    void box();

}
