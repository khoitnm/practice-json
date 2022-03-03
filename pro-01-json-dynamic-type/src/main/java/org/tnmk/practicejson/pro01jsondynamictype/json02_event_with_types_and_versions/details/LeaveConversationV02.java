package org.tnmk.practicejson.pro01jsondynamictype.json02_event_with_types_and_versions.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveConversationV02 {
  private String authorFirstName;
  private String authorLastName;
}
