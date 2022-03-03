package org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class VersionedMetadataResolver implements TypeIdResolver {
  private static final String DETAIL_TYPE_AND_VERSION_DELIMITER = "_&_";
  private static final String FLD_NAME_VERSION = "versionNumber";
  private static final String FLD_NAME_DETAIL_TYPE = "detailType";

  @Override public void init(JavaType javaType) {
  }

  /**
   * @param o the object that have {@link JsonTypeInfo} annotation.
   * @return the string (id) that would be use to identify the target's class.
   */
  @Override public String idFromValue(Object o) {
    return idFromValueAndType(o, null);
  }

  /**
   * @param o the object that have {@link JsonTypeInfo} annotation.
   * @return the string (id) that would be use to identify the target's class.
   *  In this specific case, it's the combination of detailType and versionNumber.
   */
  @Override public String idFromValueAndType(Object o, Class<?> aClass) {
    try {
      log.trace("idFromValue({}): start", o);
      if (o == null) {
        return null;
      }

      String versionNumber = (String) BeanUtils.getPropertyDescriptor(o.getClass(), FLD_NAME_VERSION).getReadMethod().invoke(o);
      DetailType detailType = (DetailType) BeanUtils.getPropertyDescriptor(o.getClass(), FLD_NAME_DETAIL_TYPE).getReadMethod().invoke(o);
      String id = serializeId(detailType, versionNumber);
      log.trace("idFromValue(...): end: {}", id);
      return id;
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override public String idFromBaseType() {
    throw new UnsupportedOperationException("idFromBaseType");
  }

  @Override public JavaType typeFromId(DatabindContext databindContext, String id) {
    log.trace("typeFromId({}, {})", databindContext, id);
    TypeId typeId = deserializeId(id);
    DetailSchemaRegister detailSchema = DetailSchemaRegister.findSchema(typeId.versionNumber, typeId.detailType);
    return databindContext.getTypeFactory().constructParametricType(VersionedMetadata.class, detailSchema.getDetailsClass());
  }

  @Override public String getDescForKnownTypeIds() {
    throw new UnsupportedOperationException("getDescForKnownTypeIds");
  }

  @Override public JsonTypeInfo.Id getMechanism() {
    return JsonTypeInfo.Id.CUSTOM;
  }

  private String serializeId(DetailType detailType, String versionNumber) {
    return detailType + DETAIL_TYPE_AND_VERSION_DELIMITER + versionNumber;
  }

  private TypeId deserializeId(String id) {
    String[] parsedId = id.split(DETAIL_TYPE_AND_VERSION_DELIMITER);
    return new TypeId(DetailType.valueOf(parsedId[0]), parsedId[1]);
  }

  @RequiredArgsConstructor
  private static class TypeId {
    private final DetailType detailType;
    private final String versionNumber;
  }
}
