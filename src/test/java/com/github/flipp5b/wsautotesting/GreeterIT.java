package com.github.flipp5b.wsautotesting;

import com.eviware.soapui.tools.SoapUITestCaseRunner;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Arquillian.class)
public class GreeterIT {
    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        return ShrinkWrap.createFromZipFile(WebArchive.class, new File("target/ws-autotesting.war"));
    }

    @Test
    @RunAsClient
    public void testGreeter() throws Exception {
        SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
        runner.setProjectFile("src/test/resources/Greeter-soapui-project.xml");
        runner.setJUnitReport(true);
        runner.setPrintReport(true);
        runner.setOutputFolder("target/failsafe-reports");
        runner.run();
    }
}