package org.tnmk.practicejson.pro01jsondynamictype.json00_simple_convert_by_class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // for Json conversion.
@AllArgsConstructor
public class Json00Parent {
  private Json00Child child;
}
