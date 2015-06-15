package org.javaee7.wildfly.samples.everest.uzer;

import eu.agilejava.snoop.annotation.EnableSnoopClient;
import javax.ws.rs.core.Application;

/**
 * @author arungupta
 */
@EnableSnoopClient
@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {
}
