package org.tnmk.practicejson.pro01jsondynamictype.json00_simple_convert_by_class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tnmk.practicejson.pro01jsondynamictype.json01_simple_convert_by_customresolver.Child;

@Data
@NoArgsConstructor // for Json conversion.
@AllArgsConstructor
public class Parent {
  private Child child;
}
