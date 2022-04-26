package com.bezkoder.springjwt.models;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "productsales")
public class PeoductsSale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Nullable
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  private Product product;
  private Long quantity;
  private String detail;
  @Nullable
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  private Customer customer;
}
