package com.doordash.merchant.campaign.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doordash.merchant.campaign.service.api.MapperService;

@Service
public class MapperServiceImpl implements MapperService {

  @Autowired
  private Mapper mapper;

  @Override
  public <E, T> List<T> mapEntityToList(List<E> entity, Class<T> tClass) {
    List<T> result = new ArrayList<>();
    for (E e : entity) {
      result.add(this.mapper.map(e, tClass));
    }
    return result;
  }
}
