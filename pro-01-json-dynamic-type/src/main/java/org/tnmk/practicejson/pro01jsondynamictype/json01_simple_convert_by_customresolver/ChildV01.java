package org.tnmk.practicejson.pro01jsondynamictype.json01_simple_convert_by_customresolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // for Json conversion.
@AllArgsConstructor
public class ChildV01 implements Child {
  private String fullName;
}
