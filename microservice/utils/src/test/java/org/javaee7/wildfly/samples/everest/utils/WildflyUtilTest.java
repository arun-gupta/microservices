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

import org.javaee7.wildfly.samples.everest.utils.qualifiers.QSecureServerPort;
import org.javaee7.wildfly.samples.everest.utils.qualifiers.QServerName;
import org.javaee7.wildfly.samples.everest.utils.qualifiers.QServerPort;
import org.javaee7.wildfly.samples.everest.test.ArquillianBase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Ryan McGuinness [rmcguinness@walmartlabs.com]
 *         Created: 6/21/15
 */
public class WildflyUtilTest extends ArquillianBase {
    private static final Logger LOG = LoggerFactory.getLogger(WildflyUtilTest.class);

    @Inject
    @QServerName
    private String serverName;

    @Inject
    @QServerPort
    private int port;

    @Inject
    @QSecureServerPort
    private int securePort;

    @Test
    public void testHostAndPort() {
        assertNotNull("Host is null", serverName);
        assertTrue("port is 0", port != 0);
        assertTrue("secure port is 0", securePort != 0);
        LOG.info("Server Information: {}:{}/{}", serverName, port, securePort);
    }
}
