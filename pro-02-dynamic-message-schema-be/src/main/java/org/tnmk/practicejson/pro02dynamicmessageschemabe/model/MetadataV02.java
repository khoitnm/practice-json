package org.tnmk.practicejson.pro02dynamicmessageschemabe.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

@Data
public class MetadataV02<D> implements Metadata<D> {
  private MessageType messageType;
  private String versionNumber;
  @Nullable private DetailType detailType;
  @Nullable private D detailData;

  private String messageSid;
}
