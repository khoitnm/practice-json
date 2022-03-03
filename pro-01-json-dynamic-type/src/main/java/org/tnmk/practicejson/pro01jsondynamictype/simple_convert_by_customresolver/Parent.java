package org.tnmk.practicejson.pro01jsondynamictype.simple_convert_by_customresolver;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CUSTOM
    , include = JsonTypeInfo.As.EXISTING_PROPERTY
    , property = "childVersion"
    , visible = false
)
@JsonTypeIdResolver(ParentWithChildResolver.class)
@Data
@NoArgsConstructor // for Json conversion.
@AllArgsConstructor
public class Parent<C> {
  private String childVersion;
  private C child;
}
