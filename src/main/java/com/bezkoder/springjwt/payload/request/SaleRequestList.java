package com.bezkoder.springjwt.payload.request;

import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.models.Company;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class SaleRequest implements Serializable {
  private Long id;
  private Long productId;
  private String name;
  private Long quantity;

}
