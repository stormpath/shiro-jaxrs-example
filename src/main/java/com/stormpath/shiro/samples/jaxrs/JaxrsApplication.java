/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.stormpath.shiro.samples.jaxrs;

import com.stormpath.shiro.samples.jaxrs.dao.DefaultStormtrooperDao;
import com.stormpath.shiro.samples.jaxrs.resources.StormtroooperResource;
import org.apache.shiro.web.jaxrs.ShiroFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JAX-RS {@link Application} that is implementation agnostic.
 * @since 1.4
 */
@ApplicationPath("/")
public class JaxrsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        // register Shiro
        classes.add(ShiroFeature.class);

        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();

        // This example does NOT use DI, so we will just create an instance to use as a singleton.
        singletons.add(new StormtroooperResource(new DefaultStormtrooperDao()));

        return singletons;
    }
}
