package org.javaee7.wildfly.samples.zookeeper;

import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;

/**
 * @author arungupta
 */
public class ZooKeeper {

    private static ZooKeeper INSTANCE;
    private static String host;
    private static int port;

    public static final ZooKeeper getInstance(String host, int port) {
        if (null == INSTANCE) {
            INSTANCE = new ZooKeeper();
            ZooKeeper.host = host;
            ZooKeeper.port = port;
        }
        return INSTANCE;
    }

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

    public String discoverService(String name) {
        try {
            CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(host + ":" + port, new RetryNTimes(5, 1000));
            curatorFramework.start();
            String znode = "/services/" + name;
            
            // Level 1: simplest thing to do
            List<String> uris = curatorFramework.getChildren().forPath(znode);
            String child =  uris.get(0);                        
            return new String(curatorFramework.getData().forPath(ZKPaths.makePath(znode, child)));
            
//        // Level 2: register watches to check the status of the service
//        uris = curatorFramework.getChildren().usingWatcher(new Watcher() {
//            @Override
//            public void process(WatchedEvent we) {
//                if (we.getType() == Event.EventType.NodeDeleted) {}
//            }
//        }).forPath(znode);
//        
//        // Level 3: Done during initialization
//        PathChildrenCache pcache = new PathChildrenCache(curatorFramework, znode, true);
//        pcache.start();
//
//        List<ChildData> childdata = pcache.getCurrentData();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
