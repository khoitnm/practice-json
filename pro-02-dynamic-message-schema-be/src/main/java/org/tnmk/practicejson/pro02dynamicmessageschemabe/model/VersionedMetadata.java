package org.tnmk.practicejson.pro02dynamicmessageschemabe.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.Data;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CUSTOM
    , property = "@detailTypeAndVersion"
)
@JsonTypeIdResolver(VersionedMetadataResolver.class)
@Data
public class VersionedMetadata<D> {
  private String versionNumber;
  /**
   * Its value comes from {@link DetailSchemaRegister}.
   */
  private DetailType detailType;
  private D detailData;
}
