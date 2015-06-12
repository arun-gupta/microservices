package org.javaee7.wildfly.samples.services.zookeeper;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.javaee7.wildfly.samples.services.ServiceRegistry;
import org.javaee7.wildfly.samples.services.ZooKeeperRegistry;

/**
 * @author arungupta
 */
@ZooKeeperRegistry
@ApplicationScoped
public class ZooKeeper implements ServiceRegistry {

    private final CuratorFramework curatorFramework;
    private final ConcurrentHashMap<String, String> uriToZnodePath;
    
    @Inject
    public ZooKeeper() {
        String host = "192.168.99.103";
        int port = 2181;
        curatorFramework = CuratorFrameworkFactory
                .newClient(host + ":" + port, new RetryNTimes(5, 1000));
        curatorFramework.start();
        uriToZnodePath = new ConcurrentHashMap<>();
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
                System.out.println("\n\nFound path: " + uriToZnodePath.get(uri) + "\n\n");
                curatorFramework.delete().forPath(uriToZnodePath.get(uri));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Could not unregister service \"" 
                    + name 
                    + "\", with URI \"" + uri + "\": " + ex.getLocalizedMessage());
        }
    }

    @Override
    public String discoverService(String name) {
        try {
            String znode = "/services/" + name;

            List<String> uris = curatorFramework.getChildren().forPath(znode);
            return new String(curatorFramework.getData().forPath(ZKPaths.makePath(znode, uris.get(0))));
        } catch (Exception ex) {
            throw new RuntimeException("Service \"" + name + "\" not found");
        }
    }
}
