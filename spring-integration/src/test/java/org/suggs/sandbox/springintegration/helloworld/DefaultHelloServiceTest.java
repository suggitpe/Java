package org.suggs.sandbox.springintegration.helloworld;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DefaultHelloServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultHelloServiceTest.class);

    @Test
    public void foo() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/helloworld-context.xml");
        HelloService helloService = context.getBean("helloGateway", HelloService.class);
        LOG.info(helloService.sayHello("World"));
    }
}
