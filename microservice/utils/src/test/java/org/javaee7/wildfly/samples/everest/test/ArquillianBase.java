package org.javaee7.wildfly.samples.everest.test;

import org.javaee7.wildfly.samples.everest.exception.InitializationException;
import org.javaee7.wildfly.samples.everest.utils.WildflyUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

/**
 * @author Ryan McGuinness [rmcguinness@walmartlabs.com]
 *         Created: 6/19/15
 */
@RunWith(Arquillian.class)
public abstract class ArquillianBase {
    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "utils.jar")
            .addPackages(true, "org.javaee7.wildfly.samples.everest")
            .addAsResource("logging.properties")
            .addAsResource("log4j.properties")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");

        System.out.println(jar.toString(true));
        return jar;
    }
}