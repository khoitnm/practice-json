package org.tnmk.practicejson.pro02dynamicmessageschemabe.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.Data;
import org.springframework.lang.Nullable;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CUSTOM
    , property = "@messageType,detailType,versionNumber"
)
@JsonTypeIdResolver(MetadataResolver.class)
@Data
public class MetadataV01<D> {
  private MessageType messageType;
  private String versionNumber;
  @Nullable private DetailType detailType;
  @Nullable private D detailData;
}
