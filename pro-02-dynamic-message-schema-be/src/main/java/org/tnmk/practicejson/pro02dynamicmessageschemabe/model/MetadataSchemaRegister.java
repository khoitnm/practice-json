package org.tnmk.practicejson.pro02dynamicmessageschemabe.model;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.model.metadataschemas.detailschemas.JoinConversationV01;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.model.metadataschemas.detailschemas.LeaveConversationV01;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.model.metadataschemas.detailschemas.LeaveConversationV02;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.model.metadataschemas.MetadataV01;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.model.metadataschemas.MetadataV02;

@Getter
public enum MetadataSchemaRegister {
  USER_MESSAGE_V01(MessageType.USER, null, "0.0.1", new TypeReference<MetadataV01<Void>>() {
  }),
  USER_MESSAGE_V02(MessageType.USER, null, "0.0.2", new TypeReference<MetadataV02<Void>>() {
  }),
  JOIN_CONVERSATION_V01(MessageType.SYSTEM, DetailType.JOIN_CONVERSATION, "0.0.1", new TypeReference<MetadataV01<JoinConversationV01>>() {
  }),
  LEAVE_CONVERSATION_V01(MessageType.SYSTEM, DetailType.LEAVE_CONVERSATION, "0.0.1", new TypeReference<MetadataV01<LeaveConversationV01>>() {
  }),
  LEAVE_CONVERSATION_V02(MessageType.SYSTEM, DetailType.LEAVE_CONVERSATION, "0.0.2", new TypeReference<MetadataV01<LeaveConversationV02>>() {
  }),
  LEAVE_CONVERSATION_V03(MessageType.SYSTEM, DetailType.LEAVE_CONVERSATION, "0.0.3", new TypeReference<MetadataV02<LeaveConversationV02>>() {
  });

  private final MessageType messageType;
  private final DetailType detailType;
  private final String version;
  private final TypeReference<?> metadataSchema;

  MetadataSchemaRegister(MessageType messageType, DetailType detailType, String version, TypeReference<?> metadataSchema) {
    this.messageType = messageType;
    this.detailType = detailType;
    this.version = version;

    this.metadataSchema = metadataSchema;
  }

  public static MetadataSchemaRegister findSchema(MessageType messageType, DetailType detailType, String versionNumber) {
    MetadataSchemaRegister[] schemas = values();
    for (MetadataSchemaRegister schema : schemas) {
      if (isEqualsIfNull(schema.messageType, messageType)
          && isEqualsIfNull(schema.detailType, detailType)
          && isEqualsIfNull(schema.version, versionNumber)) {
        return schema;
      }
    }
    throw new UnsupportedOperationException("Don't have any metadataSchema with"
        + " messageType " + messageType
        + " detailType " + detailType
        + " version " + versionNumber
    );
  }

  private static boolean isEqualsIfNull(Object a, Object b) {
    if (a == null) {
      return (b == null);
    } else {
      return a.equals(b);
    }
  }
}
