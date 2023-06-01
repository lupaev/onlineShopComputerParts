package com.example.onlineshopcomputerparts.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "monitor")
@Entity
public class Monitor extends Product{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int dioganal;

}
