package org.javaee7.wildfly.samples.services.zookeeper;

import java.util.List;
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

    private String host;
    private int port;
    
    @Inject
    public ZooKeeper() {
        host = "192.168.99.103";
        port = 2181;
    }

    @Override
    public void registerService(String name, String uri) {
        try {
            CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(host + ":" + port, new RetryNTimes(5, 1000));
            curatorFramework.start();
            String znode = "/services/" + name;
            
            if (curatorFramework.checkExists().forPath(znode) == null) {
                curatorFramework.create().creatingParentsIfNeeded().forPath(znode);
            }
            
            curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(znode+"/_", uri.getBytes());
            
            // store the returned path in a map, this can be used to delete the node during unregister
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public void unregisterService(String name, String uri) {
//        try {
//            CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(zkHost + ":" + zkPort, new RetryNTimes(5, 1000));
//            curatorFramework.start();
//            String znode = "/services/" + name;
//            
//            if (curatorFramework.checkExists().forPath(znode) == null) {
//                curatorFramework.create().creatingParentsIfNeeded().forPath(znode);
//            }
//            
//            curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(znode, uri.getBytes());
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
    }

    @Override
    public String discoverService(String name) {
        try {
            CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(host + ":" + port, new RetryNTimes(5, 1000));
            curatorFramework.start();
            String znode = "/services/" + name;

            List<String> uris = curatorFramework.getChildren().forPath(znode);
            String child =  uris.get(0);                        
            return new String(curatorFramework.getData().forPath(ZKPaths.makePath(znode, child)));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
