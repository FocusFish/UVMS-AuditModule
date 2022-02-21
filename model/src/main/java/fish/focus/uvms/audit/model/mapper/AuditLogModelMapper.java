package fish.focus.uvms.audit.model.mapper;

import fish.focus.schema.audit.source.v1.AuditDataSourceMethod;
import fish.focus.schema.audit.source.v1.CreateAuditLogRequest;
import fish.focus.schema.audit.v1.AuditLogType;

public class AuditLogModelMapper {

    public static String mapToAuditLog(String objectType, String operation, String affectedObject, String username) {
        return mapToAuditLog(objectType, operation, affectedObject, null, username);
    }

    public static String mapToAuditLog(String objectType, String operation, String affectedObject, String comment, String username) {
        CreateAuditLogRequest createAuditLogRequest = new CreateAuditLogRequest();

        AuditLogType auditLogType = new AuditLogType();
        auditLogType.setAffectedObject(affectedObject);
        auditLogType.setObjectType(objectType);
        auditLogType.setOperation(operation);
        auditLogType.setUsername(username);
        auditLogType.setComment(comment);

        createAuditLogRequest.setAuditLog(auditLogType);
        createAuditLogRequest.setMethod(AuditDataSourceMethod.CREATE);

        String auditData = JAXBMarshaller.marshallJaxBObjectToString(createAuditLogRequest);
        return auditData;
    }
}
