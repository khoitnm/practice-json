package org.tnmk.practicejson.pro01jsondynamictype.json01_simple_convert_by_customresolver;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CUSTOM
    , include = JsonTypeInfo.As.EXISTING_PROPERTY
    , property = "childVersion"
    , visible = true // this will help deserialize process to set value into "childVersion" field. Otherwise, that field will be null.
)
@JsonTypeIdResolver(ParentWithChildResolver.class)
@Data
@NoArgsConstructor // for Json conversion.
@AllArgsConstructor
public class Parent<C> {
  private String childVersion;
  private C child;
}
