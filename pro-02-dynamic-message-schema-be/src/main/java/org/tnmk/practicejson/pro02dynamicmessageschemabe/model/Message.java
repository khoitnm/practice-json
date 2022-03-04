package org.tnmk.practicejson.pro02dynamicmessageschemabe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
  private List<MetadataV01<?>> listOfVersionedMetadata;
}
