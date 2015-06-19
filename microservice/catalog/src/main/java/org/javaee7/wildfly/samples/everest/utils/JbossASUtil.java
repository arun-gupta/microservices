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

import org.javaee7.wildfly.samples.everest.exception.InitializationException;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * @author WalmartLabs
 * @author Ryan McGuinness [rmcguinness@walmartlabs.com]
 *         Created: 6/19/15
 */
public final class JbossASUtil {
    private static InitialContext CTX = null;

    private JbossASUtil() {
    }

    private static void init() throws InitializationException {
        if (JbossASUtil.CTX == null) {
            try {
                Hashtable props = new Hashtable();
                props.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
                props.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
                JbossASUtil.CTX = new InitialContext(props);
            } catch (NamingException ne) {
                throw new InitializationException(ne);
            }
        }
    }

    public static String getConnectorValue(String base, String attr) {
        init();
        String value;
        try {
            MBeanServerConnection serverConn = (MBeanServerConnection) CTX.lookup("jmx/rmi/RMIAdaptor");
            ObjectName obj = ObjectName.getInstance("jboss.as", "standard-sockets", base);
            value = (String) serverConn.getAttribute(obj, attr);
        } catch (Exception e) {
            throw new InitializationException(e);
        }
        return value;
    }

    public String getHttpHostName() throws InitializationException {
        return getConnectorValue("http", "boundAddress");
    }

    public String getHttpPort() throws InitializationException {
        return getConnectorValue("http", "port");
    }

    public String getHttpsHostName() throws InitializationException {
        return getConnectorValue("https", "boundAddress");
    }

    public String getHttpsPort() throws InitializationException {
        return getConnectorValue("https", "port");
    }
}