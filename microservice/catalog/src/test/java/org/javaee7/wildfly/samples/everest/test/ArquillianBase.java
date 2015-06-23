package org.javaee7.wildfly.samples.everest.test;

import org.javaee7.wildfly.samples.everest.exception.InitializationException;
import org.javaee7.wildfly.samples.everest.utils.WildflyUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

/**
 * @author Ryan McGuinness [rmcguinness@walmartlabs.com]
 *         Created: 6/19/15
 */
@RunWith(Arquillian.class)
public abstract class ArquillianBase {
    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "catalog.war")
            .addPackages(true, "org.javaee7.wildfly.samples.everest.test")
            .addClass(WildflyUtil.class)
            .addClass(InitializationException.class);

        war.merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                .importDirectory("src/main/webapp").as(GenericArchive.class),
            "/", Filters.includeAll());

        System.out.println(war.toString(true));
        return war;
    }
}