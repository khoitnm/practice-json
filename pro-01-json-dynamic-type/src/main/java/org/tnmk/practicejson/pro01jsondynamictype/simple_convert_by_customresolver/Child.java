package org.tnmk.practicejson.pro01jsondynamictype.simple_convert_by_customresolver;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.MINIMAL_CLASS
    , property = "@class"
)
@JsonTypeIdResolver(ChildResolver.class)
public interface Child {

}
