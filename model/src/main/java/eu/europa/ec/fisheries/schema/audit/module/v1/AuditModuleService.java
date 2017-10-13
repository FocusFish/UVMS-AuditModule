package eu.europa.ec.fisheries.schema.audit.module.v1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.13
 * 2017-09-18T16:51:39.594+02:00
 * Generated source version: 3.1.13
 * 
 */
@WebServiceClient(name = "AuditModuleService", 
                  wsdlLocation = "file:/C:/git/UVMS-AuditModule-APP/model/src/main/resources/contract/AuditModuleService.wsdl",
                  targetNamespace = "urn:module.audit.schema.fisheries.ec.europa.eu:v1") 
public class AuditModuleService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("urn:module.audit.schema.fisheries.ec.europa.eu:v1", "AuditModuleService");
    public final static QName AuditModulePortType = new QName("urn:module.audit.schema.fisheries.ec.europa.eu:v1", "AuditModulePortType");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/git/UVMS-AuditModule-APP/model/src/main/resources/contract/AuditModuleService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(AuditModuleService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/git/UVMS-AuditModule-APP/model/src/main/resources/contract/AuditModuleService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public AuditModuleService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AuditModuleService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AuditModuleService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public AuditModuleService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public AuditModuleService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public AuditModuleService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns AuditModulePortType
     */
    @WebEndpoint(name = "AuditModulePortType")
    public AuditModulePortType getAuditModulePortType() {
        return super.getPort(AuditModulePortType, AuditModulePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AuditModulePortType
     */
    @WebEndpoint(name = "AuditModulePortType")
    public AuditModulePortType getAuditModulePortType(WebServiceFeature... features) {
        return super.getPort(AuditModulePortType, AuditModulePortType.class, features);
    }

}