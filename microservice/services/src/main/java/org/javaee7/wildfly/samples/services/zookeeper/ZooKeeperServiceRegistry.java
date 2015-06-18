package org.javaee7.wildfly.samples.services.zookeeper;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;
import org.javaee7.wildfly.samples.services.ZooKeeperServices;

/**
 * @author arungupta
 */
@ZooKeeperServices
@ApplicationScoped
public class ZooKeeperServiceRegistry implements ServiceRegistry {

    private final CuratorFramework curatorFramework;
    private final ConcurrentHashMap<String, String> uriToZnodePath;
    
    @Inject
    public ZooKeeperServiceRegistry() {
        try {
            Properties props = new Properties();
            props.load(this.getClass().getResourceAsStream("/zookeeper.properties"));
            
            curatorFramework = CuratorFrameworkFactory
                    .newClient(props.getProperty("host") 
                            + ":" 
                            + props.getProperty("port"), new RetryNTimes(5, 1000));
            curatorFramework.start();
            uriToZnodePath = new ConcurrentHashMap<>();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getLocalizedMessage());
        }
    }

    @Override
    public void registerService(String name, String uri) {
        try {
            String znode = "/services/" + name;

            if (curatorFramework.checkExists().forPath(znode) == null) {
                curatorFramework.create().creatingParentsIfNeeded().forPath(znode);
            }

            String znodePath = curatorFramework
                    .create()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(znode+"/_", uri.getBytes());

            uriToZnodePath.put(uri, znodePath);
        } catch (Exception ex) {
            throw new RuntimeException("Could not register service \"" 
                    + name 
                    + "\", with URI \"" + uri + "\": " + ex.getLocalizedMessage());
        }
    }
    
    @Override
    public void unregisterService(String name, String uri) {
        try {
            if (uriToZnodePath.contains(uri)) {
                curatorFramework.delete().forPath(uriToZnodePath.get(uri));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Could not unregister service \"" 
                    + name 
                    + "\", with URI \"" + uri + "\": " + ex.getLocalizedMessage());
        }
    }

    @Override
    public String discoverServiceURI(String name) {
        try {
            String znode = "/services/" + name;

            List<String> uris = curatorFramework.getChildren().forPath(znode);
            return new String(curatorFramework.getData().forPath(ZKPaths.makePath(znode, uris.get(0))));
        } catch (Exception ex) {
            throw new RuntimeException("Service \"" + name + "\" not found: " + ex.getLocalizedMessage());
        }
    }
}
