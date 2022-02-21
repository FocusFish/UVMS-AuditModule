/*
﻿Developed with the contribution of the European Commission - Directorate General for Maritime Affairs and Fisheries
© European Union, 2015-2016.

This file is part of the Integrated Fisheries Data Management (IFDM) Suite. The IFDM Suite is free software: you can
redistribute it and/or modify it under the terms of the GNU General Public License as published by the
Free Software Foundation, either version 3 of the License, or any later version. The IFDM Suite is distributed in
the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a
copy of the GNU General Public License along with the IFDM Suite. If not, see <http://www.gnu.org/licenses/>.
 */
package fish.focus.uvms.audit.rest.service;

import fish.focus.schema.audit.search.v1.AuditLogListQuery;
import fish.focus.schema.audit.source.v1.GetAuditLogListByQueryResponse;
import fish.focus.uvms.commons.date.JsonBConfigurator;
import fish.focus.uvms.rest.security.RequiresFeature;
import fish.focus.uvms.rest.security.UnionVMSFeature;
import fish.focus.uvms.audit.service.bean.AuditServiceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/audit")
@Stateless
public class AuditRestResource {

    final static Logger LOG = LoggerFactory.getLogger(AuditRestResource.class);

    @Inject
    private AuditServiceBean serviceLayer;

    private Jsonb jsonb;

    @PostConstruct
    public void init() {
        jsonb = new JsonBConfigurator().getContext(null);
    }

    /**
     * @responseMessage 200 Audit list successfully retrieved
     * @responseMessage 500 Error when retrieving the list values
     * @summary Gets a list of audit entries filtered by a query
     */
    @POST
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Path("/list")
    @RequiresFeature(UnionVMSFeature.viewAudit)
    public Response getListByQuery(AuditLogListQuery query) {
        LOG.info("Get list invoked in rest layer:{}", query);
        try {
            GetAuditLogListByQueryResponse response = serviceLayer.getList(query);
            String returnString = jsonb.toJson(response);
            return Response.ok(returnString).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception ex) {
            LOG.error("[ Error when getting list. {}] {}", query, ex);
            throw ex;
        }
    }
}
