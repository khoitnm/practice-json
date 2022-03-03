package org.tnmk.practicejson.pro01jsondynamictype.simple_convert_by_customresolver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChildSchemasRegistry {
  public static final String V01 = "1.0.0";
  public static final String V02 = "2.0.0";
  public static final Map<String, Class> childClassesMapByVersion = Collections.unmodifiableMap(construct());

  private static Map<String, Class> construct() {
    Map<String, Class> result = new HashMap<>();
    result.put(V01, ChildV01.class);
    result.put(V02, ChildV02.class);
    return result;
  }

}
