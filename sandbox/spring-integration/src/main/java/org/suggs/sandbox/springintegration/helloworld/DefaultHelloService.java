package org.suggs.sandbox.springintegration.helloworld;

public class DefaultHelloService implements HelloService {

    @Override
    public String sayHello(String aName) {
        return "Hello " + aName;
    }
}
