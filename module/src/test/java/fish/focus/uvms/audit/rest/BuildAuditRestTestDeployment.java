package fish.focus.uvms.audit.rest;

import fish.focus.uvms.commons.date.JsonBConfigurator;
import fish.focus.uvms.rest.security.UnionVMSFeature;
import fish.focus.uvms.usm.jwt.JwtTokenHandler;
import fish.focus.uvms.audit.service.BuildAuditServiceTestDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.File;
import java.util.Arrays;

public abstract class BuildAuditRestTestDeployment extends BuildAuditServiceTestDeployment {

    final static Logger LOG = LoggerFactory.getLogger(BuildAuditRestTestDeployment.class);

    @EJB
    private JwtTokenHandler tokenHandler;

    private String token;

    protected WebTarget getWebTarget() {

        Client client = ClientBuilder.newClient();
        client.register(JsonBConfigurator.class);
        //return client.target("http://localhost:28080/test/rest");
        return client.target("http://localhost:8080/audit/rest");
    }

    protected String getToken() {
        if (token == null) {
            token = tokenHandler.createToken("user",
                    Arrays.asList(UnionVMSFeature.viewAudit.getFeatureId()));
        }
        return token;
    }
}
