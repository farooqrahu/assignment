package com.bezkoder.springjwt.models;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "productorders")
public class ProductOrderInvoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long invoiceNo;
  @Nullable
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  private Customer customer;

  @OneToMany(mappedBy = "productOrderInvoice")
  private List<ProductSale> productSales;
}
