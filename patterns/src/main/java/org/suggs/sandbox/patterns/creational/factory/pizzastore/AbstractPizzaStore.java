/*
 * AbstractPizzaStore.java created on 22 Aug 2007 06:06:50 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizzastore;

import org.suggs.sandbox.patterns.creational.factory.IPizza;
import org.suggs.sandbox.patterns.creational.factory.IPizzaStore;
import org.suggs.sandbox.patterns.creational.factory.PizzaStoreException;

/**
 * Abstract class to encapsulate the pizza process itself. This allows the delegated concrete pizza stores to
 * create their own menus of pizza types in the createPizza method.
 */
public abstract class AbstractPizzaStore implements IPizzaStore {

    @Override
    public IPizza orderPizza( String aType ) throws PizzaStoreException {
        IPizza ret = createPizza( aType );

        ret.prepare();
        ret.bake();
        ret.cut();
        ret.box();

        return ret;
    }

    /**
     * Create the correct type of pizza based on the passed in type. This is the key to the factory pattern,
     * we have created an interface from which the implementing (concrete) stores can create their own pizza
     * types. All concrete types will essentially implement their own factory methods.
     */
    protected abstract IPizza createPizza( String aType ) throws PizzaStoreException;

}
