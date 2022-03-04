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

  private List<MetadataV01<?>> randomVersionedMetadataList() {
    return Arrays.asList(
        null,
        random_UserMessage(),
        randomDetail_JoinConversation(),
        randomDetail_LeaveConversation01(),
        randomDetail_LeaveConversation02()
    );
  }

  private MetadataV01<?> random_UserMessage() {
    MetadataV01<JoinConversationV01> metadataV01 = new MetadataV01<>();
    metadataV01.setMessageType(MessageType.USER);
    metadataV01.setVersionNumber(MetadataSchemaRegister.USER_MESSAGE_V01.getVersion());
    return metadataV01;
  }

  private MetadataV01<?> randomDetail_JoinConversation() {
    MetadataV01<JoinConversationV01> metadataV01 = new MetadataV01<>();
    metadataV01.setMessageType(MessageType.SYSTEM);
    metadataV01.setVersionNumber(MetadataSchemaRegister.JOIN_CONVERSATION_V01.getVersion());
    metadataV01.setDetailType(MetadataSchemaRegister.JOIN_CONVERSATION_V01.getDetailType());
    metadataV01.setDetailData(new JoinConversationV01("author_" + System.nanoTime()));
    return metadataV01;
  }

  private MetadataV01<?> randomDetail_LeaveConversation01() {
    MetadataV01<LeaveConversationV01> metadataV01 = new MetadataV01();
    metadataV01.setMessageType(MessageType.SYSTEM);
    metadataV01.setVersionNumber(MetadataSchemaRegister.LEAVE_CONVERSATION_V01.getVersion());
    metadataV01.setDetailType(MetadataSchemaRegister.LEAVE_CONVERSATION_V01.getDetailType());
    metadataV01.setDetailData(new LeaveConversationV01("author_" + System.nanoTime()));
    return metadataV01;
  }

  private MetadataV01<?> randomDetail_LeaveConversation02() {
    MetadataV01<LeaveConversationV02> metadataV01 = new MetadataV01();
    metadataV01.setMessageType(MessageType.SYSTEM);
    metadataV01.setVersionNumber(MetadataSchemaRegister.LEAVE_CONVERSATION_V02.getVersion());
    metadataV01.setDetailType(MetadataSchemaRegister.LEAVE_CONVERSATION_V02.getDetailType());
    metadataV01.setDetailData(new LeaveConversationV02("firstName" + System.nanoTime(), "lastName" + System.nanoTime()));
    return metadataV01;
  }
}
