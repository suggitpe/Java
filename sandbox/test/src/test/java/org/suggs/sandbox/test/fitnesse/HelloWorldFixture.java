package org.suggs.sandbox.test.fitnesse;

import fitnesse.junit.FitNesseSuite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: suggitpe
 * Date: 2/21/12
 * Time: 6:56 PM
 */
@RunWith(FitNesseSuite.class)
@FitNesseSuite.Name("FitNesse.SuiteAcceptanceTests.HelloWorldSuite")
@FitNesseSuite.FitnesseDir("/mnt/data/fitnesse")
@FitNesseSuite.DebugMode(true)
@FitNesseSuite.OutputDir(systemProperty = "java.io.tmpdir", pathExtension = "fitnesse")
public class HelloWorldFixture {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldFixture.class);

    @Test
    public void dummy() {
        LOG.debug("Testing");
    }

    private String string1;
    private String string2;

    public String Concatenate(){
        return string1 + " " + string2;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }
}
