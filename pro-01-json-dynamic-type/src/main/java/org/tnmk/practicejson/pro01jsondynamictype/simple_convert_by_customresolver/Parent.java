package org.tnmk.practicejson.pro01jsondynamictype.simple_convert_by_customresolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // for Json conversion.
@AllArgsConstructor
public class Parent {
  private String childVersion;
  private Child child;
}
