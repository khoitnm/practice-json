package org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions;

import lombok.Getter;
import org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions.details.JoinConversationV01;
import org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions.details.LeaveConversationV01;
import org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions.details.LeaveConversationV02;

@Getter
public enum DetailSchemaRegister {
  JOIN_CONVERSATION_V01(DetailType.JOIN_CONVERSATION, "0.0.1", JoinConversationV01.class),
  LEAVE_CONVERSATION_V01(DetailType.LEAVE_CONVERSATION, "0.0.1", LeaveConversationV01.class),
  LEAVE_CONVERSATION_V02(DetailType.LEAVE_CONVERSATION, "0.0.2", LeaveConversationV02.class);

  private final DetailType type;
  private final String version;
  private final Class<?> detailsClass;

  DetailSchemaRegister(DetailType type, String version, Class<?> detailsClass) {
    this.type = type;
    this.version = version;
    this.detailsClass = detailsClass;
  }

  public static DetailSchemaRegister findSchema(String versionNumber, DetailType detailType) {
    DetailSchemaRegister[] schemas = values();
    for (DetailSchemaRegister schema : schemas) {
      if (schema.version.equals(versionNumber) && schema.type.equals(detailType)) {
        return schema;
      }
    }
    throw new UnsupportedOperationException("Don't have any DetailSchema with version " + versionNumber + " and detailType " + detailType);
  }
}
