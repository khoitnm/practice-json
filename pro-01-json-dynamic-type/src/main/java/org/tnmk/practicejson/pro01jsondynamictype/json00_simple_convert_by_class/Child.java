package org.tnmk.practicejson.pro01jsondynamictype.json00_simple_convert_by_class;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.MINIMAL_CLASS
//    , include = JsonTypeInfo.As.PROPERTY
    , property = "@class"
)
public interface Child {

}
