package org.tnmk.practicejson.pro01jsondynamictype.json00_simple_convert_by_class;

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
    Json00ChildA child = new Json00ChildA("childA" + System.nanoTime());
    Json00Parent originalParent = new Json00Parent(child);

    String jsonString = jsonHelper.toJson(originalParent);
    log.info("Json: \n{}", jsonString);

    Json00Parent parsedJson00Parent = jsonHelper.toObject(jsonString, Json00Parent.class);
    Assertions.assertEquals(originalParent, parsedJson00Parent);
  }
}
