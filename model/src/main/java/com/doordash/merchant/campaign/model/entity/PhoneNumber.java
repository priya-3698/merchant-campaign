package com.doordash.merchant.campaign.model.entity;

import javax.persistence.*;

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
public class PhoneNumber {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column(name = DatabaseNames.PHONE_NUMBER, nullable = false, unique = true)
  private String phoneNumber;

  @Column(name = DatabaseNames.PHONE_TYPE, nullable = false)
  @Enumerated(EnumType.STRING)
  private PhoneNumberType phoneType;

  @Column(name = DatabaseNames.OCCURRENCES, nullable = false)
  private int occurrences;
}
