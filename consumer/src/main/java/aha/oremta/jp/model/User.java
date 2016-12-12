package aha.oremta.jp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author aha_oretama
 * @Date 2016/12/12
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
  private String id;
  private String name;
  private String email;

  private List<Product> products;
}
