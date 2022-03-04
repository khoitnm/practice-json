package org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions;

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
   * Its actually type will be decided by {@link DetailSchemaRegister}.
   */
  private DetailType detailType;
  private D detailData;
}
