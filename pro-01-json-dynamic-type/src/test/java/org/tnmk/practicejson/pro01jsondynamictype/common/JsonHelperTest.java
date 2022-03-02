package org.tnmk.practicejson.pro01jsondynamictype.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.practicejson.pro01jsondynamictype.item.model.ChildA;
import org.tnmk.practicejson.pro01jsondynamictype.item.model.Parent;
import org.tnmk.practicejson.pro01jsondynamictype.testinfra.BaseIntegrationTest;

@Slf4j
public class JsonHelperTest extends BaseIntegrationTest {
  @Autowired
  private JsonHelper jsonHelper;

  @Test
  public void test() {
    ChildA child = new ChildA("childA" + System.nanoTime());
    Parent originalParent = new Parent(child);

    String jsonString = jsonHelper.toJson(originalParent);
    log.info("Json: \n{}", jsonString);

    Parent parsedParent = jsonHelper.toObject(jsonString, Parent.class);
    Assertions.assertEquals(originalParent, parsedParent);
  }
}
