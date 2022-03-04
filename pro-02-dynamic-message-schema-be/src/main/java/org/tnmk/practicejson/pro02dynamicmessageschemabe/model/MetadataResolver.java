package org.tnmk.practicejson.pro02dynamicmessageschemabe.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class MetadataResolver implements TypeIdResolver {
  private static final String DETAIL_TYPE_AND_VERSION_DELIMITER = ",";

  private String fieldMessageType;
  private String fieldDetailType;
  private String fieldVersion;
  private JavaType objectType;

  @Override public void init(JavaType javaType) {
    JsonTypeInfo annotation = javaType.getRawClass().getAnnotation(JsonTypeInfo.class);
    String typeIdentifierProperty = annotation.property();
    if (!StringUtils.hasText(typeIdentifierProperty)) {
      throw new UnsupportedOperationException("Please define JsonTypeInfo.property for " + javaType);
    }
    typeIdentifierProperty = typeIdentifierProperty.substring(1); // remove prefix '@'
    String[] typeIdentifierFields = typeIdentifierProperty.split(DETAIL_TYPE_AND_VERSION_DELIMITER);
    fieldMessageType = typeIdentifierFields[0];
    fieldDetailType = typeIdentifierFields[1];
    fieldVersion = typeIdentifierFields[2];
  }

  /**
   * @param o the object that have {@link JsonTypeInfo} annotation.
   * @return the string (id) that would be use to identify the target's class.
   */
  @Override public String idFromValue(Object o) {
    if (o == null) {
      return null;
    }
    return idFromValueAndType(o, o.getClass());
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

      MessageType messageType = getFieldValue(o, fieldMessageType);
      DetailType detailType = getFieldValue(o, fieldDetailType);
      String versionNumber = getFieldValue(o, fieldVersion);

      String schemaIdentifier = serializeSchemaId(messageType, detailType, versionNumber);
      log.trace("idFromValue(...): end: {}", schemaIdentifier);
      return schemaIdentifier;
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override public String idFromBaseType() {
    throw new UnsupportedOperationException("idFromBaseType");
  }

  @Override public JavaType typeFromId(DatabindContext databindContext, String id) {
    log.trace("typeFromId({}, {})", databindContext, id);
    SchemaId schemaId = deserializeSchemaId(id);
    MetadataSchemaRegister detailSchema = MetadataSchemaRegister.findSchema(schemaId.messageType, schemaId.detailType, schemaId.versionNumber);
    return databindContext.getTypeFactory().constructType(detailSchema.getMetadataSchema());
  }

  @Override public String getDescForKnownTypeIds() {
    throw new UnsupportedOperationException("getDescForKnownTypeIds");
  }

  @Override public JsonTypeInfo.Id getMechanism() {
    return JsonTypeInfo.Id.CUSTOM;
  }

  private String serializeSchemaId(MessageType messageType, DetailType detailType, String versionNumber) {
    String detailTypeStr = detailType != null ? detailType.toString() : "";
    return String.join(DETAIL_TYPE_AND_VERSION_DELIMITER, String.valueOf(messageType), detailTypeStr, versionNumber);
  }

  private SchemaId deserializeSchemaId(String schemaId) {
    String[] parsedId = schemaId.split(DETAIL_TYPE_AND_VERSION_DELIMITER);
    String detailTypeStr = parsedId[1];
    DetailType detailType = StringUtils.hasText(detailTypeStr) ? DetailType.valueOf(detailTypeStr) : null;
    return new SchemaId(MessageType.valueOf(parsedId[0]), detailType, parsedId[2]);
  }

  @RequiredArgsConstructor
  private static class SchemaId {
    private final MessageType messageType;
    private final DetailType detailType;
    private final String versionNumber;
  }

  private <T> T getFieldValue(Object targetObject, String fieldName) throws InvocationTargetException, IllegalAccessException {
    T fieldValue = (T) BeanUtils.getPropertyDescriptor(targetObject.getClass(), fieldName).getReadMethod().invoke(targetObject);
    return fieldValue;
  }
}
