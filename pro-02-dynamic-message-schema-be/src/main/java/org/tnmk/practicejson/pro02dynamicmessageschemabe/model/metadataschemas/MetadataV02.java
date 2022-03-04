package org.tnmk.practicejson.pro02dynamicmessageschemabe.model.metadataschemas;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.model.DetailType;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.model.MessageType;
import org.tnmk.practicejson.pro02dynamicmessageschemabe.model.Metadata;

@Data
public class MetadataV02<D> implements Metadata<D> {
  private MessageType messageType;
  private String versionNumber;
  @Nullable private DetailType detailType;
  @Nullable private D detailData;

  private String messageSid;
}
