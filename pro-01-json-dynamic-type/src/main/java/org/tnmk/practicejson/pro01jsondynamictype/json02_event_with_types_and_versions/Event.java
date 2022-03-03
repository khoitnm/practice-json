package org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
  private List<VersionedMetadata<?>> listOfVersionedMetadata;
}
