package org.tnmk.practicejson.pro01jsondynamictype.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // for Json conversion.
@AllArgsConstructor
public class ChildA {
  private String fullName;
}
