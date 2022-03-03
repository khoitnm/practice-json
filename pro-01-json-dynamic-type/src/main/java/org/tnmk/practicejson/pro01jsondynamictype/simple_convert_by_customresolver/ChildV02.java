package org.tnmk.practicejson.pro01jsondynamictype.simple_convert_by_customresolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tnmk.practicejson.pro01jsondynamictype.simple_convert_by_customresolver.Child;

@Data
@NoArgsConstructor // for Json conversion.
@AllArgsConstructor
public class ChildV02 implements Child {
  private String firstName;
  private String lastName;
}
