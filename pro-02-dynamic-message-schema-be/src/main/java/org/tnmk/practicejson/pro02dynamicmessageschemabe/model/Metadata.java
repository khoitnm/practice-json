package org.tnmk.practicejson.pro02dynamicmessageschemabe.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CUSTOM
    , property = "@messageType,detailType,versionNumber"
)
@JsonTypeIdResolver(MetadataResolver.class)
public interface Metadata<D> {
  MessageType getMessageType();

  String getVersionNumber();

  DetailType getDetailType();

  D getDetailData();
}
