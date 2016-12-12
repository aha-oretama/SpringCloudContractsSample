package aha.oremta.jp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author aha_oretama
 * @Date 2016/12/12
 */
@NoArgsConstructor
@Getter
@Setter
public class Product {
  private String id;
  private String name;
  private String url;

  public Product(String id) {
    this.id = id;
  }
}
