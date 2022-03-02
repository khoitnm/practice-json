package org.tnmk.practicejson.pro01jsondynamictype.item.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.MINIMAL_CLASS
//    , include = JsonTypeInfo.As.PROPERTY
    , property = "type"
)
public interface Child {

}
