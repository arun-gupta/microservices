/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.javaee7.wildfly.samples.everest.utils;

import org.javaee7.wildfly.samples.everest.utils.exception.InitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author Ryan McGuinness [rmcguinness@walmartlabs.com]
 *         Created: 6/19/15
 */
@Startup
@Singleton
public class WildFlyUtil {
    private static final Logger log = LoggerFactory.getLogger(WildFlyUtil.class);
    private static String hostName = "localhost";
    private static int hostPort = 8080;
    private static int hostSecurePort = 8443;

    @PostConstruct
    void init() throws InitializationException {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            ObjectName http = new ObjectName("jboss.as:socket-binding-group=standard-sockets,socket-binding=http");
            hostName = (String) mBeanServer.getAttribute(http,"boundAddress");
            hostPort = (Integer) mBeanServer.getAttribute(http,"boundPort");

            ObjectName ws = new ObjectName("jboss.ws", "service", "ServerConfig");
//            hostName = (String) mBeanServer.getAttribute(ws, "WebServiceHost");
//            hostPort = (int) mBeanServer.getAttribute(ws, "WebServicePort");
            hostSecurePort = (int) mBeanServer.getAttribute(ws, "WebServiceSecurePort");
            log.info("--> " + hostName + " : " + hostPort + "/" + hostSecurePort);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitializationException(e);
        }

    }

    public static String getHostName() {
        return hostName;
    }

    public static int getHostPort() {
        return hostPort;
    }

    public static int getSecurePort() {
        return hostSecurePort;
    }
}