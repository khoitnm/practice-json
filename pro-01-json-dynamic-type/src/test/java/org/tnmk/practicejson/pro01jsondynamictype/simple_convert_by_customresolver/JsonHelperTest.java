package org.tnmk.practicejson.pro01jsondynamictype.simple_convert_by_customresolver;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.practicejson.pro01jsondynamictype.common.JsonHelper;
import org.tnmk.practicejson.pro01jsondynamictype.testinfra.BaseIntegrationTest;

@Slf4j
public class JsonHelperTest extends BaseIntegrationTest {
  @Autowired
  private JsonHelper jsonHelper;

  @Test
  public void test() {
    ChildV01 child = new ChildV01("child01" + System.nanoTime());
    Parent originalParent = new Parent(ChildSchemasRegistry.V01, child);

    String jsonString = jsonHelper.toJson(originalParent);
    log.info("Json: \n{}", jsonString);

    Parent parsedParent = jsonHelper.toObject(jsonString, Parent.class);
    Assertions.assertEquals(originalParent, parsedParent);
  }
}
