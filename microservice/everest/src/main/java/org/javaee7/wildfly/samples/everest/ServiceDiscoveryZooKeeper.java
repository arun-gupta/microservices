package org.javaee7.wildfly.samples.everest;

import javax.enterprise.inject.Vetoed;

/**
 * @author arungupta
 */
@Vetoed
public class ServiceDiscoveryZooKeeper implements ServiceDiscoveryURI {

    @Override
    public String getUserServiceURI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCatalogServiceURI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOrderServiceURI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    // Query Zookeeper for the URI of the serviceName
//    private URI findServiceURI(String serviceName) {
//        return URI.create(serviceName);
//    }
//    
//    public void startZookeeperDiscovery() {
//        DiscoveryServer server = new DiscoveryServer("catalog", URI.create(CATALOG_SERVICE));
//    }
//
//    public void stopZookeeperDiscovery() {
//
//    }
}
