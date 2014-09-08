package org.apache.solr.rest.jersey;

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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.SolrCore;
import org.apache.solr.servlet.SolrDispatchFilter;

/**
 * FIXME - mdodsworth: doc me!
 */
@Path("{coreName}/schema")
public class SchemaResource {

  private final String coreName;

  public SchemaResource(@PathParam("coreName") String coreName) {
    this.coreName = coreName;
  }

  @GET
  @Path("version")
  public float getVersion() {
    return getCore().getLatestSchema().getVersion();
  }

  @GET
  @Path("name")
  public String getName() {
    return getCore().getLatestSchema().getSchemaName();
  }

  @GET
  @Path("uniquekey")
  public String getUniqueKeyField() {
    return getCore().getLatestSchema().getUniqueKeyField().getName();
  }

  //======== helper methods ========//
  
  private SolrCore getCore() {
    CoreContainer coreContainer = SolrDispatchFilter.CORE_CONTAINER;
    return coreContainer.getCore(coreName);
  }
}