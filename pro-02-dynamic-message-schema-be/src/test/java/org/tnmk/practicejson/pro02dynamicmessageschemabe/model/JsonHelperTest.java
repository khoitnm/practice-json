package org.tnmk.practicejson.pro02dynamicmessageschemabe.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.common.JsonHelper;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.model.details.JoinConversationV01;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.model.details.LeaveConversationV01;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.model.details.LeaveConversationV02;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.testinfra.BaseIntegrationTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class JsonHelperTest extends BaseIntegrationTest {
  @Autowired
  private JsonHelper jsonHelper;

  @Test
  public void test() {
    Message message = new Message(randomVersionedMetadataList());

    String jsonString = jsonHelper.toJson(message);
    log.info("Json: \n{}", jsonString);

    Message parsedObject = jsonHelper.toObject(jsonString, Message.class);
    Assertions.assertEquals(message, parsedObject);
  }

  private List<Metadata<?>> randomVersionedMetadataList() {
    return Arrays.asList(
        null,
        random_UserMessageV01(),
        random_UserMessageV02(),
        randomDetail_JoinConversation(),
        randomDetail_LeaveConversation01(),
        randomDetail_LeaveConversation02(),
        randomDetail_LeaveConversation03()
    );
  }

  private Metadata<?> random_UserMessageV01() {
    MetadataV01<Void> metadata = new MetadataV01<>();
    metadata.setMessageType(MessageType.USER);
    metadata.setVersionNumber(MetadataSchemaRegister.USER_MESSAGE_V01.getVersion());
    return metadata;
  }

  private Metadata<?> random_UserMessageV02() {
    MetadataV02<Void> metadata = new MetadataV02<>();
    metadata.setMessageType(MessageType.USER);
    metadata.setVersionNumber(MetadataSchemaRegister.USER_MESSAGE_V02.getVersion());
    metadata.setMessageSid("Sid"+System.nanoTime());
    return metadata;
  }

  private Metadata<?> randomDetail_JoinConversation() {
    MetadataV01<JoinConversationV01> metadataV01 = new MetadataV01<>();
    metadataV01.setMessageType(MessageType.SYSTEM);
    metadataV01.setVersionNumber(MetadataSchemaRegister.JOIN_CONVERSATION_V01.getVersion());
    metadataV01.setDetailType(MetadataSchemaRegister.JOIN_CONVERSATION_V01.getDetailType());
    metadataV01.setDetailData(new JoinConversationV01("author_" + System.nanoTime()));
    return metadataV01;
  }

  private Metadata<?> randomDetail_LeaveConversation01() {
    MetadataV01<LeaveConversationV01> metadataV01 = new MetadataV01();
    metadataV01.setMessageType(MessageType.SYSTEM);
    metadataV01.setVersionNumber(MetadataSchemaRegister.LEAVE_CONVERSATION_V01.getVersion());
    metadataV01.setDetailType(MetadataSchemaRegister.LEAVE_CONVERSATION_V01.getDetailType());
    metadataV01.setDetailData(new LeaveConversationV01("author_" + System.nanoTime()));
    return metadataV01;
  }

  private Metadata<?> randomDetail_LeaveConversation02() {
    MetadataV01<LeaveConversationV02> metadata = new MetadataV01();
    metadata.setMessageType(MessageType.SYSTEM);
    metadata.setVersionNumber(MetadataSchemaRegister.LEAVE_CONVERSATION_V02.getVersion());
    metadata.setDetailType(MetadataSchemaRegister.LEAVE_CONVERSATION_V02.getDetailType());
    metadata.setDetailData(new LeaveConversationV02("firstName" + System.nanoTime(), "lastName" + System.nanoTime()));
    return metadata;
  }

  private Metadata<?> randomDetail_LeaveConversation03() {
    MetadataV02<LeaveConversationV02> metadata = new MetadataV02();
    metadata.setMessageType(MessageType.SYSTEM);
    metadata.setVersionNumber(MetadataSchemaRegister.LEAVE_CONVERSATION_V03.getVersion());
    metadata.setDetailType(MetadataSchemaRegister.LEAVE_CONVERSATION_V03.getDetailType());
    metadata.setDetailData(new LeaveConversationV02("firstName" + System.nanoTime(), "lastName" + System.nanoTime()));
    metadata.setMessageSid("Sid"+System.nanoTime());
    return metadata;
  }
}
