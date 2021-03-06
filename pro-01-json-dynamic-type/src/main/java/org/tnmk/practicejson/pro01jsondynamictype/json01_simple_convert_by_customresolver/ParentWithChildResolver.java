package org.tnmk.practicejson.pro01jsondynamictype.json01_simple_convert_by_customresolver;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.tnmk.practicejson.pro01jsondynamictype.json01_simple_convert_by_customresolver.ChildSchemasRegistry.childClassesMapByVersion;

@Slf4j
public class ParentWithChildResolver implements TypeIdResolver {
  // Should find a way to inject it.
  private final TypeFactory typeFactory = TypeFactory.defaultInstance();

  private JavaType parentType;
  private String versionFieldName = "childVersion";
  private String versionValue;

  @Override public void init(JavaType javaType) {
    log.info("init({})", javaType);
    parentType = javaType;
  }

  @Override public String idFromValue(Object o) {
    try {
      log.info("idFromValue({}): start", o);

      String id = (String) BeanUtils.getPropertyDescriptor(o.getClass(), versionFieldName).getReadMethod().invoke(o);
      log.info("idFromValue(...): end: {}", id);
      return id;
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override public String idFromValueAndType(Object o, Class<?> aClass) {
    throw new UnsupportedOperationException("idFromValueAndType(" + o + ", " + aClass + ")");

  }

  @Override public String idFromBaseType() {
    throw new UnsupportedOperationException("idFromBaseType");
  }

  @Override public JavaType typeFromId(DatabindContext databindContext, String id) throws IOException {
    log.info("typeFromId({}, {})", databindContext, id);
    this.versionValue = id;
    Class childClass = childClassesMapByVersion.get(versionValue);
    //    JavaType childType = typeFactory.constructType(childClass);
    return typeFactory.constructParametricType(parentType.getRawClass(), childClass);
  }

  @Override public String getDescForKnownTypeIds() {
    throw new UnsupportedOperationException("getDescForKnownTypeIds");
  }

  @Override public JsonTypeInfo.Id getMechanism() {
    return JsonTypeInfo.Id.CUSTOM;
  }
}
