package com.doordash.merchant.campaign.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.doordash.merchant.campaign.model.constants.DatabaseNames;
import com.doordash.merchant.campaign.model.enums.PhoneNumberType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DatabaseNames.PHONE_NUMBER_DB)
public class PhoneNumber implements Serializable {

  private static final long serialVersionUID = 115987772448053798L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = DatabaseNames.PHONE_NUMBER, nullable = false)
  private String phoneNumber;

  @Column(name = DatabaseNames.PHONE_TYPE, nullable = false)
  @Enumerated(EnumType.STRING)
  private PhoneNumberType phoneType;

  @Column(name = DatabaseNames.OCCURRENCES, nullable = false)
  private Integer occurrences;
}
