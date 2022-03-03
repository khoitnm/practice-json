package org.tnmk.practicejson.pro01jsondynamictype.simple_convert_by_class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tnmk.practicejson.pro01jsondynamictype.simple_convert_by_customresolver.Child;

@Data
@NoArgsConstructor // for Json conversion.
@AllArgsConstructor
public class ChildA implements Child {
  private String fullName;
}