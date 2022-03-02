package org.tnmk.practicejson.pro01jsondynamictype.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonHelper {
  private final ObjectMapper objectMapper;

  public String toJson(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }
  }

  public <T> T toObject(String jsonString, Class<T> resultClass) {
    try {
      return objectMapper.readValue(jsonString, resultClass);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Cannot parse json. Rootcause:" + e.getMessage() + ". Json: \n" + jsonString, e);
    }
  }
}
