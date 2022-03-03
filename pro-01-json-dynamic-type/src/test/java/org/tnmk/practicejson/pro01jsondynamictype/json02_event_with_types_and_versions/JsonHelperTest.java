package org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.practicejson.pro01jsondynamictype.common.JsonHelper;
import org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions.details.JoinConversationV01;
import org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions.details.LeaveConversationV01;
import org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions.details.LeaveConversationV02;
import org.tnmk.practicejson.pro01jsondynamictype.testinfra.BaseIntegrationTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class JsonHelperTest extends BaseIntegrationTest {
  @Autowired
  private JsonHelper jsonHelper;

  @Test
  public void test() {
    Event event = new Event(randomVersionedMetadataList());

    String jsonString = jsonHelper.toJson(event);
    log.info("Json: \n{}", jsonString);

    Event parsedObject = jsonHelper.toObject(jsonString, Event.class);
    Assertions.assertEquals(event, parsedObject);
  }

  private List<VersionedMetadata<?>> randomVersionedMetadataList() {
    return Arrays.asList(null, randomDetail_JoinConversation(), randomDetail_LeaveConversation01(), randomDetail_LeaveConversation02());
  }

  private VersionedMetadata<?> randomDetail_JoinConversation() {
    VersionedMetadata<JoinConversationV01> versionedMetadata = new VersionedMetadata<>();
    versionedMetadata.setVersionNumber(DetailSchemaRegister.JOIN_CONVERSATION_V01.getVersion());
    versionedMetadata.setDetailType(DetailSchemaRegister.JOIN_CONVERSATION_V01.getType());
    versionedMetadata.setDetailData(new JoinConversationV01("author_" + System.nanoTime()));
    return versionedMetadata;
  }

  private VersionedMetadata<?> randomDetail_LeaveConversation01() {
    VersionedMetadata<LeaveConversationV01> versionedMetadata = new VersionedMetadata();
    versionedMetadata.setVersionNumber(DetailSchemaRegister.LEAVE_CONVERSATION_V01.getVersion());
    versionedMetadata.setDetailType(DetailSchemaRegister.LEAVE_CONVERSATION_V01.getType());
    versionedMetadata.setDetailData(new LeaveConversationV01("author_" + System.nanoTime()));
    return versionedMetadata;
  }

  private VersionedMetadata<?> randomDetail_LeaveConversation02() {
    VersionedMetadata<LeaveConversationV02> versionedMetadata = new VersionedMetadata();
    versionedMetadata.setVersionNumber(DetailSchemaRegister.LEAVE_CONVERSATION_V02.getVersion());
    versionedMetadata.setDetailType(DetailSchemaRegister.LEAVE_CONVERSATION_V02.getType());
    versionedMetadata.setDetailData(new LeaveConversationV02("firstName" + System.nanoTime(), "lastName" + System.nanoTime()));
    return versionedMetadata;
  }
}
